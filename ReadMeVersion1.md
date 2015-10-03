#Hash Table Project

For this project, you will be creating a simple HashTable object.

##Commit Schedule
Version 0: 10/01/15 8am </br>
Version 1 draft: 10/05/15 8am </br>
Version 1 final: 10/07/15 8am 

##HashTable\<K,V> API - Version 1
This version of HashTable builds on Version 0 and adds generics as well as a few additional methods.

###HashTable()
Default constructor. Initializes to capacity 100 (or an appropriate prime number if you're using quadratic probing).

###HashTable(int capacity)

###public void put(K key, V value)
Puts a key-value entry in the hashtable. Deals with collisions by placing the object in the next open spot (OR by using quadratic probing). 

###public String toString()
String representation of the HashTable.

###private void rehash()
Doubles the size of the HashTable and rehashes each item contained within. Should be called anytime calling the put function makes the current fill of the HashTable exceed the load factor.

###public V remove(K key)
Removes the Entry with the corresponding key and returns its value. Returns null if the key does not exist in the table.

###public V get(K key)
Returns the value that corresponds to key. Returns null if the key does not exist in the table.

###public boolean containsKey(K key)
Returns whether or not key exists in the table.

###public boolean containsValue(V value)
Returns whether or not value exists in the table.

###private class Entry\<K,V>
Nested class used to hold key-value pairings. Should have appropriate constructors and accessors as necessary.





