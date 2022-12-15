// I am the sole author of the work in this repository.

import structure5.*;

/**
* A Table holds a collection of strings, each of which has an
* associated FrequencyList
*/

//$ Excellent!
public class Table {

  private Vector<Association<String, FrequencyList>> table;

  /** Construct an empty table */
  public Table() {
    table = new Vector<Association<String, FrequencyList>>(100);
    //$ Vectors are extensible, so you don't actually need to declare a size.
    //$ But if you can make a good guess, it can save you some computation later
  }

  /**
  * Updates the table as follows
  * If key already exists in the table, update its FrequencyList
  * by adding value to it
  * Otherwise, create a FrequencyList for key and add value to it
  */

  public void add(String key, String value) {

    int index;
    //index of association with String key as its key value
    index = table.indexOf(new Association<String, FrequencyList>(key, new FrequencyList()));

    FrequencyList newFreqList = new FrequencyList();
    Association<String, FrequencyList> newAssoc = new Association<String, FrequencyList>(key, newFreqList);

    if (index == -1) {
      table.add(newAssoc);
    } else {
      newAssoc = table.get(index);
    }

    //retreieving appropriate frequency list
    newFreqList = newAssoc.getValue();
    //updating accordingly
    newFreqList.add(value);
  }

  /**
  * If key is in the table, return one of the characters from
  * its FrequencyList with probability equal to its relative frequency
  * Otherwise, determine a reasonable value to return
  */
  public char choose(String key) {

    char character;
    int index;

    Association<String, FrequencyList> currentAssoc =  new Association<String, FrequencyList>(key, new FrequencyList());
    //$ Why not just use indexOf and save yourself a search, like you did in add above?
    if (table.contains(currentAssoc)) {
      index = table.indexOf(currentAssoc);
      //find Association with String key as its key
      currentAssoc = table.get(index);
      FrequencyList currentList = new FrequencyList();
      //get frequency list associated with that string
      currentList = currentAssoc.getValue();
      //$ You can get the frequency list in one line:
      //$ FrequencyList currentList = currentAssoc.getValue()
      //choose a random character from the frequency list with probability equal to its relative frequency
      character = currentList.choose();
      return character;
    } else {
      System.out.println("Key is not in table");
      return '0';
    }
  }

  /** Produce a string representation of the Table */
  public String toString() {
    String stringRep;
    stringRep = "";
    String line;

    for (int i = 0; i < table.size(); i++) {
      Association<String, FrequencyList> currentAssoc = table.get(i);

      line = currentAssoc.getKey() +  " : " + '\n' + currentAssoc.getValue()  + '\n';
      stringRep += line;
    }

    return stringRep;

  }


//  Use main to test your Table class as you implement import it
  public static void main(String[] args) {

    Table tester = new Table();

    tester.add("tabl", "e");
    tester.add("tabl", "s");
    tester.add("phon", "e");
    System.out.println(tester.choose("tabl"));
    tester.toString();
    System.out.println(tester);
  }

}
