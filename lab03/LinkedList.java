/**
 * Implementation of lists, using doubly linked elements, and dummy nodes.
 * Starter class for "9.10 Laboratory: Lists with Dummy Nodes"
 * Please modify this code by following the directions in  on page 216 of
 * Java Structures sqrt(7) edition by Duane Bailey.
 */

import structure5.*;
import java.util.Iterator;

public class LinkedList<E> extends DoublyLinkedList<E> {

	// Use these variables inherited from DoublyLinkedList
	/**
	* Number of elements within the list.
	*	protected int count;
	*/

	/**
	* Reference to head of the list.
	*
	protected DoublyLinkedNode<E> head;
	*/

	/**
	* Reference to tail of the list.
	*
	protected DoublyLinkedNode<E> tail;
	*/
	//$ We can use DoublyLinkedList head and tail, instead of making new ones.
	//constructing dummy nodes for beginning and end of list
	private DoublyLinkedNode<E>  head = new DoublyLinkedNode<E>(null);
	private DoublyLinkedNode<E>  tail = new DoublyLinkedNode<E>(null);

	/**
	* Constructs an empty list.
	*
	* @post constructs an empty list
	*
	*/
	//$ These next few lines are exactly what your clear method
	//$ does. Use that helper method here instead to avoid
	//$ duplicated code.
	public LinkedList() {

		head.setNext(tail);
		tail.setPrevious(head);
		count = 0;

	}

	/**
	* Determine the number of elements in the list.
	*
	* @post returns the number of elements in list
	*
	* @return The number of elements found in the list.
	*/
	public int size() {
		return count;
	}

	/**
	* Determine if the list is empty.
	*
	* @post returns true iff the list has no elements.
	*
	* @return True iff list has no values.
	*/
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	* Remove all values from list.
	*
	* @post removes all the elements from the list
	*/
	public void clear() {

		head.setNext(tail);
		tail.setPrevious(head);
		count = 0;

	}

	/**
	* A private routine to add an element after a node.
	* @param value the value to be added
	* @param previous the node the come before the one holding value
	* @pre previous is non null
	* @post list contains a node following previous that contains value
	*/
	protected void insertAfter(E value, DoublyLinkedNode<E> previous) {

		//stick value after previous node
		//setting up links correctly to be used in other methods

		DoublyLinkedNode<E> valueNode = new DoublyLinkedNode<E>(value);

		//setting the node previous to previous nodes's next as valueNode
		previous.next().setPrevious(valueNode);

		//making the node originally next to previous node valueNode's next node
		valueNode.setNext(previous.next());

		//seting valueNode as the node next to previous node
		previous.setNext(valueNode);

		//setting previous as the previous node to valueNode
		valueNode.setPrevious(previous);

	}

	/**
	* A private routine to remove a node.
	* @param node the node to be removed
	* @pre node is non null
	* @post node node is removed from the list
	* @return the value of the node removed
	*/
	protected E remove(DoublyLinkedNode<E> node) {

		//setting removed node's next node's previous reference
		node.next().setPrevious(node.previous());
		//setting removed node's previous node's next reference
		node.previous().setNext(node.next());

		count--;

		return node.value();
	}


	/**
	* Add a value to the head of the list.
	*
	* @pre value is not null
	* @post adds element to head of list
	*
	* @param value The value to be added.
	*/
	//$ Could call insertAfter(value, head) to simplfy code.
	public void addFirst(E value) {

		// construct a temporary element, making it first element of list
		DoublyLinkedNode<E> temp = new DoublyLinkedNode<E>(value);

		//setting appropriate references
		temp.setPrevious(head);
		temp.setNext(head.next());
		head.setNext(temp);
		temp.next().setPrevious(temp);

		// update count to reflect addition of 1 more element
		count++;
	}

	/**
	* Add a value to the tail of the list.
	*
	* @pre value is not null
	* @post adds new value to tail of list
	*
	* @param value The value to be added.
	*/
	//$ Could call insertAfter(value, tail) to simplfy code.
	public void addLast(E value) {

		// construct a temporary element, making it last element of list
		DoublyLinkedNode<E> temp = new DoublyLinkedNode<E>(value);

		//setting appropriate references
		temp.setPrevious(tail.previous());
		temp.setNext(tail);
		tail.setPrevious(temp);
		temp.previous().setNext(temp);

		// update count to reflect addition of 1 more element
		count++;
	}

