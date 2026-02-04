@echo off
cd /d "%~dp0.."

if not exist build mkdir build

dir /s /b src\main\java\*.java > sources.txt

javac -d build @sources.txt
if errorlevel 1 exit /b 1

del sources.txt

java -cp build dev.vdokit.VdoKitApp
pause
