I am the sole author of the work in this repository
1. (1 point) What is the best solution to the 15-block problem?
  * The best subset (left stack) is: [4, 5, 6, 10, 11, 12, 13] = 20.23411306140849

2. (3 points) How long does it take your program to find the answer to the 20-block
problem? Based on the time taken to solve the 20-block problem, about how long
do you expect it would take to solve the 21-block problem? What is the actual
time? How about the 25-block problem? Do these agree with your expectations,
given the time complexity of the problem? What about the 40- and 50-block
problems? (These will take a very long time. Just estimate based on the run
times of the smaller problems). To answer this question run each program 3 times and fill in the table below with the *median*
(you may wish to edit this file directly on GitLab so that you can view the
table rendered using markdown formatting---use "preview" to see how it looks)



| Problem Size  | Estimated time | Actual Time |
| :------------ | :------------: | :---------: |
| 20 blocks     |       --       |  7.841s     |
| 21 blocks     |    (15.682)    |  14.644s    |
| 22 blocks     |    (31.364)    |  31.750s    |
| 25 blocks     |    (4m10.908s) |  5m36.455s  |
| 40 blocks     |  (8221884.416) |     --      |
| 50 blocks     | (8419209641.98)|     --      |

* $ 20 blocks should be less than 5 s---it's fast on my machine; unclear why this descripancy exists 

  * Explain how you arrived at your estimates.
    * I arrived to these estimates because the TwoTower program has O(2^n)
      running time. There are exactly 2n subsets of n blocks. Logically, that
      means that there are 2(n+1) subsets of n+1 blocks and 2(n+2) subsets of
      n+2 blocks etc. Each time we increase our problem size by one block, the
      amount of possible subsets doubles. Therefore, the amount of subsets that
      TwoTowers has to consider in order to find the best and second best subsets
      also doubles, leading us to 0(2^n) running time. If it takes 7.841 to
      find the answer for 20 blocks, I would suspect it would take twice as long
      (15.682) to find the answer for 21 blocks and twice as long as that
      (31.364) to find the answer for 22 blocks etc. Since 40 is 20 blocks
      larger than 20, I multiplied the running time for 20 blocks by 2^20 to
      find the estimated running time for 40 blocks (8221884.416 ~ 95 days) and
      by 2^30 to estimate the running time for 50 blocks (8419209641.98 ~ 266 years).
	* $ Since you need to add each subset, it's actually O(n*2^n) time



  * Do the actual timings agree with your expectations, given the time complexity of the problem?
    * Yes, the actual running times agree with our expectations because each
      one block increment results in a running time that is about twice as long.

      * $ good!

3. (1 point) This method of exhaustively checking the subsets of blocks will not work for
very large problems. Consider, for example, the problem with 50 blocks: there
are 2^50 different subsets. One approach is to repeatedly pick and evaluate
random subsets of blocks (e.g., stop the computation after 1 second of elapsed
time, printing the best subset found). How would you implement `randomSubset`, a
new `SubsetIterator` method that returns a random subset? (Describe your
strategy. You do not need to actually implement it.)

  * Since each subset corresponds to a bit representation of numbers from 0
  * to 2^n, I would create a random number generator that generates a number
  * between 0 and 2^n. I would then store the vector of blocks represented
  * by that random number and use the subset iterator to go through all
  * the possible subsets of blocks within that random subset. Using a Java
  * timer class, I would allow this loop to run for 1 second and after the loop
  * I would store and find the best and second best subset using similar methods
  * to the ones implemented in TwoTowers.java. I would then loop through the above
  * program x amount of times (creating nested for-loops). Lastly, I would compare
  * all of the stored best and second best outcomes of each subset to find the two
  * subsets with the smallest sums and print them out.

  * $ Good
