package battleship;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// Test every non-private method in the Ship class.

public class ShipTest {
	Ship myShip;
	Ocean myOcean;

	@Before
	public void setUp() {
		myOcean = new Ocean();
	}

	@Test
	public void testGetLength() {
		myShip = new Submarine();
		assertEquals(myShip.getLength(), 1);
		myShip = new Destroyer();
		assertEquals(myShip.getLength(), 2);
		myShip = new Cruiser();
		assertEquals(myShip.getLength(), 3);
		myShip = new Battleship();
		assertEquals(myShip.getLength(), 4);
		myShip = new EmptySea();
		assertEquals(myShip.getLength(), 1);
	}

	@Test
	public void testGetBowRow() {
		myShip = new Submarine();
		myShip.placeShipAt(0, 0, true, myOcean);
		assertEquals(myShip.getBowRow(), 0);

		myShip = new Destroyer();
		myShip.placeShipAt(2, 3, true, myOcean);
		assertEquals(myShip.getBowRow(), 2);

		myShip = new Cruiser();
		myShip.placeShipAt(4, 6, false, myOcean);
		assertEquals(myShip.getBowRow(), 4);

		myShip = new Battleship();
		myShip.placeShipAt(5, 7, false, myOcean);
		assertEquals(myShip.getBowRow(), 5);
	}

	@Test
	public void testGetBowColumn() {// If we put unreasonable values here, the
									// test will give errors!
		myShip = new Submarine();
		myShip.placeShipAt(9, 9, true, myOcean);
		assertEquals(myShip.getBowColumn(), 9);

		myShip = new Destroyer();
		myShip.placeShipAt(7, 8, true, myOcean);
		assertEquals(myShip.getBowColumn(), 8);

		myShip = new Cruiser();
		myShip.placeShipAt(7, 0, false, myOcean);
		assertEquals(myShip.getBowColumn(), 0);

		myShip = new Battleship();
		myShip.placeShipAt(6, 6, false, myOcean);
		assertEquals(myShip.getBowColumn(), 6);
	}

	@Test
	public void testIsHorizontal() {
		myShip = new Submarine();
		myShip.placeShipAt(9, 9, true, myOcean);
		assertTrue(myShip.isHorizontal());

		myShip = new Destroyer();
		myShip.placeShipAt(7, 8, true, myOcean);
		assertTrue(myShip.isHorizontal());

		myShip = new Cruiser();
		myShip.placeShipAt(7, 0, false, myOcean);
		assertFalse(myShip.isHorizontal());

		myShip = new Battleship();
		myShip.placeShipAt(6, 6, false, myOcean);
		assertFalse(myShip.isHorizontal());
	}

	@Test
	public void testSetBowRow() {
		myShip = new Submarine();
		myShip.setBowRow(0);
		assertEquals(myShip.getBowRow(), 0);

		myShip = new Destroyer(); // It is some unreasonable values.
		myShip.setBowRow(12);
		assertEquals(myShip.getBowRow(), 12);

		myShip = new Cruiser();
		myShip.setBowRow(3);
		assertEquals(myShip.getBowRow(), 3);

		myShip = new Battleship();
		myShip.setBowRow(7);
		assertEquals(myShip.getBowRow(), 7);
	}

	@Test
	public void testSetBowColumn() {
		myShip = new Submarine();
		myShip.setBowColumn(9);
		assertEquals(myShip.getBowColumn(), 9);

		myShip = new Destroyer(); // It is some unreasonable values.
		myShip.setBowColumn(-1);
		assertEquals(myShip.getBowColumn(), -1);

		myShip = new Cruiser();
		myShip.setBowColumn(0);
		assertEquals(myShip.getBowColumn(), 0);

		myShip = new Battleship();
		myShip.setBowColumn(5);
		assertEquals(myShip.getBowColumn(), 5);
	}

	@Test
	public void testSetHorizontal() {
		myShip = new Submarine();
		myShip.setHorizontal(false);
		assertFalse(myShip.isHorizontal());

		myShip = new Destroyer();
		myShip.setHorizontal(true);
		assertTrue(myShip.isHorizontal());

		myShip = new Cruiser();
		myShip.setHorizontal(false);
		assertFalse(myShip.isHorizontal());

		myShip = new Battleship();
		myShip.setHorizontal(true);
		assertTrue(myShip.isHorizontal());
	}

