@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  laboratorMpp startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and LABORATOR_MPP_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\laboratorMpp-1.0-SNAPSHOT.jar;%APP_HOME%\lib\spring-context-4.3.8.RELEASE.jar;%APP_HOME%\lib\spring-jms-4.3.8.RELEASE.jar;%APP_HOME%\lib\activemq-spring-5.14.5.jar;%APP_HOME%\lib\jackson-databind-2.8.8.1.jar;%APP_HOME%\lib\mysql-connector-java-6.0.5.jar;%APP_HOME%\lib\sqlite-jdbc-3.16.1.jar;%APP_HOME%\lib\spring-aop-4.3.8.RELEASE.jar;%APP_HOME%\lib\spring-beans-4.3.8.RELEASE.jar;%APP_HOME%\lib\spring-core-4.3.8.RELEASE.jar;%APP_HOME%\lib\spring-expression-4.3.8.RELEASE.jar;%APP_HOME%\lib\spring-messaging-4.3.8.RELEASE.jar;%APP_HOME%\lib\spring-tx-4.3.8.RELEASE.jar;%APP_HOME%\lib\slf4j-api-1.7.13.jar;%APP_HOME%\lib\xbean-spring-4.2.jar;%APP_HOME%\lib\activemq-broker-5.14.5.jar;%APP_HOME%\lib\activemq-pool-5.14.5.jar;%APP_HOME%\lib\geronimo-jta_1.0.1B_spec-1.0.1.jar;%APP_HOME%\lib\commons-pool2-2.4.2.jar;%APP_HOME%\lib\jackson-annotations-2.8.0.jar;%APP_HOME%\lib\jackson-core-2.8.8.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\activemq-client-5.14.5.jar;%APP_HOME%\lib\activemq-openwire-legacy-5.14.5.jar;%APP_HOME%\lib\activemq-jms-pool-5.14.5.jar;%APP_HOME%\lib\geronimo-jms_1.1_spec-1.1.1.jar;%APP_HOME%\lib\hawtbuf-1.11.jar;%APP_HOME%\lib\geronimo-j2ee-management_1.1_spec-1.0.1.jar

@rem Execute laboratorMpp
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %LABORATOR_MPP_OPTS%  -classpath "%CLASSPATH%" Main.MainMVC %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable LABORATOR_MPP_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%LABORATOR_MPP_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
