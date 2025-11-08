@echo off
echo Starting PostgreSQL Database with Docker...

:: Start only the database service
docker-compose up -d db

echo Waiting for database to start...
timeout /t 10 /nobreak

echo Checking database status...
docker-compose logs --tail=10 db

echo.
echo Database should be running on: localhost:5432
echo You can now run the application.
echo.
pause