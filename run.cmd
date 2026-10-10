@echo off
setlocal
cd /d "%~dp0"

if exist "C:\Program Files\Java\jdk-21\bin\java.exe" (
    set "JAVA_HOME=C:\Program Files\Java\jdk-21"
) else if not defined JAVA_HOME (
    if exist "C:\Program Files\Java\jdk-17\bin\java.exe" (
        set "JAVA_HOME=C:\Program Files\Java\jdk-17"
    ) else (
        echo [ERROR] JDK 21 was not found and JAVA_HOME is not set to a valid JDK.
        echo Set JAVA_HOME to your JDK folder, for example:
        echo   set "JAVA_HOME=C:\Program Files\Java\jdk-21"
        echo Then run run.cmd again.
        exit /b 1
    )
)

if not exist "%JAVA_HOME%\bin\java.exe" (
    echo [ERROR] JAVA_HOME does not point to a JDK: "%JAVA_HOME%"
    echo Set it to the JDK folder, not its bin folder.
    exit /b 1
)

where mvn.cmd >nul 2>nul
if errorlevel 1 (
    echo [ERROR] Maven was not found in PATH.
    echo Install Apache Maven and add its bin folder to PATH, then open a new CMD window.
    exit /b 1
)

echo Starting Cafe Management System with JAVA_HOME="%JAVA_HOME%"
mvn.cmd clean compile exec:java
exit /b %ERRORLEVEL%
