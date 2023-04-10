
javac -cp lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar:. cse12pa1student/*.java

java -cp lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore cse12pa1student.BasketTest 2>&1 | grep -v "\tat"
