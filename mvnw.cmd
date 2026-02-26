@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF)
@REM Maven Wrapper startup batch script for Windows
@REM ----------------------------------------------------------------------------

@echo off

set MAVEN_PROJECTBASEDIR=%~dp0

@REM Find java.exe
set JAVA_EXE=java.exe

@REM Check JAVA_HOME
if not "%JAVA_HOME%"=="" goto javaHomeSet
goto findJava

:javaHomeSet
set JAVA_EXE=%JAVA_HOME%\bin\java.exe
if exist "%JAVA_EXE%" goto execute
echo JAVA_HOME is set but java.exe was not found at %JAVA_HOME%\bin\java.exe
goto findJava

:findJava
where java >nul 2>nul
if %ERRORLEVEL% equ 0 goto execute
echo Java not found. Please install Java 8 or higher.
exit /b 1

:execute
set WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar

@REM Download Maven wrapper jar if it doesn't exist
if exist "%WRAPPER_JAR%" goto runWrapper

echo Downloading Maven Wrapper...
set WRAPPER_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar

@REM Try PowerShell download
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile '%WRAPPER_JAR%'}" >nul 2>nul
if exist "%WRAPPER_JAR%" goto runWrapper

@REM Try certutil download
certutil -urlcache -split -f "%WRAPPER_URL%" "%WRAPPER_JAR%" >nul 2>nul
if exist "%WRAPPER_JAR%" goto runWrapper

echo Failed to download Maven Wrapper. Please download manually:
echo %WRAPPER_URL%
echo Save to: %WRAPPER_JAR%
exit /b 1

:runWrapper
"%JAVA_EXE%" ^
  -jar "%WRAPPER_JAR%" ^
  %*
