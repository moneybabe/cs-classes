class StatementC {
    int square(int input) {
        return input*input;
    }

    int times2(int input) {
        return input*2;
    }
}

class Open1C {
    StatementC instance = new StatementC();
    int r1 = instance.square(3);   // expected 9
    int r2 = instance.times2(2);    // expected 4
}

/* Tester Library v.3.0
-----------------------------------
Tests defined in the class: Open1C:
---------------------------
Open1C:
---------------
new Open1C:1(
 this.instance = new StatementC:2()
 this.r1 = 9
 this.r2 = 4)
---------------
No test methods found.
 */