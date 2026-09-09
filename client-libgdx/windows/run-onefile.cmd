@echo off
setlocal EnableExtensions
set "CACHE=%LOCALAPPDATA%\WorkspaceCache\1.0.2"
set "APP=%CACHE%\DataViewer\DataViewer.exe"

if not exist "%APP%" (
  if exist "%CACHE%" rmdir /s /q "%CACHE%"
  mkdir "%CACHE%" >nul 2>&1
  powershell.exe -NoLogo -NoProfile -NonInteractive -ExecutionPolicy Bypass -Command ^
    "Expand-Archive -LiteralPath '%~dp0payload.zip' -DestinationPath '%CACHE%' -Force"
  if errorlevel 1 exit /b 2
)

if not exist "%APP%" exit /b 3
start "Data Viewer" /wait "%APP%" %*
exit /b %ERRORLEVEL%
