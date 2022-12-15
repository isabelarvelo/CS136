# Thought Questions

1. What strategy might you use to pick a random number of coins such
that there is a 50 percent chance of a game with three coins, a 25
percent chance of a game with four coins, a 12.5 percent chance of a
game with five coins, and so on? (_Note_: You are not required to
implement this strategy in your Coinstrip game, but after you’ve
concretely described the strategy in text, writing the code to
implement it is often the easy part)
  * In order to design this strategy, you must think about adding one coin at a
    time. We start with a minimum amount of coins that is three. There is a
    100% probability that we will have at least three coins. Now, we want there
    to be a 50% probability of adding one more coin to have a game of four coins
    and then a 50% probability of adding another coin so there is a 25% of
    having five coins and so on. The probabilities are diminishing because you
    need to add all of the previous coins before you add the next one. You could
    use a random number generator to select value 0 or 1 and then use a while
    loop that states if the number is 1 to add another coin and reset the random
    number. The loop would keep adding additional coins the random generator
    generates the value 0 at which point the loop would stop and the number of
    coins would be set. You could also set a max number of coins, let’s say 5
    in this case, and include numCoins<5 in the while statement.
  * $ Good!


2. How might you ensure that the initial games your program generates
are not immediate wins? Can you describe a property of the game state
that would guarantee that it is possible for players to select a
sequence of _N_ moves before the game is won (you would be able to
choose the moves)?
  * In order to ensure that the initial games your program generates are not
   immediate wins you could fix the position of the first coin on the gameboard
   to be on or to the right of the second space on the board. This would mean that
   the position of the leftmost coin or Coin 0 would be determined by
   1 + randomint(0,3). This would ensure that there are not immediate wins
   because you could always move your first coin to the left and move other
   coins to fill where the first coin was. In order to guarantee that it is
   possible for players to select n moves before the game is won, you could
   ensure that coins are at least one space apart from one another by adding 2
   when using the random number generator to assign coin positions on the board.
   This coupled with the strategy mentioned to prevent automatic wins would
   make it possible for players to select a sequence of n moves for any
   Coinstrip game with n coins before the game is won.
   * $ Exactly right, good.


3. Suppose the computer could occasionally provide good hints. What
opportunities appear easy to recognize? (Some strategies may be hard
for the computer to determine, but are there “obvious” cases where the
computer should always make a particular move?)
  * The most obvious or easiest opportunity would be when all but one of the
   coins are to the leftmost end of the board. The hint would be to move the
   only remaining coin the farthest most to the left. Other than that, the
   hints could provide strategies depending on the number of coins on the game
   board not moved all the way to the left already because this determines
   whether there are an odd or even number of moves left. If there are an odd
   number of coins not moved all the way to the left on the game board, the
   computer should not move a coin the farthest to the left and wait for the
   opponent to do so. Therefore, if each player continues on to move the
   remaining coins to their greatest amount left, the computer will win.
   * $ Good answer.



4. How might you write a method, computerPlay, where the computer
plays to win? You __do not__ need to write the method, but give a
high-level idea of how you might go about implementing it.
  * If I were to write a method called computerPlay, I would first make some
   sort of assumption about how the opponent will play and design a strategy
   to respond to that. The computer could assume that the player will move the
   coin closest to the left all the way to the left. Similar to the logic
   mentioned above, if there are an odd number of moves left the computer will
   refrain from moving a coin all the way to its left to guarantee an even
   number of moves left. By controlling the number of moves left after it has
   played, the computer is ensuring that it will have the last move and
   subsequently win the game. If the opponent does not meet the assumption and
   does not move a coin to the leftmost available position the computer can
   attempt to regain control over the number of moves left by either moving a
   free coin all the way to the left or 1 away or 3 away from the leftmost
   position.
   * $ Good response, I like how you're also thinking about how to design your method such that the computer will always try to win.


5. A similar game, called Welter’s Game (after C. P. Welter, who
analyzed the game), allows the coins to pass each other. Would this
modification of the rules change your implementation significantly?
  * This modification would not change my implementation of the game
   significantly. It would mainly impact my isValidMove() method. Allowing
   coins to skip over each other would reduce the amount of conditions checked
   on each player’s move. The implementation of the computerPlay method above
   would be greatly impacted because it wouldn’t be as easy to determine the
   number of possible turns left.
  * $ Good.
