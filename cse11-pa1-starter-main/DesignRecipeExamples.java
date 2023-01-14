import tester.*;

class DesignRecipeExamples {
    int perimeter(int width, int height) {
        return (width + height) * 2;
    }

    int perimeterTest1 = this.perimeter(3, 5);  // expected value: 16
    int perimeterTest2 = this.perimeter(6, 10); // expected value: 32


    int borderArea(int innerWidth, int innerHeight, int outerWidth, int outerHeight) {
        return outerWidth * outerHeight - innerWidth * innerHeight;
    }

    int borderAreaTest1 = this.borderArea(2, 2, 5, 5);  // expected value: 21
    int borderAreaTest2 = this.borderArea(5, 5, 10, 10);    //expected value: 75


    // it converts USD to HKD, it takes in the args int usd
    int usdToHkd(int usd) {
        double hkd = usd / 7.85;
        return (int) hkd;
    }

    // reference value: 10.191, expected return value: 10 because the return type is int
    int usdToHkdTest1 = this.usdToHkd(80);
    // reference vakye: 101.91, expected return value: 101 because the return type is int
    int usdToHkdTest2 = this.usdToHkd(800);


    // it combines meters and centimeters into total in centimeters
    int totalCentimeters(int meters, int centimeters) {
        return meters * 100 + centimeters;
    }

    /* I can't find a pair of arguments that produce incorrect output
     * All the parameters and algorithm only involves very basic arithmetic and the most basic int type
     * I don't see a reason or a scenario that it will produce an incorrect output
     */
    int totalCentimetersTest1 = this.totalCentimeters(5, 10);   // expected value: 510
    int totalCentimetersTest2 = this.totalCentimeters(1, 200);   // expected value: 300

}