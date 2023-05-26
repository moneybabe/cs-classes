/**
 * This file contains a skeleton implementation of a HashMap using separate
 * chaining. 
 * It implements the DefaultMap interface, which has the following methods:
 * put, replace, remove, set, get, size, isEmpty, containsKey, keys.
 * Details of methods can be found in DefaultMap.java.
**/

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class MyHashMap<K, V> implements DefaultMap<K, V> {
	public static final double DEFAULT_LOAD_FACTOR = 0.75;
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	public static final String ILLEGAL_ARG_CAPACITY = "Initial Capacity must be non-negative";
	public static final String ILLEGAL_ARG_LOAD_FACTOR = "Load Factor must be positive";
	public static final String ILLEGAL_ARG_NULL_KEY = "Keys must be non-null";
	
	private double loadFactor;
	private int capacity;
	private int size;

	// Use this instance variable for Separate Chaining conflict resolution
	private List<HashMapEntry<K, V>>[] buckets;  
	
	// Use this instance variable for Linear Probing
	// private HashMapEntry<K, V>[] entries; 	

	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	/**
	 * 
	 * @param initialCapacity the initial capacity of this MyHashMap
	 * @param loadFactor the load factor for rehashing this MyHashMap
	 * @throws IllegalArgumentException if initialCapacity is negative or loadFactor not
	 * positive
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, double loadFactor) throws IllegalArgumentException {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_CAPACITY);
		} else if (loadFactor <= 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_LOAD_FACTOR);
		}

		this.loadFactor = loadFactor;
		this.capacity = initialCapacity;
		this.size = 0;

		// if you use Separate Chaining
		this.buckets = (List<HashMapEntry<K, V>>[]) new ArrayList<?>[this.capacity];
	}

	/**
	 * Helper method to rehash the array when the load factor is reached
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		if (this.size < this.capacity * this.loadFactor) {
			return;
		}
		this.capacity *= 2;
		List<HashMapEntry<K, V>>[] newBuckets = (List<HashMapEntry<K, V>>[]) 
												new List<?>[this.capacity];
		for (int i = 0; i < this.buckets.length; i++) {
			if (this.buckets[i] == null) {
				continue;
			}
			for (int j = 0; j < this.buckets[i].size(); j++) {
				HashMapEntry<K, V> entry = this.buckets[i].get(j);
				int keyHash = Objects.hashCode(entry.getKey());
				int index = ((keyHash % this.capacity) + this.capacity) % this.capacity;
				if (newBuckets[index] == null) {
					newBuckets[index] = new ArrayList<HashMapEntry<K, V>>();
				}
				newBuckets[index].add(entry);
			}
		}
		this.buckets = newBuckets;
	}

	/**
	 * Helper method to find the index of the key in the array
	 * @param key the key to find in this MyHashMap
	 * @return the index of the key in the array, -1 if not found
	 */
	private int findKey(K key) throws IllegalArgumentException {
		int keyHash = Objects.hashCode(key);
		int index = ((keyHash % this.capacity) + this.capacity) % this.capacity;
		
		if (this.buckets[index] == null) {
			return -1;
		}

		for (int i = 0; i < this.buckets[index].size(); i++) {
			HashMapEntry<K, V> entry = this.buckets[index].get(i);
			if (entry.getKey().equals(key)) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		// can also use key.hashCode() assuming key is not null
		int keyHash = Objects.hashCode(key); 
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}

		if (findKey(key) != -1) {
			return false;
		}

		rehash();

		int index = ((keyHash % this.capacity) + this.capacity) % this.capacity;
		if (this.buckets[index] == null) {
			this.buckets[index] = new ArrayList<HashMapEntry<K, V>>();
		}

		HashMapEntry<K, V> entry = new HashMapEntry<K, V>(key, value);
		this.buckets[index].add(entry);
		this.size++;

		return true;
	}

	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}

		int arrIndex = findKey(key);
		if (arrIndex == -1) {
			return false;
		}

		int keyHash = Objects.hashCode(key);
		int index = ((keyHash % this.capacity) + this.capacity) % this.capacity;
		HashMapEntry<K, V> entry = this.buckets[index].get(arrIndex);
		entry.setValue(newValue);

		return true;
	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}

		int arrIndex = findKey(key);
		if (arrIndex == -1) {
			return false;
		}

		int keyHash = Objects.hashCode(key);
		int index = ((keyHash % this.capacity) + this.capacity) % this.capacity;
		this.buckets[index].remove(arrIndex);
		this.size--;
		
		return true;
	}

	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}

		boolean replaced = replace(key, value);
		if (!replaced) {
			rehash();
			int keyHash = Objects.hashCode(key);
			int index = ((keyHash % this.capacity) + this.capacity) % this.capacity;
			if (this.buckets[index] == null) {
				this.buckets[index] = new ArrayList<HashMapEntry<K, V>>();
			}

			HashMapEntry<K, V> entry = new HashMapEntry<K, V>(key, value);
			this.buckets[index].add(entry);
			this.size++;
		}
		
	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}

		int arrIndex = findKey(key);
		if (arrIndex == -1) {
			return null;
		}

		int keyHash = Objects.hashCode(key);
		int index = ((keyHash % this.capacity) + this.capacity) % this.capacity;
		return this.buckets[index].get(arrIndex).getValue();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}

		if (findKey(key) == -1) {
			return false;
		}

		return true;
	}

	@Override
	public List<K> keys() {
		List<K> keys = new ArrayList<K>();
		for (int i = 0; i < this.buckets.length; i++) {
			if (this.buckets[i] != null) {
				for (int j = 0; j < this.buckets[i].size(); j++) {
					HashMapEntry<K, V> entry = this.buckets[i].get(j);
					keys.add(entry.getKey());
				}
			}
		}

		return keys;
	}
	
	private static class HashMapEntry<K, V> implements DefaultMap.Entry<K, V> {
		
		K key;
		V value;
		
		private HashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
		@Override
		public void setValue(V value) {
			this.value = value;
		}
	}
}
