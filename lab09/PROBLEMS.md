# Diff Thought Questions

 1. This question is about the reason why the more complex hash function in Task 3 led to a significant speedup over the simpler hash function in Task 2. Let's say that throughout all recursive calls, remainingFile1Index is always between 0 and n-1, and remainingFile2Index is always between 0 and m-1
   * In this case, how many distinct IntPairs can there be throughout the execution of the program?  (We say that two IntPairs are distinct if either of the two integers they contain are not the same---in other words, if equals() returns false.)

   There can be m*n distinct pairs because there are m different values that could be the first integer and there are n different values that could be the second integer. Since the order of these two integers is significant, the number of distinct pairs is m * n.

   * How many possible hashCodes can be output by all IntPairs? (Hint: What is the maximum value that can be output by hashCode()? What is the minimum value?)

   The maximum value that can be the output of IntPairs is the size of file 1 + the size of file 2. Those two numbers added would create the biggest IntPair. The minimum value is 0, which would occur if both of the file indices in are 0. So,
   file1.size() + file2.size() hashCodes can be output by all IntPairs.

   * $ Good, but make sure to put your expression in terms of m and n

2. Rather than using an IntPair or a BetterIntPair in this lab, we could have used an Association<Integer, Integer>. What would be the downside of doing this?

  If we had an association instead using an IntPair or BetterIntPair we would have a repeat of the keys in the association. Since the key corresponds to the line in File 1 there are multiple times when we store the same line in File 1 with different lines in file 2. Since Associations would need different distinct keys, this would not work.

  * $ Great answer!
