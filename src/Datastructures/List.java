package Datastructures;

/**
 * Implementation of the List.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * List class.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class List<E extends Comparable<E>> {
	/** Reference to the first element in the list*/
	private Node first;
	
	/** Reference to the last element in the list*/
	private Node last;
	
	/** Datastructures.List size*/
	private Integer size = 0;
	
    public class Node {
    	/** Data stored in each node */
        private E Data;
    	/** Reference to the next node */
        private Node next;
    	/** Reference to the previous node */
        private Node prev;

        /**
    	 * Parametrized Constructor for the Node class
    	 */
        public Node(Node prev, E Data, Node next) {
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
        public E get() {
        		return Data;
        }
    }//class Node

		
	/**
	 * Default Constructor for the Datastructures.List class
	 */
	public List() {
		first = last = null;
		size = 0;
	}

	
	/**
	 * Parametrized method for the Datastructures.List class
	 *
	 * @param data the data that the Datastructures.List will store
	 */
	public List(E data) {
		addLast(data);
	}
	
	/**
	 * Method that returns the element that is stored at the beginning of the list
	 *
	 * @return the first element
	 */
	public E getFirst() {
		return first.Data;
	}

	/**
	 * Method that returns the data that is stored at the end of the list
	 *
	 * @return the last data
	 */
	public E getLast() {
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
	public E get (Integer pos){
		E d = null;
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
	public void addLast(E Data) {
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

		first = first().next;
		size--;
	}
	
	
	/**
	 * It removes the data passed as parameter (if it is stored in the list)
	 *
	 * 
	 */
	public int removeDato(E dato) {
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
	public void sortedAdd(E Data) {
		if(first.Data.compareTo(Data)>0){
			addFirst(Data);
		}
		else if(last.Data.compareTo(Data) < 0){
			addLast(Data);
		}else{
			boolean inserted = false;
			Node aux = first;
			while (!inserted) {
				if (aux.Data.compareTo(Data) < 0) {
					Node newNode = new Node(aux, Data, aux.next);
					newNode.next.prev = newNode;
					aux.next = newNode;
					size++;
					inserted = true;
				}
			}
		}
	}



	/**
	 * It adds an elmement to the list in a sorted way but inversed
	 *
	 */
	public void sortedAddInversed(E Data) {

		if(first.Data.compareTo(Data)<0){
			addFirst(Data);
		}
		else if(last.Data.compareTo(Data) > 0){
			addLast(Data);
		}else{
			boolean inserted = false;
			Node aux = first;
			while (!inserted) {
				if (aux.Data.compareTo(Data) > 0) {
					Node newNode = new Node(aux, Data, aux.next);
					newNode.next.prev = newNode;
					aux.next = newNode;
					size++;
				}
			}
		}
	}
	
	/**
	 * It adds an element to the list by the beginning
	 *
	 * @param Data valor que se va a insertar
	 */
	public void addFirst(E Data) {
		/**
		 */
		Node newNode = new Node(null, Data, first);
		first = newNode;
		size++;
	}

	
	/**
	 * Checks whether a data is contained in the list
	 *
	 */
	public boolean contains(E Data) {
		Node p = first;
		while (p != null){
			if(p.Data == Data){
				return true;
			}
			p = p.next;
		}
		return false;
   }
   
	/**
	 * It adds a data into the list before the value passed as parameter (searchedValue) 
	 *
	 * @param Data valor que se va a insertar
	 * @param searchedValue valor delante del cual se insertará el nuevo dato
	 */
	public void addBefore(Integer Data, Integer searchedValue ) {
			}

	/**
	 * It adds a data into the list after the value passed as parameter (searchedValue) 
	 *
	 */
	public void addAfter(Integer Data, Integer searchedValue ) {
		}
	
	/**
	 * It adds a data into the list before the position passed as parameter (index) 
	 *
	 */
	public void addBeforeIndex(Integer Data, int index) {
		}
   
	/**
	 * It adds a data into the list after the position passed as parameter (index)
	 *
	 */
	public void addAfterIndex(Integer Data, int index ) {
		}

	/**
	 * It adds a data into the list in the position passed as parameter (index)
	 *
	 */
	public void addIndex(Integer Data, int index ) {

	}

	/**
	 * Permite eliminar el Data almacenado en una posición dada
	 *
	 * @param index posición del Data que se eliminará
	 * @return el dato que está al inicio de la lista
	 */
	public E removeIndex(int index) {
		Node p = first;
		if(index < size){
			for (int i = 0; i < index; i++) {
				p = p.next;
			}
			removeDato(p.Data);
		}
		return null;
	}

	
	/**
	* Change the value stored in the index position by the data passed as parameter
	*/
	public void set(int index, Integer Data) {

	}
  
		
 /**
  * Main method. It tests the list and shows the results 
  *
  * @param args An array of String that the main method receives as parameter
  */
	public static void main (String args[]) {
//		Integer[] dataSet = {new Integer(2), new Integer(8),
//							new Integer(3), new Integer(1),
//							new Integer(4), new Integer(5),
//							new Integer(6), new Integer(7),
//							new Integer(9), new Integer(0)};
//
//		//Testing the addition by the end
//		List list = new List();
//		for (int i = 0; i < dataSet.length; i++) {
//			list.addLast(dataSet[i]);
//		}
//
//		//Showing the list
//        Node iteratorNode = list.first();
//        while (iteratorNode!= null) {
//        		System.out.print(iteratorNode.get() + " : ");
//        		iteratorNode = iteratorNode.next();
//        }
//		System.out.println("\n--------------------------");
//
//		//Showing the list
//        for (int i=0;i<list.size();i++) {
//    			System.out.print(list.get(i)+ " : ");
//        }
//		System.out.println("\n--------------------------");
//
//		for (int i = 0; i < 5; i++)
//			list.removeLast();
//
//		//Showing the list
//        for (int i=0;i<list.size();i++) {
//    			System.out.print(list.get(i)+ " : ");
//        }
//		System.out.println("\n--------------------------");
	}
}



