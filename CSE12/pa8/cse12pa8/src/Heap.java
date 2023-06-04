/**
 * @author Neo Lee, yl003@ucsd.edu
 * This file implements a heap data structure with class Entry<K, V> and class Heap<K, V>.
 */
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Heap class implements a heap data structure
 * @param <K> type of key of the entry
 * @param <V> type of value of the entry
 */
public class Heap<K, V> {
    public List<Entry<K, V>> entries;
    public Comparator<K> comparator;

    /**
     * Constructor that takes a comparator as an argument
     * Initialize entries as an empty ArrayList
     * comprarator is used to compare the keys of two entries
     * @param comparator
     */
    public Heap(Comparator<K> comparator) {
        this.entries = new ArrayList<Entry<K, V>>();
        this.comparator = comparator;
    }

    /**
     * It adds the entry to the end of the heap and then bubbles it up
     * Duplicate keys are allowed
     * @param key key of the entry
     * @param value value of the entry
     */
    public void add(K key, V value) {
        this.entries.add(new Entry<K, V>(key, value));
        this.bubbleUp(this.entries.size() - 1);
    }

    /**
     * It removes the root of the heap and then bubbles down to maintain the heap property
     * @return the root of the heap
     */
    public Entry<K, V> poll() {
        if (this.entries.size() == 0) { throw new NoSuchElementException(); }
        Entry<K, V> entry = this.entries.get(0);
        this.entries.set(0, this.entries.get(this.entries.size() - 1));
        this.entries.remove(this.entries.size() - 1);
        this.bubbleDown(0);
        return entry;
    }

    /**
     * It returns the root of the heap, but does not remove it
     * @return the root of the heap
     */
    public Entry<K, V> peek() {
        if (this.entries.size() == 0) { throw new NoSuchElementException(); }
        return this.entries.get(0);
    }

    /**
     * It returns the heap as an ArrayList
     * @return the heap as an ArrayList
     */
    public List<Entry<K, V>> toArray() {
        return this.entries;
    }

    /**
     * It checks if the heap is empty
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.entries.size() == 0;
    }

    /**
     * It returns the parent index of the given index
     * @param index
     * @return the parent index of the given index
     */
    public int parent(int index) {
        if (index == 0) { throw new NoSuchElementException(); }
        return (index - 1) / 2;
    }

    /**
     * It returns the left child index of the given index
     * @param index
     * @return the left child index of the given index
     */
    public int left(int index) {
        return 2 * index + 1;
    }

    /**
     * It returns the right child index of the given index
     * @param index
     * @return the right child index of the given index
     */
    public int right(int index) {
        return 2 * index + 2;
    }

    /**
     * It swaps the entries at the given indices
     * @param index1
     * @param index2
     */
    public void swap(int index1, int index2) {
        Entry<K, V> temp = this.entries.get(index1);
        this.entries.set(index1, this.entries.get(index2));
        this.entries.set(index2, temp);
    }

    /**
     * It bubbles up the entry at the given index 
     * to maintain the heap priority property
     * @param index
     */
    public void bubbleUp(int index) {
        if (index == 0) { return; }
        int parent = this.parent(index);
        if (comparator.compare(entries.get(index).getKey(), 
                                entries.get(parent).getKey()) > 0) {
            swap(index, parent);
            bubbleUp(parent);
        }
    }

    /**
     * It bubbles down the entry at the given index 
     * to maintain the heap priority property
     * @param index
     */
    public void bubbleDown(int index) {
        int left = this.left(index);
        int right = this.right(index);
        int largest = index;
        if (left < this.entries.size() && 
            comparator.compare(entries.get(left).getKey(), 
                                entries.get(largest).getKey()) > 0) {
            largest = left;
        }
        if (right < this.entries.size() && 
            comparator.compare(entries.get(right).getKey(), 
                                entries.get(largest).getKey()) > 0) {
            largest = right;
        }
        if (largest != index) {
            swap(index, largest);
            bubbleDown(largest);
        }
    }

    /**
     * It checks if the entries at the given indices exist 
     * and the entry at index1 is greater than the entry at index2
     * @param index1
     * @param index2
     * @return true if the entries at the given indices exist 
     * and the entry at index1 is greater than the entry at index2, false otherwise
     */
    public boolean existsAndGreater(int index1, int index2) {
        return index1 < this.entries.size() && 
               index2 < this.entries.size() && 
               comparator.compare(entries.get(index1).getKey(), 
                                    entries.get(index2).getKey()) > 0;
    }

    /**
     * It returns the size of the heap
     * @return the size of the heap
     */
    public int size() {
        return this.entries.size();
    }

    /**
     * It returns the string representation of the heap
     * @return the string representation of the heap
     */
    public String toString() {
        return this.entries.toString();
    }
}