	@Test
	public void testGetShipType() {
		myShip = new Submarine();
		assertEquals(myShip.getShipType(), "submarine");
		myShip = new Destroyer();
		assertEquals(myShip.getShipType(), "destroyer");
		myShip = new Cruiser();
		assertEquals(myShip.getShipType(), "cruiser");
		myShip = new Battleship();
		assertEquals(myShip.getShipType(), "battleship");
		myShip = new EmptySea();
		assertEquals(myShip.getShipType(), "empty sea");
	}

	@Test
	public void testOkToPlaceShipAt() { // Put big ships first
		// Test boundaries.
		Ship ship1 = new Battleship();
		assertFalse(ship1.okToPlaceShipAt(9, 7, true, myOcean));
		assertTrue(ship1.okToPlaceShipAt(9, 6, true, myOcean));
		assertTrue(ship1.okToPlaceShipAt(6, 0, false, myOcean));

		Ship ship2 = new Cruiser();
		assertFalse(ship2.okToPlaceShipAt(-1, -1, true, myOcean));
		assertTrue(ship2.okToPlaceShipAt(0, 0, false, myOcean));

		Ship ship3 = new Destroyer();
		assertFalse(ship3.okToPlaceShipAt(12, 9, false, myOcean));

		Ship ship4 = new Submarine();
		assertFalse(ship4.okToPlaceShipAt(10, 9, false, myOcean));

		// With other ships. Interaction.
		assertTrue(ship1.okToPlaceShipAt(6, 9, false, myOcean));
		ship1.placeShipAt(6, 9, false, myOcean);
		assertFalse(ship4.okToPlaceShipAt(5, 8, false, myOcean));
		assertFalse(ship2.okToPlaceShipAt(5, 9, true, myOcean));
		assertFalse(ship4.okToPlaceShipAt(6, 8, true, myOcean));
		assertFalse(ship2.okToPlaceShipAt(7, 8, false, myOcean));
		assertFalse(ship4.okToPlaceShipAt(8, 8, true, myOcean));
		assertFalse(ship2.okToPlaceShipAt(9, 8, false, myOcean));

		assertTrue(ship2.okToPlaceShipAt(4, 3, true, myOcean));
		ship2.placeShipAt(4, 3, true, myOcean);
		assertFalse(ship3.okToPlaceShipAt(5, 2, false, myOcean));
		assertFalse(ship3.okToPlaceShipAt(5, 3, true, myOcean));
		assertFalse(ship3.okToPlaceShipAt(5, 4, false, myOcean));
		assertFalse(ship3.okToPlaceShipAt(5, 5, true, myOcean));
		assertFalse(ship3.okToPlaceShipAt(5, 6, false, myOcean));

		assertFalse(ship3.okToPlaceShipAt(3, 2, true, myOcean));
		assertFalse(ship4.okToPlaceShipAt(3, 3, false, myOcean));
		assertFalse(ship4.okToPlaceShipAt(3, 4, false, myOcean));
		assertFalse(ship4.okToPlaceShipAt(3, 5, true, myOcean));
		assertFalse(ship3.okToPlaceShipAt(3, 6, false, myOcean));

		assertFalse(ship3.okToPlaceShipAt(4, 2, true, myOcean));
		assertFalse(ship4.okToPlaceShipAt(4, 3, false, myOcean));
		assertFalse(ship4.okToPlaceShipAt(4, 4, false, myOcean));
		assertFalse(ship4.okToPlaceShipAt(4, 5, true, myOcean));
		assertFalse(ship3.okToPlaceShipAt(4, 6, false, myOcean));

		ship3.placeShipAt(0, 2, false, myOcean);
		ship4.placeShipAt(5, 7, false, myOcean);
	}

