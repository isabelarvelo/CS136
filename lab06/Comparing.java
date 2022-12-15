import java.util.Comparator;
import structure5.*;
  /**
  * @param a an Student to compare
  * @param b an Student to compare
  * @return  (< 0) if SU box number of a is smaller than b;
  * (0) if the SU box numbers of both students are the same;
  * and (> 0) if SU box number of a is larger than b;
  */
  class SuComparator implements Comparator<Student> {

    public int compare(Student a, Student b) {
      return a.getSubox().compareTo(b.getSubox());
    }

  }
  /**
  * @param a an Student to compare
  * @param b an Student to compare
  * @return  (< 0) if the name of student a is alphabetically before student b;
  * (0) if the names are the same of both students;
  * and (> 0) if the name of student b is alphabetically before student a;
  */
  class NameComparator implements Comparator<Student> {

    public int compare(Student a, Student b) {
      return a.getName().compareTo(b.getName());
    }

  }
  /**
  * @param a an Student to compare
  * @param b an Student to compare
  * @return  (< 0) if there are more vowels in studuent a's name
  * (0) if there are the same number of vowels in both names;
  * and (> 0) if there are more vowels in student b's name
  */
class VowelComparator implements Comparator<Student> {

  public int compare(Student a, Student b) {
    int vowelsa = 0;
    int vowelsb = 0;

    for (int i = 0; i < a.getName().length(); i++) {
      if ("AEIOUaeiou".indexOf(a.getName().charAt(i)) != -1) {
        vowelsa++;
      }
    }
    for (int i = 0; i < b.getName().length(); i++) {
      if ("AEIOUaeiou".indexOf(b.getName().charAt(i)) != -1) {
        vowelsb++;
      }
    }
    return vowelsb - vowelsa;
  }
}
  /**
  * @param a an association between a string and its value which in the
  * student's case is the number of occurrences of the String
  * @param b an association between a string and its value which in the
  * student's case is the number of occurences of the String
  * @return  (< 0) if there are more occurences of the string in a
  * (0) if there are the same number of occurences in both;
  * and (> 0) if there are more occurences in b
  */
  class AssociationComparator implements Comparator<Association<String, Integer>> {

    public int compare(Association<String, Integer> a, Association<String, Integer> b) {
      return a.getValue().compareTo(b.getValue());
    }

  }
