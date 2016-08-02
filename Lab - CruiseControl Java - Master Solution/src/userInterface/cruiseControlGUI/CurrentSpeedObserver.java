/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class CurrentSpeedObserver implements Observer {
	private JLabel labelCurrentSpeed;

	public CurrentSpeedObserver(JLabel iCurrentSpeed) {
		labelCurrentSpeed = iCurrentSpeed;
	}

	public void update(Observable o, Object iNewCurrentSpeed) {
		String currentSpeed = ((Float) iNewCurrentSpeed).toString();
		labelCurrentSpeed.setText(currentSpeed);
	}
}
