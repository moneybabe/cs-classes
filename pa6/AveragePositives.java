class AveragePositives {
    public static void main (String[] args) {
        
        double[] valueArr = new double[args.length];
        for (int i = 0; i < valueArr.length; i ++) {
            valueArr[i] = Double.parseDouble(args[i]);
        }

        double totalSum = 0;
        int length = 0;
        for (int i = 0; i < valueArr.length; i++) {
            if (valueArr[i] > 0) {
                totalSum += valueArr[i];
                length += 1;
            }
        }

        if (length == 0) {
            System.out.println(0);
        } else {
            System.out.println(totalSum/length);
        }
    }
}

// (base) neo@Neos-MacBook-Air pa6 % java AveragePositives 0    
// 0
// (base) neo@Neos-MacBook-Air pa6 % java AveragePositives 0 0 0 0 0 -1 -5.5
// 0
// (base) neo@Neos-MacBook-Air pa6 % java AveragePositives 0 0 0 0 0 -1 -5.5 0.1
// 0.1
// (base) neo@Neos-MacBook-Air pa6 % java AveragePositives 0 0 0 0 0 -1 -5.5 1 2 3 4 5 0.1
// 2.5166666666666666
// (base) neo@Neos-MacBook-Air pa6 % java AveragePositives 100 200 300 400 500        
// 300.0
// (base) neo@Neos-MacBook-Air pa6 % java AveragePositives 100 200 300 400 500 -1000 -0 -0.90214
// 300.0
// (base) neo@Neos-MacBook-Air pa6 % java AveragePositives 0.5 1.0 1.5 2.0                      
// 1.25