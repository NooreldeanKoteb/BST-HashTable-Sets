// Class for a binary search tree implementation.
/**
 * @author Nooreldean Koteb
 * @version 1.0
 */
class SimpleBST<T extends Comparable<T>>{

	//-------------------------------------------------------------
	// DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
	//-------------------------------------------------------------
	
	//bad practice to have public inst. variables, but we want to test this...
  	//Root of tree
	/**
	 * This field variable holds the root of the tree
	 */
  	public Node<T> root;

  	// size of the tree (the number of nodes)
	/**
	 * This field variable holds the size of the tree
	 */
  	public int size;
	/**
	 * This method returns the size of the tree
	 * @return int size of the tree
	 */
  	public int size() { return size; }

	//provided binary tree node class
	//bad practice to have public inst. variables,
	//in a public nested class, but we want to test this...
	/**
	 * This class creates a node with a left and a right connection
	 */
	public static class Node<T>{
		/**
		 * This field variable holds the data of the node
		 */
    	T data;
		/**
		 * This field variable initializes the left and right connections of the node
		 */
    	Node<T> left, right;
		/**
		 * This constructor initializes data of the node
		 * @param data to be added into node
		 */
    	public Node(T data) { this.data = data; }
		/**
		 * This constructor initializes data, left and right connections of the node
		 * @param data to be added into node
		 * @param l left node
		 * @param r right node
		 */    	
    	public Node(T data,Node<T> l, Node<T> r){
      		this.data=data; this.left=l; this.right=r;
    	}
  	}
	//-------------------------------------------------------------
	// END OF PROVIDED "DO NOT EDIT" SECTION 
	//-------------------------------------------------------------
	

	//-------------------------------------------------------------
	// You can NOT add any instance/static variables in this class.
	// You can add methods if needed but they must be PRIVATE.
	//-------------------------------------------------------------
    
	// Return true if value is in tree;
	// return false if value is not in tree or if value is null.
	// O(H): H as the tree height
	/**
	 * This method checks if the value exists in the tree
	 * @param value to be found
	 * @return boolean true if value exists
	 */
  	public boolean contains(T value){
  		if (root == null) {
  			return false;
  		}
  		
  		Node<T>loopVal = root;
  		
  		while (true) {
	  	  		if(loopVal.data.compareTo(value) == 0) {
	  	  			return true;
	  	  		}else {
	  	  		
	  			if(loopVal.data.compareTo(value) > 0) {
	  				if(loopVal.left == null) {
	  					return false;
	  				}
	  				loopVal = loopVal.left;
	  			}else {
	  				if(loopVal.right == null) {
	  					return false;
	  				}
	  				loopVal = loopVal.right;
	  			}
  			}
  		}
  	}


  	// Insert value into tree. 
  	// No duplicates allowed; no null value allowed.
	// Return false if value cannot be added 
	//    (duplicate values or null values).
	// Return true for a successful insertion.
	// O(H): H as the tree height
	/**
	 * This method inserts a value into the tree
	 * @param value to be added
	 * @return boolean true if value is inserted
	 */
  	public boolean insert(T value){
  		if (root == null) {
  			Node<T> newNode = new Node<T> (value);
  			root = newNode;
  			this.size +=1;
  			return true;
  		}
  		if (value == null || contains(value)) {
  			return false;
  		}
  		
  		Node<T>loopVal = root;
  		Node<T> newNode = new Node<T> (value);
  		
  		while (true) {
	  			if(loopVal.data.compareTo(value) > 0) {
	  				if(loopVal.left == null) {
	  					loopVal.left = newNode;
	  					this.size +=1;
	  					return true;
	  				}
	  				loopVal = loopVal.left;
	  			}else {
	  				if(loopVal.right == null) {
	  					loopVal.right = newNode;
	  					this.size +=1;
	  					return true;
	  				}
	  				loopVal = loopVal.right;
	  			}
  			}
  	}

