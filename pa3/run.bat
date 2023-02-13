@echo off

if "%1"=="" goto help

del *.class
javac -cp lib/tester.jar *.java
java -Djava.security.manager=disallow -classpath lib/tester.jar;"%cd%" tester.Main %1
goto :eof

:help
echo "Please provide the class name. For instance: .\run.bat DesignRecipeExamples"
