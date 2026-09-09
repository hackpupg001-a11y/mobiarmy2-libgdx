param(
    [string]$AppImage = "dist\portable\DataViewer",
    [string]$Output = "dist\DataViewer-OneFile.exe"
)

$ErrorActionPreference = 'Stop'
$root = (Resolve-Path (Join-Path $PSScriptRoot '..')).Path
Set-Location $root
$appImagePath = (Resolve-Path $AppImage).Path
$appExe = Join-Path $appImagePath 'DataViewer.exe'
if (-not (Test-Path $appExe)) {
    throw "DataViewer.exe missing from app-image: $appImagePath"
}

$work = Join-Path $root 'build\onefile'
if (Test-Path $work) { Remove-Item -Recurse -Force $work }
New-Item -ItemType Directory -Force -Path $work | Out-Null

# Compress the entire jpackage app-image. The C# bootstrapper below embeds this ZIP
# as a managed resource and expands it into a hash-versioned LOCALAPPDATA cache.
$payload = Join-Path $work 'payload.zip'
Compress-Archive -Path $appImagePath -DestinationPath $payload -CompressionLevel Optimal
$payloadHash = (Get-FileHash $payload -Algorithm SHA256).Hash.ToLowerInvariant()
$cacheKey = $payloadHash.Substring(0, 16)

$outPath = Join-Path $root $Output
$outDir = Split-Path $outPath -Parent
New-Item -ItemType Directory -Force -Path $outDir | Out-Null
if (Test-Path $outPath) { Remove-Item -Force $outPath }

# Build a tiny .NET Framework bootstrap EXE instead of relying on legacy self-extractor tooling.
# Windows 10/11
# already includes .NET Framework 4.x, while the actual game/runtime remains the
# self-contained jpackage image embedded in payload.zip.
$source = Join-Path $work 'OneFileBootstrap.cs'
$sourceText = @"
using System;
using System.Diagnostics;
using System.IO;
using System.IO.Compression;
using System.Reflection;
using System.Text;
using System.Threading;

internal static class OneFileBootstrap
{
    private const string CacheKey = "$cacheKey";
    private const string ResourceName = "Payload.Zip";

    [STAThread]
    private static int Main(string[] args)
    {
        string local = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);
        string cacheRoot = Path.Combine(local, "WorkspaceCache", CacheKey);
        string appDir = Path.Combine(cacheRoot, "DataViewer");
        string appExe = Path.Combine(appDir, "DataViewer.exe");

        using (var mutex = new Mutex(false, @"Local\WorkspaceDataOneFile_" + CacheKey))
        {
            bool owns = false;
            try
            {
                try { owns = mutex.WaitOne(TimeSpan.FromMinutes(3)); }
                catch (AbandonedMutexException) { owns = true; }
                if (!owns) throw new TimeoutException("Timed out waiting for portable cache initialization.");

                if (!File.Exists(appExe))
                    ExtractPayload(cacheRoot);
            }
            finally
            {
                if (owns) mutex.ReleaseMutex();
            }
        }

        if (!File.Exists(appExe))
            throw new FileNotFoundException("Embedded application did not extract correctly.", appExe);

