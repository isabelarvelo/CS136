//We are the sole authors of the work in this repository.

import structure5.*;
import java.util.Comparator;

/**
* A general-purpose class for storing any object that can be sorted
* with a Comparator
*/
public class MyVector<E> extends Vector<E> {
  /**
  *constuructor that inherits all of Vectors properties and methods
  */
  public MyVector() {
    super();
  }

  /**
  * @param c a comparator that is used to order objects in this Vector
  * @pre c is non-null
  * @post this vector's contents are ordered as determined by c
  */
  public void sort(Comparator<E> c) {
    for (int i = elementCount - 1; i > 0; i--) {
      int big = findPosOfMax(c, i);
      swap(i, big);
    }
  }

  /**
  * @param i and j are integers of the positions in the vector we want to swap
  * @pre i and j  are integres that arent bigger than the size of the vector
  * @post the value stored in positions i and j are now swapped
  */
  private void swap(int i, int j) {
    E temp = get(i);
    set(i, get(j));
    set(j, temp);
  }


  /**
  * @param c a comparator that is used to order objects in this Vector
  * @param last is an integer that indicates whrere in the vector the previous biggest value was
  * @pre c is non-null
  * @post the value stored in positions i and j are now swapped
  */
  private int findPosOfMax(Comparator<E> c, int last) {
    int maxPos = 0; // A wild guess
    for (int i = 1; i <= last; i++) {
      if (c.compare(get(maxPos), get(i)) < 0) {
        maxPos = i;
      }
    }
    return maxPos;
  }

  /**
  * Main method that is used to test if MyVector does as it is supposed to
  */
  public static void main(String[] args) {

    // MyVector mainVector = new MyVector<Integer>();
    // VowelComparator comparator = new VowelComparator();
    // mainVector.add(new Student("Plx", "28 williams road", 4258776, 20, 7213176));
    // mainVector.add(new Student("Pliiiix", "28 williams road", 4258776, 20, 7213176));
    // mainVector.add(new Student("plop", "28 williams road", 4258776, 20, 7213176));
    // mainVector.sort(comparator);
    // System.out.println(mainVector);
  }
}
