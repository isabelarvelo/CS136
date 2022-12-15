Questions (from the text):

1. The three-parameter constructor for `DoublyLinkedNode` makes use of two `if` statements.
Suppose that you instead replace the calls to the three-parameter `DoublyLinkedNode` constructor with the one-parameter `DoublyLinkedNode` constructor,
and then you manually use `setNext` and `setPrevious` to set the appropriate references.
The `if` statements that were present in the three-parameter constructor disappear from the resulting code. Why?

* $ The reason why we don't need to have if statements is because we are guaranteed to always have a previous and next node.
* $ As apposed to a list without dummy nodes which might not have a previous or next node.

The `if` statements that were present in the previous code disappear from the resulting code because the previous and next elements will automatically be set to null and then be appropriately adjusted when the `setNext` and `setPrevious` methods are called.  These methods set the references to the adjacent nodes. If you're going to call them, there is no need to spend time setting initial previous and next references for the node.


2. The `contains` method can be written making use of the `indexOf` method, but not the other way around. Why?

The `contains` method can be written making use of the `indexOf` method because it returns a boolean based whether or not the value of `indexOf` is positive. However, since the `contains` method, itself, returns a boolean value, it cannot be used to make the indexOf method because it provides no information about where the element is in the list, only that it is or is not in the list. If `contains` returns true, the index of that value could still be anywhere from 0 to count-1.
  
 * $ Nice job!

3. Notice that we could have replaced the method `insertAfter` with a similar method, `insertBefore`.
This method would insert a new value before the indicated node. Some changes would have to be made to your code, but those changes seem relatively straightforward.
There does not appear, however, to be a choice between versions of `remove`. Why is this the case?
(Hint: Do you ever pass a dummy node to `remove`?)

* $ There is no need to have a remove before/after, because you can directly pass the exact node you want to remove.
* $ Insert needs the options because you can't reference a node that is not in the list yet.

   The insertAfter and insertBefore methods allow us to go in between the dummy head and first element of the list as well as between the last element of the list and the tail to add a new node. Since this method is always adding nodes and setting links, it will not run into an error unless there are limitations on memory or storage. You can safely call insertBefore and insertAfter on the head and tail of a doubly linked list to expand your list. However, removeAfter or removeBefore methods have the potential to remove the dummy heads, tails, or try to remove nodes that don't exist. If one were to call hypothetical removeBefore or removeAfter methods on the head or tail respectively, it would delete the dummyHead or dummyTail. Then, if these same methods were called on the now exposed head or tail that are no longer bookended by dummy nodes or the original dummy nodes, the remove method would attempt to remove a node outside the ends of the list that does not exist. You can not safely call removeBefore or removeAfter on the head or tails of the list.


4. Even though we don’t need to have the special cases in, for example, the indexed version of `add`,
it is desirable to handle one or more cases in a special way. What are the cases, and why is it desirable?

    It is desirable to handle adding to the beginning and end of the list in a special way using addFirst and addLast. The add method requires Java to look through the indices of the entire list to find the index before where you want to add the element which could take up to O(n) time. If we know we want to access the front or back of the list, checking the index and appropriately calling addFirst or addList decreases the maximum run time to O(1).
	 
	* $ Great job! 

5. Which file is longer (in terms of lines of Java code, excluding comments and whitespace): your final `LinkedList.java` or the original version without "dummy nodes"?
There is no need to calculate exact an line count, just note your choice and explain why.

    The original version of the code without 'dummy node' is longer than the LinkedList.java file. The dummy nodes eliminated the need for code dealing with special cases including empty lists and modifications to the head and tail. By guaranteeing that there is always at least one node at each end of the list, the dummy node implementation eliminates the need for special structures within methods. Although LinkedList.java includes helper methods not included in the original version, it is still shorter on the whole.  
