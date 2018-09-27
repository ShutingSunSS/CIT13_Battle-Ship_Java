package battleship;

import java.util.Scanner;

/**
 * This is the main class, containing the main method and a variable of type
 * Ocean.
 * <p>
 * The player does not know where the ships are. The initial display of the
 * ocean shows a 10*10 array of locations, all the same.
 * <p>
 * 
 * The human player tries to hit the ships, by calling out a row and a column
 * number. The computer responds with one bit of information: hit or miss. When
 * a ship is hit but not sunk, the program does not provide any information
 * about what kind of a ship was hit. However, when a ship is hit and sinks, the
 * program prints out a message "You just sank a <i>ship-type</i>". After each
 * shot, the computer redisplays the ocean with the new information.
 * <p>
 * 
 * When all ships have been sunk, the program prints out a message that the game
 * is over, and tells how many shots were required.
 * <p>
 * 
 * @author Shuting Sun
 * @version 1
 */
public class BattelshipGame {
	/** A variable of type <i>Ocean</i> */
	Ocean myOcean = new Ocean();

	/** Used for getting input from the user. */
	Scanner scanner = new Scanner(System.in);

	/**
	 * The main method just creates a BattelshipGame object and calls its
	 * <code>run</code> method.
	 * 
	 * @param args
	 *            Not used.
	 */
	public static void main(String args[]) {
		new BattelshipGame().run();
	}

	/**
	 * Prints a welcome method, then calls methods to perform each of the
	 * following actions:
	 * <ol>
	 * <li>Decide if the game is over,</li>
	 * <li>Ask the player to input row numbers and column numbers,</li>
	 * <li>Control the play of the game, and</li>
	 * <li>Print the final results.</li>
	 * </ol>
	 */
	void run() {
		myOcean.placeAllShipsRandomly(); // place ships!
		// Ship myShip = new Battleship();
		// myOcean.placeOneShipRandomly(myShip);
		System.out.println("Welcome to the game of Battleship!");
		System.out.println();
		myOcean.print();
		System.out.println();
		System.out.println("We need your instructions!");
		while (!myOcean.isGameOver()) {
			System.out.println("Row number (an integer between 0 - 9): ");
			String sRow = scanner.nextLine();
			while (!sRow.matches("^[0-9]$")) // Make sure the input is proper.
			{
				System.out.println("Please enter an integer between 0 - 9.");
				sRow = scanner.nextLine();
			}
			int row = Integer.parseInt(sRow);

			System.out.println("Column number (an integer between 0 - 9): ");
			String sCol = scanner.nextLine();
			while (!sCol.matches("^[0-9]$")) {
				System.out.println("Please enter an integer between 0 - 9.");
				sCol = scanner.nextLine();
			}
			int col = Integer.parseInt(sCol);

			controlPlay(row, col);
		}
		System.out.println();
		System.out.println("The Battleship game is over!");
		System.out.println("You shot: " + myOcean.getShotsFired());
	}

	/**
	 * Given the row number and the column number, print out the necessary
	 * message for different shot condition.
	 * 
	 * @param row
	 *            Row number provided by the player
	 * @param col
	 *            Column number provided by the user.
	 */
	void controlPlay(int row, int col) {
		boolean myFlag = myOcean.shootAt(row, col);
		Ship[][] myShips = myOcean.getShipArray();
		String shipType = myShips[row][col].getShipType();
		if (shipType.equals("empty sea")) {
			System.out.println("Miss!");
			System.out.println();
		} else if (myFlag && !myShips[row][col].isSunk()) { // real ship(afloat)
			System.out.println("Hit!");
			System.out.println();
		} else if (myFlag && myShips[row][col].isSunk()) {
			System.out.println("Hit!");
			System.out.println("You just sank a " + shipType + ".");
			System.out.println();
		} else {
			System.out.println("Splash! The ship has already sunk.");
			System.out.println();
		}
		myOcean.print();
	}
}
