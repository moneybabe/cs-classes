@echo off

if "%1"=="" goto help

javac -cp tester.jar %1.java
java -classpath "tester.jar;%cd%" tester.Main %1
goto :eof

:help
echo "Please provide the class name. For instance: .\run.bat ExampleTweets"
