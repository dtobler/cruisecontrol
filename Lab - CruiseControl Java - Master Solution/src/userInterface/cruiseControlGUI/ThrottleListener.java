/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import infrastructure.carSimulation.ThrottleSimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ThrottleListener implements ActionListener {

	private ThrottleSimulation simThrottle;

	private JButton buttonThrottle;

	public ThrottleListener(ThrottleSimulation iThrottle, JButton iButton) {
		simThrottle = iThrottle;
		buttonThrottle = iButton;
	}

	public void actionPerformed(ActionEvent e) {
		if (simThrottle.isThrottlePressed() == true) {
			simThrottle.pressThrottle(false);
			buttonThrottle.setText("Throttle");
		} else {
			simThrottle.pressThrottle(true);
			buttonThrottle.setText("Throttle (pressed)");
		}

	}
}
