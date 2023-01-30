class StatementB {
    int field1 = 1;
    boolean field1 = true;
}

class Open1B {
    StatementB instance = new StatementB();
}

/* The program produces an error:
 * Open1B.java:3: error: variable field1 is already defined in class StatementB
    boolean field1 = true;
            ^
1 error
 */