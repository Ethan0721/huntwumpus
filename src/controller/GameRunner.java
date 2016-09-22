
package controller;
/**
 * This class represents an event-driven program with a graphical user interface as a controller
 * between the view and the model. It has listeners to mediate between the view and the model.
 * 
 * This controller employs the Observer design pattern that updates two views every time the
 * state of the Hunt the Wumpus changes:
 * 
 * whenever you make a move by clicking move or shot buttons either view    
 * Author: Jian Zhao 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import view.imageView;
import view.textView;
import model.MapModel;


public class GameRunner extends JFrame {
	
	public static void main(String[] args) {
		new GameRunner().setVisible(true);
	}
	
	private JTabbedPane severalPanels;
    private JPanel view1; 	
	private JPanel view2;  
	private JButton ButtonNorth;
    private JButton ButtonSouth;
    private JButton ButtonEast;
    private JButton ButtonWest;
    private JButton ButtonUp;
    private JButton ButtonDown;
    private JButton ButtonRight;
    private JButton ButtonLeft;	
	private MapModel map;
	
	public GameRunner(){
		map = new MapModel();
		LayoutsThisFrame();
		registerListener();
		setUpModelAndObservers();
	}
	
	  public void LayoutsThisFrame() {

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setSize(800, 750);
			setLocation(100, 20);
			setLayout(null);

			
			// two panels 
			severalPanels = new JTabbedPane();	
			severalPanels.setSize(550,550);
			severalPanels.setLocation(200, 80);
			view1 = new textView(map);
			view2 = new imageView(map);
			severalPanels.add(view1, "Text View");
			severalPanels.add(view2, "Graphical View");
			add(severalPanels, BorderLayout.CENTER);
			
			// set up buttons 
		    JLabel Move = new JLabel("Move");
			Move.setLocation(95,75);
			Move.setSize(50,60);
			add(Move);
			
			JLabel Shoot = new JLabel("Shoot");
			Shoot.setLocation(95,350);
			Shoot.setSize(50,60);
			add(Shoot);
			
			
			ButtonNorth = new JButton("North");
			ButtonNorth.setLocation(90, 125);
			ButtonNorth.setSize(50,75);
			add(ButtonNorth);

			
			ButtonSouth = new JButton("South");
			ButtonSouth.setLocation(90, 200);
			ButtonSouth.setSize(50,75);	
			add(ButtonSouth);

			
			ButtonWest = new JButton("West");
			ButtonWest.setLocation(40, 200);
			ButtonWest.setSize(50,75);
			add(ButtonWest);

			
			ButtonEast = new JButton("East");
			ButtonEast.setLocation(140, 200);
			ButtonEast.setSize(50,75);
			add(ButtonEast);	

			ButtonUp = new JButton("Up");
			ButtonUp.setLocation(90, 400);
			ButtonUp.setSize(50,75);
			add(ButtonUp);

			
			ButtonDown = new JButton("Down");
			ButtonDown.setLocation(90, 475);
			ButtonDown.setSize(50,75);	
			add(ButtonDown);

			
			ButtonLeft = new JButton("Left");
			ButtonLeft.setLocation(40, 475);
			ButtonLeft.setSize(50,75);
			add(ButtonLeft);

			ButtonRight = new JButton("Right");
			ButtonRight.setLocation(140, 475);
			ButtonRight.setSize(50,75);
			add(ButtonRight);
			
			setVisible(true);
			    
	  }

	
	public  void setUpModelAndObservers() {

		map.addObserver((Observer) view1);
		map.addObserver((Observer) view2);
	}
	
	// Button Listenners 
	  public void registerListener() {
		  ButtonNorth.addActionListener(new ButtonNorthListener());
		  ButtonSouth.addActionListener(new ButtonSouthListener());
	 	  ButtonWest.addActionListener(new ButtonWestListener());
	  	  ButtonEast.addActionListener(new ButtonEastListener());
		  
	  	  ButtonUp.addActionListener(new ButtonUp());
		  ButtonDown.addActionListener(new ButtonDown());
		  ButtonLeft.addActionListener(new ButtonLeft());
		  ButtonRight.addActionListener(new ButtonRight());
	  }
		  
	  private class ButtonNorthListener implements ActionListener {
		    public void actionPerformed(ActionEvent ae) {
		    	map.moveNorth();
		    }
		  }
	  
	  private class ButtonSouthListener implements ActionListener {
		    public void actionPerformed(ActionEvent ae) {
		    	map.moveSouth();
		    }
		  }
	  
	  private class ButtonWestListener implements ActionListener {
		    public void actionPerformed(ActionEvent ae) {
		    	map.moveWest();
		    }
		  }
	  private class ButtonEastListener implements ActionListener {
		    public void actionPerformed(ActionEvent ae) {
		    	map.moveEast();
		    }
		  }
	  
	  private class ButtonUp implements ActionListener {
		    public void actionPerformed(ActionEvent ae) {
		    	map.shootUp();
		    }
		  }
	  
	  private class ButtonDown implements ActionListener {
		    public void actionPerformed(ActionEvent ae) {
		    	map.shootDown();
		    }
		  }
	  
	  private class ButtonRight implements ActionListener {
		    public void actionPerformed(ActionEvent ae) {
		    	map.shootLeft();
		    }
		  }
	  private class ButtonLeft implements ActionListener {
		    public void actionPerformed(ActionEvent ae) {
		    	map.shootRight();
		    }
	  	}
	}