  	// Remove value from tree.
	// Return false if value cannot be removed 
	//	(values not in tree or null values)  	
	// Return true for a successful removal.
  	// NOTE: Use predecessor replacement in your implementation.
	// O(H): H as the tree height
	// O(H): H as the tree height
	/**
	 * This method removes a value into the tree
	 * @param value value to be removed
	 * @return boolean true if value is removed
	 */
  	public boolean remove(T value){
  		if (value == null || !contains(value)) {
  			return false;
  		}
  		
  		Node<T>loopVal = root;
  		Node<T> tempNode = root;
  		Node<T> replacementNode = null;
  		
		if(root.data.compareTo(value) == 0) {
			if(loopVal.right == null && loopVal.left == null) {
				root = null;
				this.size -= 1;
				return true;
			}else {
				if(loopVal.right != null && loopVal.left == null) {
					root = tempNode.right;
					this.size -= 1;
  					return true;
				}
			}
		}
  		
  		while (true) {
  			if(root.data.compareTo(value) == 0 ) {
  				loopVal = loopVal.left;
  				break;
  				}
  			if(loopVal.data.compareTo(value) == 0) {
  				//if last node, it gets deleted
  				if(loopVal.right == null && loopVal.left == null) {
  					if (tempNode.left == value) {
  						tempNode.left = null;
  					}else {
  						tempNode.right = null;
  					}
  					this.size -= 1;
  					return true;
  				}
  				// if left doesnt exists and only right then first right takes its place
  				if (loopVal.left == null && loopVal.right != null){
  					if (tempNode.left == value) {
  						tempNode.left = loopVal.right;
  					}else {
  						tempNode.right = loopVal.right;
  					}
  					this.size -= 1;
  					return true;
  				}
  				//else loop val now equals next left and we move 
  				//to finding the largest smaller than outside this loop
  				loopVal = loopVal.left;
  				break;
  			}
  			
  			//Finds the value in the tree
  			if(loopVal.data.compareTo(value) > 0) {
  				if(loopVal.left != null) {
  					tempNode = loopVal;
  					loopVal = loopVal.left;
  					}
  				}
  			
  			
  			if(loopVal.data.compareTo(value) < 0) {
  				if(loopVal.right != null) {
  					tempNode = loopVal;
  					loopVal = loopVal.right;
  					}
  				}
  		}
  		
  		replacementNode = loopVal;
  		while(loopVal.right!=null) {
  			replacementNode = loopVal;
  			loopVal = loopVal.right;
  		}
  		if(loopVal.left != null) {
  			replacementNode.right = loopVal.left;
  		}else {
  			replacementNode.right = null;
  		}
  		
  		
  		if(tempNode.data.compareTo(value) == 0) {
  			tempNode.data = loopVal.data;
  			loopVal = null;
  			this.size -= 1;
  			return true;
  		}else {
			if (tempNode.left.data.compareTo(value) == 0) {
					tempNode.left.data = loopVal.data;
					if(tempNode.left.left != null && tempNode.left.left.data.compareTo(loopVal.data) == 0) {
						tempNode.left.left = null;
					}else {
						tempNode.left.right = null;
					}
				}else {
					tempNode.right.data = loopVal.data;
					if(tempNode.right.right != null && tempNode.right.right.data.compareTo(loopVal.data) == 0) {
						tempNode.right.right = null;
					}else {
						tempNode.right.left = null;
					}
				}
  		}
  		
  		this.size -= 1;
    	return true;
  	}
  
  	

	
  	// Return the biggest value in the tree rooted at t.
  	// Return null if tree is null.
	// O(H): H as the tree height
	/**
	 * This method find the max value of the tree
	 * @param t from tree
	 * @param <T> generic type
	 * @return T data of the max node
	 */
  	public static <T> T findMax(Node<T> t){
  		if (t == null) {
  			return null;
  		}
  		
  		Node<T>loopVal = t;
  		
  		while (true) {
			if(loopVal.right == null) {
				return loopVal.data;
			}else {
				loopVal = loopVal.right;
			}
  		}
  	}

  	// Remove the biggest value in the binary search tree rooted at t.
  	// Return the tree root after removal.
  	// Return null for null trees.
	// O(H): H as the tree height
	/**
	 * This method removes the max value of the tree
	 * @param t from tree
	 * @param <T> generic type
	 * @return Node tree root
	 */
  	public static<T> Node<T> removeMax(Node<T> t){
  		if (t == null) {
  			return null;
  		}
  		if(t.left == null && t.right == null) {
  			t = null;
  			return t;
  		}
  		
  		Node<T> prev = t;
  		Node<T>loopVal = t;
  		
  		while (true) {
			if(loopVal.right == null) {
				if(loopVal.left != null) {
					loopVal = loopVal.left;
					return t;
				}else {
					if(prev.left == loopVal) {
						prev.left = null;
						return t;
					}else {
						prev.right = null;
						return t;
					}
				}
			}else {
				prev = loopVal;
				loopVal = loopVal.right;
			}
  		}
  	}

