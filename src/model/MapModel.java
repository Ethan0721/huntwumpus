
package model;
/**
 * This class is the MapModel Generate a Map
 * and place everything on this map. Have get and set methods for
 * the cols and rows and after moving the hunter postion record the 
 * position of him, notify to both text view and image view let them 
 * uodate draw a new map. 
 *
 *Author: Jian Zhao 
 */


import java.util.Observable;
import java.util.Random;

public class MapModel extends Observable {
	private Rooms[][] map;
	private int HunterRow = -1;
	private int HunterCol = -1;
	private int WumpusRow = -1;
	private int WumpusCol = -1;
	private Random rand;

	private String message;
	private boolean win;
	private boolean gameover;

	// random for Draw
	public MapModel() {
		map = new Rooms[10][10];
		rand = new Random();
		win = false;
		gameover = false;
		GenerateMap();

		this.setChanged();
		notifyObservers();
	}

	// not random, for test
	public MapModel(RoomsState[][] rooms) {
		map = new Rooms[10][10];
		win = false;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new Rooms(rooms[i][j]);
			}
		}
		gameover = false;
	}

	// hunter position
	public void setHunterRow(int row) {
		this.HunterRow = row;
	}

	public int getHunterRow() {
		return HunterRow;
	}

	public void setHunterCol(int col) {
		this.HunterCol = col;
	}

	public int getHunterCol() {
		return HunterCol;
	}

	// wumpus position
	public void setWumpusCol(int col) {
		this.WumpusCol = col;
	}

	public int getWumpusCol() {
		return WumpusCol;
	}

	public void setWumpusRow(int row) {
		this.WumpusRow = row;
	}

	public int getWumpusRow() {
		return WumpusRow;
	}

	// if Win
	public boolean win() {
		return win;
	}

	// if GameOver
	public boolean gameOver() {
		return gameover;
	}

	public String getMessage() {
		return message;
	}

	public Rooms[][] getMap() {
		return map;
	}

	// Generate a random map
	// place pits then call slime method place slime
	// place Wumpus then call blood method place blood
	// place hunter in a safe place

	public void GenerateMap() {

		rand = new Random();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new Rooms(RoomsState.Nothing);
			}
		}
		placePits();
		placeWumpus();
		placeHunter();
	}

	private void placePits() {
		int row = 0;
		int col = 0;
		for (int i = 0; i < 3; i++) {

			row = rand.nextInt(10);
			col = rand.nextInt(10);
			if (map[row][col].getState().equals("[   ]")) {
				map[row][col].setState(RoomsState.Pit);
				placeSlime(row, col);

			} else
				i--;
		}
	}

	private void placeSlime(int row, int col) {
		// set slime in pits
		// the same row: cols: left = ( col + 9 ) % 10
		// cols: right = (col + 1) % 10

		// left

		if (map[row][(col + 9) % 10].getState().equals("[   ]")) {
			map[row][(col + 9) % 10].setState(RoomsState.Slime);
		}
		// right
		if (map[row][(col + 1) % 10].getState().equals("[   ]")) {
			map[row][(col + 1) % 10].setState(RoomsState.Slime);
		}
		// the same col

		// up
		if (map[(row + 9) % 10][col].getState().equals("[   ]")) {
			map[(row + 9) % 10][col].setState(RoomsState.Slime);
		}

		// down
		if (map[(row + 1) % 10][col].getState().equals("[   ]")) {
			map[(row + 1) % 10][col].setState(RoomsState.Slime);
		}
	}

	private void placeWumpus() {

		int row = rand.nextInt(10);
		int col = rand.nextInt(10);
		while (!map[row][col].getState().equals("[   ]")) {
			row = rand.nextInt(10);
			col = rand.nextInt(10);
		}
		map[row][col].setState(RoomsState.Wumpus);
		WumpusCol = col;
		WumpusRow = row;
		placeBooldorGoop(WumpusRow, WumpusCol);
	}

	private void placeBooldorGoop(int row, int col) {
		for (int i = row - 2; i <= row + 2; i++) {
			for (int j = col - 2; j <= col + 2; j++) {
				if (i == row || j == col
						|| (Math.abs(row - i) == 1 && Math.abs(col - j) == 1)) {
					int newRow = (i + 10) % 10;
					int newCol = (j + 10) % 10;

					if (map[newRow][newCol].getState().equals("[ S ]"))
						map[newRow][newCol].setState(RoomsState.Goop);

					if (map[newRow][newCol].getState().equals("[   ]")) {
						map[newRow][newCol].setState(RoomsState.Blood);

					}
				}
			}
		}
	}

	private void placeHunter() {
		boolean result = false;
		while (!result) {
			int rdHunterRow = rand.nextInt(10);
			int rdHunterCol = rand.nextInt(10);

			if (map[rdHunterRow][rdHunterCol].getState().equals("[   ]")) {
				HunterRow = rdHunterRow;
				HunterCol = rdHunterCol;
				result = true;

			}

		}
	}

	public boolean isHunter(int r, int c) {

		return (r == HunterRow && c == HunterCol);
	}

	public boolean isPit(int i, int j) {
		return map[i][j].getState().equals("[ P ]");
	}

	public boolean isWumpus(int i, int j) {
		return map[i][j].getState().equals("[ W ]");
	}

	public String RoomStatus(int r, int c) {

		if (r == HunterRow && c == HunterCol) {
			map[r][c].setVisited();
			return "[ O ]";

		} else if (map[r][c].getVisited() == true)
			return map[r][c].getState();

		else
			return "[ X ]";
	}

	public String MaptoString() {
		String str = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				str = str + RoomStatus(i, j);
			}
			str = str + "\n";
		}
		return str;

	}

	public void showMap() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j].setVisited();
			}
		}
	}

	private void setGameOver() {
		if (gameover == true) {
			showMap();
			this.setChanged();
			notifyObservers();
		}
	}

	// move north
	// Col keep same, Row change
	public void moveNorth() {
		if (gameover == false) {
			HunterRow = (HunterRow + 9) % 10;
			WarningMessage();

		}
	}

	// move south
	// Col keep same, Row change
	public void moveSouth() {
		if (gameover == false) {
			HunterRow = (HunterRow + 1) % 10;
			WarningMessage();

		}
	}

	// move East
	// Row keep same, Col change

	public void moveEast() {
		if (gameover == false) {
			HunterCol = (HunterCol + 1) % 10;
			WarningMessage();

		}
	}

	public void moveWest() {
		if (gameover == false) {
			HunterCol = (HunterCol + 9) % 10;
			WarningMessage();
		}
	}

	public String shootUp() {
		if (HunterCol == WumpusCol) {
			message = "You shoot the Wumpus successfully.\n   You won!";
			win = true;
		} else {
			message = "You missed the shoot, You lost.\n      Game Over!";
		}
		gameover = true;
		setGameOver();
		return message;

	}

	public String shootDown() {
		if (HunterCol == WumpusCol) {
			message = "You shoot the Wumpus successfully.\n   You won!";
			win = true;
		} else {
			message = "You missed the shoot, You lost.\n      Game Over!";
		}
		gameover = true;
		setGameOver();
		return message;
	}

	public String shootLeft() {
		if (HunterRow == WumpusRow) {
			message = "You shoot the Wumpus successfully.\n   You won!";
			win = true;
		} else {
			message = "You missed the shoot, You lost.\n      Game Over!";
		}
		gameover = true;
		setGameOver();
		return message;

	}

	public String shootRight() {
		if (HunterRow == WumpusRow) {
			message = "You shoot the Wumpus successfully.\n   You won!";
			win = true;
		} else {
			message = "You missed the shoot, You lost.\n      Game Over!";
		}
		gameover = true;
		setGameOver();
		return message;

	}

	public String WarningMessage() {

		if (map[HunterRow][HunterCol].getState().equals("[   ]")) {
			message = "You are safe, nothing happens.";
		}

		if (map[HunterRow][HunterCol].getState().equals("[ W ]")) {
			message = "You are eaten by Wumpus.\n 	Game Over!";
			gameover = true;
			setGameOver();

		}
		if (map[HunterRow][HunterCol].getState().equals("[ B ]")) {
			message = "Caution: Blood!\n Wumpus around you";

		}
		if (map[HunterRow][HunterCol].getState().equals("[ G ]")) {
			message = "You walked into goop. \n";

		}
		if (map[HunterRow][HunterCol].getState().equals("[ S ]")) {
			message = "You Walked into slime. Pit around you.\n";

		}
		if (map[HunterRow][HunterCol].getState().equals("[ P ]")) {
			message = "You Walked into a bottomless pit.\n 	  You die.\n   	 Game Over!";
			gameover = true;
			setGameOver();

		}
		this.setChanged();
		notifyObservers();

		return message;
	}

}