	/**
	* Remove a value from the head of the list.
	* Value is returned.
	*
	* @pre list is not empty
	* @post removes first value from list
	*
	* @return The value removed from the list.
	*/
	//$ Could call remove(head.next()) to simplfy code
	public E removeFirst() {

		Assert.pre(!isEmpty(), "List is empty.");

		//first element of list that is being removed
		DoublyLinkedNode<E> temp = head.next();

		//setting appropriate references
		head.setNext(temp.next());
		temp.next().setPrevious(head);

		//decrementing count
		count--;

		//returning value that was removed
		return temp.value();
	}

	/**
	* Remove a value from the tail of the list.
	*
	* @pre list is not empty
	* @post removes value from tail of list
	*
	* @return The value removed from the list.
	*/
	//$Could call remove(tail.previous()) to simplfy code
	public E removeLast() {
		Assert.pre(!isEmpty(), "List is empty.");

		//last element of list that is being removed
		DoublyLinkedNode<E> temp = tail.previous();

		//setting appropriate references
		temp.previous().setNext(tail);
		tail.setPrevious(temp.previous());

		//decrementing count
		count--;

		//returning value that was removed
		return temp.value();
	}

	/**
	* Get a copy of the first value found in the list.
	*
	* @pre list is not empty
	* @post returns first value in list.
	*
	* @return A reference to first value in list.
	*/
	public E getFirst() {
		return head.next().value();
	}

	/**
	* Get a copy of the last value found in the list.
	*
	* @pre list is not empty
	* @post returns last value in list.
	*
	* @return A reference to the last value in the list.
	*/
	public E getLast() {
		return tail.previous().value();
	}


	/**
	* A private routine to get a node from list.
	* @param i the index of node to be retrieved
	* @pre list is not empty
	* @post returns the node at index i of the list
	* @return the node
	*/
	protected DoublyLinkedNode<E> getNode(int i) {
		DoublyLinkedNode<E> node = new DoublyLinkedNode<E>(null);

		//represents current index of list at each iteration while searching for node at index i
		int k;
		//$ Nice additon to decrease running time!
		//binary search to decrease run time
		if (i < count / 2) {
			node = head.next();
			for (k = 0; k < i; k++) {
				node = node.next();
			}
		} else {
			node = tail.previous();
			for (k = count - 1; k > i; k--) {
				node = node.previous();
			}
		}
		return node;
	}

	/**
	* Insert the value at location.
	*
	* @pre 0 <= i <= size()
	* @post adds the value o as the ith element of the list
	* @param i the index of this new value
	* @param o the the value to be stored
	*/
	public void add(int i, E o) {
		Assert.pre((0 <= i) && (i <= size()), "Index out of range.");

		//special cases to ensure O(1) run time for adding first and last element
		if (i == 0) {
			addFirst(o);
		} else if (i == size()) {
			addLast(o);
		} else {
				DoublyLinkedNode<E> previous = getNode(i - 1);
				insertAfter(o, previous);
				count++;
		}
	}


	/**
	* Remove and return the value at location i.
	*
	* @pre 0 <= i < size()
	* @post removes and returns the object found at that location.
	*
	* @param i the position of the value to be retrieved.
	* @return the value retrieved from location i (returns null if i invalid)
	*/
	public E remove(int i) {
		Assert.pre((0 <= i) && (i < size()), "Index out of range.");

		//special cases to ensure O(1) run time for removing first and last element
		if (i == 0) {
			return removeFirst();
		} else if (i == (size() - 1)) {
			return removeLast();
		}

		remove(getNode(i));

		//return value that was removed
		return get(i);
	}

	/**
	* Get the value at location i.
	*
	* @pre 0 <= i < size()
	* @post returns the object found at that location.
	*
	* @param i the position of the value to be retrieved.
	* @return the value retrieved from location i (returns null if i invalid)
	*/
	public E get(int i) {
		Assert.pre((0 <= i) && (i < size()), "Index out of range.");

		//will start looking at the 0th element of list
		DoublyLinkedNode<E> node = head.next();

		//index of list at each iteration while searching for index i
		int k;

		//search for node at index i
		for (k = 0; k < i; k++) {
			node = node.next();
		}

		//return the value found
		return node.value();
	}


	/**
	* Set the value stored at location i to object o, returning the old value.
	*
	* @pre 0 <= i < size()
	* @post sets the ith entry of the list to value o, returns the old value.
	* @param i the location of the entry to be changed.
	* @param o the new value
	* @return the former value of the ith entry of the list.
	*/
	public E set(int i, E o) {
		Assert.pre((0 <= i) && (i < size()), "Index out of range.");

		//retrieve node at index i
		DoublyLinkedNode<E> currentNode = getNode(i);
		//store value of node that is being replaced
		E oldValue = currentNode.value();
		//set new Value
		currentNode.setValue(o);

		//return value of former node at index i
		return oldValue;

	}

