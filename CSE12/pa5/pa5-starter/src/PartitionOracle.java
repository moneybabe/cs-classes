
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

public class PartitionOracle {

    /**
     * Feel free to use this method to call partition. It catches any exceptions or
     * errors and returns a definitely-invalid pivot (-1) to turn errors into
     * potential failures. For example, in testPartition, you may use
     * 
     * runPartition(p, someArray, someLow, someHigh)
     * 
     * instead of
     * 
     * p.partition(someArray, someLow, someHigh)
     * 
     * @param p
     * @param strs
     * @param low
     * @param high
     * @return
     */
    public static int runPartition(Partitioner p, String[] strs, int low, int high) {
        try {
            return p.partition(strs, low, high);
        } catch (Throwable t) {
            return -1;
        }
    }

    // The three methods below are for you to fill in according to the PA writeup.
    // Feel free to make other helper methods as needed.

    public static String isValidPartitionResult(String[] before, int low, int high, int pivot, String[] after) {
        HashMap<String, Integer> beforeCount = new HashMap<String, Integer>();
        for (int i = 0; i < before.length; i++) {
            if (beforeCount.containsKey(before[i])) {
                beforeCount.put(before[i], beforeCount.get(before[i]) + 1);
            } else {
                beforeCount.put(before[i], 1);
            }
        }

        HashMap<String, Integer> afterCount = new HashMap<String, Integer>();
        for (int i = 0; i < after.length; i++) {
            if (afterCount.containsKey(after[i])) {
                afterCount.put(after[i], afterCount.get(after[i]) + 1);
            } else {
                afterCount.put(after[i], 1);
            }
        }
        
        if (!beforeCount.equals(afterCount)) {
            return "before and after are not the same";

        } else if (pivot < low || pivot >= high) {
            return "pivot is not between low and high";

        } else if (!Arrays.equals(Arrays.copyOfRange(before, high, before.length), 
                    Arrays.copyOfRange(after, high, after.length))) {
            return "elements after high are not the same";

        } else if (!Arrays.equals(Arrays.copyOfRange(before, 0, low), 
                    Arrays.copyOfRange(after, 0, low))) {
            return "elements before low are not the same";
            
        } 

        for (int i = low ; i < pivot; i++) {
            if (after[i].compareTo(after[pivot]) > 0) {
                return "elements before pivot are not less than pivot";
            }
        }

        for (int i = pivot + 1; i < high; i++) {
            if (after[i].compareTo(after[pivot]) < 0) {
                return "elements after pivot are not greater than pivot";
            }
        }
        
        return null;
        
    }

    public static String[] generateInput(int n) {
        Random r = new Random();
        String[] strs = new String[n];
        int asciiForACapLetter;
        String s;

        for (int i = 0; i < n; i++) {
            asciiForACapLetter = r.nextInt(26) + 65;  // Generates a random letter from A - Z
            s = Character.toString((char)(asciiForACapLetter));
            strs[i] = s;
        }
        
        return strs;
    }
    
    public static CounterExample findCounterExample(Partitioner p) {
        String[] beforeArr;
        String[] afterArr;
        Random r = new Random();
        int length;
        int low;
        int high;
        int pivot;
        String reason;

        for (int i = 0 ; i < 500; i++) {
            length = r.nextInt(100) + 1;
            beforeArr = generateInput(length);
            afterArr = Arrays.copyOf(beforeArr, beforeArr.length);
            low = r.nextInt(afterArr.length);
            high = r.nextInt(afterArr.length - low) + low + 1;
            pivot = runPartition(p, afterArr, low, high);
            reason = isValidPartitionResult(beforeArr, low, high, pivot, afterArr);
            
            if (reason != null) {
                return new CounterExample(beforeArr, low, high, pivot, afterArr, reason);
            }
            
        }
        
        return null;
    }

    // public static void main(String[] args) {
    //     // You can uncomment the following line to test your partitioner
    //     CounterExample ce = findCounterExample(new CentralPivotPartitioner());
    //     System.out.println(ce);
    // }

}
