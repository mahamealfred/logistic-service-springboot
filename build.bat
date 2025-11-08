@echo off
echo Building Logistics Application...

:: Clean and build the application using Maven Wrapper
echo Step 1: Compiling and packaging...
call mvnw.cmd clean package -DskipTests

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Build successful!
    echo JAR file created: target\logistics-app-1.0.0.jar
    echo File size:
    for %%I in ("target\logistics-app-1.0.0.jar") do echo   %%~zI bytes
    echo.
) else (
    echo.
    echo Build failed! Please check the errors above.
    pause
    exit /b 1
)

pause