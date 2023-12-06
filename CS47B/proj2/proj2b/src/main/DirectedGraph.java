package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<K, V> {
    private static class Node<K, V> {
        private K key;
        private Set<V> values;
        private Set<Node<K, V>> kids;

        public Node(K key, Set<V> values, Set<Node<K, V>> kids) {
            this.key = key;
            this.values = values;
            this.kids = kids;
        }

        public K getKey() {
            return this.key;
        }

        public Set<V> getValues() {
            if (this.values == null) {
                return new HashSet<V>();
            }
            return this.values;
        }

        public Set<Node<K, V>> getKids() {
            if (this.kids == null) {
                return new HashSet<Node<K, V>>();
            }
            return this.kids;
        }

        public void addValue(V values) {
            if (this.values == null) {
                this.values = new HashSet<V>();
            }
            this.values.add(values);
        }

        public void addKid(Node<K, V> kid) {
            if (this.kids == null) {
                this.kids = new HashSet<Node<K, V>>();
            }
            this.kids.add(kid);
        }
    }

    private HashMap<K, Node<K, V>> nodes;

    public DirectedGraph() {
        nodes = new HashMap<K, Node<K, V>>();
    }

    public void addNode(K key) {
        this.nodes.putIfAbsent(key, new Node<K, V>(key, null, null));
    }

    public void addValue(K key, V value) {
        this.nodes.get(key).addValue(value);
    }

    public void addValue(K key, List<V> values) {
        for (V value : values) {
            this.nodes.get(key).addValue(value);
        }
    }

    public void addKid(K key, K kidKey) {
        this.addNode(kidKey);
        this.nodes.get(key).addKid(this.nodes.get(kidKey));
    }

    public void addKid(K key, List<K> kidKeys) {
        kidKeys.forEach(kidKey -> this.addKid(key, kidKey));
    }

    public List<V> getValues(K key) {
        if (!this.nodes.containsKey(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        return new ArrayList<V>(this.nodes.get(key).getValues());
    }
    
    public List<V> getValues(List<K> key) {
        List<V> values = new ArrayList<V>();
        key.forEach(k -> values.addAll(this.getValues(k)));
        return values;
    }
    
    public List<Node<K, V>> getKids(K key) {
        if (!this.nodes.containsKey(key) ) {
            throw new IllegalArgumentException("Invalid key");
        }
        return new ArrayList<Node<K, V>>(this.nodes.get(key).getKids());
    }

    public List<Node<K, V>> getAllKids(K key) {
        Set<Node<K, V>> kidsSet = new HashSet<Node<K, V>>();
        for (Node<K, V> kid : this.getKids(key)) {
            kidsSet.add(kid);
            kidsSet.addAll(this.getAllKids(kid.getKey()));
        }
        return new ArrayList<Node<K, V>>(kidsSet);
    }

    public List<V> getAllKidsValues(K key) {
        Set<V> valuesSet = new HashSet<V>();
        this.getAllKids(key).forEach(kid -> valuesSet.addAll(kid.getValues()));
        return new ArrayList<V>(valuesSet);
    }
}
