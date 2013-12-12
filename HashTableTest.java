/*
 * Name:		Zach Graham
 * Class:		CS 341
 * Year:		Fall 2013
 * Assignment:	Hash Table Assignment
 */

package hashtable;

import java.util.Random;
import java.util.Date;

/**
 * Demonstration of the reduction of time required to perform operations in a hash table as the hash table size increases
 * 
 * In this program we generate NUM_TO_GENERATE number of randomized integers between 1 and NUM_TO_GENERATE
 * 
 * We then iterate NUMBER_OF_ITERATIONS times
 * 
 * We start with a size 1 hash table, and then with each iteration, double the hash table size
 * 
 * Each iteration, we record the time it takes for each add and remove operation into an array
 * 
 * Then, we average the results of each operation over all the iterations, and then print out the results
 * 
 * @author Zach Graham
 *
 */
public class HashTableTest {
	
	private static final int NUM_TO_GENERATE = 50000;			// The number of random numbers we generate for testing (takes between 5-6 seconds on my PC)
	
	private static final int HASHTABLE_INITIAL_CAPACITY = 1;	// We start with a size 1 hash table
	
	private static final int HASHTABLE_ENDING_CAPACITY = 5;		// We double the hash table size this number of times
	
	private static final int NUMBER_OF_ITERATIONS = 5;			// The number of times times we test each hash table size and then average the results
	
	HashTable hashTable;										// The hash table
	
	int hashTableSize;											// Keeps track of the number of items in the hash table
	
	
	int[] randomizedArray;										// Array for storing the randomized numbers
	
	long[] timerAddTotal;										// Array of times for the add test
	long[] timerRemoveTotal;									// Array of times for the remove test
	
	/**
	 * Default constructor 
	 * 
	 * Executes the hash table test
	 */
	HashTableTest() {
		
		hashTableSize = HASHTABLE_INITIAL_CAPACITY;			
		
		hashTable = new HashTable(hashTableSize);				// Instantiate new hash table with initial capacity
		
		int[] intArray = new int[NUM_TO_GENERATE];				// Instantiate a new array of ints with capacity NUM_TO_GENERATE
		
		for (int i = 0; i < NUM_TO_GENERATE; i++) {				// Populate the array from 1 to NUM_TO_GENERATE
			
			intArray[i] = i+1; 
		}
		
		randomizedArray = shuffleArray(intArray);				// Now randomize the array
		
		
		System.out.printf("-- Hash Table Test --%n%n");
		
		System.out.printf("Generated a set of %d random integers for inserting and removing%n%n"
						+ "In this test we iterate %d times and record the time taken to perform each test, and then average the results%n%n"
						+ "We start with a hash table size of %d, and double the size each iteration%n%n" 
						+ "Notice how the operation (add, remove) time decreases as we increase the hash table size%n%n%n",
						NUM_TO_GENERATE, NUMBER_OF_ITERATIONS, HASHTABLE_INITIAL_CAPACITY);
		
		System.out.printf("- Beginning -%n%n%n");
		
		int iterationsNum = NUMBER_OF_ITERATIONS * HASHTABLE_ENDING_CAPACITY;			// Total number of iterations
		
		timerAddTotal = new long[NUMBER_OF_ITERATIONS];									// Create arrays of longs to store the times for each iteration
		timerRemoveTotal = new long[NUMBER_OF_ITERATIONS];
		
		for (int iteration = 0; iteration < NUMBER_OF_ITERATIONS; iteration++) {		// Loop NUMBER_OF_ITERATIONS times
			
			System.out.printf("Iteration #%d%n-------------%n%n", iteration + 1);
			
			for (int tableSize = HASHTABLE_INITIAL_CAPACITY; tableSize <= HASHTABLE_ENDING_CAPACITY; tableSize++) {
				
				addRemoveIteration(tableSize - 1);										// Perform an add/remove sequence
				
				if (tableSize != HASHTABLE_ENDING_CAPACITY) {			
					
					hashTableSize = hashTableSize * 2;									// Double the hash table size
					
					System.out.printf(" --- Now increasing the hash table size to: %d%n%n", hashTableSize);
					
					hashTable = new HashTable(hashTableSize);
				}
				
			}
			
			hashTable = new HashTable(HASHTABLE_INITIAL_CAPACITY);						// Reset hash table size to initial capacity for the next iteration
			hashTableSize = HASHTABLE_INITIAL_CAPACITY;
			
		}
		
		System.out.printf("-- Finished Hash Table Test --%n%n%n-- Test Results --%n%n");
		
		int hashTableSize = HASHTABLE_INITIAL_CAPACITY;
		
		for (int i = 0; i < timerAddTotal.length; i++) {								// Now output the timing results
	
			System.out.printf(" Hash Table Size %d%n--------------------%n" +
							  "- Add Time Average:\t%dms%n- Remove Time Average:\t%dms%n%n" +
							  "",
							  hashTableSize, (timerAddTotal[i] / NUMBER_OF_ITERATIONS), (timerRemoveTotal[i] / NUMBER_OF_ITERATIONS));
			
			hashTableSize *= 2;
		}
		
		System.out.printf("- Finished - ");
	}
	