  	// Return the in-order predecessor of t's data in the tree with t as root.
  	// This in-order predecessor is the biggest value that is smaller than t's data.
  	// Return null if t's data is the smallest or if tree is null.
	// O(H): H as the tree height   
  	
	/**
	 * This method finds the predecessor node of the given node
	 * @param t from tree
	 * @param <T> generic type
	 * @return T data of the predecessor node
	 */
  	public static<T> T findPredecessor(Node<T> t){
  		if(t == null || t.left == null) {
  			return null;
  		}
  		if(t.left.right == null) {
  			return t.left.data;
  		}else {
  			return t.left.right.data;
  		}
  	}

	// Return the height of the tree.
	// Return -1 for null trees.
	// O(N): N is the tree size
  	
	/**
	 * This method finds the height of the tree
	 * @return int height of tree
	 */
	public int height(){
		if (root == null) {
			return -1;
		}
		return heightHelper(root);
	}
	
	/**
	 * This method Helps finds the height of the tree
	 * @param branch from tree
	 * @return int highest height
	 */
	private int heightHelper(Node<T> branch) {
		Node<T>loopVal = branch;
		int left =  0;
		int right = 0;
		
		if(loopVal.left != null) {
			left += 1 + heightHelper(loopVal.left);
		}
		
		if(loopVal.right != null) {
			right += 1 + heightHelper(loopVal.right);
		}
		
		if (left > right) {
			return left;
		}else {
			return right;
		}
	}
	
	
	// Return the number of leaf nodes in the tree.
	// Return zero for null trees.
	// NOTE: Your implementation MUST be recursive.
	// O(N): N is the tree size
	
	/**
	 * This method finds the number of leaves from the tree
	 * @return int number of leaves
	 */
	public int numLeaves(){
		if (root == null) {
			return 0;
		}
		return numLeavesHelper(root);
	}
	
	/**
	 * This method Helps finds the number of leaves from the tree
	 * @param branch from tree
	 * @return int number of leaves
	 */
	private int numLeavesHelper(Node<T> branch) {
		Node<T>loopVal = branch;
		int leaf =  0;
		
		if(loopVal.left != null) {
			leaf += numLeavesHelper(loopVal.left);
		}else {
			return 1;
		}
		
		if(loopVal.right != null) {
			leaf +=numLeavesHelper(loopVal.right);
		}else {
			return 1;
		}
		return leaf;
	}

	// Return a string representation of the tree
	// follow IN-ORDER traversal to include data of all nodes.
	// Include one space after each node.
	// O(N): N is the tree size
	//
	// Return empty string "" for null trees.
	// Check main method below for more examples.
	//
	// Example 1: a single-node tree with the root data as "A":
	//            toString() should return "A "
	//
	// Example 2: a tree with three nodes:      B
	//										   / \
	//										  A   C 
	//            toString() should return "A B C "
	
	/**
	 * This method creates a string of tree values
	 * @return String of tree in order form
	 */
  	public String toString(){
  		if (root == null) {
  			return "";
  		}
  		return toStringHelper(root);
  	}
  	
	/**
	 * This method Helps create a string of tree values
	 * @param branch from tree
	 * @return String of tree in order form
	 */
  	private String toStringHelper(Node<T> branch) {
		Node<T>loopVal = branch;
		String values = "";
		
		if(loopVal.left != null) {
			values += String.valueOf(toStringHelper(loopVal.left));
		}
		
		values +=String.valueOf(loopVal.data)+" ";
		
		if(loopVal.right != null) {
			values += String.valueOf(toStringHelper(loopVal.right));
		}
		return values;
  	}
  	
	// Return an array representation of all values 
	// following PRE-ORDER traversal.
	//  - The root value of the tree should be element 0
	//    in the array. 
	//  - The length of the array should be the size 
	//    of tree.
	// O(N): N is the tree size
	//
	// Example 1: a single-node tree with the root data as "A"
	//            toArray() should return {"A"}
	//
	// Example 2: a tree with three nodes:      B
	//										   / \
	//										  A   C 
	//            toArray() should return {"B","A","C"}
	// Check main method below for more examples.

	//@SuppressWarnings("unchecked")
  	
	/**
	 * This method creates an Array of tree values
	 * @return Object[] Array of objects
	 */
	public Object[] toArray() {		
		Object[] listValues = new Object[size];
		int index = 0;
		return toArrayHelper(root, listValues, index);
	}
	
