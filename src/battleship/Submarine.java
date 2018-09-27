package battleship;

import java.util.Arrays;


class Submarine extends Ship {
	/**
	 * The constructor will set the inherited length variable to the correct
	 * value, and to initialize the hit array.
	 */
	Submarine() {
		length = 1;
		Arrays.fill(hit, false);
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the ship type: "submarine".
	 */
	@Override
	String getShipType() {
		return "submarine";
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the ship length: "1".
	 */
	@Override
	int getLength() {
		return 1;
	}
}
