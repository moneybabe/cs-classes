// You can (and should) add "implements Partitioner" below once you have the implementation ready
import java.util.Random;

/*
 * Link to the website that helped me with this code:
 * https://github.com/joeyajames/Java/blob/master/QuickSort.java
 * Author's github username: joeyajames
 * 
 * MIT LICENSE: https://github.com/joeyajames/Java/blob/master/LICENSE
 * 
 * I changed the array type from int to String and changed the swap method to swap Strings 
 * instead of ints.
 * Also, the partition method was changed to compare Strings instead of ints.
 * Besides, the partition method was originally inclusive of the high index, 
 * but I changed it to be exclusive.
 * 
 * It is not buggy because I cannot find CounterExample in TestPartitionOracle.java
 * 
 * Although the pivot is chosen randomly, worst case tight big-O bound is O(n^2).
 */

public class WebPartitioner implements Partitioner{

    private int getPivot(int low, int high) {
		Random rand = new Random();
		return rand.nextInt((high - low) + 1) + low;
	}

    private void swap(String[] A, int index1, int index2) {
		String temp = A[index1];
		A[index1] = A[index2];
		A[index2] = temp;		
	}

    public int partition(String[] A, int low, int high) {
		high -= 1;
        swap(A, low, getPivot(low, high));
		int border = low + 1;
		for (int i = border; i <= high; i++) {
			if (A[i].compareTo(A[low]) < 0) {
				swap(A, i, border++);
			}
		}
		swap(A, low, border-1);
		return border-1;
	}
}
