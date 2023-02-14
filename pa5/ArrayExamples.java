import java.util.Arrays;
import tester.*;


class ArrayExamples {
    String joinWith(String[] strArr, String separator) {
        return String.join(separator, strArr); 
    }

    boolean allTrue(boolean[] boolArr) {
        for (boolean b: boolArr) {
            if (!b) return false;
        }
        return true;
    }

    boolean allWithinRange(double[] doubArr, double low, double high) {
        for (double d: doubArr) {
            if (d < low || d > high) return false;
        }
        return true;
    }

    Pair maxmin(int[] intArr) {
        Arrays.sort(intArr);
        return new Pair(intArr[0], intArr[intArr.length - 1]);
    }

    String earliest(String[] strArr) {
        String foo = strArr[0];
        for (String i: strArr) {
            if (foo.compareTo(i) > 0) {
                foo = i;
            }
        }
        return foo;
    }

    int lookup(String[] keys, int[] values, String key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            };
        }
        return -1;
    }

    void testJoinWith(Tester t) {
        String[] testStr = {"a", "b", "c"};
        String[] testStr2 = {"a", "b", "c", "d"};

        t.checkExpect(this.joinWith(testStr, ","), "a,b,c");
        t.checkExpect(this.joinWith(testStr, " "), "a b c");
        t.checkExpect(this.joinWith(testStr2, "~"), "a~b~c~d");
    }

    void testAllTrue(Tester t) {
        boolean[] testArr = {true, true, false};
        boolean[] testArr2 = {true, true, true, true};
        boolean[] testArr3 = {false, false, false};

        t.checkExpect(this.allTrue(testArr), false);
        t.checkExpect(this.allTrue(testArr2), true);
        t.checkExpect(this.allTrue(testArr3), false);
    }

    void testAllWithinRange(Tester t) {
        double[] testArr = {2.6, 9.0, 3.5, 5.6};
        double[] testArr2 = {-2.5, 11.9, 3.5, 5.6};

        t.checkExpect(this.allWithinRange(testArr, 2, 9), true);
        t.checkExpect(this.allWithinRange(testArr, 2.1, 9), true);
        t.checkExpect(this.allWithinRange(testArr, 2, 8.999), false);
        t.checkExpect(this.allWithinRange(testArr2, 5, 11.9), false);
        t.checkExpect(this.allWithinRange(testArr2, -2.5, 11.9), true);
    }

    void testMaxmin(Tester t) {
        int[] testArr = {5, 3, 0, 1, -1};
        int[] testArr2 = {-4, -0, -9, -11};
        int[] testArr3 = {5, 4, 3, 2, 1};

        t.checkExpect(this.maxmin(testArr), new Pair(-1, 5));
        t.checkExpect(this.maxmin(testArr2), new Pair(-11, 0));
        t.checkExpect(this.maxmin(testArr3), new Pair(1, 5));
    }

    void testEarliest(Tester t) {
        String[] testArr = {"b", "d", "e", "a"};
        String[] testArr2 = {"hello", "heklo", "zebra", "zzzz"};
        String[] testArr3 = {"hi", "hello", "gm", "gn"};

        t.checkExpect(this.earliest(testArr), "a");
        t.checkExpect(this.earliest(testArr2), "heklo");
        t.checkExpect(this.earliest(testArr3), "gm");
    }

    void testLookup(Tester t) {
        int[] valuesArr = {36000, 44900, 33467};
        int[] valuesArr2 = {-36000, 44900, 33467};
        String[] keysArr = {"UCSD", "UCLA", "UCI"};
        
        t.checkExpect(this.lookup(keysArr, valuesArr, "UCI"), 33467);
        t.checkExpect(this.lookup(keysArr, valuesArr, "Stanford"), -1);
        t.checkExpect(this.lookup(keysArr, valuesArr2, "UCSD"), -36000);
    }
}

class Pair {
    int a;
    int b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}