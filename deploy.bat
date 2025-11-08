@echo off
echo ========================================
echo Logistics Application Deployment
echo ========================================
echo.

:: Step 1: Build the application
echo Step 1: Building application...
call build.bat

if %ERRORLEVEL% NEQ 0 (
    echo Build failed! Deployment aborted.
    pause
    exit /b 1
)

:: Step 2: Check if JAR file exists
echo.
echo Step 2: Verifying build output...
if not exist "target\logistics-app-1.0.0.jar" (
    echo ERROR: JAR file not found at: target\logistics-app-1.0.0.jar
    echo Please run build.bat first.
    pause
    exit /b 1
)

echo ✓ JAR file verified: target\logistics-app-1.0.0.jar

:: Step 3: Build and start Docker containers
echo.
echo Step 3: Starting Docker containers...
docker-compose down

echo Building Docker images...
docker-compose build --no-cache

echo Starting services...
docker-compose up -d

:: Step 4: Wait for services to start
echo.
echo Step 4: Waiting for services to start...
echo This may take 30-60 seconds...
timeout /t 45 /nobreak

:: Step 5: Health check
echo.
echo Step 5: Performing health check...
curl -f http://localhost:8080/api/v1/actuator/health

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo ✅ DEPLOYMENT SUCCESSFUL!
    echo ========================================
    echo.
    echo Application URLs:
    echo   Main API: http://localhost:8080/api/v1
    echo   Health:   http://localhost:8080/api/v1/actuator/health
    echo.
    echo Test Credentials:
    echo   Admin:  admin@logistics.com / admin123
    echo   Driver: driver@logistics.com / driver123
    echo.
    echo To view logs: docker-compose logs -f
    echo To stop:      docker-compose down
    echo ========================================
) else (
    echo.
    echo ⚠️  Health check failed! Services might still be starting...
    echo.
    echo Check service status: docker-compose ps
    echo View logs: docker-compose logs app
    echo.
    echo Retrying health check in 15 seconds...
    timeout /t 15 /nobreak
    curl -f http://localhost:8080/api/v1/actuator/health
    if %ERRORLEVEL% EQU 0 (
        echo ✅ Application is now healthy!
    ) else (
        echo ❌ Application failed to start. Check logs with: docker-compose logs app
    )
)

pause