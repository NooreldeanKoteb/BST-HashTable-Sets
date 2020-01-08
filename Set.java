// for addAll() only
import java.util.Collection;

// A Set using HashTable.
/**
 * @author Nooreldean Koteb
 * @version 1.0
 */
class Set<T extends Comparable<T>>{

	//-------------------------------------------------------------
	// DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
	//-------------------------------------------------------------
	/**
	 * This field initializes the storage hash table
	 */
	private HashTable<T> storage = new HashTable<>(5);

 	// Return the number of values in set.
	/**
	 * This method returns size of hash table
	 * @return int size
	 */
	public int size() {
		return storage.size();
	}
	
	// If value not present in set, add value and return true.
	// Otherwise, do not add and return false.
	/**
	 * This method adds a value to the hash table
	 * @param value to add
	 * @return boolean true if add was successful
	 */
	public boolean add(T value){
		return storage.add(value);
	}

	// Return true if value in set, false otherwise.
	/**
	 * This method checks if a value is in the hash table
	 * @param value to find
	 * @return boolean true if item is in hash table
	 */
	public boolean contains(T value){
		return storage.contains(value);
	}

	// If value present in set, remove value and return true.
	// Otherwise return false and no change to set.
	/**
	 * This method removes a value from the hash table
	 * @param value to remove
	 * @return boolean true if item was removed from hash table
	 */
	public boolean remove(T value){
		return storage.remove(value);
	}
	/**
	 * This method returns a string of hash table values
	 * @return String of hash table values
	 */
	public String toString(){
		return storage.toString();
	}
	/**
	 * This method returns an object array of hash table values
	 * @return Object array of hash table values
	 */
	public Object[] toArray(){
		return storage.toArray();
	}

	//-------------------------------------------------------------
	// END OF PROVIDED "DO NOT EDIT" SECTION 
	//-------------------------------------------------------------

	//-------------------------------------------------------------
	// You can NOT add any instance/static variables in this class.
	// You can add methods if needed but they must be PRIVATE.
	//-------------------------------------------------------------
 	
	
	// Accept a collection of values and add them into set 
	// one by one.  Follow the order as provided by the 
	// .iterator() of the collection to add.
	// Return the number of values successfully added.
	/**
	 * This method adds all values to a set
	 * @param c collection of values
	 * @return int of values added successfully
	 */
	public int addAll(Collection<T> c){	
		int setSize = 0;
		for(T value : c) {
			if(add(value)) {
				setSize += 1;
			}
		}
		return setSize;
	}
			
	// Construct and return the intersection set of this and other.
	// Intersection set should include all values 
	// that are in both this set and other.
	// Original sets should not be modified.
	/**
	 * This method adds all values only in both sets
	 * @param other Set of values
	 * @return Set of values added successfully
	 */
	@SuppressWarnings("unchecked")
	public Set<T> intersection(Set<T> other){
		Set<T> allValues = new Set<T>();
		Object[] hashArray = new Object[size()];
		hashArray = this.storage.toArray();
		
		for(int i = 0; i <size(); i++ ) {
			if(other.contains((T)hashArray[i])) {
				allValues.add((T) hashArray[i]);
			}
		}
		
		return allValues;
	}
	
	// Construct and return the union set of this and other.
	// Union set should include all values 
	// that belongs to at least one of this set and other.
	// Original sets should not be modified.
	/**
	 * This method adds all values from both sets
	 * @param other Set of values
	 * @return Set of values added successfully
	 */
	@SuppressWarnings("unchecked")
	public Set<T> union(Set<T> other){
		Set<T> allValues = new Set<T>();
		Object[] hashArray = new Object[size()];
		hashArray = this.storage.toArray();
		
		Object[] otherValues = new Object[other.size()];
		otherValues = other.toArray();
		
		for(int i = 0; i <size(); i++ ) {
			allValues.add((T) hashArray[i]);
		}
		
		for(int i = 0; i <other.size(); i++ ) {
			allValues.add((T) otherValues[i]);
		}
		return allValues;
	}

	// Construct and return the difference set: this - other.
	// Result should include all values in this set
	// but not in other.
	// Original sets should not be modified.
	/**
	 * This method adds all values not in both sets and not in other
	 * @param other Set of values
	 * @return Set of values added successfully
	 */
	@SuppressWarnings("unchecked")
	public Set<T> difference(Set<T> other){
		Set<T> allValues = new Set<T>();
		Object[] hashArray = new Object[size()];
		hashArray = this.storage.toArray();
		
		for(int i = 0; i <size(); i++ ) {
			if(other.contains((T)hashArray[i])) {
				continue;
			}else {
				allValues.add((T) hashArray[i]);
			}
		}

		return allValues;
	}

