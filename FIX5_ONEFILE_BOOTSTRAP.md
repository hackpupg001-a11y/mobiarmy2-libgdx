# FIX5 — One-file portable bootstrapper

GitHub Windows Server 2025 successfully compiled the LibGDX client/fat JAR, but the one-file step failed inside legacy `IExpress`.

FIX5 removes IExpress entirely.

The one-file artifact is now built by compiling a tiny C#/.NET Framework bootstrap EXE that:

1. embeds the complete `jpackage` app-image ZIP as a managed resource;
2. extracts it on first run to a SHA-256-versioned cache under `%LOCALAPPDATA%\WorkspaceCache\...`;
3. starts `DataViewer.exe` and forwards command-line arguments;
4. reuses the cache on later launches;
5. uses a mutex to avoid two simultaneous first-run extractions.

This preserves the desired distribution format: the user carries only `DataViewer-OneFile.exe`; there is no installer wizard.

The build no longer depends on `IExpress` or 7-Zip SFX behavior.
