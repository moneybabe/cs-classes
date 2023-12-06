import java.util.Set;
import java.util.Iterator;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
public class BSTMap<K extends Comparable<? super K>, V> implements Map61B<K, V> {

	private Node<K, V> root = null;
	private int size = 0;

	/**
	 * Helper method to find the node with the given key
	 * @param node
	 * @param key
	 * @return the node with the given key or the null if the key is not in the BST
	 */
	private Node<K, V> findNode(Node<K, V> node, K key) {
		if (node == null) {
			return null;
		} else if (node.getKey().compareTo(key) == 0) {
			return node;
		} else if (node.getKey().compareTo(key) > 0) {
			return findNode(node.getLeft(), key);
		} else {
			return findNode(node.getRight(), key);
		}
	}
	
	private void addNode(Node<K, V> node, Node<K, V> newNode) {
		if (node.getKey().compareTo(newNode.getKey()) > 0) {
			if (node.getLeft() == null) {
				node.setLeft(newNode);
				newNode.setParent(node);
			} else {
				addNode(node.getLeft(), newNode);
			}
		} else {
			if (node.getRight() == null) {
				node.setRight(newNode);
				newNode.setParent(node);
			} else {
				addNode(node.getRight(), newNode);
			}
		}
	}

	/**
	 * Adds the specified key, value pair to this DefaultMap
	 * Note: duplicate keys are not allowed
	 * 
	 * @return true if the key value pair was added to this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public void put(K key, V value) throws IllegalArgumentException {		
		Node<K, V> node = findNode(this.root, key);
		if (node != null) {
			node.setValue(value);
			return;
		}
		
		if (this.root == null) {
			this.root = new Node<K, V>(key, value);
			this.size++;
			return;
		}

		Node<K, V> newNode = new Node<K, V>(key, value);
		addNode(this.root, newNode);
		this.size++;
	}

	/**
	 * @return the value corresponding to the specified key
	 * @throws IllegalArgumentException if the key is null
	 */
    @Override
	public V get(K key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}
		Node<K, V> node = this.findNode(this.root, key);
		return node == null ? null : node.getValue();
	}
    
	@Override
	public boolean containsKey(K key) {
		Node<K, V> node = this.findNode(this.root, key);
		return node != null;
	}

	/**
	 * 
     * @return The number of (key, value) pairs in this DefaultMap
	 */
    @Override
	public int size() {
        return this.size;
	}	

    @Override
    public void clear() {
		this.root = null;
		this.size = 0;
    }
    
	@Override
	public Set<K> keySet() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public V remove(K key) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Iterator<K> iterator() {
		throw new UnsupportedOperationException("Unimplemented method 'iterator'");
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
	private static class Node<K extends Comparable<? super K>, V>{
		
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
		public V getValue() {

			return this.value;
		}

		/**
		 * Set the value of this node
		 * @param value the value to set
		 */
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