        var psi = new ProcessStartInfo
        {
            FileName = appExe,
            WorkingDirectory = appDir,
            UseShellExecute = false,
            Arguments = JoinArguments(args)
        };
        using (Process p = Process.Start(psi))
        {
            if (p == null) return 3;
            p.WaitForExit();
            return p.ExitCode;
        }
    }

    private static void ExtractPayload(string cacheRoot)
    {
        string parent = Path.GetDirectoryName(cacheRoot);
        if (String.IsNullOrEmpty(parent)) throw new InvalidOperationException("Invalid cache path.");
        Directory.CreateDirectory(parent);

        string staging = cacheRoot + ".staging-" + Guid.NewGuid().ToString("N");
        string zipPath = Path.Combine(Path.GetTempPath(), "WorkspaceData-" + Guid.NewGuid().ToString("N") + ".zip");
        try
        {
            if (Directory.Exists(staging)) Directory.Delete(staging, true);
            Directory.CreateDirectory(staging);

            using (Stream input = Assembly.GetExecutingAssembly().GetManifestResourceStream(ResourceName))
            {
                if (input == null) throw new InvalidOperationException("Embedded payload resource is missing.");
                using (FileStream output = File.Create(zipPath)) input.CopyTo(output);
            }

            ZipFile.ExtractToDirectory(zipPath, staging);

            string stagedExe = Path.Combine(staging, "DataViewer", "DataViewer.exe");
            if (!File.Exists(stagedExe)) throw new InvalidDataException("Portable payload is missing DataViewer.exe.");

            if (Directory.Exists(cacheRoot)) Directory.Delete(cacheRoot, true);
            Directory.Move(staging, cacheRoot);
        }
        finally
        {
            if (File.Exists(zipPath)) File.Delete(zipPath);
            if (Directory.Exists(staging)) Directory.Delete(staging, true);
        }
    }

    private static string JoinArguments(string[] args)
    {
        if (args == null || args.Length == 0) return String.Empty;
        var sb = new StringBuilder();
        for (int i = 0; i < args.Length; i++)
        {
            if (i > 0) sb.Append(' ');
            sb.Append(QuoteArgument(args[i] ?? String.Empty));
        }
        return sb.ToString();
    }

    private static string QuoteArgument(string value)
    {
        if (value.Length > 0 && value.IndexOfAny(new[] { ' ', '\t', '"' }) < 0) return value;
        var sb = new StringBuilder();
        sb.Append('"');
        int slashes = 0;
        foreach (char c in value)
        {
            if (c == '\\') { slashes++; continue; }
            if (c == '"')
            {
                sb.Append('\\', slashes * 2 + 1);
                sb.Append('"');
                slashes = 0;
                continue;
            }
            if (slashes > 0) { sb.Append('\\', slashes); slashes = 0; }
            sb.Append(c);
        }
        if (slashes > 0) sb.Append('\\', slashes * 2);
        sb.Append('"');
        return sb.ToString();
    }
}
"@
[System.IO.File]::WriteAllText($source, $sourceText, [System.Text.UTF8Encoding]::new($false))

$candidates = @(
    (Join-Path $env:WINDIR 'Microsoft.NET\Framework64\v4.0.30319\csc.exe'),
    (Join-Path $env:WINDIR 'Microsoft.NET\Framework\v4.0.30319\csc.exe')
)
$csc = $candidates | Where-Object { Test-Path $_ } | Select-Object -First 1
if (-not $csc) { throw 'C# compiler csc.exe was not found; .NET Framework 4.x is required to build the one-file bootstrapper.' }

$compileArgs = @(
    '/nologo',
    '/target:winexe',
    '/optimize+',
    '/platform:anycpu',
    "/out:$outPath",
    '/reference:System.dll',
    '/reference:System.Core.dll',
    '/reference:System.IO.Compression.dll',
    '/reference:System.IO.Compression.FileSystem.dll',
    "/resource:$payload,Payload.Zip",
    $source
)
& $csc @compileArgs
if ($LASTEXITCODE -ne 0) { throw "C# one-file bootstrap compile failed with exit code $LASTEXITCODE" }
if (-not (Test-Path $outPath)) { throw "One-file EXE was not created: $outPath" }

$bytes = [System.IO.File]::ReadAllBytes($outPath)
if ($bytes.Length -lt 2 -or $bytes[0] -ne 0x4D -or $bytes[1] -ne 0x5A) {
    throw 'One-file output is not a Windows PE executable'
}
if ($bytes.Length -le (Get-Item $payload).Length) {
    throw 'One-file EXE does not appear to contain the embedded portable payload'
}
Write-Host "[PASS] One-file portable EXE: $outPath ($($bytes.Length) bytes)"
Write-Host "[PASS] Embedded payload SHA256: $payloadHash"
Write-Host "[PASS] Runtime cache: %LOCALAPPDATA%\WorkspaceCache\$cacheKey"
