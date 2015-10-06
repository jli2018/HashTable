/**
 * My (almost) Final HashTable class. Without Quadratic Probing - to be added.
 * 
 * @author Jessica Li
 * @version 1 -- 10/02/15
 */
public class HashTable<K,V>
{
	private Entry[] htable; 
	private double population = 0.0;
	private final double loadFactor = 0.6;

AAAAHHHHHHH CHANGES HAVE BEEN MADE


	/**
	 * Default constructor. Initializes to capacity 100.
	 */
	public HashTable()
	{
		htable = new Entry[100];
	}

	/**
	 * Constructor. Initializes size of array to capacity.
	 * 
	 * @param capacity	determines the capacity of the HashTable array htable
	 */
	public HashTable( int capacity )
	{
		htable = new Entry[capacity];
	}

	/**
	 * Puts Entry into hashtable. 
	 * Increments the population, and if percentage of array filled exceeds loadFactor, rehashes the table. 
	 * Always takes the absolute value of the hashcode, since sometimes the hashcode is negative. 
	 * Deals with collisions by placing the object in the next open spot. 
	 * 
	 * 
	 * @param key	the key of the entry, give the hash value
	 * @param value the value associated with the key, to be stored in the spot that key codes for
	 */
	public void put( K key, V value )
	{

		population++;

		if ( (population/htable.length) > loadFactor )
		{
			rehash();
		}


		int code = Math.abs( key.hashCode() );			// how to deal with negatives?
		code = code % ( htable.length );
		//System.out.println( code );
		//int quad = 1; 
		while ( htable[code] != null )
		{
			code++;
			code = code % htable.length;			//in case incrementing code makes it go over the length of the table
			//code = code + quad;
			//quad *= 2; 
		}

		Entry ee = new Entry( key, value );
		htable[code] = ee;		
	}

	/**
	 * Returns String representation of the HashTable. Adds "key: value" if spot in array is not null, 
	 * otherwise adds null. Adds these strings to an overall string toReturn and returns toReturn.
	 * 
	 * @return	a string representing the contents of the htable array
	 */
	public String toString()
	{
		String toReturn = "";
		for ( int i = 0; i < htable.length; i++ )
		{
			if ( htable[i] != null )
				toReturn += htable[i].key + ": " + htable[i].value + "\t";					//can i do this? assume they are strings?
			else
				toReturn += "null" + "\t";
		}

		return toReturn;
	}

	/**
	 * Doubles the length of the HashTable and rehashes each item contained within. 
	 * Creates new array htable2 of double length, assigns to htable. 
	 * Temporary array tempTable stores values of old htable array for rehashing into new htable array.
	 * Uses the put() method to rehash the Entries. 
	 */
	private void rehash()
	{
		Entry[] htable2 = new Entry[ htable.length * 2 ];
		Entry[] tempTable = htable;
		htable = htable2;
		for ( int i = 0; i < tempTable.length; i++ )
		{
			if ( tempTable[i] != null )
			{
				put( (K) tempTable[i].key, (V) tempTable[i].value );			//typecasting because the key and value are of type Object?
			}
		}
	}

	/**
	 * Removes the Entry with the corresponding key and returns its value. 
	 * Returns null if the key does not exist in the table.
	 * Uses loop to search through array, and if key matches, stores the value,
	 * sets the spot in array to null, and returns the stored value. 
	 *
	 * @param key	the key of the Entry to be located and removed
	 * @return 		the value of the Entry with the corresponding key; if no Entries match, returns null
	 */
	public V remove( K key )
	{

		for ( int i = 0; i < htable.length; i++ )
		{
			if ( htable[i] != null && htable[i].key.equals( key ) )
			{
				V v = (V) htable[i].value;			//typecasting 
				htable[i] = null;
				return v;		
			}
		}
		return null;
	}

	/**
	 * Returns the value that corresponds to key. Returns null if the key does not exist in the table.
	 * Exactly the same code as in the remove() method, only without setting the location to null.
	 * I wanted to not repeat the same code, but couldn't think of how to do so. 
	 * 
	 * @param key 	the key for the value to be returned
	 * @return 		the value of the Entry with the corresponding key; if no Entries match, returns null
	 */
	public V get( K key )
	{
		for ( int i = 0; i < htable.length; i++ )
		{
			if ( htable[i] != null && htable[i].key.equals( key ) )
			{
				V v = (V) htable[i].value;			//typecasting 
				return v;		
			}
		}
		return null;
	}

	/**
	 * Returns whether or not key exists in the table. Uses get() method. 
	 * If get() returns a value, this method returns true. If get() returns null, this method returns false.
	 * Should I use get()? Doesn't it give the computer more work even if I repeat less code? 
	 *
	 * @param key 	the key to be found
	 * @return 		true if key exists in table, false if key does not exist in table
	 */
	public boolean containsKey( K key )
	{
		if ( get(key) != null )
		{
			return true;
		}
		return false;
	}

	/**
	 * Returns whether or not value exists in the table.
	 * Searches table using for loop. If Entry is not null and its value matches, returns true. 
	 * Otherwise, returns false. 
	 * 
	 * @param value the value to be found
	 * @return 		true if value exists in table, false if value does not exist in table
	 */
	public boolean containsValue( V value )
	{
		for ( int i = 0; i < htable.length; i++ )
		{
			if ( htable[i] != null && htable[i].value.equals( value ) )
			{
				return true;		
			}
		}
		return false;
	}

	/**
	 * Main method for testing program. Creats a HashTable object with array of length 3, creates key and value for Entry. 
	 * Uses loop to put key and value in array 3 times. Prints the HashTable each time. Tests rehash().
	 * Adds three more entries/keys and values. Prints table.
	 * Tests remove() and prints out HashTable again. 
	 * Tests get(), containsKey(), containsValue(), each with one successful and one null case. 
	 * 
	 * @param args	main method parameter
	 */
	public static void main( String[] args )
	{
		HashTable<String, String> ht = new HashTable<String, String>( 3 );

		String k = "apple";							//when does this give a negative hashCode value?
		String v = "red fruit";
		//System.out.println( k.hashCode() );			//prints out hashcode value for String k

		for ( int i = 0; i < 3; i++ )
		{
			ht.put( k, v );
			System.out.println( ht.toString() );
			System.out.println();
		}

		ht.put( "pomme", "un fruit rouge" );
		ht.put( "a", "b" );
		ht.put( "x", "y" );
		System.out.println( ht.toString() );
		System.out.println();


		System.out.println( "remove() method: " + ht.remove( "apple" ) );
		System.out.println( ht.toString() );
		System.out.println();
		System.out.println( "get() method: " + ht.get( k ) );
		System.out.println( "containsKey() method: " + ht.containsKey( k ) );
		System.out.println( "containsValue() method: " + ht.containsValue( v ) );
		System.out.println( "get() method: " + ht.get( "pear" ) );
		System.out.println( "containsKey() method: " + ht.containsKey( "pear" ) );
		System.out.println( "containsValue() method: " + ht.containsValue( "not an apple" ) );

	}


	/**
	 * Nested class used to hold key-value pairings. Has one contructor to initialize key and value. 
	 * Because key and value are public, accessors are not needed to make these fields accessible to the HashTable class. 
	 */
	private class Entry<K,V>
	{
		public K key;
		public V value;

		/**
		 * Contructor for Entry objects. Initializes key and value fields. 
		 * 
		 * @param k 	passed to key
		 * @param v 	passed to value
		 */
		public Entry( K k, V v )
		{
			key = k;
			value = v;
		}
	}

}
