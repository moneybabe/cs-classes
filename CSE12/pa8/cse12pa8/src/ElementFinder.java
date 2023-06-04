/**
 * @author Neo Lee, yl003@ucsd.edu
 * This file implements a method to find the Kth largest or smallest element in a file.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Collections;

/**
 * ElementFinder class implements a method to find the Kth largest or smallest element in a file.
 */
public class ElementFinder {

	/**
	 * Static method that finds the Kth largest or smallest element in a file.
	 * The file should contain only integers separated by spaces.
	 * @param filename name of the file
	 * @param K Kth largest or smallest element
	 * @param operation "largest" or "smallest"
	 * @return Kth largest or smallest element
	 */
	public static int Kth_finder(String filename, int K, String operation) {
		// Create a comparator depending upon the type of operation
		PriorityQueue<Integer> heap;
		if (operation.equals("largest")) {
			// min heap
			heap = new PriorityQueue<Integer>(Integer::compare);
		} else if (operation.equals("smallest")) {
			// max heap
			heap = new PriorityQueue<Integer>(Collections.reverseOrder(Integer::compare));
		} else {
			throw new IllegalArgumentException();
		}
		
		try {
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(" ");
                for (String s : data) {
					Integer num = Integer.parseInt(s);
					heap.add(num);
					if (heap.size() > K) { heap.poll(); }
				}
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
		
		if (heap.size() == K) { return heap.peek(); }

		return -1;
	}
		
}
