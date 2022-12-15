/*
 * Recursion.java
 * Starter code for the recursion lab.
 *
 */
import structure5.*;
//how to add assert statement in isBalanced
//check if run times are correct of isbalanced
public class Recursion {

	/***** Warmup 0.2 ********************************************/

	// Note: Warmup questions are not graded, but you may wish to
	// complete & test this method since the last question builds
	// upon it.

	/**
	 * Given a set of integers and a target number, determines
	 * whethere there is a subset of those numbers that sum to the
	 * target number.
	 *
	 * @param setOfNums a set of numbers that may appear in the subset
	 * @param targetSum the target value for the subset
	 * @return true if some subset of numbers in setOfNums sums to targetSum
	 */
	public static boolean canMakeSum(int[] setOfNums, int targetSum) {
		return false;
	}


	/*****  1  ***************************************************/

	/**
* A public routine that takes the height of a pyramid of cannonballs and
*returns the number of cannonballs it contains
* @param int the height of the pyramid
* @pre the height must be a positive integer
* @post an integer that indicates the number of cannonballs the pyramid contains
*/

	public static int countCannonballs(int height) {
    if (height == 1) {
      return 1;
    } else {
      return height * height + countCannonballs(height - 1);
    }
	}


	/*****  2  ***************************************************/

	/**
	 * A method that determines if a string reads the same forwards
	 * and backwards.
	 *
	 * @param str the string to check
	 * @return true if `str` is a palindrome.
	 * @pre str must be a String
	 * @post boolean value indicating if it is a palindrome
	 */
	public static boolean isPalindrome(String str) {
		//base case: considering the empty string to be a palindrome
  if (str.length() == 0 || str.length() == 1) {		// $ Can shorten: if (str.length() <= 1)
    return true;
  }

  //check if first and last character match
  if (str.charAt(0) == str.charAt(str.length() - 1)) {
      return isPalindrome(str.substring(1, str.length() - 1)); //take out first and last element

    } else {
      return false;
    }


	}

	/*****  3  ***************************************************/

	/**
	 * Checks whether `str` is a string of properly nested and matched
	 * parens, brackets, and braces.
	 *
	 * @param str a string of parens, brackets, and braces
	 * @return true if str is properly nested and matched
	 * @pre str has no other characters other than brackets
	 * @post boolean value indicating if the parathesis are balanced
	 */
	public static boolean isBalanced(String str) {
		//basecase
    if (str.length() == 0) {
      return true;
    } else if (str.length() == 1) {	
      return false;
    } else {
      int index = str.indexOf("()");
      if (index > -1) {
        return (isBalanced(str.substring(0, index) + str.substring(index + 2)));
      }
      int index2 = str.indexOf("[]");
      if (index2 > -1) {
        return (isBalanced(str.substring(0, index2) + str.substring(index2 + 2)));
      }
      int index3 = str.indexOf("{}");
      if (index3 > -1) {
        return (isBalanced(str.substring(0, index3) + str.substring(index3 + 2)));
      }
      int index4 = str.indexOf("\"\"");
      if (index4 > -1) {
        return (isBalanced(str.substring(0, index4) + str.substring(index4 + 2)));
      }
    }
		assert false: "should never get to this line";
    return false;
	}

	/*****  4  ***************************************************/

	/**
	 * A method to print all subsequences of str (order does not matter).
	 *
	 * @param str string to print all subsequences of
	 * @pre str must be a String
	 * @post prints out all combinations
	 */
	public static void subsequences(String str) {
		subsequenceHelper(str, "");
	}

	/**
	 * Helper method for subsequences()
	 * `soFar` keeps track of the characters currently in the substring
	 *   being built
	 */
	protected static void subsequenceHelper(String str, String soFar) {
		if (str.length() == 0) {
	    System.out.println(soFar + "");
	  } else {

	  subsequenceHelper(str.substring(1), soFar + str.charAt(0));
	  subsequenceHelper(str.substring(1), soFar);
	}
	}

	/*****  5  ***************************************************/

	/**
* A public routine that prints the standard binary representation of a number
* @param int the number we want to process
* @pre the number must be an integer
* @post an integer binary representation of the number
*/
	public static void printInBinary(int number) {
		if (number > 0) {
      printInBinary(number / 2);
      System.out.print(number % 2);

    }
	}


	/*****  6a  ***************************************************/


	/**
	 * Return whether a subset of the numbers in nums add up to sum,
	 * and print them out.
	 *
	 * pre: nums must be an array and targetSum must be an int
	 * post: boolean value indicates if numbers in num add up to sum exactly
	 * Big-O runtime: O(n)
	 */
	public static boolean printSubsetSum(int[] nums, int targetSum) {
		return printSubsetSumHelper(nums,  targetSum, nums.length);
	}
	/**
	 * Helper funcition for printSubsetSum
		* n is just the indicator for which numbers we are considering adding
	 */
	public static boolean printSubsetSumHelper(int[] setOfNums, int targetSum, int n) {
    if (targetSum == 0) {
      return true;
    }
		if (n == 0) {
      return false;
    }
    if (printSubsetSumHelper(setOfNums,  targetSum, n - 1)) {
      return true;
    }

    if (printSubsetSumHelper(setOfNums, targetSum - setOfNums[n - 1], n - 1)) {
      System.out.print(setOfNums[n - 1] +  " ");
      return true;
    }

    return false;

  }

