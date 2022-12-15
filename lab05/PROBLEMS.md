Answer the following Questions (also found on page 246 of the
textbook), and submit your answers with the rest of your code this
week. Note that we have added additional text in italics below to help
clarify the questions.

1. Problem __10.3__: Suppose you wish to fill a stack with a copy of
another, maintaining the order of elements. Using only Stack
operations (push and pop), describe how this would be done. How many stacks are
necessary (including the source stack and any additional stacks that
you use)? *Note: the original stack and its contents should be
preserved. After completing the steps that you describe, there should
be two identical stacks.*

 * In order to accomplish this, you pop each element from the original stack 
and push it onto an originally empty temporary stack until the original stack 
is empty. At this point, the temporary stack holds all of the contents of the 
original stack in reverse order. Then you pop each element from the temporary 
stack and create a new object that contains this value and then push this object
on to a new initially empty stack and the now empty original stack to create 
two stacks identical to the original stack. Three stacks are necessary, the source
stack, the initially empty stack that will hold the copy, and a temporary stack
for the procedure. 

2. Problem __10.4__: Suppose you wish to reverse the order of elements
of a stack (when you are done, the "reversed elements" should be
located in the original stack). Using only Stacks and Stack
operations (push and pop), describe how this could be done. How many stacks are
necessary (including the source stack and any additional stacks that
you use)?

 * You could solve this problem using recursion or iteration. I will explain 
an iterative procedure. You could pop the top item of the stack and hold it in
a temporary variable. Then you would pop each element underneath of it, 
immediately pushing it on to an initially empty temporary stack. 
Once the original stack is empty, you would push the element held in the
temporary variable onto the stack and then pop each element of the temporary stack
and push it back onto the original stack. Using a for loop decrementing from 
stack.size(), you would repeat this for each new element that ends up at the top 
of the stack until the element that started at the front of the stack is at the 
end of the stack. After the ith iteration, the first i elements of the stack are
in reversed order and the last n-i elements are still in their original orientation. 
Intuitively, the temporary stack decreases in size with each iteration. 
Two stacks are necessary: the source stack and a temporary stack. 

 * $ Nice job

3. Problem __10.5__: Suppose you wish to move the contents of one
queue into another, preserving the order of the elements. Using only
Queue operations (enqueue and dequeue), describe how this would be done. *Also 
indicate the number of queues needed, including the source queue, the destination
queue, and any additional queues that you use.*

 * Create a for loop that will execute n, size of the queue, times. 
 During each iteration, dequeue the first element of the original queue and 
enqueue it at the end of the initially empty copy. You will only need two queues: 
the source queue and destination queue.
 * $ Nice job
