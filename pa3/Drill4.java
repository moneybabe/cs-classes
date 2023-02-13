import java.util.Arrays;

class Drill4 {
    String phaseOfWater (int temp) {
        if (temp >= 100) {
            return "vapor";
        } else if (temp >0) {
            return "liquid";
        } else {
            return "solid";
        }
    }

    int maxDifference(int i1, int i2, int i3) {
        int[] array = {i1, i2, i3};
        Arrays.sort(array);
        return array[2] - array[0];
    }

    double ringArea(double r1, double r2) {
        return Math.PI*r2*r2 - Math.PI*r1*r1;
    }

    String pow1 = this.phaseOfWater(50);    // "liquid"
    String pow2 = this.phaseOfWater(100);    // "vapor"
    String pow3 = this.phaseOfWater(0);    // "solid"

    int md1 = this.maxDifference(5, -1, 3);  // 6
    int md2 = this.maxDifference(9, 9, -5);  // 14
    int md3 = this.maxDifference(2, 1, 0);  // 2

    double ra1 = this.ringArea(1, 5);    // 24pi
    double ra2 = this.ringArea(2, 3);    // 5pi
    double ra3 = this.ringArea(0, 5);    // 25pi
}