/**
 * Class to calculate the diff between two files
 * */

import structure5.*;
import java.util.Scanner;
import java.lang.Math;

//$ Great work!

/**
 * Class to calculate the diff between two files */
public class Diff {

	//original file
	protected Vector<String> file1;
	//new version of the file
	protected Vector<String> file2;

	/**
	 * Constructor for diff
	 * @param file1Name is the path to the original file
	 * @param file2Name is the path to the new version of the file
	 */
	public Diff(String file1Name, String file2Name) {
		file1 = readInFile(file1Name);
		file2 = readInFile(file2Name);
	}

	/**
	 * Reads in the fine given by fileName.
	 * Note that this method requires Java 11.
	 * @param  fileName name of the file
	 * @return each line of the file as elements of a Vector */
	protected Vector<String> readInFile(String fileName) {
		Vector<String> ret = new Vector<String>();
		Scanner sc = new Scanner(new FileStream(fileName));
		while (sc.hasNext()) {
			ret.add(sc.nextLine());
		}
		return ret;
	}

	/**
	 * toString method
	 * @return the concatenation of the two files
	 */
	public String toString() {
		String ret = "-----\nFile 1:\n-----\n";
		for (String line : file1) {
			ret += line + "\n";
		}
		ret += "-----\nFile2:\n-----\n";
		for (String line : file2) {
			ret += line + "\n";
		}
		return ret;
	}

	/**
	 * Finds the diff of two files
	 * @pre 	file1 and file2 are strings holding the
	 * files we want to compare
	 * @post 	the diff is printed to the terminal
	 */
	public void findDiff() {
		System.out.println(diffHelper(0, 0).getValue());
	}


	/**
	 * The recursive helper method for calulating the diff
	 * @pre remainingFile1, remainingFile2, and table are not null
	 * @param remainingFile1Index the first line of file 1 not yet diffed
	 * @param remainingFile2Index the first line of file 2 not yet diffed
	 * @return An association corresponding to the diff between remainingFile1
	 * and remainingFile2.  The key is the cost of the diff (number of changes
	 * necessary).  The value is the diff output. */
	protected Association<Integer, String> diffHelper(int remainingFile1Index, int remainingFile2Index) {

		//base case: one file has no remaining lines
		if (remainingFile1Index == file1.size())  {
			return diffHelperHelper(remainingFile2Index, file2, ">");
		}
		 if (remainingFile2Index == file2.size())  {
			return diffHelperHelper(remainingFile1Index, file1, "<");
		}

		//instantiating associations that will store solutions
		Association<Integer, String> case1 = null; //removing lines from both files
		Association<Integer, String> case2 = null; //removing from file 1
		Association<Integer, String> case3 = null; //removing from file 2

		//check if first lines match
		if (file1.get(remainingFile1Index).equals(file2.get(remainingFile2Index))) {
			//If so, calculate recursively the optimal result with matching lines
			case1 = diffHelper(remainingFile1Index + 1, remainingFile2Index + 1);
		}

	//calculate the cost of removing a line from file1 (store solution in an Association)
	case2 = diffHelper(remainingFile1Index + 1, remainingFile2Index);

	//calculate the cost of removing a line from file2 (store solution in an Association)
  case3 = diffHelper(remainingFile1Index, remainingFile2Index + 1);

	//calculate the minimum between the three associations
	//calculate the return value using the best recursive solution
	//and return it
	return tieBreakHelper(case1, case2, case3, remainingFile1Index, remainingFile2Index);

	}

	//helper method to break ties and return the best solution
	protected Association<Integer, String> tieBreakHelper(Association<Integer, String> case1, Association<Integer, String> case2, Association<Integer, String> case3, int remainingFile1Index, int remainingFile2Index) {

		//if case 1 is not null and has the minimum number of differences, return case 1
		if (case1 != null && case1.getKey() < case2.getKey() && case1.getKey() < case3.getKey()) {
				return case1;
			} else if (case2.getKey() <= case3.getKey()) { 	//if case 2 is just as good as case 3, return case 2
			 //adding 1 to the index because the line number is one ahead of the file index because it starts at 1 not 0
			int lineNum1 = remainingFile1Index + 1;
			//add 1 to the key of case 2  for cost for taking a line from file1
			return new Association<Integer, String>(case2.getKey() + 1, "< " + (remainingFile1Index + 1) + ": " + file1.get(remainingFile1Index) + "\n" + case2.getValue());
		} else {
			int lineNum2 = remainingFile2Index + 1;
			//add 1 to the key of case 3  for cost for taking a line from file2
		  return new Association<Integer, String>(case3.getKey() + 1, "> " + (remainingFile2Index + 1) + ": " + file2.get(remainingFile2Index) + "\n" + case3.getValue());
		}
	}

	//helper method to handle base case: if we are at the end of one file, print
	//all the remaining lines of the other file
	protected Association<Integer, String> diffHelperHelper(int remainingFileIndex, Vector<String> file, String symbol) {
		String diffRep = "";
		int diff = 0;

		for (int i = remainingFileIndex; i < file.size(); i++) {
			//$ You can improve formatting here by adding a space after the symbol!
			diffRep += symbol + (i + 1) + ": " + file.get(i) + "\n";
			diff += 1;
		}

		return new Association<Integer, String>(diff, diffRep);
	}


	/**
	 * main method: two command line arguments; the first is the original file,
	 * the second is the new version to be compared to. */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Diff <file1> <file2>");
			System.exit(1);
		}
		Diff diff = new Diff(args[0], args[1]);
		diff.findDiff();
	}
}
