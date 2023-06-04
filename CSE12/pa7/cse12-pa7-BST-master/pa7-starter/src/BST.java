/**
 * @author Neo Lee, yl003@ucsd.edu
 * This file contains an implementation of a binary search tree in class BST.
 * The class implements the DefaultMap interface written by 
 * @author Juan Dominguez and @author Qiyue Wang.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {

	private Node<K, V> root;
	private int size;

	/**
	 * Helper method to find the node with the given key
	 * @param node
	 * @param key
	 * @return the node with the given key or the leaf node if the key is not in the BST
	 */
	private Node<K, V> findNode(Node<K, V> node, K key) {
		if (node.getKey().compareTo(key) == 0 || node.isLeaf()) { return node; 
		} else if (node.getKey().compareTo(key) > 0) { 
			if (node.getLeft() == null) { return node; }
			return findNode(node.getLeft(), key); 
		} else {
			if (node.getRight() == null) { return node; }
			return findNode(node.getRight(), key);
		}
	}

	/**
	 * Helper method to traverse the BST in order to find the node with the smallest key
	 * @param node
	 * @return the node with the smallest key
	 */
	private Node<K, V> traverseLeft(Node<K, V> node) {
		if (node.getLeft() == null) { return node; }
		return traverseLeft(node.getLeft());
	}

	/**
	 * Adds the specified key, value pair to this DefaultMap
	 * Note: duplicate keys are not allowed
	 * 
	 * @return true if the key value pair was added to this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		if (key == null) { throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY); }
		if (this.root == null) {
			this.root = new Node<K, V>(key, value);
			this.size++;
			return true;
		}

		Node<K, V> node = findNode(this.root, key);
		if (node.getKey().compareTo(key) == 0) { return false; }

		if (node.getKey().compareTo(key) > 0) {
			node.setLeft(new Node<K, V>(key, value));
			node.getLeft().setParent(node);
			this.size++;
		} else {
			node.setRight(new Node<K, V>(key, value));
			node.getRight().setParent(node);
			this.size++;
		}

		if (node.isLeaf()) { throw new IllegalArgumentException("Put incomplete"); }
		if (findNode(this.root, key).getKey().compareTo(key) != 0) { 
			throw new IllegalArgumentException("Put incomplete");
		}

		return true;
	}

	/**
	 * Replaces the value that maps to the key if it is present
	 * @param key The key whose mapped value is being replaced
	 * @param newValue The value to replace the existing value with
	 * @return true if the key was in this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if (key == null) { throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY); }
		if (this.root == null) { return false; }

		Node<K, V> node = findNode(this.root, key);
		if (node.getKey().compareTo(key) != 0) { return false; }
		node.setValue(newValue);

		if (node.getKey().compareTo(key) == 0 && node.getValue() != newValue) { 
			throw new IllegalArgumentException("Replace incomplete");
		}

		return true;
	}

	/**
	 * Remove the entry corresponding to the given key
	 * 
	 * @return true if an entry for the given key was removed
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if (key == null) { throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY); }
		if (this.root == null) { return false; }

		Node<K, V> node = findNode(this.root, key);
		if (node.getKey().compareTo(key) != 0) { return false; }
		
		// if there is only one node in the BST
		if (node == this.root && node.isLeaf()) { 
			this.root = null; 
			this.size--;
			return true;
		}
		
		// if node is a leaf
		else if (node.isLeaf()) {
			if (node.getParent().getLeft() == node) {
				node.getParent().setLeft(null);
			} else {
				node.getParent().setRight(null);
			}
			
			if (findNode(this.root, key).getKey().compareTo(key) == 0) { 
				throw new IllegalArgumentException("Remove incomplete");
			}
			this.size--;
			return true;
		}

		// if node has only right child
		else if (node.getLeft() == null) {
			if (node.getParent() == null) {
				this.root = node.getRight();
				this.root.setParent(null);
			} else if (node.getParent().getLeft() == node) {
				node.getParent().setLeft(node.getRight());
			} else {
				node.getParent().setRight(node.getRight());
			}

			if (findNode(this.root, key).getKey().compareTo(key) == 0) { 
				throw new IllegalArgumentException("Remove incomplete");
			}
			this.size--;
			return true;
		}

		// if node has only left child
		else if (node.getRight() == null) {
			if (node.getParent() == null) {
				this.root = node.getLeft();
				this.root.setParent(null);
			} else if (node.getParent().getLeft() == node) {
				node.getParent().setLeft(node.getLeft());
			} else {
				node.getParent().setRight(node.getLeft());
			}

			if (findNode(this.root, key).getKey().compareTo(key) == 0) { 
				throw new IllegalArgumentException("Remove incomplete");
			}
			this.size--;
			return true;
		}

		// if node has both children
		else {
			Node<K, V> successor = traverseLeft(node.getRight());
			node.setKey(successor.getKey());
			node.setValue(successor.getValue());
			if (successor.getParent().getLeft() == successor) {
				if (successor.getRight() != null) {
					successor.getParent().setLeft(successor.getRight());
					successor.getRight().setParent(successor.getParent());
				} else {
					successor.getParent().setLeft(null);
				}
			} else {
				if (successor.getRight() != null) {
					successor.getParent().setRight(successor.getRight());
					successor.getRight().setParent(successor.getParent());
				} else {
					successor.getParent().setRight(null);
				}
			}

			if (findNode(this.root, key).getKey().compareTo(key) == 0) { 
				throw new IllegalArgumentException("Remove incomplete");
			}
			this.size--;
			return true;
		}
	}

	/**
	 * Adds the key, value pair to this DefaultMap if it is not present,
	 * otherwise, replaces the value with the given value
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if (key == null) { throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY); }
		boolean replaced = replace(key, value);
		if (!replaced) { put(key, value); }
	}

	/**
	 * @return the value corresponding to the specified key
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		if (key == null) { throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY); }
		if (this.root == null) { return null; }
		Node<K, V> node = findNode(this.root, key);
		if (node.getKey().compareTo(key) != 0) { return null; }

		return node.getValue();
	}

	/**
	 * 
	 * @return The number of (key, value) pairs in this DefaultMap
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * 
	 * @return true iff this.size() == 0 is true
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * @return true if the specified key is in this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if (key == null) { throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY); }
		if (this.root == null) { return false; }
		Node<K, V> node = findNode(this.root, key);
		if (node.getKey().compareTo(key) == 0) { return true; }
		return false;
	}

	/**
	 * Helper method to traverse the BST in order and copy the keys to the list
	 * @param node
	 * @param keys
	 */
	private void inOrderTraverse(Node<K, V> node, List<K> keys) {
		if (node == null) { return; }
		inOrderTraverse(node.getLeft(), keys);
		keys.add(node.getKey());
		inOrderTraverse(node.getRight(), keys);
	}

	/**
	 * 
	 * @return an array containing the keys of this DefaultMap. If this DefaultMap is 
	 * empty, returns array of length zero. 
	 */
	@Override
	public List<K> keys() {
		List<K> keys = new ArrayList<K>();
		inOrderTraverse(this.root, keys);
		return keys;
	}
	
	/**
	 * This class represents a node in the BST
	 * Instance variables:
	 * left: the left child of this node
	 * right: the right child of this node
	 * parent: the parent of this node
	 * key: the key of this node
	 * value: the value of this node
	 */
	private static class Node<K extends Comparable<? super K>, V> 
								implements DefaultMap.Entry<K, V> {
		
		private Node<K, V> left;
		private Node<K, V> right;
		private Node<K, V> parent;
		private K key;
		private V value;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		/**
		 * @return the key
		 */
		@Override
		public K getKey() {
			return this.key;
		}

		/**
		 * Set the key of this node
		 * @param key the key to set
		 */
		public void setKey(K key) {
			this.key = key;
		}
		
		/**
		 * @return the value
		 */
		@Override
		public V getValue() {

			return this.value;
		}

		/**
		 * Set the value of this node
		 * @param value the value to set
		 */
		@Override
		public void setValue(V value) {

			this.value = value;
		}

		/**
		 * Set the left child of this node
		 * @param left the left child to set
		 */
		public void setLeft(Node<K, V> left) {
			this.left = left;
		}

		/**
		 * Set the right child of this node
		 * @param right the right child to set
		 */
		public void setRight(Node<K, V> right) {
			this.right = right;
		}

		/**
		 * Set the parent of this node
		 * @param parent the parent to set
		 */
		public void setParent(Node<K, V> parent) {
			this.parent = parent;
		}

		/**
		 * @return the left child
		 */
		public Node<K, V> getLeft() {
			return this.left;
		}

		/**
		 * @return the right child
		 */
		public Node<K, V> getRight() {
			return this.right;
		}

		/**
		 * @return the parent
		 */
		public Node<K, V> getParent() {
			return this.parent;
		}

		/**
		 * @return true iff this node is a leaf
		 */
		public boolean isLeaf() {
			return this.left == null && this.right == null;
		}
		
		
	}
	 
}