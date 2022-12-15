//I am the sole author of the work in this repository
//$ Good job
import java.util.Scanner;
import structure5.*;


public class CharacterIterator extends AbstractIterator<Character> {

  protected int current;
  protected String theString;

  /**
  * constructor for CharacterIterator
  * given a string it sets up the string we want to iterate through
  */
  public CharacterIterator(String str) {
    theString = str;
    reset();
  }


  /**
  * Increments the iterator's index by one and returns the character at the new index.
  * @pre hasNext()
  * @post returns current value, and then increments iterator
  */
  public Character next() {
    return theString.charAt(current++);
  }

  /**
  * @post returns boolean indicating if there is another value to iterate over
  */
  public boolean hasNext() {
    return current < theString.length();
  }


  /**
  * Resets iterator to beginning of String
  * @pre iterator may be initialized or even amid-traversal
  * @post reset iterator to the beginning of the string
  */
  public void reset() {
    current = 0;
  }


  /**
  * Returns the next value to be visited
  * @pre hasNext()
  * @post returns the current value that next() will return
  */
  public Character get() {
    return theString.charAt(current);
  }


  /**
  * Main method to test iterator
  */
  public static void main(String[] args) {
    CharacterIterator ci = new CharacterIterator("Hello world!");
    for (char c : ci) {
      System.out.println(c);
    }
  }

}
