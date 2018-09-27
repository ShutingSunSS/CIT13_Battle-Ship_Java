package battleship;

import java.util.Arrays;

class Destroyer extends Ship {
	/**
	 * The constructor will set the inherited length variable to the correct
	 * value, and to initialize the hit array.
	 */
	Destroyer() {
		length = 2;
		Arrays.fill(hit, false);
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the ship type: "destroyer".
	 */
	@Override
	public String getShipType() {
		return "destroyer";
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the ship length: "2".
	 */
	@Override
	public int getLength() {
		return 2;
	}
}
