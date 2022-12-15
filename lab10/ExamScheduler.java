import structure5.*;
import java.util.Iterator;
import java.util.Scanner;

public class ExamScheduler {

  private GraphListUndirected<String, Integer> myGraph = new GraphListUndirected<String, Integer>();
  private Vector<Student> myStudents = new Vector<Student>();

  /**
  * Read in a file and storing the information in a vector of student objects
  *@param Scanner we are using to read input
  *@return Vector of students
  *@pre input file is ordered with first line as the name of the student and the
  * next 4 lines are their courses and every student is taking exactly 4 courses
  *@post myStudents instance variable is now populated
  */
  public Vector<Student> readText(Scanner in) {
    //to read in classes and store the students in myGraph
    while (in.hasNextLine()) {
        String name = in.nextLine(); //reading in name

        //$ You could simplify this section slightly with a for loop:
        //$ first construct your `coursesVector`, and then
        //$ simply add then next four lines to the vector
        //$ (no need to store them in variables!)
        String course1 = in.nextLine();
        String course2 = in.nextLine();
        String course3 = in.nextLine();
        String course4 = in.nextLine();
        Vector<String> coursesVector = new Vector<String>();
        coursesVector.add(course1);
        coursesVector.add(course2);
        coursesVector.add(course3);
        coursesVector.add(course4);
        Student newStudent = new Student(name, coursesVector);
        myStudents.add(newStudent);
        }
        return myStudents;
  }


//$ Since you are not using static methods in `ExamScheduler`, all your methods
//$ should be protected or private, not public, to limit possible misuse.


  /**
* Creates a graph with each course as a node and an edge representing if a
* student is enrolled in both classes
*@return A GraphListUndirected that holds the courses as nodes and has edges
*if there is a student enrrolled in both courses
*@pre myStudents has already been populated
*@post populated myGraph (instance variable)
*/
  public GraphListUndirected<String, Integer> createGraph() {
    for (Student s: myStudents) { //for each student
      //$ can condense these next 2 lines into
      //$ Vector<String> scourses = s.getCourses();
      Vector<String> scourses = new Vector<String>();
      scourses = s.getCourses();
      for (int i = 0; i < scourses.size(); i++) { //for each course
        //$ Graph's `add` method doesn't allow you to add duplicate verticies,
        //$ so you don't actually need to do this contains check!
        if (!myGraph.contains(scourses.get(i))) {
        myGraph.add(scourses.get(i));
       } //add vertex
        if (i > 0) { //not the first one
          for (int j = 0; j < i + 1; j++) { //add all the necessary edges
            myGraph.addEdge(scourses.get(i), scourses.get(j), 1);
          }
        }
      }
    }
  return myGraph;
  }

/**
* Goes through the graph created and puts the courses in slots
*@return a Vector<Vector<string>> where each spot in the bigger vector holds a
*slot number and each entry in the second bector holds a course that fits in that slot
*@pre myStudents and myGraph has already been populated
*@post no global variables modified
*/
public Vector<Vector<String>> organize() {
  GraphListUndirected<String, Integer> classes = createGraph();
  Vector<Vector<String>> slots = new Vector<Vector<String>>();
  while (!classes.isEmpty()) {
    int i = 0;
    Vector<String> slotNumber = new Vector<String>();
    for (String course:classes) {
      Boolean valid = true;
      for (String alreadyInSlot :slotNumber) { //go through slotNumber to see if there is an existing edge
        if (alreadyInSlot.equals(course) || classes.containsEdge(alreadyInSlot, course)) {
          valid = false;
        }
      }
      if (valid) {
        slotNumber.add(course);
      }
    }
    for (String inslot:slotNumber) {
      classes.remove(inslot);
    }
    slots.add(slotNumber);
  }
  return slots;
  //$ Nice
}

/**
* Takes a Vector<Vector<String>> and formats it to print out nicely
*@return A string representation of our parameter
*@pre toFormat must be populated
*@param toFormat must be a Vector<Vector<String>>
*/
  public String format(Vector<Vector<String>> toFormat) {
    String toPrint = "";
    for (int i = 0; i < toFormat.size(); i++) {
      toPrint = toPrint + "Slot " + i + ": ";
      for (String nameOfCourse:toFormat.get(i)) {
        toPrint = toPrint + nameOfCourse + " ";
      }
      toPrint = toPrint + "\n";
    }
    return toPrint;
  }

