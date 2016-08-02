/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import infrastructure.carSimulation.ClutchSimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ClutchListener implements ActionListener {

	private JButton buttonClutch;

	private JTextField testSelectedGear;

	private ClutchSimulation simClutch;

	public ClutchListener(ClutchSimulation iSimClutch,
			JButton iButtonClutch, JTextField iButtonSelectedGear) {
		buttonClutch = iButtonClutch;
		testSelectedGear = iButtonSelectedGear;
		simClutch = iSimClutch;
	}

	public void actionPerformed(ActionEvent e) {
		if (simClutch.isClutchPedalPressed() == true) {
			simClutch.pressClutchPedal(false);
			buttonClutch.setText("Clutch");
			testSelectedGear.setEditable(false);
		} else {
			simClutch.pressClutchPedal(true);
			buttonClutch.setText("Clutch (pressed)");
			testSelectedGear.setEditable(true);
		}
	}
}
