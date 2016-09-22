
package model;
// Class RoomsState: Enum type
// author: Jian Zhao 

public enum RoomsState {
	Wumpus("[ W ]"), 
	Slime("[ S ]"), 
	Goop("[ G ]"),
	Pit("[ P ]"), 
	Blood("[ B ]"),
	Hunter("[ O ]"), 
	Nothing("[   ]");

	private String state;
	
	
	private RoomsState(String str) {
		this.state = str;
	}
	
	public String getValue() {
		return state;
	}
}
