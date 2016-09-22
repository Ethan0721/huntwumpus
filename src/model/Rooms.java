package model;

//Class Rooms 
//The Rooms Object has two behavior : one is get the Status of itself 
// the Other one is knows if it has been visited. 
// Author: Jian Zhao 
	
	public class Rooms {
	
		private boolean visited;
		private String roomsState;
		
		public Rooms(RoomsState roomsState){
			visited = false;
			setState(roomsState);
		}
		
		public void setState(RoomsState states){
			this.roomsState = states.getValue();
		}
		public String getState(){
			return roomsState;
		}
		public void setVisited (){
			visited = true;
		}
		public boolean getVisited(){
			return visited;
		}
		
	}
