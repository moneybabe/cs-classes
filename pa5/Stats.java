import java.util.Arrays;

class Stats {

    static double product(double[] arr) {
        double foo = 1;
        for (double i: arr) {
            foo *=i;
        }
        return foo;
    }
    
    static double total(double[] arr) {
        double total = 0;
        for (double i: arr) {
            total += i;
        }
        return total;
    }

    static double mean(double[] arr) {
        return total(arr) / arr.length;
    }


    static double min(double[] arr) {
        Arrays.sort(arr);
        return arr[0];
    }

    static double max(double[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }

    public static void main(String[] args) {
        String[] parameters = Arrays.copyOfRange(args, 1, args.length);
        double[] arr = Arrays.stream(parameters).mapToDouble(Double::parseDouble).toArray();;

        if (args[0].equals("--product")) {
            System.out.println(product(arr));
        } else if (args[0].equals("--mean")) {
            System.out.println(mean(arr));
        } else if (args[0].equals("--total")) {
            System.out.println(total(arr));
        } else if (args[0].equals("--max")) {
            System.out.println(max(arr));
        } else if (args[0].equals("--min")) {
            System.out.println(min(arr));
        } else {
            System.out.println("Bad option " + args[0]);
        }
    }
    
}