	/*****  6b  ***************************************************/

	/**
	 * Return the number of different ways elements in nums can be
	 * added together to equal sum (you do not need to print them all).
	 *
	 * pre: targetSum cannot be zero when first starting
	 * post: returns the total number of combinations the numbers in the array can add to the targetSum
	 * Big-O runtime: O(2^n)
	 */
	public static int countSubsetSumSolutions(int[] nums, int targetSum) {
		return countSubsetSumHelper(nums,  targetSum, nums.length);
	}
	/**
	 * Helper method for countSubsetSumSolutions n is keeping track of which elements we are now done with
	 */
	public static int countSubsetSumHelper(int[] setOfNums, int targetSum, int n) {
		// $ You should only be checking this when you're finished iterating thru array
		// $ as-is, you may miss some subsets.  For example, setOfNums = {2,-1,1} and targetSum = 2 should return 2, but your code will return 1
	if (targetSum == 0) {
		return 1;
	}

	if (n == 0) {
		return 0;
	}

	return countSubsetSumHelper(setOfNums,  targetSum, n - 1)
							+	countSubsetSumHelper(setOfNums, targetSum - setOfNums[n - 1], n - 1);

}


	/***********************************************************/

	/**
	 * Add testing code to main to demonstrate that each of your
	 * recursive methods works properly.
	 *
	 * Think about the so-called corner cases!
	 *
	 * Remember the informal contract we are making: as long as all
	 * predconditions are met, a method should return with all
	 * postconditions met.
	 */

	protected static void testCannonballs() {
		System.out.println("Testing cannonballs: ....");
		System.out.println(countCannonballs(3));
		System.out.println(countCannonballs(10));
	}

	protected static void testPalindrome() {
		System.out.println("Testing isPalindrome: ....");
		System.out.println(isPalindrome("mom"));
		System.out.println(isPalindrome("deeded"));
		System.out.println(isPalindrome("ablewasIereIsawelba"));
	}

	protected static void testBalanced() {
		System.out.println("Testing isBalanced: ....");
		System.out.println(isBalanced("[{[()()]}]"));
		System.out.println(isBalanced("[{[()()]}][{[()()]}]"));
		System.out.println(isBalanced("[{[()()]}{]{[()()]}]"));
	}

	protected static void testSubsequence() {
		System.out.println("Testing subsequences: ....");
		subsequences("abc");
		System.out.println();
		subsequences("CSCI136");
		System.out.println();
		subsequences("a");
		System.out.println();
		subsequences("");
		System.out.println();
	}

	protected static void testBinary() {
		System.out.println("Testing printInBinary: ....");
		printInBinary(0);
		System.out.println();
		printInBinary(30);
		System.out.println();
		printInBinary(1);
		System.out.println();
		printInBinary(110);
		System.out.println();
		printInBinary(2048);
		System.out.println();
		printInBinary(43);
		System.out.println();
    	}

	protected static void testCanMakeSum() {
		System.out.println("Testing canMakeSum: ....");
		int[] numSet = {2, 5, 7, 12, 16, 21, 30};
		System.out.println(canMakeSum(numSet, 21));
		System.out.println(canMakeSum(numSet, 22));
		System.out.println(canMakeSum(numSet, 3));
		System.out.println(canMakeSum(numSet, 30));
	}

	protected static void testPrintSubsetSum() {
		System.out.println("Testing printSubsetSum: ....");
		int[] numSet = {2, 5, 7, 12, 16, 21, 30};
		System.out.println(printSubsetSum(numSet, 21));
		System.out.println(printSubsetSum(numSet, 22));
		System.out.println(printSubsetSum(numSet, 3));
		System.out.println(printSubsetSum(numSet, 30));
	}

	protected static void testCountSubsetSum() {
		System.out.println("Testing countSubsetSumSolutions: ....");
		int[] numSet = {2, 5, 7, 12, 16, 21, 30};
		System.out.println(countSubsetSumSolutions(numSet, 21));
		System.out.println(countSubsetSumSolutions(numSet, 22));
		System.out.println(countSubsetSumSolutions(numSet, 3));
		System.out.println(countSubsetSumSolutions(numSet, 30));
	}

	/**
	 * Main method that calls testing methods to verify
	 * the functionality of each lab exercise.
	 *
	 * Please supplement the testing code with additional
	 * correctness tests as needed.
	 */
	public static void main(String[] args) {
		testCannonballs();
		testPalindrome();
		testBalanced();
		testSubsequence();
		testBinary();
		testCanMakeSum();
		testPrintSubsetSum();
		testCountSubsetSum();
	}
}
