@echo off
setlocal
cd /d "%~dp0"
echo ================================================
echo   Data Viewer - Windows EXE Builder
echo ================================================
echo.
where java >nul 2>&1 || (echo [ERROR] Java/JDK 21 not found in PATH.& pause & exit /b 1)
java -version
python tools\verify_source.py || (echo [ERROR] Source verification failed.& pause & exit /b 1)
call gradlew.bat --no-daemon clean :core:jar :desktop:dist --stacktrace || (echo [ERROR] Gradle build failed.& pause & exit /b 1)
if not exist "desktop\build\libs\WorkspaceData.jar" (echo [ERROR] Fat JAR missing.& pause & exit /b 1)
where jpackage >nul 2>&1 || (echo [ERROR] jpackage not found. Install/use JDK 21.& pause & exit /b 1)
if exist "dist\portable" rmdir /s /q "dist\portable"
mkdir "dist\portable"
jpackage --type app-image --name DataViewer --app-version 1.0.2 --vendor "Local Workspace" --description "Local Data Viewer" --input "desktop\build\libs" --main-jar "WorkspaceData.jar" --main-class "com.mygdx.game.DesktopLauncher" --dest "dist\portable" --java-options "-Dfile.encoding=UTF-8" || (echo [ERROR] jpackage app-image failed.& pause & exit /b 1)
copy /y "windows\OPEN_ENDPOINT.bat" "dist\portable\DataViewer\OPEN_ENDPOINT.bat" >nul
copy /y "windows\INFO.txt" "dist\portable\DataViewer\INFO.txt" >nul
powershell.exe -NoProfile -ExecutionPolicy Bypass -File "windows\build-onefile-portable.ps1" || (echo [ERROR] One-file portable build failed.& pause & exit /b 1)
echo.
echo [PASS] Portable folder EXE:
echo   %CD%\dist\portable\DataViewer\DataViewer.exe
echo [PASS] ONE-FILE portable EXE:
echo   %CD%\dist\DataViewer-OneFile.exe
echo.
echo Installer EXE is also built automatically by GitHub Actions on windows-2025.
pause
