package battleship;

import java.lang.Math;


/**
 * This class describes characteristics common to all ships.
 * <p>
 * It has subclasses:
 * <p>
 * <i>Battleship</i> <i>Cruiser</i> <i>Destroyer</i> <i>Submarine</i>
 * <i>EmptySea</i>
 * 
 * @author Shuting Sun
 * @version 1
 */
public abstract class Ship {
	/** The row (0 to 9) which contains the bow (front) of the ship. */
	int bowRow;
	/** The column (0 to 9) which contains the bow (front) of the ship. */
	int bowColumn;
	/**
	 * The number of squares occupied by the ship. An "empty sea" location has
	 * length 1.
	 */
	int length;
	/** True if the ship occupies a single row, false otherwise. */
	boolean horizontal;
	/**
	 * An array of booleans telling whether that part of the ship has been hit.
	 */
	boolean[] hit = new boolean[4];

	/**
	 * An abstract class, returns the length of this particular ship.
	 * <p>
	 * (An abstract "ship" does not have fixed length.)
	 */
	abstract int getLength();

	/**
	 * Getters
	 * 
	 * @return Returns <i>bowRow</i>
	 */
	int getBowRow() {
		return bowRow;
	}

	/**
	 * Getters
	 * 
	 * @return Returns <i>bowRow</i>
	 */
	int getBowColumn() {
		return bowColumn;
	}

	/**
	 * Getters
	 * 
	 * @return Returns <i>horizontal</i>
	 */
	boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Setters
	 * 
	 * @param row
	 *            Give the row value to the bowRow.
	 */
	void setBowRow(int row) {
		bowRow = row;
	}

	/**
	 * Setters
	 * 
	 * @param row
	 *            Give the column value to the bowColumn.
	 */
	void setBowColumn(int column) {
		bowColumn = column;
	}

	/**
	 * Setters
	 * 
	 * @param row
	 *            Give the horizontal value to the object.
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * An abstract class, returns the type of this ship.
	 */
	abstract String getShipType();

	/**
	 * Returns true if it is okay to put a ship of this length with its bow in
	 * this location, with the given orientation. Returns false otherwise. The
	 * ship must not: 1. overlap another ship 2. touch another ship (vertically,
	 * horizontally, or diagonally) 3. stick out beyond the array Just say
	 * whether it is legal to do so and do nothing else.
	 * 
	 * @param row
	 *            Put the bow of the ship in this row.
	 * @param column
	 *            Put the bow of the ship in this column.
	 * @param horizontal
	 *            The orientation of the ship.
	 * @param ocean
	 *            An object, ships array is available from this object.
	 * @return Returns true if it is okay to put a ship of this length with its
	 *         bow in this location, with the given orientation. Returns false
	 *         otherwise.
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		Ship[][] myShips = ocean.getShipArray();
		if (row > 9 || row < 0 || column > 9 || column < 0)
			return false;
		else if (horizontal) { // the ship's orientation is horizontal
			if (column + length > 10) // Use the instance variable "length" here
				return false;
			else {
				for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, 9); i++) {
					for (int j = Math.max(column - 1, 0); j <= Math.min(column + length, 9); j++) {
						if (!myShips[i][j].getShipType().equals("empty sea"))
							return false;
					}
				}
				return true;
			}
		} else { // the ship's orientation is vertical
			if (row + length > 10)
				return false;
			else {
				for (int i = Math.max(row - 1, 0); i <= Math.min(row + length, 9); i++) {
					for (int j = Math.max(column - 1, 0); j <= Math.min(column + 1, 9); j++) {
						if (!myShips[i][j].getShipType().equals("empty sea"))
							return false;
					}
				}
				return true;
			}
		}
	}

	/**
	 * Puts the ship in the ocean. This involves giving values to the bowRow,
	 * bowColumn, and horizontal instance variables in the ship, and it also
	 * involves putting a reference to the ship in each of 1 or more locations
	 * (up to 4) in the ships array in the Ocean object.
	 * 
	 * @param row
	 *            Put the bow of the ship in this row.
	 * @param column
	 *            Put the bow of the ship in this column.
	 * @param horizontal
	 *            The orientation of the ship.
	 * @param ocean
	 *            An object, ships array is available from this object.
	 * @return Puts the ship in the ocean.
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		Ship[][] myShips = ocean.getShipArray();
		bowRow = row;
		bowColumn = column;
		this.horizontal = horizontal;
		if (horizontal) {
			for (int i = column; i < column + length; i++)
				myShips[row][i] = this; // references
		} else {
			for (int i = row; i < row + length; i++)
				myShips[i][column] = this; // references
		}
	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship has
	 * not already been sunk, mark that part of the ship as "hit" (in the hit
	 * array, 0 indicates the bow) and return true, otherwise return false.
	 * 
	 * @param row
	 *            The row number of a given location.
	 * @param column
	 *            The column number of a given location.
	 * @return If a part of the ship occupies the given row and column, and the
	 *         ship has not already been sunk, mark that part of the ship as
	 *         "hit" (in the hit array, 0 indicates the bow) and return true,
	 *         otherwise return false.
	 */
	boolean shootAt(int row, int column) {
		int myRow = Math.abs(row - bowRow);
		int myColumn = Math.abs(column - bowColumn);
		if (isSunk())
			return false;
		else if (Math.min(myRow, myColumn) == 0 && Math.max(myRow, myColumn) < length) {
			hit[Math.max(myRow, myColumn)] = true;
			return true;
		} else
			return false;
	}

	/**
	 * Return true if every part of the ship has been hit, false otherwise.
	 * 
	 * @return Return true if every part of the ship has been hit, false
	 *         otherwise.
	 */
	boolean isSunk() {
		for (int i = 0; i < length; i++) {
			if (hit[i] == false) // There is some part of the ship that has not
									// been hit.
				return false;
		}
		return true;
	}

	/**
	 * Returns a single character String to use in the Ocean's print method. 1.
	 * the method should return "x" if the ship has been sunk 2. "S" if it has
	 * not been sunk 3. be used to print out locations in the ocean that have
	 * been shot at, and it should not be used to print locations that have not
	 * been shot at.
	 * 
	 * @return Returns a single character String to use in the Ocean's print
	 *         method.
	 */
	@Override
	public String toString() {
		if (isSunk())
			return "x";
		else
			return "S";
	}
}

/**
 * Each of these classes has a constructor, the purpose of which is to set the
 * inherited length variable to the correct value, and to initialize the hit
 * array.
 */

