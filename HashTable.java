// A hash table implemented with separate chaining.
// Every chain is organized as a binary search tree.

// imports for debugging only
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Random;
/**
 * @author Nooreldean Koteb
 * @version 1.0
 */
class HashTable<T extends Comparable<T>> {

	//-------------------------------------------------------------
	// DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
	//-------------------------------------------------------------
	/**
	 * This field variable holds the minimum hash Table length
	 */
	static private int minLength = 2;
	/**
	 * This field variable holds the size of the hash table
	 */
	private int size = 0;
	/**
	 * This field variable holds an array of BST
	 */
	private SimpleBST<T>[] storage;

	// Return the table length.
	/**
	 * This method returns hash table length
	 * @return int hash table length
	 */
	public int getLength() {
		return storage.length;
	}
	
	// Return the number of values in hash table.
	/**
	 * This method returns the size of hash table
	 * @return size of the hash table
	 */
	public int size() {
		return size;
	}

	// Return a string representation of all values in hash table.
	/**
	 * This method returns a string of the values in the hash table
	 * @return String of table values
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			if(storage[i] != null) {
				s.append(storage[i].toString());
			}
		}
		return s.toString().trim();
	}

	// Return a detailed string representation of the hash table.
	// Give the contents of each table entry.
	// Verbose mode: a detailed report of tree features for every entry.		
	/**
	 * This method returns hash table length
	 * @param verbose on or off
	 * @return String Debugged hash table format
	 */
	public String toStringDebug(boolean verbose) {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			if (storage[i]!=null && storage[i].size()!=0){
				s.append("[" + i + "]: " + storage[i].toString().trim() + "\n");
				if (verbose){
					s.append("\t tree size:" + storage[i].size() + "\n");
					s.append("\t tree height:" + storage[i].height() + "\n");
					s.append("\t number of leaves:" + storage[i].numLeaves() + "\n");
				}
			}
			else
				s.append("[" + i + "]: null\n");
			
		}
		return s.toString().trim();
	}

	//-------------------------------------------------------------
	// END OF PROVIDED "DO NOT EDIT" SECTION 
	//-------------------------------------------------------------

	//-------------------------------------------------------------
	// You can NOT add any instance/static variables in this class.
	// You can add methods if needed but they must be PRIVATE.
	//-------------------------------------------------------------
 	
	// Constructor.
	// Make sure the table is initialized with given length.
	// Use minLength to initialize table if given length goes below that.
	/**
	 * This constructor initializes the hash table
	 * @param length of hash table
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int length) {
		if(length < minLength) {
			//initialize using minLength
			storage = new SimpleBST[minLength];
		}else {
			//initialize with length
			storage = new SimpleBST[length];
		}
	}
	
	
	// Add value into hash table.
	// - Use separate chaining for collision.
	// - Return false if value cannot be added 
	//    (duplicate values or null values).
	// - Return true for a successful insertion.
	// - After adding a new value, if the table
	//   has a load>= 80%, rehash the table to twice
	//   the size.
	//
	// Worst case: O(N), Average case: O(load)
	//  - N as the number of values in hash table
	//  - not considering rehashing overhead
	/**
	 * This method adds a value to the hash table
	 * @param value to be added
	 * @return boolean true if addition to table was a success
	 */
	public boolean add(T value) {
		int index = 0;
		if (value == null) {
			return false;
		}
		
		if((value.hashCode()%getLength()) < 0) {
			index = (value.hashCode()%getLength()* -1);
		}else {
			index = (value.hashCode()%getLength());
		}
		
		if(this.storage[index] == null) {
			this.storage[index] = new SimpleBST<T>();
		}
		
		if(this.storage[index].insert(value) == true) {
			this.size += 1;
			
			if(((double)size()/(double)getLength()) >=0.8) {
				rehash(getLength()*2);
			}
			
			return true;
			
		}else {
			return false;
		}
	}
	
	// Check whether value is in hash table.
	//  - Return true is present, false otherwise
	// Worst case: O(N), Average case: O(load)
	//  - N as the number of values in hash table
	/**
	 * This method finds a value in the hash table
	 * @param value to be found
	 * @return boolean true if value was in the hash table
	 */
	public boolean contains(T value){
		int index = 0;
		
		if(value == null) {
			return false;
		}
		
		if((value.hashCode()%getLength()) < 0) {
			index = (value.hashCode()%getLength()* -1);
		}else {
			index = (value.hashCode()%getLength());
		}
		
		if(this.storage[index] == null) {
			return false;
		}
		
		return this.storage[index].contains(value);
	}
	
	// Remove and return true if value is in hash table.
	// Return false if value cannot be removed 
	//	(values not in tree or null values)  	
	// Worst case: O(N), Average case: O(load)
	//  - N as the number of values in hash table
	/**
	 * This method removes a value from the hash table
	 * @param value to be removed
	 * @return boolean true if removal from table was a success
	 */
	public boolean remove(T value) {
		int index = 0;
		
		if(value == null) {
			return false;
		}
		
		if((value.hashCode()%getLength()) < 0) {
			index = (value.hashCode()%getLength()* -1);
		}else {
			index = (value.hashCode()%getLength());
		}
		
		if(this.storage[index] == null) {
			return false;
		}
		
		if(this.storage[index].remove(value) == true) {
			this.size -=1;
			return true;
		}else {
			return false;
		}
	}
	
	// Rehash hash table to newLength.
	// - This can be used to increase or decrease the 
	//   capacity of the storage.  

	// - If the new capacity is smaller than minLength, 
	//   return false and do not rehash.
	
	// - If you are asked to rehash to a length such that 
	//   the load would be >= 0.8, double the length parameter 
	//   until the load would be < 0.8 and use that new length.

    // - If the new length has to go beyond Integer.MAX_VALUE 
    //   to satisfy the load requirement above, return false 
    //   and do not rehash.  
	
	// - Return true if you were able to rehash.

	// All values should be rehashed following this order:
	//   - Hash table entries should be rehashed based on array index
	//     in ascending order.
	//   - Multiple values in a chain (i.e. a binary search tree)
	//     should be rehashed in pre-order. 
	//     (Hint: consider .toArray() in SimpleBST class)

	// O(N+M): N as the number of values in hash table; 
	//         M as the table length.
	/**
	 * This method rehashes the hash table
	 * @param newLength hash table length
	 * @return boolean true if rehashing was successful
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int newLength) {
		Object[] temphashArray = new Object[size()];
		temphashArray = toArray();
		
		if(newLength < minLength) {
			return false;
		}
		if(newLength > Integer.MAX_VALUE) {
			return false;
		}
		
		storage = new SimpleBST[newLength];
		
		while(((double)size()/(double)getLength()) >= 0.8) {
				newLength = newLength*2;
				storage = new SimpleBST[newLength];
		}
		
		
		for (int i = 0; i<size(); i++) {
			add((T) temphashArray[i]);
			this.size -= 1;
		}
		
		return true;
	}

	// Return an array representation of all value in hash table.
	// The array should be assembled following this order:
	//   - Hash table entries should be checked based on array index
	//     in ascending order.
	//   - Multiple values in a chain (i.e. a binary search tree)
	//     should be rehashed in pre-order. 
	//     (Hint: consider .toArray() in SimpleBST class)
	// The array length should be the same as number of values in hash table.
	//
	// O(N+M): N as the number of values in hash table; 
	//         M as the table length.
	/**
	 * This method creates an array from the hash table
	 * @return Object array of hash table values
	 */
	public Object[] toArray(){
		Object[] hashArray = new Object[size()];
		int nextIndex = 0;
		
		for(int i = 0; i<getLength(); i++) {
			if(this.storage[i] == null) {
			}else {
				
				Object[] treeArray = new Object[this.storage[i].size];
				treeArray = this.storage[i].toArray();
				
				for(int j=0; j< this.storage[i].size; j++) {
					hashArray[nextIndex] = treeArray[j];
					nextIndex+=1;
				}
			}
		}
		return hashArray;
	}
	
	// Return the average tree height.
	// - If nonEmptyOnly is true, only consider non-empty trees;
	//   otherwise all trees are considered.
	// - Return -1.0 if all trees are empty but only non-empty 
	//   trees are considered
	/**
	 * This method gets the average tree height
	 * @param nonEmptyOnly true if nonEmptyOnly is on
	 * @return double avg tree height
	 */
	public double avgTreeHeight(boolean nonEmptyOnly){
		double nonEmptyTrees = 0.0;
		double height = 0.0;
		
		for(int i = 0; i<getLength(); i++) {
			if(this.storage[i] == null) {
				if(nonEmptyOnly == false) {
					height-=1;
				}
			}else {
				nonEmptyTrees+= 1;
				height += (double) this.storage[i].height();
				
			}
		}
		
		if(nonEmptyOnly == true) {
			if(nonEmptyTrees == 0.0) {
				return -1.0;//all are empty
			}

			return (height/nonEmptyTrees);
		}else {
			return (height/(double)getLength());
		}
	}

	// Return the average tree size.
	// - If nonEmptyOnly is true, only consider non-empty trees;
	//   otherwise all trees are considered.
	// - Return 0.0 if all trees are empty but only non-empty 
	//   trees are considered
	/**
	 * This method average tree size
	 * @param nonEmptyOnly true if nonEmptyOnly is on
	 * @return double avg tree size
	 */
	public double avgTreeSize(boolean nonEmptyOnly){
		double nonEmptyTrees = 0.0;
		double size = 0.0;
		
		for(int i = 0; i<getLength(); i++) {
			if(this.storage[i] == null) {
			}else {
				nonEmptyTrees+=1;
				size += (double) this.storage[i].size;
			}
		}
		
		if(nonEmptyOnly == true) {
			if(nonEmptyTrees == 0.0) {
				return 0.0;//all are empty
			}
			return (size/nonEmptyTrees);
		}else {
			return (size/(double)getLength());
		}
	}

	// Return the average number of leaves.
	// - If nonEmptyOnly is true, only consider non-empty trees;
	//   otherwise all trees are considered.
	// - Return 0.0 if all trees are empty but only non-empty 
	//   trees are considered
	/**
	 * This method average number of trees
	 * @param nonEmptyOnly true if nonEmptyOnly is on
	 * @return double avg number of trees
	 */
	public double avgNumLeaves(boolean nonEmptyOnly){
		double nonEmptyTrees = 0.0;
		double leaves = 0.0;
		
		for(int i = 0; i<getLength(); i++) {
			if(this.storage[i] == null) {
			}else {
				nonEmptyTrees+=1;
				leaves += (double) this.storage[i].numLeaves();
			}
		}
		if(nonEmptyOnly == true) {
			if(nonEmptyTrees == 0.0) {
				return 0.0;//all are empty
			}
			
			return (leaves/nonEmptyTrees);
			
		}else {
			return (leaves/(double)getLength());
		}
	}
	
	// Return the min and max tree size as a pair.
	/**
	 * This method creates a pair of min and max tree size
	 * @return Pair min and max tree size
	 */
	public Pair<Integer,Integer> minAndMaxTreeSize(){
		int min = 0;
		int max = 0;
		
		for(int i = 0; i<getLength(); i++) {
			if(this.storage[i] == null && min >= 0) {
				min = 0;
			}else {
				if(this.storage[i].size > max) {
					max = this.storage[i].size;
				}
				if(this.storage[i].size < min) {
					min = this.storage[i].size;
				}
			}
		}
		
		Pair<Integer, Integer> minMax = new Pair<Integer, Integer>(min,max);
		
		return minMax;
	}

	// Return the min and max tree height as a pair.
	/**
	 * This method creates a pair of min and max tree height
	 * @return Pair min and max tree height
	 */
	public Pair<Integer,Integer> minAndMaxTreeHeight(){
		int min = 0;
		int max = 0;
		
		for(int i = 0; i<getLength(); i++) {
			if(this.storage[i] == null) {
				min = -1;
			}else {
				if(this.storage[i].height() > max) {
					max = this.storage[i].height();
				}
				if(this.storage[i].height() < min) {
					min = this.storage[i].height();
				}
			}
		}
		
		Pair<Integer, Integer> minMax = new Pair<Integer, Integer>(min,max);
		
		return minMax;
	}

	// Return the min and max number of leaves in trees as a pair.
	/**
	 * This method creates a pair of min and max number of leaves
	 * @return Pair min and max number of leaves
	 */
	public Pair<Integer,Integer> minAndMaxNumLeaves(){
		int min = 0;
		int max = 0;
	
		for(int i = 0; i<getLength(); i++) {
			if(this.storage[i] == null && min >= 0) {
				min = 0;
			}else {
				if(this.storage[i].numLeaves() > max) {
					max = this.storage[i].numLeaves();
				}
				if(this.storage[i].numLeaves() < min) {
					min = this.storage[i].numLeaves();
				}
			}
		}
		
		Pair<Integer, Integer> minMax = new Pair<Integer, Integer>(min,max);
		
		return minMax;
	}

	
	
	//-------------------------------------------------------------
	// Main Method For Your Testing -- Edit all you want
	//-------------------------------------------------------------

	public static void main(String[] args) {
		BufferedWriter writer=null;
		boolean debug=false;
		
		try{
			if (args.length==1 && args[0].equals("-debug")){
				writer = new BufferedWriter(new FileWriter(new File("debug.txt")));
				debug = true;
			}
		
			HashTable<String> ht1 = new HashTable<>(10);
		
			//empty hash table
			if(ht1.getLength() == 10 && ht1.size() == 0 && ht1.toString().equals("")) {
				System.out.println("Yay 1");
			}
		
			//add
			if (ht1.add("a") && ht1.add("c") && ht1.add("computer") && !ht1.add("c")
				&& ht1.getLength() == 10 && ht1.size() == 3){
				System.out.println("Yay 2");		
			}
			// hashCode() references:
			// "a": 97, "c": 99, "computer": -599163109
		
						
			//basic features of hash table
			if(ht1.contains("a") && ht1.contains("computer") && ht1.contains("c") && !ht1.contains("cs")
				&& ht1.toString().equals("a c computer") 
				&& ht1.toStringDebug(false).equals("[0]: null\n[1]: null\n[2]: null\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: a\n[8]: null\n[9]: c computer")) {
				System.out.println("Yay 3");
			}
			//System.out.println(ht1.toStringDebug(true));
				
			if (debug){
				writer.write("====================================================="+System.getProperty("line.separator"));			
				writer.write("ht1 after add(\"a\"), add(\"c\"), add(\"computer\")"+System.getProperty("line.separator"));			
				writer.write("-----------------------------------------------------"+System.getProperty("line.separator"));			
				writer.write(ht1.toStringDebug(true)+System.getProperty("line.separator")+System.getProperty("line.separator"));
			}
		
			//remove
			if (!ht1.remove("data") && ht1.remove("c") && ht1.size() == 2
				&& !ht1.contains("c")  && ht1.contains("computer")){
				System.out.println("Yay 4");
			
			}
		
			//rehash
			HashTable<Integer> ht2 = new HashTable<>(5);
			ht2.add(105);
			ht2.add(26);
			ht2.add(11);
			if (ht2.getLength()==5 && ht2.size()==3 && ht2.add(55) && ht2.getLength()==10 
				&& ht2.add(5) && ht2.add(-11) && ht2.add(31) && ht2.getLength()==10 && ht2.size()==7){
				System.out.println("Yay 5");		
			}

			//System.out.println(ht2.toString());
			//System.out.println(ht2.toStringDebug(true));
			if (debug){
				writer.write("====================================================="+System.getProperty("line.separator"));			
				writer.write("ht2 after adding these in order: 105, 26, 11, 55, 5, -11, 31"+System.getProperty("line.separator"));			
				writer.write("-----------------------------------------------------"+System.getProperty("line.separator"));			
				writer.write(ht2.toStringDebug(true)+System.getProperty("line.separator")+System.getProperty("line.separator"));
			}

			if (ht2.toString().equals("-11 11 31 5 55 105 26") && ht2.size() == 7 && 
				ht2.toStringDebug(false).equals("[0]: null\n[1]: -11 11 31\n[2]: null\n[3]: null\n[4]: null\n[5]: 5 55 105\n[6]: 26\n[7]: null\n[8]: null\n[9]: null")) {
				System.out.println("Yay 6");
			}
		
			//tree features from hash table
			if (ht2.avgTreeSize(false)==0.7 && ht2.avgTreeSize(true)==7.0/3 &&
				ht2.avgTreeHeight(false)==-4.0/10 && ht2.avgTreeHeight(true)==1 &&
				ht2.avgNumLeaves(false)==0.4 && ht2.avgNumLeaves(true)==4.0/3){
				System.out.println("Yay 7");					
			}

			//more tree features
			if (ht2.minAndMaxTreeSize().toString().equals("<0,3>") &&
				ht2.minAndMaxTreeHeight().toString().equals("<-1,2>") &&
				ht2.minAndMaxNumLeaves().toString().equals("<0,2>")){
				System.out.println("Yay 8");					
			}
		
			//more rehash				
			if(ht2.rehash(1) == false && ht2.size() == 7 && ht2.getLength() == 10) {
				System.out.println("Yay 9");
			}

			if(ht2.rehash(11) == true && ht2.size() == 7 && ht2.getLength() == 11) {
				System.out.println("Yay 10");
			}
			//System.out.println(ht2.toString());
			//System.out.println(ht2.toStringDebug(true));
		
			if (debug){
				writer.write("====================================================="+System.getProperty("line.separator"));			
				writer.write("ht2 after rehash to length 11"+System.getProperty("line.separator"));			
				writer.write("-----------------------------------------------------"+System.getProperty("line.separator"));			
				writer.write(ht2.toStringDebug(true)+System.getProperty("line.separator")+System.getProperty("line.separator"));
				
				//bigger hashtable w/ major clusterings
				HashTable<Integer> ht3 = new HashTable<>(20);
				ht3.add(160); ht3.add(20);ht3.add(100); ht3.add(80);
				ht3.add(400); ht3.add(280); ht3.add(640);
				
				ht3.add(543); ht3.add(3); ht3.add(283); ht3.add(343); ht3.add(443);
				
				ht3.add(334); ht3.add(974); ht3.add(454);
				
				writer.write("====================================================="+System.getProperty("line.separator"));			
				writer.write("ht3 of 15 values clustered into three trees"+System.getProperty("line.separator"));			
				writer.write("-----------------------------------------------------"+System.getProperty("line.separator"));			
				writer.write(ht3.toStringDebug(true)+System.getProperty("line.separator")+System.getProperty("line.separator"));
				
				//bigger hashtable w/ uniform distribution
				ht3 = new HashTable<>(20);
				int count=0;
				Random r = new Random(0);
				while (count<15){
					if (ht3.add(r.nextInt(1000)))
						count++;
				}
				writer.write("====================================================="+System.getProperty("line.separator"));			
				writer.write("ht3 of 15 values uniformly distributed"+System.getProperty("line.separator"));			
				writer.write("-----------------------------------------------------"+System.getProperty("line.separator"));			
				writer.write(ht3.toStringDebug(true)+System.getProperty("line.separator"));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
				if (writer!=null)
					writer.close();		
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}


}