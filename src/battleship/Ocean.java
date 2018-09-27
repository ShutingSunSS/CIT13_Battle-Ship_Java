package battleship;
import java.util.Random; // placeAllshipsRandomly

/**
 * The ocean contains a 10*10 array of ships, representing the "ocean", 
 * and some method to manipulate it. 
 * @author Shuting Sun
 * @version 1
 */
public class Ocean {
    /** The 10*10 two-dimensional array of Ship object. */
	private Ship[][] ships = new Ship[10][10];
	/** The total number of shots fired by the users. */
	private int shotsFired;
	/** The number of times a shot hit a ship. */
	private int hitCount;
	
	/** Record if a given location is fired upon. <i>true</i> means yes, <i>false</i> means no */
	boolean[][] firedUpon = new boolean[10][10];
	
	/** A random number generator. */
	Random random = new Random();
	
	/** 
	 * The constructor. Creates an empty ocean: fills the ships array with EmptySeas.
	 * Initializes game variables, such as how many shots have been fired.
	 */
	Ocean(){
		//ships
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				ships[i][j] = new EmptySea();
			}
		}
		shotsFired = 0;
		hitCount = 0;
	}
	
	/** 
	 * Place all ten ships randomly on the initially empty ocean.
	 * Place larger ships before smaller ones.
	 */
	void placeAllShipsRandomly(){//如果算法写不好放船岂不都要放好久＝＝
		placeOneShipRandomly(new Battleship());
		for (int i = 0; i < 2; i++)
			placeOneShipRandomly(new Cruiser());
		for (int i = 0; i < 3; i++)
			placeOneShipRandomly(new Destroyer());
		for (int i = 0; i < 4; i++)
			placeOneShipRandomly(new Submarine());
	}
	
	/**
	 * A new method which could be used to place a ship at a time.
	 * @param aShip This is a Ship object.
	 */
	void placeOneShipRandomly(Ship aShip){
		int myRow = 0;
		int myColumn = 0;
		boolean myHorizontal = false;
		
		do{
			myRow = random.nextInt(10); // 0-9
		    myColumn = random.nextInt(10);// 0-9
		    int myH = random.nextInt(2); // 0-1
			if (myH == 1){
				myHorizontal = true;
			}else
				myHorizontal = false;
		}while (! aShip.okToPlaceShipAt(myRow, myColumn, myHorizontal, this)); // Find the right combination
			
		aShip.placeShipAt(myRow, myColumn, myHorizontal, this);
	}
	
	/** 
	 * Return true if the given location contains a ship, false if it does not.
	 * @param row The row number of the given location.
	 * @param column The column number of the given location.
	 * @return true if the given location contains a ship, false if it does not.
	 */
	boolean isOccupied(int row, int column){ //Just for initialization
		if (row >= 0 && row <= 9 && column >= 0 && column <= 9){
			if (ships[row][column].getShipType().equals("empty sea"))
				return false;
			else 
				return true;
		}else
			return false;
	}
	
	/** 
	 * Returns true if the given location contains a real ship, still afloat.
	 * false if it does not.
	 * In addition, the method updates the number of shots that have been fired, 
	 * and the number of hits.
	 * @param row The row number of the given location.
	 * @param column The column number of the given location.
	 * @return Returns true if the given location contains a real ship, still afloat.
	 * false if it does not.
	 */
	boolean shootAt(int row, int column){
		shotsFired++;
		firedUpon[row][column] = true;
		if( (!ships[row][column].getShipType().equals("empty sea")) 
				&& (!ships[row][column].isSunk()) ){
			hitCount++; //when the ship is not sunk, the hitCount will increase, if the ship is sunk, hitCount will not increas
			ships[row][column].shootAt(row, column);
			//String type = ships[row][column].getShipType(); // need to know which type has been hit.
		    return true;
		}else
			return false;
	}

	
	/** 
	 * Returns the number of shots fired.
	 * @return Returns the number of shots fired.
	 */
	int getShotsFired(){
		return shotsFired;
	}
	
	/** 
	 * Returns the number of hits recorded. 
	 * All hits are counted, not just the first time a given square is hit.
	 * @return Returns the number of hits recorded. 
	 */
	int getHitCount(){
		return hitCount;
	}
	
	/** 
	 * Returns true if all ships have been sunk, otherwise false.
	 * @return Returns true if all ships have been sunk, otherwise false.
	 */
	boolean isGameOver(){
		for (int i = 0; i <= 9; i++){
			for (int j = 0; j <= 9; j++){
				if (isOccupied(i, j) && !ships[i][j].isSunk())
					return false;
			}
		}
		return true;
	}
	
	/** 
	 * Returns the actual 10*10 array of ships, not a copy.
	 * @return Returns the actual 10*10 array of ships, not a copy.
	 */
	Ship[][] getShipArray(){
		return ships;
	}
	
	/** 
	 * Prints the ocean. 
	 * Row numbers should be displayed along the left edge.
	 * Column numbers should be displayed along the top.
	 * Top left corner square should be (0, 0)
	 * 'S': a location that you have fired upon and hit a real ship.
	 * '-': a location that you have fired upon and found nothing there.
	 * 'x': a location containing a sunken ship.
	 * '.': a location that you have never never fired upon.
	 */
	void print(){
		System.out.println("   0 1 2 3 4 5 6 7 8 9");
		int row = 0;
		for (int i = 0; i <= 9; i++){
			System.out.print(row + "  ");
			row++;
			for (int j = 0; j <= 9; j++){
				if(firedUpon[i][j])
					System.out.print(ships[i][j].toString() + " ");
				else
					System.out.print("." + " ");
			} 
			System.out.println();
		}
	}
}
