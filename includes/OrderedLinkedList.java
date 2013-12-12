/*
 * Name:		Zach Graham
 * Class:		CS 341
 * Year:		Fall 2013
 * Assignment:	Hash Table Assignment
 */

package hashtable.includes;

import hashtable.includes.OutOfBoundsException;
import hashtable.includes.ValueNotFoundException;

/**
 * Integer ordered linked list class
 * 
 * Arranges input integer nodes into sorted ascending order (smallest to largest)
 * 
 * @author Zach Graham
 *
 */
public class OrderedLinkedList
{
	private int count; // Number of nodes in the list
	private Node first, last;
	
	/**
	 * Default constructor. Creates an empty list
	 */
	public OrderedLinkedList()
	{
		count = 0;
		first = null;
		last = null;
	}
	
	/**
	 * Adds the specified integer value to the ordered list
	 * 
	 * @param intKey the value to insert into the list
	 */
	public void add(int intKey)
	{
		Node traverseNode, previousNode = first;
		Node newNode = new Node(intKey);
		
		// If the list is empty, sets the first and last in the list references to the new node
		if(isEmpty())
		{
			first = newNode;
			last = newNode;
		}
			// If the new node's key is greater than the last node in the list's key, add it after the last node
			// And then set the last node's reference to the new node
			else if (newNode.getKey() > last.getKey())
			{
				last.setNext(newNode);
				newNode.setNext(null);
				last = newNode;
			}
			
			// Else if the new node's key is less than the first's key place the new node before the first one
			// And then set the new node's next reference to the originally first node and the new node becomes the new first node
			else if (newNode.getKey() < first.getKey())
			{
				newNode.setNext(first);
				first = newNode;
			}
		// Else, traverse the list until a value is found that is greater than the key in newNode
		// Set the traverse node's next reference to newNode, and set newNode's next reference to the one after traverseNode
		// Basically newNode is inserted in between the two
		else
		{
			traverseNode = previousNode.getNext();
			
			while (newNode.getKey() > traverseNode.getKey())
			{
				traverseNode = traverseNode.getNext();
				previousNode = previousNode.getNext();
			}
			newNode.setNext(traverseNode);
			previousNode.setNext(newNode);
		}
		count++; // Increment number of items in the list
		
	}
	
	/**
	 * Removes the specified integer from the list
	 * 
	 * @param targetInt the int to be removed
	 */
	public void remove(int targetInt)
						throws ValueNotFoundException
	{
		boolean found = false;
		
		Node previous = first;
		Node temp = first;
		
		// Walk the list until the target is found
		while (temp != null && !found)
		{	
			if (temp.getKey() == targetInt)
			{
				// If the target is located at the first index, set the reference to first to the node right after first
				if (findIndexed(0) == targetInt)
				{
					first = first.getNext();
					
				}
				found = true;
				previous.setNext(temp.getNext());
				count--; // Decrement count of items in the list
			}
			else
			{
				previous = temp; // Else continue walking the list, storing each next node in temp
				temp = temp.getNext();
			}
		}
		
		if (!found)
			throw new ValueNotFoundException();
	}
	
	
	/**
	 * Returns true if the list is empty
	 * 
	 * @return true if list empty
	 */
	public boolean isEmpty()
	{
		return (count == 0);
	}
	
	/**
	 * Traverses the list, assigning an index value to each node, starting with 0
	 * Returns the key value of the node at the target index
	 * 
	 * @param targetIndex the index to search for
	 * @return true if index found
	 */
	public int findIndexed(int targetIndex)
	{
		int counter = 0;
		
		boolean found = false;
		
		Node temp = first;
		
		// If the target index is greater than the number of items in the list, throw an exception
		if (targetIndex > count || targetIndex < 0)
		{
			throw new OutOfBoundsException();
		}
		
		// While the counter is less than or equal to the target and not found, loop
		while (counter <= targetIndex && !found)
		{
			if (counter == targetIndex) // If the counter is the same as the target index, return
			{
				found = true;
				return temp.getKey();
			}
			else
			{
				temp = temp.getNext();
			}
			counter++;
		}
		
		if (!found)
		{
			throw new OutOfBoundsException();
		}
		
		return -1; // Shouldn't ever reach this
		
	}
	
	/**
	 * Traverses the list for the first occurrence of the target key and returns true if found
	 * 
	 * @param targetKey the target value to search for
	 * @return true if found
	 */
	public boolean findKeyed(int targetKey)
	{
		boolean found = false;
		
		Node temp = first;
		
		// Loop through the linked list until the key is found, or not
		while(temp != null && !found)
		{
			if (temp.getKey() == targetKey)
			{
				found = true;
			}
			else
			{
				temp = temp.getNext();
			}
		}
		
		return found; // Returns found or not
	}
	
	/**
	 * Returns the number of elements in the list
	 * 
	 * @return count of list elements
	 */
	public int size()
	{
		return count;
	}
	
	/**
	 * Returns a string representation of the linked list
	 * 
	 * @return String the linked list in string form
	 */
	public String toString()
	{
		String returnString = "";
		
		Node temp = first;
		
		int counter = 0;
		
		//returnString += String.format("List Contains %d Items:%n", count);
		
		while (temp != null)
		{
			returnString += String.format("Key: [%d]%n", temp.getKey());
			temp = temp.getNext();
			counter++;
		}
		return returnString;
	}

	
	/**
	 * Represents a node in a linked list
	 * Accepts an integer as the node's key value
	 * 
	 * @author Zach Graham
	 *
	 */
	private class Node
	{
		private Node next;
		private int intKey;
		
		/**
		 * Constructor that accepts an integer to store in the node
		 * 
		 * @param key integer to store in the node
		 */
		Node(int key)
		{
			next = null;
			intKey = key;
		}
		
		/**
		 * Returns the next node after this one
		 * 
		 * @return the next node
		 */
		public Node getNext()
		{
			return next;
		}
		
		/**
		 * Sets the next node after this one
		 * 
		 * @param node the node to be set as the one following this one
		 */
		public void setNext(Node node)
		{
			next = node;
		}

		/**
		 * Returns the value, or key, stored within this node
		 * 
		 * @return the integer key stored within the node
		 */
		public int getKey()
		{
			return intKey;
		}
		
		/**
		 * Sets this key's value to the one specified
		 * 
		 * @param key the value to assign to this node's key
		 */
		public void setKey(int key)
		{
			intKey = key;
		}
		
	} // End class Node
	
} // End class IntegerOrderedLinkedList