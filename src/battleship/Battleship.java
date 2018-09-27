package battleship;

import java.util.Arrays;


class Battleship extends Ship {
	/**
	 * The constructor will set the inherited length variable to the correct
	 * value, and to initialize the hit array.
	 */
	Battleship() {
		length = 4;
		Arrays.fill(hit, false);
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the ship type: "battleship".
	 */
	@Override
	String getShipType() {
		return "battleship";
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the ship length: "4".
	 */
	@Override
	int getLength() {
		return 4;
	}
}
