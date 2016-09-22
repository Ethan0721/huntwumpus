package view;

// Text View : Print the map to String 
// And update them; 

// Author: Jian Zhao 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.MapModel;

public class textView extends JPanel implements Observer {

	private JTextArea plainText;
	private JTextArea str;
	private MapModel map;
	private String returnString;
	private String message;

	// constructor: 
	public textView(MapModel map) {

		returnString = map.MaptoString();
		plainText = new JTextArea();
		str = new JTextArea();
		str.setSize(50, 50);
		str.setLocation(350, 350);

		setBackground(Color.lightGray);
		setLocation(200, 50);
		setSize(550, 500);
		plainText.setPreferredSize(new Dimension(480, 400));
		Font font = new Font("Courier", Font.BOLD, 15);

		plainText.setFont(font);
		str.setFont(font);
		add(plainText);
		add(str);
		plainText.setText(returnString);
	}

	@Override
	public void update(Observable theObserved, Object arg) {
		map = (MapModel) theObserved;
		message = ((MapModel) theObserved).getMessage();
		plainText.updateUI();
		returnString = map.MaptoString();
		plainText.setText(map.MaptoString());
		str.setText(message);
		
	}
}
