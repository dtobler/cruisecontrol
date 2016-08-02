/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import infrastructure.carSimulation.SpeedModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class DisturbanceListener implements ActionListener {
	private JTextField testDisturbance;

	private SpeedModel speedModel;

	public DisturbanceListener(JTextField iDisturbance, SpeedModel iSpeedModel) {
		testDisturbance = iDisturbance;
		speedModel = iSpeedModel;
	}

	public void actionPerformed(ActionEvent e) {
		String newDisturbance = testDisturbance.getText();

		try {
			speedModel.setDisturbance(Float.parseFloat(newDisturbance));
		} catch (NumberFormatException ex) {
			speedModel.setDisturbance(0);
		}

		testDisturbance.setText(new Float(speedModel.getDisturbance())
				.toString());
	}

}
