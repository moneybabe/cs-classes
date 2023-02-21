import tester.*;

interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    String toString();
    double toDouble();
}

class WholeNumber implements Number{
    int n;

    WholeNumber(int n) {
        this.n = n;
    }

    public int numerator() {
        return this.n;
    }

    public int denominator(){
        return 1;
    }

    public Number add(Number other) {
        if (other.denominator() == 1) {
            return new WholeNumber(this.n + other.numerator());
        } else {
            return other.add(this);
        }
        
    }

    public Number multiply(Number other) {
        if (other.denominator() == 1) {
            return new WholeNumber(this.n*other.numerator());
        } else {
            return other.multiply(this);
        }
    }

    public String toString() {
        return String.valueOf(this.n);
    }

    public double toDouble() {
        return this.n/1.0;
    }
}

class Fraction implements Number {
    int n;
    int d;

    Fraction(int n, int d) {
        this.n = n;
        this.d = d;
    }

    public int numerator() {
        return this.n;
    }

    public int denominator() {
        return this.d;
    }

    public Number add(Number other) {
        return new Fraction(this.n*other.denominator() + this.d*other.numerator(), this.d*other.denominator());
    }

    public Number multiply(Number other) {
        return new Fraction(this.n*other.numerator(), this.d*other.denominator());
    }

    public String toString() {
        return this.n + "/" + this.d;
    }

    public double toDouble() {
        return this.n / (this.d/1.0);
    }
}

class ExamplesNumber {
    Number w1 = new WholeNumber(5);
    Number w2 = new WholeNumber(7);
    Number f1 = new Fraction(3, 4);
    Number f2 = new Fraction(5, 10);

    boolean testAdd(Tester t) {
        return t.checkExpect(this.w1.add(w2).toString(), "12") &&
                t.checkExpect(this.w1.add(f1).toString(), "23/4") &&
                t.checkExpect(this.f1.add(w1).toString(), "23/4") &&
                t.checkExpect(this.f2.add(w1).toString(), "55/10") &&
                t.checkExpect(this.f1.add(f1).toString(), "24/16") &&
                t.checkExpect(this.f1.add(f2).toString(), "50/40");
    }

    boolean testMultiply(Tester t) {
        return t.checkExpect(this.w1.multiply(w2).toString(), "35") &&
                t.checkExpect(this.w1.multiply(f1).toString(), "15/4") &&
                t.checkExpect(this.f1.multiply(w1).toString(), "15/4") &&
                t.checkExpect(this.f2.multiply(w1).toString(), "25/10") &&
                t.checkExpect(this.f1.multiply(f1).toString(), "9/16") &&
                t.checkExpect(this.f1.multiply(f2).toString(), "15/40");
    }

    boolean testToDouble(Tester t) {
        return t.checkExpect(this.w1.multiply(w2).toDouble(), 35.0) &&
                t.checkExpect(this.w1.multiply(f1).toDouble(), 15/4.0) &&
                t.checkExpect(this.f1.multiply(w1).toDouble(), 15/4.0) &&
                t.checkExpect(this.f2.multiply(w1).toDouble(), 25/10.0) &&
                t.checkExpect(this.f1.multiply(f1).toDouble(), 9/16.0) &&
                t.checkExpect(this.f1.multiply(f2).toDouble(), 15.0/40);
    }

    // Exploration
    double ex1 = 0.1 + 0.2 + 0.3;
    double ex2 = 0.1 + (0.2 + 0.3);
    String ex3 = (new Fraction(1,10)).add(new Fraction(2, 10)).add(new Fraction(3, 10)).toString();
    String ex4 = (new Fraction(1,10)).add((new Fraction(2, 10)).add(new Fraction(3, 10))).toString();
}