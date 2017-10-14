package datastructures;

/**
* Implementation of the method for the datastructures.List class.
*
* @version 2.0
* @author
* <b> Profesores DP </b><br/x>
* Program Development<br/>
* 16/17 Course
*/
public class List {
	/** Reference to the first element in the list*/
	private Node first;
	
	/** Reference to the last element in the list*/
	private Node last;
	
	/** datastructures.List size*/
	Integer size=0;
	
    public class Node {
    	/** Data stored in each node */
        private Integer Data;
    	/** Reference to the next node */
        private Node next;
    	/** Reference to the previous node */
        private Node prev;

        /**
    	 * Parametrized Constructor for the Node class
    	 */
        public Node(Node prev, Integer Data, Node next) {
            this.Data = Data;
            this.next = next;
            this.prev = prev;
        }
    	/**
    	 * Method that returns the next node in the list (for traversing the list)
    	 *
    	 * @return the next node in the list
    	 */
        public Node next() {
        		return next;
        }
    	/**
    	 * Method that returns the previous node in the list (for traversing the list)
    	 *
    	 * @return the previous node
    	 */
        public Node prev() {
        		return prev;
        }

    	/**
    	 * Method to obtain a data
    	 *
    	 * @return the data contained in the current node
    	 */        
        public Integer get() {
        		return Data;
        }
    }//class Node

		
	/**
	 * Default Constructor for the datastructures.List class
	 */
	public List() {
		first = last = null;
		size = 0;
	}

	
	/**
	 * Parametrized method for the datastructures.List class
	 *
	 * @param data the data that the datastructures.List will store
	 */
	public List(Integer data) {
		addLast(data);
	}
	
	/**
	 * Method that returns the element that is stored at the beginning of the list
	 *
	 * @return the first element
	 */
	public Integer getFirst() {
		return first.Data;
	}

	/**
	 * Method that returns the data that is stored at the end of the list
	 *
	 * @return the last data
	 */
	public Integer getLast() {
		return last.Data;
	}
	/**
	 * Method that returns the first node 
	 *
	 * @return first node
	 */
	public Node first() {
		return first;
	}

	/**
	 * Method that returns the node at the end of the list
	 *
	 * @return last node
	 */
	public Node last() {
		return last;
	}
	/**
	 *  Method to check whether the list is empty
	 *
	 * @return true if the list is empty and otherwise false 
	 */
	public boolean estaVacia (){
		return (size == 0);
	}
	
	/**
	 * Method that returns the size of the list
	 *
	 * @return the size of the list
	 */
	public Integer size (){
		return size;
	}
	
	/**
	 * Method that returns the data contained in the position passed as parameter
	 * @param pos the position of the element to be returned 
	 * @return the data contained in the position passed as parameter
	 */
	public Integer get (Integer pos){
		Integer d = null; 
		Node iter=first;
		Integer i=0; 
		boolean encontrado = false;
		while(i<=pos && !encontrado && iter!= null) {
			if(i==pos) {
				encontrado = true;
				d=iter.Data;
			}
			i++;
			iter=iter.next;
		}
		return d;
	}
	
	
	/**
	 * Method to add a data by the end of the list
	 *
	 * @param Data value that is going to be added to the list
	 */
	public void addLast(Integer Data) {
        Node l = last;
        Node nodo = new Node(l, Data, null);
        last = nodo;
        if (l == null)
            first = nodo;
        else
            l.next = nodo;
        size++;
	}
	
	/**
	 * It removes the last data in the list
	 *
	 */
	public void removeLast() {
		if (last != null){
			last = last.prev();
		}	
		//there are not elements
		if (last == null) 	first = null;
		else last.next=null;
		size --;
	}

