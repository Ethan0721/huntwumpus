package view;

// Image View: read and print each object's image in certain location in the map
// and update them;
// Author Jian Zhao;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.MapModel;
import model.Rooms;

public class imageView extends JPanel implements Observer {

	private Image Blood, Goop, Ground, Slime, SlimePit, TheHunter, Wumpus;
	private MapModel map;

	public imageView(MapModel map) {

		this.map = map;
		try {
			Ground = ImageIO.read(new File("src/Ground.png"));
			Wumpus = ImageIO.read(new File("src/Wumpus.png"));
			Slime = ImageIO.read(new File("src/Slime.png"));
			Blood = ImageIO.read(new File("src/Blood.png"));
			SlimePit = ImageIO.read(new File("src/SlimePit.png"));
			TheHunter = ImageIO.read(new File("src/TheHunter.png"));
			Goop = ImageIO.read(new File("src/Goop.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// update it 
	public void update(Observable theObservedObject, Object unused) {
		map = (MapModel) theObservedObject;
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		Image img = null;
		Image hunterImage = null;

		Rooms[][] temp = map.getMap();
	// put ground image first;  
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (temp[i][j].getState() != "[ X ]") {
					g2.drawImage(Ground, j * 50, i * 50, 50, 50, null);

				}
			}
		}		
	// add images for pit wumpus slimepit blood and goop 
	// add hunterimage at last;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map.RoomStatus(i, j).equals("[ O ]")
						|| map.RoomStatus(i, j).equals("[   ]")
						|| map.RoomStatus(i, j).equals("[ P ]")
						|| map.RoomStatus(i, j).equals("[ B ]")
						|| map.RoomStatus(i, j).equals("[ G ]")
						|| map.RoomStatus(i, j).equals("[ W ]")
						|| map.RoomStatus(i, j).equals("[ S ]")) {

					if ( map.RoomStatus(i, j).equals("[ O ]") && map.isWumpus(i,j) == true) {
						g2.drawImage(Wumpus, j * 50, i * 50, 50, 50, null);
					}
					else if ( map.RoomStatus(i, j).equals("[ O ]") && map.isPit(i,j) == true) {
						g2.drawImage(SlimePit, j * 50, i * 50, 50, 50, null);

					}
					else 
						g2.drawImage(hunterImage, j * 50, i * 50, 50, 50, null);
					
				} else if (!map.gameOver()) {
					g2.setColor(Color.BLACK);
					g2.fillRect(j * 50, i * 50, 50, 50);
				}
			}
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if (temp[i][j].getState() == "[ S ]") {
					img = Slime;
				} else if (temp[i][j].getState() == "[ W ]") {
					img = Wumpus;
				} else if (temp[i][j].getState() == "[ B ]") {
					img = Blood;
				} else if (temp[i][j].getState() == "[ G ]") {
					img = Goop;
				} else if (temp[i][j].getState() == "[ P ]") {
					img = SlimePit;
				}
				if (map.isHunter(i, j))
					img = TheHunter;

				if (temp[i][j].getState() == "[ S ]" && temp[i][j].getVisited()
						|| temp[i][j].getState() == "[ W ]"
						&& temp[i][j].getVisited()
						|| temp[i][j].getState() == "[ B ]"
						&& temp[i][j].getVisited()
						|| temp[i][j].getState() == "[ G ]"
						&& temp[i][j].getVisited()
						|| temp[i][j].getState() == "[ P ]"
						&& temp[i][j].getVisited() || map.isHunter(i, j)
						&& temp[i][j].getVisited())

					g2.drawImage(img, j * 50, i * 50, 50, 50, null);
			}
		}
	}
}
