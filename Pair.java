// A generic class representing two values as a pair.
/**
 * @author Nooreldean Koteb
 * @version 1.0
 */
class Pair<T1, T2>{

	//-------------------------------------------------------------
	// DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
	//-------------------------------------------------------------
	/**
	 * This field variable holds a generic value
	 */
	T1 first;
	/**
	 * This field variable holds a generic value
	 */
	T2 second;
	/**
	 * This constructor initializes the first and second values
	 * @param v1 generic value
	 * @param v2 generic value
	 */
	public Pair(T1 v1, T2 v2) {
		this.first = v1;
		this.second = v2;
	}
	
	// Return string representation of a pair 
	// as <first,second> with no spaces
	/**
	 * This method returns a string with the first and second generic values
	 * @return String first and second values
	 */
	public String toString() {
		return "<" + first + "," + second + ">";
	}

	//-------------------------------------------------------------
	// END OF PROVIDED "DO NOT EDIT" SECTION 
	//-------------------------------------------------------------

	//-------------------------------------------------------------
	// You can NOT add any instance/static variables in this class.
	// You can add methods if needed but they must be PRIVATE.
	//-------------------------------------------------------------
 	

	// Return true if o is a pair and that
	// the two pairs have the same first value and the same second value.
	// i.e. <"Alice", 1> is considered as equal to <"Alice", 1> 
	/**
	 * This method checks if the object given is equal to this
	 * @param o object to compare
	 * @return boolean true if equal
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (o instanceof Object) {
			Pair<T1,T2> item = (Pair<T1,T2>) o;
			if(item.first == this.first && item.second == this.second) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	// Compute an integer hash code for this object.
	// It must follow hash contract and distribute well.
	/**
	 * This method creates a hashcode for the pair
	 * @return int hashcode
	 */
	public int hashCode() {		
		int multi = 29;
		int hash = 0;
		for(int i=0; i<toString().length(); i++) {
			hash = multi * hash + toString().charAt(i);
		}
		return hash;
	}

		
	//-------------------------------------------------------------
	// Main Method For Your Testing -- Edit all you want
	//-------------------------------------------------------------
	
	public static void main(String[] args){
		Pair<String,String> name1 = new Pair<>("George", "Mason");
		Pair<String,String> name2 = new Pair<>("George", "Washington");
		Pair<String,String> name3 = new Pair<>("George", "Mason");
		
		//equals
		if(!name1.equals(name2) && name3.equals(name3)){
			System.out.println("Yay 1");
		}

		//hashCode: hash contract and distribute well
		if (name1.hashCode() == name3.hashCode() && name1.hashCode()!=name2.hashCode()){
			System.out.println("Yay 2");		
		}
		
		int[] buckets= new int[20];
		Pair<Integer,Integer> pair;
		for (int i=-400; i<570; i+=97){
			for (int j=-401; j<571; j+=97){
				pair = new Pair<>(i, j);
				int hashCode = pair.hashCode();
				buckets[Math.abs(hashCode)%20]++;
			}
		}
		int nonEmpty=0;
		for (int i=0;i<buckets.length;i++){
			if (buckets[i]>0)
				nonEmpty ++;
		}
		// all buckets have been hit
		if (nonEmpty==buckets.length){
			System.out.println("Yay 3");				
		}

		//more hashCode distribution checking
		// - need a working Set class: enable the testing below 
		//    after you are done with Set

		/**/
		Set<Integer> hashCodes = new Set<>();
		int repeat = 0;
		
		for (int i=-10; i<10; i++){
			for (int j=-10; j<10; j++){
			
				pair = new Pair<>(i, j);
				int hashCode = pair.hashCode();
			
				// if it cannot add into the set, we have seen this hash code: repeat!
				if (!hashCodes.add(hashCode))
					repeat ++;
			}
		}
		
		// report the percentage of repeating hash code
		System.out.printf("Percentage of repeat: %.3f%%\n",(double)repeat/(20*20-1)*100);
		
		/**/
		
	}
}