package battleship;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	Ocean myOcean;

	@Before
	public void setUp() {
		myOcean = new Ocean();
	}

	@Test
	public void testOcean() {
		Ship[][] myShips = myOcean.getShipArray();
		assertEquals(myOcean.getShotsFired(), 0);
		assertEquals(myOcean.getHitCount(), 0);
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				assertEquals(myShips[i][j].getShipType(), "empty sea");
	}

	@Test
	public void testPlaceOneShipRandomly() {
		Ship[][] myShips = myOcean.getShipArray();
		Ship ship1 = new Battleship();
		myOcean.placeOneShipRandomly(ship1);

		int count1 = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (myShips[i][j].getShipType().equals("battleship"))
					count1++;
			}
		}
		assertTrue(count1 == 4);

		Ship ship2 = new Cruiser();
		myOcean.placeOneShipRandomly(ship2);
		int count2 = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (myShips[i][j].getShipType().equals("cruiser"))
					count2++;
			}
		}
		assertTrue(count2 == 3);

		Ship ship3 = new Destroyer();
		myOcean.placeOneShipRandomly(ship3);
		int count3 = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (myShips[i][j].getShipType().equals("destroyer"))
					count3++;
			}
		}
		assertTrue(count3 == 2);

		Ship ship4 = new Submarine();
		myOcean.placeOneShipRandomly(ship4);
		int count4 = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (myShips[i][j].getShipType().equals("submarine"))
					count4++;
			}
		}
		assertTrue(count4 == 1);
	}

	@Test
	public void testPlaceAllShipsRandomly() {
		Ship[][] myShips = myOcean.getShipArray();
		myOcean.placeAllShipsRandomly();
		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (myShips[i][j].toString().equals("S"))
					count++;
			}
		}
		assertTrue(count == 20);
	}

	@Test
	public void testIsOccupied() {
		Ship myShip = new Battleship();
		myShip.placeShipAt(2, 2, false, myOcean);
		assertTrue(myOcean.isOccupied(2, 2));
		assertTrue(myOcean.isOccupied(3, 2));
		assertTrue(myOcean.isOccupied(4, 2));
		assertTrue(myOcean.isOccupied(5, 2));

		assertFalse(myOcean.isOccupied(0, 0));
		assertFalse(myOcean.isOccupied(9, 9));
		assertFalse(myOcean.isOccupied(-2, 0));
	}

	@Test
	public void testShootAt() {
		Ship[][] myShips = myOcean.getShipArray();
		Ship ship1 = new Submarine();
		ship1.placeShipAt(4, 7, true, myOcean);
		assertTrue(myOcean.shootAt(4, 7));
		assertTrue(myOcean.firedUpon[4][7]);

		assertFalse(myOcean.firedUpon[0][0]);
		assertFalse(myOcean.shootAt(0, 0));
		assertTrue(myOcean.firedUpon[0][0]);
		assertTrue(myShips[4][7].isSunk());

		assertFalse(myOcean.shootAt(4, 7));

		Ship ship2 = new Destroyer();
		ship2.placeShipAt(3, 3, false, myOcean);
		assertTrue(myOcean.shootAt(3, 3));
		assertTrue(myOcean.shootAt(4, 3));
		assertTrue(myShips[3][3].isSunk());
		assertTrue(myShips[4][3].isSunk());
		assertTrue(ship2.isSunk());
		assertFalse(myOcean.shootAt(3, 3));
		assertFalse(myOcean.shootAt(4, 3));

		assertTrue(myOcean.firedUpon[4][3]);
	}

	@Test
	public void testGetShotsFired() {
		assertTrue(myOcean.getShotsFired() == 0);
		Ship ship1 = new Battleship();
		ship1.placeShipAt(3, 8, false, myOcean);
		myOcean.shootAt(3, 8);
		assertTrue(myOcean.getShotsFired() == 1);
		myOcean.shootAt(4, 8);
		assertTrue(myOcean.getShotsFired() == 2);
		myOcean.shootAt(5, 8);
		assertTrue(myOcean.getShotsFired() == 3);
		myOcean.shootAt(6, 8);
		assertTrue(myOcean.getShotsFired() == 4);
		myOcean.shootAt(9, 9);
		assertTrue(myOcean.getShotsFired() == 5);
		myOcean.shootAt(6, 8);
		assertTrue(myOcean.getShotsFired() == 6);
	}

	@Test
	public void testGetHitCount() {
		assertTrue(myOcean.getHitCount() == 0);
		Ship ship1 = new Battleship();
		ship1.placeShipAt(3, 8, false, myOcean);
		myOcean.shootAt(3, 8);
		assertTrue(myOcean.getHitCount() == 1);
		myOcean.shootAt(4, 8);
		assertTrue(myOcean.getHitCount() == 2);
		myOcean.shootAt(5, 8);
		assertTrue(myOcean.getHitCount() == 3);
		myOcean.shootAt(6, 8);
		assertTrue(myOcean.getHitCount() == 4);
		myOcean.shootAt(9, 9);
		assertTrue(myOcean.getHitCount() == 4);
		myOcean.shootAt(6, 8);
		assertTrue(myOcean.getHitCount() == 4);

		Ship ship2 = new Cruiser();
		ship2.placeShipAt(0, 5, true, myOcean);
		myOcean.shootAt(0, 5);
		assertTrue(myOcean.getHitCount() == 5);
		myOcean.shootAt(0, 5);
		assertTrue(myOcean.getHitCount() == 6);
		myOcean.shootAt(0, 5);
		assertTrue(myOcean.getHitCount() == 7);
		myOcean.shootAt(0, 6);
		assertTrue(myOcean.getHitCount() == 8);
		myOcean.shootAt(0, 6);
		assertTrue(myOcean.getHitCount() == 9);
		myOcean.shootAt(0, 7);
		assertTrue(myOcean.getHitCount() == 10);
		myOcean.shootAt(0, 7);
		assertTrue(myOcean.getHitCount() == 10);
		myOcean.shootAt(0, 0);
		assertTrue(myOcean.getHitCount() == 10);
	}

	@Test
	public void testIsGameOver() {
		Ship ship1 = new Battleship();
		ship1.placeShipAt(4, 6, false, myOcean);
		Ship ship2 = new Cruiser();
		ship2.placeShipAt(1, 1, true, myOcean);
		Ship ship3 = new Destroyer();
		ship3.placeShipAt(5, 2, false, myOcean);
		Ship ship4 = new Submarine();
		ship4.placeShipAt(7, 8, true, myOcean);

		myOcean.shootAt(4, 6);
		myOcean.shootAt(5, 6);
		myOcean.shootAt(6, 6);
		myOcean.shootAt(7, 6);
		assertFalse(myOcean.isGameOver());
		myOcean.shootAt(1, 1);
		myOcean.shootAt(1, 2);
		myOcean.shootAt(1, 3);
		assertFalse(myOcean.isGameOver());
		myOcean.shootAt(5, 2);
		myOcean.shootAt(6, 2);
		assertFalse(myOcean.isGameOver());
		myOcean.shootAt(7, 8);
		assertTrue(myOcean.isGameOver());
	}

	@Test
	public void testGetShipArray() {
		Ship ship1 = new Battleship();
		ship1.placeShipAt(4, 6, false, myOcean);
		Ship ship2 = new Cruiser();
		ship2.placeShipAt(1, 1, true, myOcean);
		Ship ship3 = new Destroyer();
		ship3.placeShipAt(5, 2, false, myOcean);
		Ship ship4 = new Submarine();
		ship4.placeShipAt(7, 8, true, myOcean);

		Ship[][] myShips = myOcean.getShipArray();
		assertEquals(myShips[1][1].toString(), "S");
		assertEquals(myShips[1][2].toString(), "S");
		assertEquals(myShips[1][3].toString(), "S");

		assertEquals(myShips[4][6].getShipType(), "battleship");
		assertEquals(myShips[5][6].toString(), "S");
		assertEquals(myShips[6][6].toString(), "S");
		myOcean.shootAt(5, 6);
		assertEquals(myShips[5][6].toString(), "S");

		myOcean.shootAt(5, 2);
		myOcean.shootAt(6, 2);
		assertEquals(myShips[5][2].toString(), "x");
		assertEquals(myShips[6][2].toString(), "x");
		assertEquals(myShips[6][2].getShipType(), "destroyer");
		/*
		 * for (int i = 0; i < 10; i++){ for(int j = 0; j < 10; j++){
		 * System.out.print(myShips[i][j].toString() + "  "); }
		 * System.out.println(); }
		 */
	}

	@Test
	public void testPrint() {
		Ship ship1 = new Battleship();
		ship1.placeShipAt(4, 6, false, myOcean);
		Ship ship2 = new Cruiser();
		ship2.placeShipAt(1, 1, true, myOcean);
		Ship ship3 = new Destroyer();
		ship3.placeShipAt(5, 2, false, myOcean);
		Ship ship4 = new Submarine();
		ship4.placeShipAt(7, 8, true, myOcean);
		myOcean.print();

		myOcean.shootAt(1, 1);
		myOcean.print();
		myOcean.shootAt(0, 0);
		myOcean.shootAt(4, 6);
		myOcean.shootAt(5, 6);
		myOcean.shootAt(6, 6);
		myOcean.shootAt(7, 6);

		myOcean.shootAt(4, 8);
		myOcean.shootAt(5, 8);
		myOcean.shootAt(6, 8);
		myOcean.shootAt(7, 8);

		myOcean.print();
	}
}