	// ************************************************************************************
	// ***** Additional exercises for the students ****************************************
 	// ************************************************************************************
	/**
	 * It removes the first element in the list
	 *
	 */
	public void removeFirst() {
		//TODO: Implement this method
		first = first().next;
	}
	
	
	/**
	 * It removes the data passed as parameter (if it is stored in the list)
	 *
	 * 
	 */
	public int removeDato(Integer dato) {
		Node iter = first;
		while(iter!= null) {
			if(iter.Data.equals(dato)) {
				if (iter == first) {
					removeFirst();
				} else if (iter == last) {
					removeLast();
				} else {
					iter.next.prev = iter.prev;
					iter.prev.next = iter.next;
				}
				size--;
				return 0;
			}
			iter = iter.next;
		}
		return -1;
	}
	
	
	/**
	* It adds an elmement to the list in a sorted way
	*
	*/
	public void sortedAdd(Integer Data) {
		//TODO: Implement the method 
	}
	
	/**
	 * It adds an element to the list by the beginning
	 *
	 * @param Data valor que se va a insertar
	 */
	public void addFirst(Integer Data) {
		//TODO: Implement the method 
	}

	
	/**
	 * Checks whether a data is contained in the list
	 *
	 */
	public boolean contains(Integer Data) {
		//TODO: Implement the method 
		return false;
   }
   
	/**
	 * It adds a data into the list before the value passed as parameter (searchedValue) 
	 *
	 * @param Data valor que se va a insertar
	 * @param valorbuscar valor delante del cual se insertará el nuevo dato
	 */
	public void addBefore(Integer Data, Integer searchedValue ) {
		//TODO: Implement the method 
	}

	/**
	 * It adds a data into the list after the value passed as parameter (searchedValue) 
	 *
	 */
	public void addAfter(Integer Data, Integer searchedValue ) {
		//TODO: Implement the method 
	}
	
	/**
	 * It adds a data into the list before the position passed as parameter (index) 
	 *
	 */
	public void addBeforeIndex(Integer Data, int index) {
		//TODO: Implement the method 
	}
   
	/**
	 * It adds a data into the list after the position passed as parameter (index)
	 *
	 */
	public void addAfterIndex(Integer Data, int index ) {
		//TODO: Implement the method 
	}

	/**
	 * It adds a data into the list in the position passed as parameter (index)
	 *
	 */
	public void addIndex(Integer Data, int index ) {
		//TODO: Implement the method 

	}

	/**
	 * Permite eliminar el Data almacenado en una posición dada
	 *
	 * @param index posición del Data que se eliminará
	 * @return el dato que está al inicio de la lista
	 */
	public Integer removeIndex(int index) {
		//TODO: Implement the method 
		return null;
	}

	
	/**
	* Change the value stored in the index position by the data passed as parameter
	*/
	public void set(int index, Integer Data) {
		//TODO: Implement the method 

	}
  
		
 /**
  * Main method. It tests the list and shows the results 
  *
  * @param args An array of String that the main method receives as parameter
  */
	public static void main (String args[]) {
		Integer[] dataSet = {new Integer(2), new Integer(8), 
							new Integer(3), new Integer(1),
							new Integer(4), new Integer(5),
							new Integer(6), new Integer(7),
							new Integer(9), new Integer(0)};
		
		//Testing the addition by the end 
		List list = new List();
		for (int i = 0; i < dataSet.length; i++) {
			list.addLast(dataSet[i]);
		}
		
		//Showing the list
        Node iteratorNode = list.first();
        while (iteratorNode!= null) {
        		System.out.print(iteratorNode.get() + " : ");
        		iteratorNode = iteratorNode.next();
        }
		System.out.println("\n--------------------------");

		//Showing the list
        for (int i=0;i<list.size();i++) {
    			System.out.print(list.get(i)+ " : ");
        }
		System.out.println("\n--------------------------");
        
		for (int i = 0; i < 5; i++)
			list.removeLast();
		
		//Showing the list
        for (int i=0;i<list.size();i++) {
    			System.out.print(list.get(i)+ " : ");
        }
		System.out.println("\n--------------------------");
	}
}



