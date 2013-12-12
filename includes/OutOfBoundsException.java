/*
 * Name:		Zach Graham
 * Class:		CS 341
 * Year:		Fall 2013
 * Assignment:	Hash Table Assignment
 */

package hashtable.includes;

public class OutOfBoundsException extends RuntimeException 
{
	public OutOfBoundsException()
	{
		super("Target index not found");
	}
}
