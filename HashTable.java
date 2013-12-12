/*
 * Name:		Zach Graham
 * Class:		CS 341
 * Year:		Fall 2013
 * Assignment:	Hash Table Assignment
 */

package hashtable;

import hashtable.includes.OrderedLinkedList;

/**
 * Hash Table
 * 
 * A hash table, as in this assignment, is an ADT that uses an array of linked lists as a backend 
 * 
 * Conceptually, an array of 'buckets', that stores integers consisting of integer key values
 * 
 * A hash function is used to determine which 'bucket' to insert the integer into based upon the integer key value
 * 
 * For example, in this class, we generate the hash from the modulus of the key and the size of the hash table
 * 
 * By doing this, operation time on the hash table is improved over other ADTs like search trees
 * 
 * @author Zach Graham
 *
 */
public class HashTable {
	
	private static final int INITIAL_CAPACITY = 10;				// The default initial capacity of the hash table
	
	private OrderedLinkedList[] hashArray;						// The hash table, an array of ordered linked lists
	
	private int count;											// The count of items in the hash table
	
	
	/**
	 * Default constructor
	 * 
	 * Instantiates a new hash table with the default initial capacity
	 */
	HashTable() {
		
		hashArray = new OrderedLinkedList[INITIAL_CAPACITY];	// Instantiate a new hash array with the initial capacity
		
		for (int i = 0; i < INITIAL_CAPACITY; i++) {			// Loop through each index in the array
			
			hashArray[i] = new OrderedLinkedList(); 			// Instantiate a new linked list for each index
		}
		
	}
	
	/**
	 * Constructor that instantiates a new hash table with the provided initial capacity
	 * 
	 * @param initialCapacity the size of the hash table
	 */
	HashTable(int initialCapacity) {
		
		hashArray = new OrderedLinkedList[initialCapacity];
		
		for (int i = 0; i < initialCapacity; i++) {				// Loop through each index in the array
			
			hashArray[i] = new OrderedLinkedList(); 			// Instantiate a new linked list for each index
		}
		
	}
	
	/**
	 * Adds the given key to the hash table
	 * 
	 * @param key the key to add to the hash table
	 */
	public void add(int key) {
		
		int hashTableIndex = hashFunction(key);		// Generates the index from the key
		
		hashArray[hashTableIndex].add(key);			// Then adds the key to the hash table at the specified index
		
		count++;
	}
	
	/**
	 * Removes the given key from the hash table
	 * 
	 * @param key the key to remove
	 * @return true if removal is successful, false if not
	 */
	public void remove(int key) {
		
		int hashTableIndex = hashFunction(key);		// Generates index from the key
		
		hashArray[hashTableIndex].remove(key);		// Then removes the key from the hash table
		
		count--;
	}
	
	/**
	 * Returns true if the hash table contains the given key, false if not
	 * 
	 * @param key the key to search the hash table for
	 * @return true if the key exists in the hash table, false if not
	 */
	public boolean containsKey(int key) {
		
		int hashTableIndex = hashFunction(key);				// Generates index from the key
		
		if (hashArray[hashTableIndex].findKeyed(key)) {		// If the list at the generated index contains the key, return true
			return true;
		}
		
		return false;										// Else return false
	}
	
	/**
	 * Returns the total size of the hash table
	 * 
	 * @return total number of elements in the hash table
	 */
	public int size() {
		
		return count;
	}
	
	/**
	 * Converts the given key into an index
	 * 
	 * @param key the key to hash
	 * @return index of the hash table generated from key
	 */
	private int hashFunction(int key) {
		
		return key % hashArray.length;			
	}

} // End class HashTable