	/**
	 * This method Helps create a string of tree values
	 * @param branch from tree
	 * @param listValues array to be added to
	 * @param index in the array
	 * @return Object[] Array of objects
	 */
	private Object[] toArrayHelper(Node<T> branch, Object[] listValues, int index ) {
		Node<T>loopVal = branch;
		listValues[index] = loopVal.data;
		if(loopVal.left != null) {
			index += 1;
			toArrayHelper(loopVal.left, listValues, index);
		}
		
		if(loopVal.right != null) {
			index += 1;
			toArrayHelper(loopVal.right, listValues, index);
		}
		return listValues;
}
	//-------------------------------------------------------------
	// Main Method For Your Testing -- Edit all you want
	//-------------------------------------------------------------
	public static void main(String args[]){
  		SimpleBST<Integer> t = new SimpleBST<Integer>();

		//build the tree / set the size manually
		//only for testing purpose
  		Node<Integer> node = new Node<>(112);
  		Node<Integer> node2 = new Node<>(330);
  		node2 = new Node<>(440,node2,null);
  		node = new Node<>(310,node,node2);
  		t.root = node;
  		t.size = 4;

		// Current tree:
		//			  310
		//           /   \
		//        112     440
		//                /  
		//              330 
  		
		//checking basic features
		if (t.root.data == 310 && t.contains(112) && !t.contains(211) && t.height() == 2){
			System.out.println("Yay 1");
		}
		//checking more features
		if (t.numLeaves() == 2 && SimpleBST.findMax(t.root)==440 
			&& SimpleBST.findPredecessor(t.root) == 112){
			System.out.println("Yay 2");
		}
		
		//toString and toArray	
		if (t.toString().equals("112 310 330 440 ") &&  t.toArray().length==t.size() 
			&& t.toArray()[0].equals(310) && t.toArray()[1].equals(112) 
			&& t.toArray()[2].equals(440) && t.toArray()[3].equals(330) ){
			System.out.println("Yay 3");		
			//System.out.println(t.toString());		
		}
		
		// start w/ an empty tree and insert to build the same tree as above
		t = new SimpleBST<Integer>();
  		if (t.insert(310) && !t.insert(null) && t.size()==1 && t.height()==0 
  			&& t.insert(112) && t.insert(440) && t.insert(330) && !t.insert(330)
  			){
 			System.out.println("Yay 4"); 			
  		}

  		if (t.size()==4 && t.height()==2 && t.root.data == 310 && 
  			t.root.left.data == 112 && t.root.right.data==440
  			&& t.root.right.left.data == 330){
 			System.out.println("Yay 5"); 			
  		}
		
		// more insertion
		t.insert(465);
		t.insert(321);
		t.insert(211);
		
		//			  310
		//           /   \
		//        112     440
		//          \     /  \
		//         211   330 465
		//               /
		//             321

		

		//remove leaf (211), after removal:
		//			  310
		//           /   \
		//        112     440
		//                /  \
		//               330 465
		//               /
		//             321

		if (t.remove(211) && !t.contains(211) && t.size()==6 && t.height() == 3 
			&& SimpleBST.findMax(t.root.left) == 112){
			System.out.println("Yay 6");					
		}

		//remove node w/ single child (330), after removal:
		//			  310
		//           /   \
		//        112     440
		//                /  \
		//              321 465
		if (t.remove(330) && !t.contains(330) && t.size()==5 && t.height() == 2 
			&& t.root.right.left.data == 321){
			System.out.println("Yay 7");					
		}

		//remove node w/ two children (440), after removal:
		//			  310
		//           /   \
		//        112     321
		//                  \
		//                 465
		
		if ((t.root!=null) && SimpleBST.findPredecessor(t.root.right) == 321 && t.remove(440) && !t.contains(440) 
			&& t.size()==4 && t.height() == 2 && t.root.right.data == 321){
			System.out.println("Yay 8");					
		}
		
		SimpleBST<Integer> a = new SimpleBST<Integer>();
        a.insert(656);
        a.insert(699);
        a.insert(493);
        a.insert(567);
        a.insert(466);
        a.insert(512);
        System.out.println(a.toString());
        a.remove(656);//nr
        a.remove(567);//nr
        System.out.println(a.toString());
        System.out.println(a.root.data);
        a.remove(699);
        System.out.println(a.toString());
        a.insert(500);
        a.remove(493);
        System.out.println(a.toString());
		
	}

}


