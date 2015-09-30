/**
 * My Primitive HashTable class.
 * 
 * @author Jessica Li
 * @version 0, 09/30/15
 */
public class HashTable
{
	private Object[] htable; 
	private double population = 0.0;
	private final double loadFactor = 0.6;


	/**
	 * Default constructor. Initializes to capacity 100.
	 */
	public HashTable()
	{
		htable = new Object[100];
	}

	/**
	 * Constructor. Initializes size of array to capacity.
	 * 
	 * @param capacity	determines the capacity of the HashTable array htable
	 */
	public HashTable( int capacity )
	{
		htable = new Object[capacity];
	}

	/**
	 * Puts the object in the hashtable. Deals with collisions by placing the object using quadratic probing, 
	 * but not with prime numbers?
	 * Always takes the absolute value of the hashcode. 
	 * Rehashes table is load factor is exceeded. Load factor is compared with population of array / capacity of array. 
	 * 
	 * @param obj	the object to be hashed
	 */
	public void put( Object obj )
	{
		int code = Math.abs( obj.hashCode() );				// how to deal with negatives?
		code = code % ( htable.length );
		int quad = 1; 
		while ( htable[code] != null )
		{
			code = code + quad;
			quad *= 2; 
		}

		htable[code] = obj;
		population++; 


		if ( (population/htable.length) > loadFactor )
		{
			rehash();
		}
	}

	/**
	 * Returns String representation of the HashTable.
	 * 
	 * @return	a string representing the contents of the htable array
	 */
	public String toString()
	{
		String toReturn = "";
		for ( int i = 0; i < htable.length; i++ )
		{
			toReturn += htable[i] + "\t" ;
		}

		return toReturn;
	}

	/**
	 * Doubles the size of the HashTable and rehashes each item contained within. 
	 * Created new array of double size, assigned to htable. 
	 * Temporary array tempTable stores values of old htable array for rehashing into new htable array.
	 */
	private void rehash()
	{
		Object[] htable2 = new Object[ htable.length * 2 ];
		Object[] tempTable = htable;
		htable = htable2;
		for ( int i = 0; i < tempTable.length; i++ )
		{
			if ( tempTable[i] != null )
			{
				put( tempTable[i] );
			}
		}

	}

	/**
	 * Main method for testing program. Creats a HashTable object and uses loop to put in the same string 10 times 
	 * and print the table 10 times.
	 * 
	 * @param args	main method parameter
	 */
	public static void main( String[] args )
	{
		HashTable ht = new HashTable();

		String s = "Jessica";					//why does this give a negative hashCode value?
		//System.out.println( s.hashCode() );			//prints out hashcode value for String s

		for ( int i = 0; i < 10; i++ )
		{
			ht.put( s );
			System.out.println( ht.toString() );
			System.out.println();
		}

		
	}

}
