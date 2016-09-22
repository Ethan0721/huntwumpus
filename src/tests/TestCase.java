	package tests;	
// Test: test eveything in the MapModel 
// Author: Jian Zhao 	
	
	import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
	import java.io.PrintStream;

import org.junit.Test;

import model.MapModel;
	import model.Rooms;
	import model.RoomsState;
	
	
	public class TestCase {
		
		@Test
		public void TestHiddenMap() {
			
		RoomsState[][] AA = new RoomsState[10][10];
	
		for(int i=0; i<10; i++){
			for(int j=0; j< 10; j++){
				AA[i][j] = RoomsState.Nothing;
			}
		}
			MapModel map = new MapModel(AA);
			for(int i =0; i<10; i++){
				for(int j =0; j<10; j++){
					assertEquals("[ X ]",map.RoomStatus(0,0));
				}
			}
			
	//		 	 	0  1  2  3	4  5  6  7  8  9
	//			 0 [X][X][X][X][X][X][X][X][X][X]
	//			 1 [X][O][X][X][X][X][X][X][X][X]
	//			 2 [X][X][X][X][X][X][X][X][X][X]
	//			 3 [X][X][X][X][X][X][X][X][X][X]
	//			 4 [X][X][X][X][X][X][X][X][X][X]
	//			 5 [X][X][X][X][X][X][X][X][X][X]
	//			 6 [X][X][X][X][X][X][X][X][X][X]
	//			 7 [X][X][X][X][X][X][X][X][X][X]
	//			 8 [X][X][X][X][X][X][X][X][X][X]
	//			 9 [X][X][X][X][X][X][X][X][X][X]
			

			map.setHunterRow(2);
			map.setHunterCol(2);
			assertEquals(2, map.getHunterRow());
			assertEquals(2, map.getHunterCol());
			
			assertEquals("[ O ]", map.RoomStatus(2,2));
			assertEquals("[ X ]", map.RoomStatus(0,0));
			assertEquals("[ X ]", map.RoomStatus(1,4));
			
			map.setWumpusRow(1);
			map.setWumpusCol(2);
			
			assertEquals(1, map.getWumpusRow());
			assertEquals(2, map.getWumpusCol());
			assertEquals("[ X ]", map.RoomStatus(1,2));
			assertEquals("[ X ]", map.RoomStatus(2,5));
			map.setHunterRow(1);
			map.setHunterCol(2);
			
			assertEquals("[ O ]", map.RoomStatus(1,2));
			assertEquals("[   ]", map.RoomStatus(2,2));
			
		}
		
		@Test
		public void TestNotHiddenAndInitial() {
			
		RoomsState[][] AA = new RoomsState[10][10];
	
		for(int i=0; i<10; i++){
			for(int j=0; j< 10; j++){
				AA[i][j] = RoomsState.Nothing;
			}
		}
		AA[1][3] = RoomsState.Blood;
		AA[4][4] = RoomsState.Blood;
		AA[4][5] = RoomsState.Blood;
		AA[6][5] = RoomsState.Pit;
		AA[7][4] = RoomsState.Goop;
		AA[4][8] = RoomsState.Wumpus;
		
		MapModel map = new MapModel(AA);
		
	// 	 	0  1  2  3	4  5  6  7  8  9
	//	 0 [X][X][X][X][X][X][X][X][X][X]
	//	 1 [X][X][O][B][X][X][X][X][X][X]
	//	 2 [X][X][X][X][X][X][X][X][X][X]
	//	 3 [X][X][X][X][X][X][X][X][X][X]
	//	 4 [X][X][X][X][B][B][X][X][W][X]
	//	 5 [X][X][X][X][X][X][X][X][X][X]
	//	 6 [X][X][X][X][X][P][X][X][X][X]
	//	 7 [X][X][X][X][G][X][X][X][X][X]
	//	 8 [X][X][X][X][X][X][X][X][X][X]
	//	 9 [X][X][X][X][X][X][X][X][X][X]
		
		assertEquals("[ X ]", map.RoomStatus(1,2));
		assertEquals("[ X ]", map.RoomStatus(1,3));
		assertEquals("[ X ]", map.RoomStatus(4,4));
		assertEquals("[ X ]", map.RoomStatus(4,5));
		assertEquals("[ X ]", map.RoomStatus(6,5));
		assertEquals("[ X ]", map.RoomStatus(7,4));
		assertEquals("[ X ]", map.RoomStatus(4,8));
		
		map.setHunterRow(1);
		map.setHunterCol(1);
		
		assertEquals("[ O ]", map.RoomStatus(1,1));
		assertEquals("[ X ]", map.RoomStatus(1,2));
		
		map.setHunterRow(1);
		map.setHunterCol(2);
		assertEquals("[ O ]", map.RoomStatus(1,2));
		assertEquals("[   ]", map.RoomStatus(1,1));
		
		map.setHunterRow(1);
		map.setHunterCol(3);
		
		assertEquals("[ O ]", map.RoomStatus(1,3));
		assertEquals("[   ]", map.RoomStatus(1,2));
		
		map.setHunterRow(1);
		map.setHunterCol(4);
		
		assertEquals("[ B ]", map.RoomStatus(1,3));
		assertEquals("[ O ]", map.RoomStatus(1,4));

		map.showMap();
			
		
		assertEquals("[ B ]", map.RoomStatus(1,3));
		assertEquals("[ B ]", map.RoomStatus(4,4));
		assertEquals("[ B ]", map.RoomStatus(4,5));
		assertEquals("[ P ]", map.RoomStatus(6,5));
		assertEquals("[ G ]", map.RoomStatus(7,4));
		assertEquals("[ W ]", map.RoomStatus(4,8));
		
		assertEquals("[   ]", map.RoomStatus(0,0));
		assertEquals("[   ]", map.RoomStatus(4,0));
		assertEquals("[   ]", map.RoomStatus(0,5));
		assertEquals("[   ]", map.RoomStatus(0,2));
		assertEquals("[   ]", map.RoomStatus(2,4));
		assertEquals("[   ]", map.RoomStatus(7,7));
		
		map.setHunterRow(1);
		map.setHunterCol(3);
		
	   assertEquals("[ O ]", map.RoomStatus(1,3));
	}
		@Test
		public void TestMove() {
			
		RoomsState[][] AA = new RoomsState[10][10];
	
		for(int i=0; i<10; i++){
			for(int j=0; j< 10; j++){
				AA[i][j] = RoomsState.Nothing;
			}
		}
		AA[1][1] = RoomsState.Wumpus;
		AA[1][2] = RoomsState.Blood;
		AA[1][3] = RoomsState.Blood;
		AA[1][4] = RoomsState.Blood;
		AA[1][4] = RoomsState.Pit;
		AA[1][4] = RoomsState.Goop;
		MapModel map = new MapModel(AA);
		
// 	 		0  1  2  3	4  5  6  7  8  9
	//	 0 [X][O][X][X][X][X][X][X][X][X]			
	//	 1 [X][W][B][B][G][X][X][X][X][X]
	//	 2 [X][X][X][X][X][X][X][X][X][X]
	//	 3 [X][X][X][X][X][X][X][X][X][X]
	//	 4 [X][X][X][X][X][X][X][X][X][X]
	//	 5 [X][X][X][X][X][X][X][X][X][X]
	//	 6 [X][X][X][X][X][X][X][X][X][X]
	//	 7 [X][X][X][X][X][X][X][X][X][X]
	//	 8 [X][X][X][X][X][X][X][X][X][X]
	//	 9 [X][X][X][X][X][X][X][X][X][X]
		map.setHunterRow(0);
		map.setHunterCol(1);
		
		assertEquals("[ X ]", map.RoomStatus(0,0));
		assertEquals("[ O ]", map.RoomStatus(0,1));
		assertEquals("[ X ]", map.RoomStatus(0,2));
		
		map.moveNorth();
		assertEquals("[ O ]", map.RoomStatus(9,1));
		map.moveSouth();
		assertEquals("[ O ]", map.RoomStatus(0,1));
		
		map.moveEast();
		assertEquals("[ O ]", map.RoomStatus(0,2));
		
		map.moveWest();
		assertEquals("[ O ]", map.RoomStatus(0,1));
		
		}
		
		
		@Test
		public void TestMaptoString() {
			
		RoomsState[][] AA = new RoomsState[10][10];
		
		
		for(int i=0; i<10; i++){
			for(int j=0; j< 10; j++){
				AA[i][j] = RoomsState.Nothing;
				}
			}
		MapModel map = new MapModel(AA);
		assertEquals("[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"
				+ 	 "[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"
				+ 	 "[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"
				+ 	 "[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"
				+ 	 "[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"
				+ 	 "[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"
				+ 	 "[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"
				+ 	 "[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"
				+	 "[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"
				+ 	 "[ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ][ X ]\n"	, map.MaptoString());	
		map.setHunterRow(1);
		map.setHunterCol(1);
		//assertEquals("You are safe, nothing happens.", map.WarningMessage());
		System.out.println(map.MaptoString());
		
		}
		
		
		@Test
		public void TestMessage() {
			
		RoomsState[][] AA = new RoomsState[10][10];
		
		
		for(int i=0; i<10; i++){
			for(int j=0; j< 10; j++){
				AA[i][j] = RoomsState.Nothing;
				}
			}
		
		MapModel map = new MapModel(AA);
		MapModel map1 = new MapModel();
//	 	0  1  2  3	4  5  6  7  8  9
//	 0 [O][B][B][X][P][G][W][X][X][X]			
//	 1 [X][X][X][X][X][X][X][X][X][X]
//	 2 [X][X][X][X][X][X][X][X][X][X]
//	 3 [X][X][X][X][X][X][X][X][X][X]
//	 4 [X][X][X][X][X][X][X][X][X][X]
//	 5 [X][X][X][X][X][X][X][X][X][X]
//	 6 [X][X][X][X][X][X][X][X][X][X]
//	 7 [X][X][X][X][X][X][X][X][X][X]
//	 8 [X][X][X][X][X][X][X][X][X][X]
//	 9 [X][X][X][X][X][X][X][X][X][X]
		
		
		AA[0][1] = RoomsState.Blood;
		AA[0][2] = RoomsState.Blood;
		AA[0][4] = RoomsState.Pit;
		AA[0][5] = RoomsState.Goop;
		AA[0][6] = RoomsState.Wumpus;
		map.setHunterRow(0);
		map.setHunterCol(0);
		map.moveWest();
     	assertEquals("[ O ]", map.RoomStatus(0,9));
     	
		map.moveEast();
		map.getMessage();
		map.getMap();	
		map.isPit(0,4);
		map.isWumpus(0,6);
		map.isHunter(0, 0);
		}
		
		@Test
		public void Test() {
			
		RoomsState[][] AA = new RoomsState[10][10];
		
		
		for(int i=0; i<10; i++){
			for(int j=0; j< 10; j++){
				AA[i][j] = RoomsState.Nothing;
				}
			}
		MapModel map = new MapModel(AA);
		map.setHunterRow(5);
		map.setHunterCol(1);
		
		map.setWumpusRow(5);
		map.setWumpusCol(2);
		
		AA[5][5] = RoomsState.Wumpus;
		AA[0][1] = RoomsState.Blood;
		AA[0][1] = RoomsState.Slime;

		map.shootLeft();
		assertEquals("You shoot the Wumpus successfully.\n   You won!", map.shootLeft() );
		map.shootRight();
		assertEquals("You shoot the Wumpus successfully.\n   You won!", map.shootRight() );
		map.shootDown();
		assertEquals("You missed the shoot, You lost.\n      Game Over!", map.shootDown() );
		map.shootUp();
		assertEquals("You missed the shoot, You lost.\n      Game Over!", map.shootDown() );
		}
		
		
		@Test
		public void TestShoot2() {	
		RoomsState[][] AA = new RoomsState[10][10];
		
		
		for(int i=0; i<10; i++){
			for(int j=0; j< 10; j++){
				AA[i][j] = RoomsState.Nothing;
				}
			}
		MapModel map = new MapModel(AA);
		map.setHunterRow(8);
		map.setHunterCol(8);
		map.setWumpusRow(5);
		map.setWumpusCol(8);
		AA[5][8] = RoomsState.Wumpus;
		AA[0][1] = RoomsState.Blood;
		AA[0][1] = RoomsState.Slime;
		AA[0][3] = RoomsState.Goop;
		AA[9][9] = RoomsState.Pit;

		map.shootDown();
		assertEquals("You shoot the Wumpus successfully.\n   You won!", map.shootDown() );
		assertTrue(map.win()== true);
		assertTrue(map.gameOver()== true);
		
		map.shootUp();
		assertEquals("You shoot the Wumpus successfully.\n   You won!", map.shootUp() );
		assertTrue(map.win()== true);
		assertTrue(map.gameOver()== true);
		map.shootLeft();
		assertEquals("You missed the shoot, You lost.\n      Game Over!", map.shootLeft() );
		map.shootRight();
		assertEquals("You missed the shoot, You lost.\n      Game Over!", map.shootRight() );
		
		
		}
	}
