package battleship;

import java.util.Arrays;

class EmptySea extends Ship {
	/**
	 * The constructor will set the inherited length variable to 1
	 */
	EmptySea() {
		length = 1;
		Arrays.fill(hit, false);
	}

	/**
	 * The method overrides shootAt(int row, int column) that is inherited from
	 * Ship.
	 * 
	 * @return Always return false to indicate that nothing was hit.
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}

	/**
	 * The method overrides isSunk() that is inherited from Ship.
	 * 
	 * @return Always return false to indicate that you did not sink anything.
	 */
	@Override
	boolean isSunk() {
		return false; // false!!
	}

	/**
	 * Return a single-character String to use in the Ocean's print method.
	 * 
	 * @return Returns a single character String "-" to use in Ocean's print
	 *         method.
	 */
	@Override
	public String toString() {
		return "-";
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the length of empty sea: "1".
	 */
	@Override
	int getLength() {
		return 1;
	}

	/**
	 * Substantiate the abstract class.
	 * 
	 * @return Return the type of the object: "empty sea".
	 */
	@Override
	String getShipType() {
		return "empty sea";
	}
}