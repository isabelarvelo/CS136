//We are the sole authors of the work in this repository.

/**
* A class that creates Student objects and contains methods to access relevant
* information about individual students
*/
public class Student {

	protected String name;
	protected String address;
	protected long campusPhone;
	protected Integer suBox;
	protected long personalCell;

	/**
	* Construct a Student with initial name, address, campusPhone, suBox, and personalCell
	*@param
	*/
	public Student(String name, String address, long campusPhone, Integer suBox, long personalCell) {
		this.name = name;
		this.address = address;
		this.campusPhone = campusPhone;
		this.suBox = suBox;
		this.personalCell = personalCell;
	}

	/**
	* Fetch the name of a Student
	*/
	public String getName() {
		return name;
	}

	/**
	* Fetch the address of a Student
	*/
	public String getAddress() {
		return address;
	}

	/**
	* Fetch the campus phone number of a Student
	*/
	public long getCamPhone() {
		return campusPhone;
	}

	/**
	* Fetch the campus SU box of a Student
	*/
	public Integer getSubox() {
		return suBox;
	}

	/**
	* Fetch the cell phone number of a Student
	*/
	public long getCell() {
		return personalCell;
	}

	/**
	* Determine the string representation of a Student
	*/
	public String toString() {
		return ("Name : " + name + "\n" + "Address: " + address + "\n"
		+ "Campus Phone: " + campusPhone + "\n" + "Su Box: " + suBox + "\n"
		+ "personal Cell: " + personalCell + "\n");
	}

	/**
  * Main method that is used to test if Student class functions as it is supposed to
  */
	public static void main(String[] args) {
		long num = 123;
		Student abby = new Student("abby", "23 williams road", num, 12, num);
		Student kelsey = new Student("kelsey", "28 williams road", num, 20, num);
		System.out.print(abby);
		System.out.print(kelsey);
	}

}
