package tp2;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<KeyType, DataType> implements Iterable<KeyType> {

    private static final int DEFAULT_CAPACITY = 20;
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private Node<KeyType, DataType>[] map;
    private int size = 0;
    private int capacity;
    private final float loadFactor; // Compression factor

    public HashMap() { this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR); }

    public HashMap(int initialCapacity) {
        this(initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY,
                DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        capacity = initialCapacity;
        this.loadFactor = 1 / loadFactor;
        map = new Node[capacity];
    }

    /**
     * Finds the index attached to a particular key
     * This is the hashing function ("Fonction de dispersement")
     * @param key Value used to access to a particular instance of a DataType within map
     * @return Index value where this key should be placed in attribute map
     */
    private int hash(KeyType key){
        int keyHash = key.hashCode() % capacity;
        return Math.abs(keyHash);
    }

    /**
     * @return if map should be rehashed
     */
    private boolean needRehash() {
        return size * loadFactor > capacity;
    }

    /**
     * @return Number of elements currently in the map
     */
    public int size() {
        return size;
    }

    /**
     * @return Current reserved space for the map
     */
    public int capacity(){
        return capacity;
    }

    /**
     * @return if map does not contain any element
     */
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isPrime(int n) {
        if( n == 2 || n == 3 ) return true;
        if( n == 1 || n % 2 == 0 ) return false;

        for (int i = 3; i * i <= n; i += 2) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /** TODO Average Case : O(1)
     * Find the next prime after increasing the capacity by CAPACITY_INCREASE_FACTOR (multiplication)
     */
    private void increaseCapacity() {
        int n = capacity() * CAPACITY_INCREASE_FACTOR;

        //Ici, comme CAPACITY_INCREASE_FACTOR est 2, le nombre sera toujours divisible par deux
        //On passe donc au prochain pour verifier si il est premier (nombre impair)
        n++;
        while(!isPrime(n)) {
            n += 2;
        }

        this.capacity = n;
    }

    /***
     * Gets the node attached to a key
     * @param key which is attached to the node we want to find
     * @return Node if found, null otherwise
     */
    private Node<KeyType, DataType> getNode(KeyType key) {
        Node<KeyType, DataType> node = map[hash(key)];
        while(node != null) {
            if(node.key.equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /** TODO Average Case : O(n)
     * Increases capacity by CAPACITY_INCREASE_FACTOR (multiplication) and
     * reassigns all contained values within the new map
     */
    private void rehash() {
        Node<KeyType, DataType>[] oldMap = map;

        increaseCapacity();

        map = new Node[capacity];

        for (Node<KeyType, DataType> node : oldMap) {
            while (node != null) {
                int index = hash(node.key);
                Node<KeyType, DataType> nodeAtIndex = map[index];
                if (nodeAtIndex != null) {
                    Node<KeyType, DataType> next = nodeAtIndex.next;
                    while (next != null) {
                        nodeAtIndex = next;
                        next = nodeAtIndex.next;
                    }
                    nodeAtIndex.next = node;
                } else {
                    map[index] = node;
                }
                Node<KeyType, DataType> next = node.next;
                node.next = null;
                node = next;
            }
        }
    }



    /** TODO Average Case : O(1)
     * Finds if map contains a key
     * @param key Key which we want to know if exists within map
     * @return if key is already used in map
     */
    public boolean containsKey(KeyType key) {
        return getNode(key) != null ? true : false;
    }

    /** TODO Average Case : O(1)
     * Finds the value attached to a key
     * @param key Key which we want to have its value
     * @return DataType instance attached to key (null if not found)
     */
    public DataType get(KeyType key) {
        Node<KeyType, DataType> node = getNode(key);
        return node != null ? node.data : null;
    }

    /** TODO Average Case : O(1) , Worst case : O(n)
     * Assigns a value to a key
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) {
        int index = hash(key);
        Node<KeyType, DataType> element = map[index];
        // On verifie si l'element existe deja
        while(element != null) {
            if (element.key.equals((key))) {
                DataType data = element.data;
                element.data = value;
                return data;
            } else {
                element = element.next;
            }
        }
        size++;
        // On verifie si il faut augmenter la capacite du tableau
        if(needRehash()) {
            rehash();
            index = hash(key);
        }
        // L'element n'existe pas, on cree un nouveau node pour l'ajouter
        element = new Node<>(key, value);
        element.next = map[index];
        map[index] = element;

        return null;
    }

    /** TODO Average Case : O(1)
     * Removes the node attached to a key
     * @param key Key which is contained in the node to remove
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType remove(KeyType key) {
        int index = hash(key);
        Node<KeyType, DataType> e = map[index];
        Node<KeyType, DataType> last = null;

        while(e != null) {
            if(e.key.equals((key))) {
                if(last == null) {
                    map[index] = e.next;
                } else {
                    last.next = e.next;
                }
                size--;
                return e.data;
            }
            last = e;
            e = e.next;
        }
        return null;
    }

    /** TODO Worst Case : O(1)
     * Removes all nodes contained within the map
     */
    public void clear() {
        if(size > 0) {
            Arrays.fill(map, null);
            size = 0;
        }
    }

    static class Node<KeyType, DataType> {
        final KeyType key;
        DataType data;
        Node<KeyType, DataType> next; // Pointer to the next node within a Linked List

        Node(KeyType key, DataType data)
        {
            this.key = key;
            this.data = data;
            next = null;
        }
    }

    @Override
    public Iterator<KeyType> iterator() {
        return new HashMapIterator();
    }

    // Iterators are used to iterate over collections like so:
    // for (Key key : map) { doSomethingWith(key); }
    private class HashMapIterator implements Iterator<KeyType> {
        private Node<KeyType, DataType> node;
        private int index = 0;
        private int counter = 0;

        HashMapIterator() {
            if(size() > 0) {
                while(map[index] == null) {
                    index++;
                }
                node = map[index];
            } else {
                node = null;
            }
        }

        /** TODO Worst Case : O(n)
         * Determine if there is a new element remaining in the hashmap.
         */
        public boolean hasNext() {
            return counter < size() - 1;
        }

        /** TODO Worst Case : O(n)
         * Return the next new key in the hashmap.
         */
        public KeyType next() {
            counter++;
            KeyType key = node.key;
            if(node.next != null) {
                node = node.next;
            } else {
                while(map[++index] == null){}
                node = map[index];
            }
            return key;
        }
    }
}