  /**
  * Takes a Vector<Vector<String>> and puts the courses in alphabetical order
  * while displaying its slot number
  *@return A string representation of our parameter with the courses
  in alphabetical order with the slot numbers
  *@pre parameter must be populated
  */
  public String alphabetizeCourses(Vector<Vector<String>> listOfCourses) {
    String toPrint = "";
    //$ This implemetation is perfectly valid, but an even simpler way is to use
    //$ an `OrderedVector` to store your `Association<String, Integer>`s. That
    //$ way, when you add to the vector, it automatically takes care of
    //$ ordereing the elements, so you don't have to rewrite `sort`, `swap`,
    //$ and `findPosOfMax`!
    Vector<Association<String, Integer>> alphabetized = new Vector<Association<String, Integer>>();
    for (int i = 0; i < listOfCourses.size(); i++) {
      for (String nameOfCourse:listOfCourses.get(i)) {
        alphabetized.add(new Association<String, Integer>(nameOfCourse, i));
      }
    }
    //now our courses list is in a vector with key as the course and value as the slot number
    alphabetized = sort(alphabetized);
    for (Association<String, Integer> courseName:alphabetized) {
      toPrint = toPrint + courseName.getKey() + " in slot " + courseName.getValue() + "\n";
    }
    return toPrint;

  }

  /**
  * Takes a Vector<Association>> and sorts it by key, so alphabetically
  *@param toSort must be populated and must be a Vector<Association<String, Integer>>
  *@returns a Vector<Association<String, Integer>> with all of the
  * elements in alphabetical order
  */
  public Vector<Association<String, Integer>> sort(Vector<Association<String, Integer>> toSort) {

    for (int i = toSort.size() - 1; i > 0; i--) {
      int big = findPosOfMax(toSort, i);

      swap(toSort, i, big);
    }

    return toSort;
  }

  /**
  * Helper method for sort method
  *@param Vec must be a Vector of Associations as specified and i and j must
  * each be less than the vector size
  *@post swaps the elements in the ith and jth position in the vector given
  */
  private static void swap(Vector<Association<String, Integer>> Vec, int i, int j) {

    Association<String, Integer> temp = Vec.get(i);

    Vec.set(i, Vec.get(j));
    Vec.set(j, temp);

  }
  /**
  * Helper method for the sort method
  *@pre A must be populated and must be a Vector<Association<String, Integer>>
  *@returns an int that indicated in which positon the maximum value of the vector is
  */
  public static int findPosOfMax(Vector<Association<String, Integer>> A, int last) {
    int maxPos = 0;

    for (int i = 1; i <= last; i++) {

      if (A.get(maxPos).getKey().compareTo(A.get(i).getKey()) < 0) {
        maxPos = i;
      }
    }
    return maxPos;
  }

  /**
  * Main method to run all the methods and get the desired output
  */
  public static void main(String[] args) {
    //$ typo here that stops your code from compiling! should be
    //$ ExamScheduler main = new ExamScheduler();
    NewExamScheduler main = new NewExamScheduler();
    Vector<Student> myVector = main.readText(new Scanner(System.in));
    Graph<String, Integer> myGraph = main.createGraph();
    Vector<Vector<String>> slots = main.organize();
    String originialAnswer = main.format(slots);
    System.out.print(originialAnswer);
    System.out.println();
    System.out.println("The Extension is the following");
    String printed = main.alphabetizeCourses(slots);
    System.out.println(printed);

	}
}
