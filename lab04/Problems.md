# Thought Questions

Please specify the big-O running time of the following methods, along
with a brief explanation.

1. `countCannonballs(int height)`
   * O(n)
   * Explanation: We count calls to countCannonballs. We call it height-1 times
     and each call is 0(1) so the entire method is O(n).

2. `boolean isPalindrome(String str)`
   * O(n)
   * Explanation: We call isPalindrome() n/2 times, each call requires a call to
     str.substring().Since the substring is shrinking with each call, it requires
     linear time so the entire method is O(n).  


3. `boolean isBalanced(String str)`
   * O(n^2)
   * Explanation: We call isPalindrome n/2 times and each call requires O(n) time
   for the indexOf method.

4. `void subsequences(String str)`
   * O(n*2^n)
   * Explanation: subsequences() calls subSeqHelper() which is O(n*2^n). subSeqHelper() takes O(n) time to execute because the substring call takes n times. Each time it is called it calls itself twice recursively so we make 2^n calls to subSeqHelper() making subsequences() O(n * 2^n) time.

5. `void printInBinary(int number)`
   * O(log(n))
   * Explanation: printinBinary takes constant time to execute and takes log(n)
     because it can be divided by two at most log(n) times.


6. `boolean printSubSetSum(int[] nums, int targetSum)`
   * O(n)
   * Explanation: printSubsetSum() calls printSubsetSumHelper(). printSubsetSumHelper()
     takes constant time to execute and calls itself n times. Therefore,
     printSubsetSumHelper() takes O(n) meaning that printSubsetSum() takes O(n)
     time.
* $ O(2^n) or O(2^nums.length)
* $ Each call makes two recursive calls. Since we reduce our problem size by 1 in each recursive call, the length of any one "chain" of recursive calls is `n`. Pictorially, we can represent this series of recursive calls as a "tree" shape, with a "depth" of `n`. In the lowest level of this tree, we see that there are `2^n` base cases. Each recursive call does a constant amount of work, so our total runtime is `O(2^n)`


7. `int countSubSetSumSolutions(int[] nums, int targetSum)`
   * O(2^n)
   * Explanation: CountSubsetSum calls CountSubsetSumHelper which takes constant
    time to execute but calls itself 2^n times. This means that CountSubsetSumHelper
    takes O(2^n) time which means CountSubsetSum takes 2^n time.
