/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import infrastructure.carSimulation.BrakeSimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BrakeListener implements ActionListener {

	private BrakeSimulation simBrake;

	private JButton buttonBrake;

	public BrakeListener(BrakeSimulation iSimBrake, JButton iButton) {
		simBrake = iSimBrake;
		buttonBrake = iButton;
	}

	public void actionPerformed(ActionEvent e) {
		if (simBrake.isBrakePedalPressed() == true) {
			simBrake.pressBrake(false);
			buttonBrake.setText("Brake");
		} else {
			simBrake.pressBrake(true);
			buttonBrake.setText("Brake (pressed)");
		}

	}
}