	@Test
	public void testPlaceShipAt() {
		Ship[][] myShips = myOcean.getShipArray();
		Ship ship1 = new Battleship();
		ship1.placeShipAt(0, 0, true, myOcean);
		assertEquals(ship1.getBowRow(), 0);
		assertEquals(ship1.getBowColumn(), 0);
		assertEquals(ship1.isHorizontal(), true);
		assertEquals(ship1.getLength(), 4);
		assertEquals(ship1.getShipType(), "battleship");
		assertEquals(myShips[0][0].getShipType(), "battleship");
		assertEquals(myShips[0][1].getShipType(), "battleship");
		assertEquals(myShips[0][2].getShipType(), "battleship");
		assertEquals(myShips[0][3].getShipType(), "battleship");
		assertEquals(myShips[0][4].getShipType(), "empty sea");
		assertEquals(myShips[1][0].getShipType(), "empty sea");

		assertTrue(ship1 == myShips[0][0]);
		assertTrue(myShips[0][0] == myShips[0][1]); // Test reference
		assertFalse(myShips[2][7] == myShips[9][9]); // They contain different
														// "empty sea" object.

		Ship ship2 = new Cruiser();
		ship2.placeShipAt(0, 1, false, myOcean); // overlap with ship1, but it
													// does not matter in test.
		assertEquals(myShips[0][1].getShipType(), "cruiser");
		assertEquals(myShips[1][1].getShipType(), "cruiser");
		assertEquals(myShips[2][1].getShipType(), "cruiser");
		assertEquals(myShips[0][0].getShipType(), "battleship");

		/*
		 * Ship ship3 = new Destroyer(); ship3.placeShipAt(-1, 0, true,
		 * myOcean); // Seems like we do not need to consider this.
		 * assertEquals(myShips[0][0].getShipType(), "destroyer");
		 */
	}

	@Test
	public void testShootAt() {
		Ship[][] myShips = myOcean.getShipArray();
		Ship ship1 = new Battleship();
		ship1.placeShipAt(1, 2, false, myOcean);
		assertEquals(myShips[1][2], ship1);
		assertTrue(myShips[2][2].shootAt(1, 2)); // refer to the same ship.
		assertTrue(ship1.shootAt(2, 2));
		assertTrue(ship1.shootAt(3, 2));
		assertTrue(ship1.shootAt(4, 2));

		assertFalse(ship1.shootAt(5, 2));

		assertFalse(ship1.shootAt(1, 2)); // the ship is sunk
		assertEquals(myShips[1][2].getShipType(), "battleship");

		assertFalse(ship1.shootAt(0, 0)); // empty sea, always return false
		assertFalse(ship1.shootAt(3, 9));
	}

	@Test
	public void testIsSunk() {
		Ship[][] myShips = myOcean.getShipArray();
		Ship ship1 = new Cruiser();
		ship1.placeShipAt(8, 7, true, myOcean);
		assertFalse(ship1.isSunk());
		ship1.shootAt(8, 7);
		assertFalse(ship1.isSunk());
		ship1.shootAt(8, 8);
		assertFalse(ship1.isSunk());
		ship1.shootAt(8, 9);
		assertTrue(ship1.isSunk());

		assertFalse(myShips[2][7].isSunk()); // empty sea, always return false
		assertFalse(myShips[0][0].isSunk());

	}

	@Test
	public void testToString() {
		Ship[][] myShips = myOcean.getShipArray();
		Ship ship1 = new Destroyer();
		ship1.placeShipAt(8, 3, true, myOcean);
		assertEquals(ship1.toString(), "S");
		ship1.shootAt(8, 3);
		assertEquals(ship1.toString(), "S");
		ship1.shootAt(8, 4);
		assertEquals(ship1.toString(), "x");

		myShips[1][1].shootAt(1, 1);
		assertEquals(myShips[1][1].toString(), "-");

		Ship ship2 = new Submarine();
		ship2.placeShipAt(1, 1, true, myOcean);
		assertEquals(ship2.toString(), "S");
		ship2.shootAt(1, 1);
		assertEquals(ship2.toString(), "x");

		Ship ship3 = new Battleship();
		ship3.placeShipAt(2, 4, true, myOcean);
		assertEquals(ship3.toString(), "S");
		ship3.shootAt(3, 4);
		assertEquals(ship3.toString(), "S");
		ship3.shootAt(5, 4);
		assertEquals(ship3.toString(), "S");
		ship3.shootAt(4, 4);
		assertEquals(ship3.toString(), "S");
		ship3.shootAt(2, 4);
		assertEquals(ship3.toString(), "x");

		myShips[9][9].shootAt(9, 9);
		assertEquals(myShips[9][9].toString(), "-"); // empty sea

		assertEquals(myShips[7][4].toString(), "-"); // empty sea
	}
}