	// Construct and return the symmetric difference set.
	// Result should include all values in exactly
	// one set of this set and other but not both.
	// Original sets should not be modified.
	/**
	 * This method adds all values not in both sets
	 * @param other Set of values
	 * @return Set of values added successfully
	 */
	@SuppressWarnings("unchecked")
	public Set<T> symmetricDifference(Set<T> other){
		Set<T> allValues = new Set<T>();
		Object[] hashArray = new Object[size()];
		hashArray = this.storage.toArray();
		
		Object[] otherValues = new Object[other.size()];
		otherValues = this.storage.toArray();
		
		for(int i = 0; i <size(); i++ ) {
			if(other.contains((T)hashArray[i])) {
				continue;
			}else {
				allValues.add((T) hashArray[i]);
			}
		}
		
		for(int i = 0; i <other.size(); i++ ) {
			if(this.storage.contains((T)otherValues[i])) {
				continue;
			}else {
				allValues.add((T) otherValues[i]);
			}
		}
		
		return allValues;
	}

	// Return true if this set is a subset of other,
	// return false otherwise.
	/**
	 * This method checks that all values in this are in other
	 * @param other Set of values
	 * @return boolean true if all values in this are in other
	 */
	@SuppressWarnings("unchecked")
	public boolean isSubset(Set<T> other){
		Object[] hashArray = new Object[size()];
		hashArray = this.storage.toArray();
		
		for(int i = 0; i <size(); i++ ) {
			if(other.contains((T)hashArray[i])) {
				continue;
			}else {
				return false;
			}
		}
		return true;
	}
	
	// Return true if there is no overlap between
	// this set and other; return false otherwise.
	/**
	 * This method checks that none of the values in this are in other
	 * @param other Set of values
	 * @return boolean true if none of the values in this are in other
	 */
	@SuppressWarnings("unchecked")
	public boolean isDisjoint(Set<T> other){
		Object[] hashArray = new Object[size()];
		hashArray = this.storage.toArray();
		
		for(int i = 0; i <size(); i++ ) {
			if(other.contains((T)hashArray[i])) {
				return false;
			}
		}
		return true;
	}
	
	
	//-------------------------------------------------------------
	// Main Method For Your Testing -- Edit all you want
	//-------------------------------------------------------------

	public static void main(String args[]){
		// arrays only used for testing here
		Integer[] data1 = { 1, 2, 3,   5,    7 };
		Integer[] data2 = {    2,   4, 5, 6    };
		String[] duplicate = {"a","a","a","b","c","b","c","d","a","e","c","b"};
		Set<Integer> set1 = new Set<>();
		Set<Integer> set2 = new Set<>();
		Set<String> noduplicate = new Set<>();
		
		//addAll
		if (set1.addAll(java.util.Arrays.asList(data1))==5 &&
			set2.addAll(java.util.Arrays.asList(data2))==4 &&
			noduplicate.addAll(java.util.Arrays.asList(duplicate)) == 5){
			System.out.println("Yay 1");
		}
		
		//System.out.println(set1);
		//System.out.println(set2);
		
		Set<Integer> set3 = set1.intersection(set2);
		//System.out.println(set3);  //should have 2 and 5 only
		if (set3.contains(2) && set3.contains(5) && !set3.contains(1) && set3.size()==2){
			System.out.println("Yay 2");
		}
		
		Set<Integer> set4 = set1.union(set2);
		//System.out.println(set4);  //should have 1,2,3,4,5,6,7 (not necessarily in that order)
		boolean ok = true;
		for (int i=1;i<8;i++){
			ok = ok && set4.contains(i);
		}
		if (ok && set4.size()==7){
			System.out.println("Yay 3");		
		}
		
		Set<Integer> set5 = set1.difference(set2);
		//System.out.println(set5);  //should have 1,3,7 (unordered)
		if (set5.size()==3 && set5.contains(1) && set5.contains(3) && set5.contains(7) 
			&& !set5.contains(2) && !set5.contains(5) && !set5.contains(4)){
			System.out.println("Yay 4");
		}

		//Set<Integer> set6 = set1.symmetricDifference(set2);
		//System.out.println(set6);  // should have 1,3,4,6,7 (unordered)
		
		if (!set1.isSubset(set2) && set3.isSubset(set2) 
			&& !set1.isDisjoint(set2) && set5.isDisjoint(set2)){
			System.out.println("Yay 5");			
		}
	
	}
	
}