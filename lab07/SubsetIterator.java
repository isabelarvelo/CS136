//I am the sole author of the work in this repository
//This class creates in iterator that iterates over an array and finds all
//   possible combination iof the elements in the vector
import java.util.Scanner;
import structure5.*;


public class SubsetIterator<E> extends AbstractIterator<Vector<E>> {

  protected Vector<E> theVector;
  protected long currentSubset; //increases from 0 (the empty set) to 2^n − 1 (the entire set of values)
//  protected int counter;
  protected long maxSubsets;
  protected long length;

  /**
  * Constructs an initialized subset iterator associated with v
  */
  public SubsetIterator(Vector<E> v) {
    //$ Good!
    theVector = v;
    maxSubsets = 1L << theVector.size();
    length = theVector.size();
    reset();
  }

  /**
  * Resets the subset counter to 0
  * @post
  */
  public void reset() {
//    counter = 0;
    currentSubset = 0;
  }

  /**
  * Returns boolean that indicates if there is another subset
  * @post  true if and only if the iterator has not yet dispensed all possible subsets
  */
  public boolean hasNext() {
    //$ Good
    return (currentSubset < maxSubsets);
  }

  /**
  * Returns a new Vector<E> of values that are part of the current subset
  * @pre traversal has more elements
  * @post: if bit i of the current counter is 1, element i of the original Vector
  * is included in the resulting subset Vector
  */
  public Vector<E> get() {
    //$ Great!

    Vector<E> subsetVector = new Vector<E>();

    if (currentSubset == 0) {
      return subsetVector;
    }

    for (int i = 0; i < length; i++) {
      if ((currentSubset & (1 << i)) != 0) {
        subsetVector.add(theVector.get(i));
      }
    }

    return subsetVector;
}

/**
* Returns a new Vector<E> of values that are part of the current subset
* @pre traversal has more elements
* @post: increments the iterated traversal
*/
  public Vector<E> next() {
    //returns the current subset before incrementing the counter.
    Vector<E>  nextSubset = get();
    currentSubset++;
    return nextSubset;

  }

  /**
	*Main method only used for testing the SubsetIterator class.
	*/
  public static void main(String[] args) {
    // Vector<Integer> mainvec = new Vector<Integer>();
    // mainvec.add(0);
    // mainvec.add(1);
    // mainvec.add(2);
    // mainvec.add(3);
    // mainvec.add(4);
    // mainvec.add(5);
    // mainvec.add(6);
    // mainvec.add(7);
    // int counter=0;
    // SubsetIterator<Integer> main = new SubsetIterator<Integer>(mainvec);
    // while (main.hasNext()) {
    //   counter++;
    //   System.out.println(main.next());
    // }
    // System.out.println();
    // System.out.print("counter: " + counter);
}

}
