import java.util.Random;
import java.util.Scanner;

public class CoinStrip implements TwoPlayerGame {
	/**
	This class is designed to represent the "board" and "game state" for the
	Silver Dollar Game. The game board will be represented as an array of type
	 char, where each array index represents a coin or an empty square and the
	 character stored at each index represents either an empty space or the number
	  of the coin in that square.
*/
	/*$
	 Okay, this is entirely not your fault, but my windows system couldn't identify
	 a character in 6 of your comment, so I went ahead and changed it!
	*/

	//number of coins in the game
	private int numCoins;

	//the total number of spaces on the gameboard
	private int totalSize;

	//array that represents the locations of the coins on gameboard
	private int coins[];

	//for random numbers
	private Random rng;

	//number of coins in the game
	private int whichCoin;

	//number of coins in the game
	private int numSpaces;


	//constructor
	public CoinStrip() {
		rng = new Random();

		//random number of coins between 3 and 6
		numCoins = 3 + rng.nextInt(3);
		createArray(numCoins);

		//creates total size of board
		totalSize = coins[numCoins - 1];
	}

	/*$
		On design, this method can be be private because it will only ever be used
		by the constructor.
	*/
	//helper method for constructor to create coins array
	public void createArray(int numCoins) {
		//create empty game array
		coins = new int[numCoins];

		//random starting position for first coin
		coins[0] = 1 + rng.nextInt(4);

		//random number generator gives random coin positions on game board
		for (int i = 0; i < numCoins - 1; i++) {
			coins[i + 1] = coins[i] + 1 + rng.nextInt(3);
			}
		}

/*The move is not valid if the user does not move the coin to the left, jumps
another coin, lands on another coin, or lands off the board*/

	public boolean isValidMove(int whichCoin, int numSpaces) {
		//variables that track current index and index of potential move
		if (!isValidCoin(whichCoin)) {
			System.out.println("Illegal move: not a valid coin");
			return false;
		}
		int preMove = coins[whichCoin];
		int postMove = coins[whichCoin] - numSpaces;
		int boundary;

		if (whichCoin == 0) {
			boundary = -1;
		} else {
			boundary = coins[whichCoin-1];
		}

		if (numSpaces > 0) {                  //make sure coin moves left on board
			if (postMove > boundary) {               //checking for skips or jumps
				return true;
			}
			/*$
				Should your program also print this error message if I try to move the
				0th coin off the board?
			*/
			System.out.println("Illegal move: can't skip or land on another coin");
			return false;
			//returns true if all conditions for a legal move are met
		} else {
			System.out.println("Illegal move: have to move left on the board");
			return false;
		}
	}



	//can only move coins that are on the game board (indexed from 0 to numCoins-1)
	public boolean isValidCoin(int whichCoin) {
		return (whichCoin >= 0 && whichCoin < getNumCoins());
	}

	public void makeMove(int whichCoin, int numSpaces) {
		if (!isValidMove(whichCoin, numSpaces)) {
			System.out.println("Please Try Again");
			System.out.println("Choose a coin to move");   //prompt user to play again
		} else {
			coins[whichCoin] = coins[whichCoin] - numSpaces;  //make move if its valid
			}
		}

	//Game is over if leftmost positions are filled by number of coins
	public boolean isGameOver() {
		return (coins[getNumCoins() - 1] == numCoins - 1);
	}

	//Returns the number of coins on the CoinStrip gameboard.
	public int getNumCoins() {
		return numCoins;
	}

	/**
	 * Returns the 0-indexed location of a specific coin. Coins are
	 * also zero-indexed. So, if the CoinStrip had 3 coins, the coins
	 * would be indexed 0, 1, or 2.
	 * @param coinNum the 0-indexed coin number
	 * @return the 0-indexed location of the coin on the CoinStrip
	 */

	public int getCoinPosition(int coinNum) {
		while (coinNum >= numCoins) {
			System.out.println("Not a valid coin number");
		}
		return coins[coinNum];
	}

	/*$
		Great job with this method, but this method should be in the toString()
		method. Remember that the toString() method is inherited from the Object
		class in Java, and should be overridden for classes that should have
		a string representation.
	*/
	public String displayBoard() {
		//create border for strip of paper
		String border = "\n" + "===============================" + "\n";

		//initialize string representation
		String stringRep = "";

		stringRep += border;

		//spaces before first coin
		int spaces = coins[0];

		//drawing spaces if there are any to the left of the first coin
		for (int i = 0; i < spaces; i++) {
			stringRep += "-|";
		}

		//insert coins in appropriate position on gameboard
		for (int i = 0; i < getNumCoins() - 1; i++) {
			stringRep += Integer.toString(i) + "|";
			spaces = coins[i + 1] - coins[i] - 1; //update spaces between current and next coin
				for (int j = 0; j < spaces; j++) {
					stringRep += "-|"; 		//display empty spaces
			}
		}

		//represent last coin
		stringRep += getNumCoins() - 1 + "|";


		//ensure the gameboard string does not shrink when moving last coin
		int remainingSpaces = totalSize - coins[getNumCoins() - 1];
		for (int i = 0; i < remainingSpaces; i++) {
			stringRep += "-|";
		}

		//add bottom border
		stringRep += border;

		return stringRep;
}


/*loop through coins

    /**
     * `public static void main(String[] args)` is a program's entry point.
     * This main method implements the functionality to play the Coinstrip
     * game.
     *
     * @param Command-line arguments are ignored.
     */

    public static void main(String[] args) {

			Scanner in = new Scanner(System.in);

	    System.out.println("Welcome to the Silver Dollar Game!");

			CoinStrip game = new CoinStrip();

			//play while game is not over
			while (!game.isGameOver()) {
	    	System.out.println();
	   		System.out.println(game.displayBoard());

				//ask for which coin to move
				System.out.println("Choose a coin to move");
				int whichCoin = in.nextInt();

				//ask for number of spaces to move coin left
				System.out.println("Choose how many spaces to move coin:");
				int numSpaces = in.nextInt();

				//play
				game.makeMove(whichCoin, numSpaces);

			}
			//Display message when game is over
			System.out.println("Game is Over!");
    }
}
