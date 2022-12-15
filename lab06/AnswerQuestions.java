//We are the sole authors of the work in this repository.

import java.util.Scanner;
import structure5.*;

/**
*A class that takes in user input in the form of phone book data and implements several
* different comparators to answers questions about the students within it.
*/
public class AnswerQuestions {

  //create MyVector object that will hold Students
  private MyVector<Student> studentVector = new MyVector<Student>();
  //create Vector that will hold area codes
  private MyVector<Association<String, Integer>> uniqueAddresses = new MyVector<Association<String, Integer>>();
  //create Vector that will hold area codes
  private MyVector<Association<String, Integer>> areaCodes = new MyVector<Association<String, Integer>>();

  /**
  *Read in data file of student information and create a MyVector of Student
  *objects
  *@pre All entries in the phonebook have exactly 3 lines followed by a
  * "divider" of dashes. The first line gives their name. The second line gives
  * their address and the third line gives their campus phone, SU Box #, and
  * home phone.
 */
  public void readText(Scanner in) {
      while (in.hasNextLine()) {
        String line1 = in.nextLine(); //reading in name
        String line2 = in.nextLine(); //reading in campus address
        if (!line2.equals("UNKNOWN")) {
          addressVector(line2);
        }
        Long cellnumber = in.nextLong(); //reading in campus phone
        int subox = in.nextInt(); //reading in SU box
        Long line4 = in.nextLong(); //reading in home phone
        //building up vector of associations between area codes and their frequency
        String num = line4 + "";
        if (num.length() > 2) {
          codeVector(num);
        }
          //The scanner is reading in the space after the long so we will call nextLine()
          //an additional time to move it to the appropriate next Line
          in.nextLine();
        String lineofdashes = in.nextLine(); //reading in line of dashes
        Student student1 = new Student(line1, line2, cellnumber, subox, line4);
        studentVector.add(student1);
      }
  }

  private void addressVector(String address) {
    int index = uniqueAddresses.indexOf(new Association<String, Integer>(address, 0));
    addToAssociation(index, uniqueAddresses, address);
  }

  private void codeVector(String num) {
    String homecode = num.substring(0, 3);
    int index = areaCodes.indexOf(new Association<String, Integer>(homecode, 0));
    addToAssociation(index, areaCodes, homecode);
  }

  private void addToAssociation(int index, MyVector<Association<String, Integer>> vector, String element) {
    if (index > -1) {
      Association<String, Integer> current = vector.get(index);
      current.setValue(current.getValue() + 1);
      //if address is not a key of any of the associations the index indexOf() returns -1
    } else {
      //create new association
      Association<String, Integer> newAssoc = new Association<String, Integer>(element, 1);
      //add association to vector
      vector.add(newAssoc);
    }
  }

  /**
  *Method that sorts phone book data by first name to answer which student
  * appears first in a printed phone book comparing names in the format in
  * which they appear in the data file.
 */
  public void questionOne() {
    NameComparator comparator = new NameComparator();
    studentVector.sort(comparator);
    System.out.println("Q1: The first name to appear in a printed phone book sorted by first name is: "
    + studentVector.get(0).getName());
    System.out.println();
  }


  /**
  *Method that sorts phonw book data by SU box number to answer which students
  * have the highest and lowest SU box numbers.
 */
  public void questionTwo() {
    SuComparator comparator = new SuComparator();
    studentVector.sort(comparator);
    System.out.println("Q2: The student with the lowest SU Box number is "
    + studentVector.get(0).getName()
    + "(" + studentVector.get(0).getSubox() + ")"
    +  ". The student with the highest SU Box number is "
    + studentVector.get(studentVector.size() - 1).getName()
    + "(" + studentVector.get(studentVector.size() - 1).getSubox() + ")");
    System.out.println();
  }


  /**
  *Method that sorts phonebook data by number of vowels in each name to answer which
  * Which student has the most vowels in his or her full name.
 */
  public void questionThree() {
    VowelComparator comparator = new VowelComparator();
    studentVector.sort(comparator);
    System.out.println("Q3: The student with the most vowels in his full name is: "
    + studentVector.get(0).getName());
    System.out.println();
  }

  /**
  * Method that sorts vector of association between unique address and frequency to
  * determine the most common unique address and the students that live there
 */
  public void questionFour() {
    AssociationComparator comparator = new AssociationComparator();
    uniqueAddresses.sort(comparator);
    String commonAddress = uniqueAddresses.get(uniqueAddresses.size() - 1).getKey();
    System.out.print("Q4: The unique address shared by the most students is "
    + commonAddress + ". ");
    System.out.print("Their names are: ");
    System.out.println();
    for (int i = 0; i < studentVector.size(); i++) {
      if (studentVector.get(i).getAddress().equals(commonAddress)) {
        System.out.println(studentVector.get(i).getName());
      }
    }
    System.out.println();
  }

  /**
  * Method that sorts vector of association between area code and frequency to
  * determine the ten most common area codes for student home phone
  * numbers, in decreasing order.
 */
  public void questionFive() {
    AssociationComparator comparator = new AssociationComparator();
    areaCodes.sort(comparator);
    System.out.println("Q5: The ten most common area codes for student home phone numbers in decreasing order are: ");
    for (int i = areaCodes.size() - 1; i >= areaCodes.size() - 11; i--) {
      System.out.println(areaCodes.get(i).getKey() + " ("
      + areaCodes.get(i).getValue() + " occurrences)");
    }
    System.out.println();
  }

  /**
  *Main method creates an AnswerQuestions object to answer questions about
  *given input
  */
  public static void main(String[] args) {
      AnswerQuestions main = new AnswerQuestions();
      main.readText(new Scanner(System.in));
      main.questionOne();
      main.questionTwo();
      main.questionThree();
      main.questionFour();
      main.questionFive();
    }

}