	/**
	 * Performs an iteration of add/remove operations
	 * 
	 * @param iterationNum the iteration number we are at
	 */
	private void addRemoveIteration(int iterationNum) {
		
		long startTime, stopTime, difference;
		
		System.out.printf(" --- Adding %d integers to the hash table with initial capacity: %d%n%n", 
				  NUM_TO_GENERATE, hashTableSize);
		
		startTime = new Date().getTime();
		
		for (int j = 0; j < randomizedArray.length; j++) {			// Now we add all of the items in the random array to the hash table
			
			hashTable.add(randomizedArray[j]);
		}
		stopTime = new Date().getTime();
		
		difference = stopTime - startTime;
		
		long addTime = difference;

		timerAddTotal[iterationNum] += addTime;
		
		System.out.printf(" --- Removing %d integers from the hash table with initial capacity: %d%n%n", 
						  NUM_TO_GENERATE, hashTableSize);
		
		startTime = new Date().getTime();
		
		for (int j = 0; j < hashTable.size(); j++) {				// Now we remove them all from the hash table in random order
			
			hashTable.remove(randomizedArray[j]);
		}
		stopTime = new Date().getTime();
		
		difference = stopTime - startTime;
		
		long removeTime = difference;
		
		timerRemoveTotal[iterationNum] += removeTime;
		
		System.out.printf("      --- The add operation took %d milliseconds%n%n", addTime);
		System.out.printf("      --- The remove operation took %d milliseconds%n%n", removeTime);
	}
		
		
	
	/**
	 * Returns a shuffled (randomized) copy of the parameter array
	 * 
	 * @param shuffleArray the array to be shuffled
	 * 
	 * @return shuffled int array
	 */
	private int[] shuffleArray(int[] shuffleArray)
	{
		Random randomInt = new Random();
		
		int[] returnArray = new int[shuffleArray.length];
		
		//First we copy everything into returnArray
		
		for (int i = 0; i < shuffleArray.length; i++)
		{
			returnArray[i] = shuffleArray[i];
		}
		
		/*
		 * Now we randomize the order of returnArray by looping through and swapping the value 
		 * at each index in returnArray with the value at a random index in the same array
		 */
		for (int i = 0; i < returnArray.length; i++)
		{
			int randomPosition = randomInt.nextInt(returnArray.length);
			int temp = returnArray[i];
			returnArray[i] = returnArray[randomPosition];
			returnArray[randomPosition] = temp;
	    }
		return returnArray;
	}	
	
	/**
	 * Instantiates a new hash table test object
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		HashTableTest hashTest = new HashTableTest();
	}

} // End class HashTableTest
