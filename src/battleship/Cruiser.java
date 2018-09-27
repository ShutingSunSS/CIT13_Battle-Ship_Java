package battleship;

import java.util.Arrays;

class Cruiser extends Ship {
	/**
	 * The constructor will set the inherited length variable to the correct
	 * value, and to initialize the hit array.
	 */
	Cruiser() {
		super();
		length = 3;
		Arrays.fill(hit, false);
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the ship type: "cruiser".
	 */
	@Override
	String getShipType() {
		return "cruiser";
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the ship length: "3".
	 */
	@Override
	int getLength() {
		return 3;
	}
}
