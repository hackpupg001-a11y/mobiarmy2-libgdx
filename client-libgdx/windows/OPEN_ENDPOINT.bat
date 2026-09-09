@echo off
setlocal
set /p ARMY2_HOST=Server IP [127.0.0.1]: 
if "%ARMY2_HOST%"=="" set "ARMY2_HOST=127.0.0.1"
set /p ARMY2_PORT=Game port [8122]: 
if "%ARMY2_PORT%"=="" set "ARMY2_PORT=8122"
set /p ARMY2_WEB_PORT=Web port [8080]: 
if "%ARMY2_WEB_PORT%"=="" set "ARMY2_WEB_PORT=8080"
start "Data Viewer" "%~dp0DataViewer.exe"
