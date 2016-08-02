/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class TargetSpeedObserver implements Observer {
	private JLabel targetSpeed;

	public TargetSpeedObserver(JLabel iTargetSpeed) {
		targetSpeed = iTargetSpeed;
	}

	public void update(Observable o, Object iNewTargetSpeed) {
		String targetSpeedValue = ((Float) iNewTargetSpeed).toString();
		targetSpeed.setText(targetSpeedValue);
	}

}