	/**
	* @param value The value sought.
	* @return the index (0 is the first element) of the value, or -1
	*/
	public int indexOf(E value) {

		//index of list element that we are comparing node.value() to- starting from front of list
		int i = 0;

		//looking for value starting at front of list
		DoublyLinkedNode<E> node = head.next();
		//$ This logic will not work if you have a null value in your list.
		//$ (That's an OK assumption.  But you can avoid that assumption
		//$ by comparing to tail directly).
		//loops stop if node equals value or it reaches dummytail
		while (node.value() != null && !node.value().equals(value)) {
			node = node.next();
			i++;
		}

		//if value was not found return -1
		if (node == tail) {
			return -1;
		} else {
			return i;
		}

	}


	/**
	* Determine the last location of a value in the list.
	*
	* @pre value is not null
	* @post returns the (0-origin) index of the value,
	*   or -1 if the value is not found
	*
	* @param value the value sought.
	* @return the index (0 is the first element) of the value, or -1
	*/
	public int lastIndexOf(E value) {

		//index of list element that we are comparing node.value() to - starting from end of list
		int i = count - 1;

		//looking for value starting at end of list
		DoublyLinkedNode<E> node = tail.previous();
		//$ This will give a null error if the value is not in the list.
		while (!node.value().equals(value)) {
			node = node.previous();
			i--;
		}

		if (node == head) {
			return -1;
		} else {
			return i;
		}
}

	/**
	* Check to see if a value is within the list.
	*
	* @pre value not null
	* @post returns true iff value is in the list
	*
	* @param value A value to be found in the list.
	* @return True if value is in list.
	*/
	public boolean contains(E value) {

	//if node containing value has positive index it must be in the list
	int index = indexOf(value);
	return index >= 0;

	}

	/**
	* Remove a value from the list.  At most one value is removed.
	* Any duplicates remain.  Because comparison is done with "equals,"
	* the actual value removed is returned for inspection.
	*
	* @pre value is not null.  List can be empty.
	* @post first element matching value is removed from list
	*
	* @param value The value to be removed.
	* @return The value actually removed.
	*/
	public E remove(E value) {

		remove(getNode(indexOf(value)));

		//return value that was removed
		return getNode(indexOf(value)).value();
	}

	/**
	* Construct an iterator to traverse the list.
	*
	* @post returns iterator that allows the traversal of list.
	*
	* @return An iterator that traverses the list from head to tail.
	*/
	public Iterator<E> iterator() {

		return new DoublyLinkedListIterator<E>(head, tail);
		//return new DoublyLinkedListIterator<E>(head);
	}

	/**
	* Construct a string representation of the list.
	*
	* @post returns a string representing list
	*
	* @return A string representing the elements of the list.
	*/
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("<LinkedList (" + count + "):");

		Iterator<E> li = iterator();
		while (li.hasNext()) {
			s.append(" " + li.next());
		}
		s.append(">");

		return s.toString();
	}

	/**
	 * Main method tests all public methods
	 * Constructs a linked lists of type String and tests methods on that list
	 */
	public static void main(String[] args) {

		LinkedList<String>  linkedlist = new LinkedList<String>();

		//Testing all methods to supplement
		linkedlist.add("Creamsicle");
		linkedlist.add("Hazelnut");
		linkedlist.add("Pistachio");
		linkedlist.clear();
		System.out.println("Clear Check: " + linkedlist.isEmpty());
		linkedlist.addFirst("Strawberry");
		linkedlist.add(1, "Chocolate");
		linkedlist.add(2, "Vanilla");
		linkedlist.add("Mint Chip");
		linkedlist.add(3, "Moose Tracks");
		linkedlist.set(3, "Mocha");
		linkedlist.add(4, "Cookies and Cream");
		linkedlist.remove("Vanilla");
 		linkedlist.addLast("Cookie Dough");
		linkedlist.remove(4);
		linkedlist.removeFirst();
		linkedlist.removeLast();
		linkedlist.add("Chocolate");
		System.out.println("French Vanilla " + linkedlist.contains("French Vanilla"));
		System.out.println("Chocolate " + linkedlist.contains("Chocolate"));
		System.out.println("Index of Chocolate " + linkedlist.indexOf("Chocolate"));
		System.out.println("Last Index of Chocolate " + linkedlist.lastIndexOf("Chocolate"));
		System.out.println("Position 1 " + linkedlist.get(1));
		System.out.println("First: " + linkedlist.getFirst());
		System.out.println("Last: " + linkedlist.getLast());
		System.out.println(linkedlist.toString());
	}
}
