import structure5.*;
import java.util.Iterator;
/**
* A class that creates Student objects and contains methods to access relevant
* information about individual students
*/
public class Student {

	protected String name;
	//$ Nice choice to use a Vector<String> here! This lets your Students have a
	//$ variable number of courses :)
	protected Vector<String> courses;

	//$ Good idea to have the constructor take the vector of courses as a
	//$ parameter as well!
	/**
	* Construct a Student with initial name, address, campusPhone, suBox, and personalCell
	*@param name
  *@param courses
  *@pre courses.size() == 4
	*/
	public Student(String name, Vector<String> courses) {
		this.name = name;
		this.courses = courses;
	}

	/**
	* Fetch the name of a Student
	*/
	public String getName() {
		return name;
	}

	/**
	* Fetch the courses a Student is taking
	*/
	//$ It's preferable to return an iterator instead of the instance variable
	//$ Or add a method getCourse(i)....
	public Vector<String> getCourses() {
		return courses;
	}


	/**
	* Determine the string representation of a Student
	*/
	public String toString() {
    String coursesRep = "";

    for (String course: courses) {
       coursesRep  += (course + " ");
    }
		return ("Name : " + getName() + "\n" + "Courses: " + coursesRep);
	}

	/**
  * Main method that is used to test if Student class functions as it is supposed to
  */
	public static void main(String[] args) {
    Vector<String> abbycourses = new Vector<String>();
    abbycourses.add("CSCI 136");
    abbycourses.add("MATH 200");
    abbycourses.add("ENGL 201");
    abbycourses.add("PHIL 101");
    Vector<String> kelseycourses = new Vector<String>();
    kelseycourses.add("CSCI 136");
    kelseycourses.add("ENVI 104");
    kelseycourses.add("STAT 101");
    kelseycourses.add("SOCI 402");
		Student abby = new Student("Abby", abbycourses);
		Student kelsey = new Student("Kelsey", kelseycourses);
		System.out.print(abby);
    System.out.println();
		System.out.print(kelsey);
    System.out.println();
	}

}
