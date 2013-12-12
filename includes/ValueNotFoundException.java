/*
 * Name:		Zach Graham
 * Class:		CS 341
 * Year:		Fall 2013
 * Assignment:	Hash Table Assignment
 */

package hashtable.includes;

public class ValueNotFoundException extends RuntimeException
{
	public ValueNotFoundException()
	{
		super("Value not found in the list");
	}
}
