/**
 * My Primitive HashTable class.
 * 
 * @author Jessica Li
 * @version 0, 09/30/15
 */
public class HashTable<K,V>
{
	private Entry[] htable; 
	private double population = 0.0;
	private final double loadFactor = 0.6;


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
	 * Puts the object in the hashtable. Deals with collisions by placing the object using quadratic probing, 
	 * but not with prime numbers?
	 * Always takes the absolute value of the hashcode. 
	 * Rehashes table is load factor is exceeded. Load factor is compared with population of array / capacity of array. 
	 * 
	 * @param obj	the object to be hashed
	 */
	public void put( K key, V value )
	{
		



		population++;

		if ( (population/htable.length) > loadFactor )
		{
			rehash();
		}



		int code = Math.abs( key.hashCode() );				// how to deal with negatives?
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
		//htable[code] = obj;
		


		
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
			if ( htable[i] != null )
				toReturn += htable[i].key + ": " + htable[i].value + "\t";					//can i do this? assume they are strings?
			else
				toReturn += "null" + "\t";
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
		Entry[] htable2 = new Entry[ htable.length * 2 ];
		Entry[] tempTable = htable;
		htable = htable2;
		for ( int i = 0; i < tempTable.length; i++ )
		{
			if ( tempTable[i] != null )
			{
				put( (K) tempTable[i].key, (V) tempTable[i].value );			//typecasting
			}
		}

	}

	public V remove( K key )
	{

		for ( int i = 0; i < htable.length; i++ )
		{
			if ( htable[i] != null && htable[i].key.equals( key ) )
			{
				V v = (V) htable[i].value;
				htable[i] = null;
				return v;			//typecasting
			}
		}
		return null;
	}

	/**
	 * Main method for testing program. Creats a HashTable object and uses loop to put in the same string 10 times 
	 * and print the table 10 times.
	 * 
	 * @param args	main method parameter
	 */
	public static void main( String[] args )
	{
		HashTable<String, String> ht = new HashTable<String, String>( 3 );

		String k = "apple";							//when does this give a negative hashCode value?
		String v = "red fruit";
		//System.out.println( s.hashCode() );			//prints out hashcode value for String s

		for ( int i = 0; i < 3; i++ )
		{
			ht.put( k, v );
			System.out.println( ht.toString() );
			System.out.println();
		}

		System.out.println( ht.remove( "apple" ) );
		System.out.println( ht.toString() );
		System.out.println();

	}


	//class for entry inside class for Hashtable??
	//can still access because it is in the Hashtable class
	private class Entry<K,V>
	{
		
		public K key;
		public V value;

		public Entry( K k, V v )
		{
			key = k;
			value = v;
		}

	}



}
