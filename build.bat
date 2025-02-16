@echo off
set "root_dir=%CD%"

:: Define the directories
set "dirs=java/jimmer-core java/jimmer-simple java/jimmer-sql java/jimmer-sql-graphql java/jimmer-cloud java/save-command kotlin/jimmer-core-kt kotlin/jimmer-simple-kt kotlin/jimmer-sql-kt kotlin/jimmer-sql-graphql-kt kotlin/jimmer-cloud-kt kotlin/save-command-kt"

:: Loop through each directory
for %%d in (%dirs%) do (
    echo --------
    echo %%d
    echo --------
    cd "%root_dir%\%%d"
    
    echo %%d | findstr /i "^java/" >nul
    if not errorlevel 1 (
        echo maven...
        call mvnw clean
        call mvnw install
    )
    
    echo gradle ...
    call gradlew clean
    call gradlew build
    
    cd "%root_dir%"
)

