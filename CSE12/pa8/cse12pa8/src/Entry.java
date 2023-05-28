/**
 * Entry class for the priority queue
 * @param <K> type of the key
 * @param <V> type of the value
 */
public class Entry<K, V> {
	K key; // aka the priority
	V value;

	/**
	 * Constructor that takes a key and a value
	 * @param k key of the entry
	 * @param v value of the entry
	 */
	public Entry(K k, V v) {
		this.key = k;
		this.value = v;
	}

	/**
	 * It returns the string representation of the entry
	 * @return the string representation of the entry
	 */
	public String toString() {
		return key + ": " + value;
	}

	/**
	 * It returns the key value of the entry
	 * @return the key value of the entry
	 */
	public K getKey() {
		return key;
	}
}