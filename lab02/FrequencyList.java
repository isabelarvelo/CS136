// I am the sole author of the work in this repository.

import structure5.*;
import java.util.Random;

//$ Looks great! Your methods are clear and concise.

/**
* A FrequencyList stores a set of characters each of which has
* an associated integer frequency
*/

public class FrequencyList {

  private Vector<Association<String, Integer>> frequencies;

  //total number of characters added to Frequency List
  //$ Good
  private int total;
  //Random number generator
  private Random rn;

  /** Construct an empty FrequencyList */
  public FrequencyList() {
    frequencies = new Vector<Association<String, Integer>>();
    //$ You might also want to set total to 0.
  }

  /** add(String ch)
  * If ch is in the FrequencyList, increment it's associated frequency
  * Otherwise add ch to FrequencyList with frequency 1
  */

  public void add(String ch) {

    int index;
    //index of association that has String ch as its key
    index = frequencies.indexOf(new Association<String, Integer>(ch, 0));

    //use index to find association and increment its count
    if (index > -1) {
      Association<String, Integer> currentFrequency = frequencies.get(index);
      currentFrequency.setValue(currentFrequency.getValue() + 1);
      //if ch is not a key of any of the associations the index indexOf() returns -1
    } else {
      //create new association
      Association<String, Integer> newchar = new Association<String, Integer>(ch, 1);
      //add association to vector
      frequencies.add(newchar);
    }

    total++;
  }

  /**
  * Return a character from the FrequencyList with probabality equal
  * to its relative frequency
  */
  public char choose() {
    //$ You should make this an instance variable: Using a new Random
    //$ object every time you choose may result in non-random behaviour
    rn = new Random();
    //chooses a random number to represent any of the letters added to Frequency List
    //this represents probabality equal to its relative frequency
    int threshold = rn.nextInt(total) + 1;
    int count = 0;
    int i = 0;

    //keep incrementing count until you reach threshold and return value at that index
    while (count < threshold) {
      Association<String, Integer> currentAssoc = frequencies.get(i);
      int increment = currentAssoc.getValue();
      count += increment;
      i++;
      if (count >= threshold) {
        return currentAssoc.getKey().charAt(0);
      }
    }
    //should be impossible to return this character
    return '\0';
  }

  /** Produce a string representation of the FrequencyList */
  public String toString() {
    String stringRep;
    stringRep = "";
    String line;

    for (int i = 0; i < frequencies.size(); i++) {
      Association<String, Integer> currentAssoc = frequencies.get(i);
      line =  "[" + currentAssoc.getKey() + " : " +  currentAssoc.getValue() + "]" + '\n';
      stringRep += line;
    }

    return stringRep;

  }

  // Use main to test your FrequencyList class as you implement it;
  //$ Good!
  public static void main(String[] args) {
    FrequencyList tester = new FrequencyList();
    tester.add("s");
    tester.add("t");
    tester.add("t");
    tester.add("u");
    tester.add("v");
    tester.add("s");
    tester.add("w");
    System.out.println(tester.choose());
    tester.toString();
    System.out.println(tester);
  }

}
