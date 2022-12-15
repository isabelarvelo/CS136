/**
* Class to calculate the diff between two files
* */

import structure5.*;
import java.util.Scanner;

//$ Great work!

/**
* Class to calculate the diff between two files */
public class HashDiff extends Diff {

	 //hash table to store previously-calculated diffs
	 protected Hashtable<IntPair, Association<Integer, String>> myHash;

	/**
	* Constructor for diff
	* @param 	 	file1Name is the path to the original file
	* @param 		file2Name is the path to the new version of the file
	*/
	public HashDiff(String file1Name, String file2Name) {
		super(file1Name, file2Name);
		myHash = new Hashtable<IntPair, Association<Integer, String>>();
	}

	/** The recursive helper method for calulating the diff
	 *@pre remainingFile1, remainingFile2, and table are not null
	 *@param remainingFile1Index the first line of file 1 not yet diffed
	 *@param remainingFile2Index the first line of file 2 not yet diffed
	 *@return An association corresponding to the diff between remainingFile1 and
	 * remainingFile2.  The key is the cost of the diff (number of changes
	 * necessary).  The value is the diff output. */
	public Association<Integer, String> diffHelper(int remainingFile1Index, int
			remainingFile2Index) {

			//handling base cases
		 if (remainingFile1Index == file1.size())  {
			 return diffHelperHelper(remainingFile2Index, file2, ">");
		 }
		 if (remainingFile2Index == file2.size())  {
			 return diffHelperHelper(remainingFile1Index, file1, "<");
		 }

		 //Declaring associations that will store solutions
		 Association<Integer, String> case1 = null; //removing lines from both files
		 Association<Integer, String> case2 = null; //removing from file 1
		 Association<Integer, String> case3 = null; //removing from file 2


		 IntPair myPair = new IntPair(remainingFile1Index, remainingFile2Index);

		 // This iteration of  diffHelper uses a hash table to avoid recalculating
		 //the optimal solution for a given subproblem twice.
		 if (myHash.get(myPair) != null) {
			 return myHash.get(myPair);
		 }

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
		 Association<Integer, String> returnCase = tieBreakHelper(case1, case2, case3, remainingFile1Index, remainingFile2Index);

		 //put difference between at two line indices in hash table
		 myHash.put(myPair, returnCase);

		 return returnCase;

	 }

 //helper method to break ties and return the best solution
 protected Association<Integer, String> tieBreakHelper(Association<Integer, String> case1, Association<Integer, String> case2, Association<Integer, String> case3, int remainingFile1Index, int remainingFile2Index) {

	 //if case 1 is not null and has the minimum number of differences, return case 1
	 if (case1 != null && case1.getKey() < case2.getKey() && case1.getKey() < case3.getKey()) {
			 return case1;
		 } else if (case2.getKey() <= case3.getKey()) { //if case is just as good as case 3, return case 2
			//adding 1 because the line number is one ahead of the file index because it starts at 1 not 0
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
			System.out.println("Usage: java HashDiff <file1> <file2>");
			System.exit(1);
		}
		Diff diff = new HashDiff(args[0], args[1]);
		diff.findDiff();
	}
}
