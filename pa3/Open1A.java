class StatementAClass1 {
    int field1 = 0;
}

class StatementAClass2 {
    int field1 = 2;
}

class Open1A {
    StatementAClass1 instance1 = new StatementAClass1();
    StatementAClass2 instance2 = new StatementAClass2();
    int field1Diff = instance1.field1 - instance2.field1;
}

/* It's completely fine to have two different classes having a field with the same name and type
 * The above program showed a subtraction between field1 from two diff classes
 */

 /* Tester Library v.3.0
-----------------------------------
Tests defined in the class: Open1A:
---------------------------
Open1A:
---------------
new Open1A:1(
 this.instance1 = new StatementAClass1:2(
  this.field1 = 0)
 this.instance2 = new StatementAClass2:3(
  this.field1 = 2)
 this.field1Diff = -2)
---------------
No test methods found. */