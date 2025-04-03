@echo off
echo Building Java Virtual Machine...

REM Create necessary directories
mkdir build 2>nul
mkdir build\classes 2>nul
mkdir build\jar 2>nul

REM Compile all Java files
echo Compiling Java files...
javac -d build\classes src\main\java\vm\*.java src\main\java\vm\examples\*.java

REM Check if compilation was successful
if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

REM Create manifest file
echo Main-Class: vm.Main > manifest.txt

REM Create JAR file
echo Creating JAR file...
jar cvfm build\jar\VirtualMachine.jar manifest.txt -C build\classes .

REM Check if JAR creation was successful
if %ERRORLEVEL% NEQ 0 (
    echo JAR creation failed!
    pause
    exit /b 1
)

REM Run the JAR file
echo Running Virtual Machine...
java -jar build\jar\VirtualMachine.jar
echo Execution completed.

pause