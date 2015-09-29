// Jessica Li
// 09/28/15


public class HashTable
{
	private Object[] htable; 
	private double population = 0.0;
	private final double loadFactor = 0.6;


	/*
	 * Default constructor. Initializes to capacity 100.
	 */
	public HashTable()
	{
		htable = new Object[100];
	}

	/*
	 * Initializes size of array to capacity.
	 */
	public HashTable( int capacity )
	{
		htable = new Object[capacity];
	}

	/*
	 * Puts the object in the hashtable. Deals with collisions by placing the object using quadratic system, I forgot the name. 
	 * Always takes the absolute value of the hashcode. 
	 * Rehashes table is load factor is exceeded. 
	 */
	public void put( Object obj )
	{
		int code = Math.abs( obj.hashCode() );								// how to deal with negatives?
		code = code % ( htable.length + 1 );
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

	/*
	 * Returns String representation of the HashTable.
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

	/*
	 * Doubles the size of the HashTable and rehashes each item contained within. 
	 * Created new array of double size, assigns to htable. Temporary array stores values of old array for rehashing into new array.
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

	/*
	 * Main method for testing program. Creats a HashTable object and puts in the same string 10 times. Prints the table 10 times.
	 */
	public static void main( String[] args )
	{
		HashTable ht = new HashTable();

		String s = "Jessica";						//why does this give a negative hashCode value?
		//System.out.println( s.hashCode() );

		for ( int i = 0; i < 10; i++ )
		{
			ht.put( s );
			System.out.println( ht.toString() );
			System.out.println();
		}

		
	}

}