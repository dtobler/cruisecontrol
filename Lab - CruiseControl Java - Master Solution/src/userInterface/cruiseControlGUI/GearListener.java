/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import infrastructure.carSimulation.GearingSimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class GearListener implements ActionListener {
	private JTextField testSelectedGear;

	private GearingSimulation simGearing;

	public GearListener(GearingSimulation iGearing, JTextField iSelectedGear) {
		testSelectedGear = iSelectedGear;
		simGearing = iGearing;
	}

	public void actionPerformed(ActionEvent e) {
		String newGear = testSelectedGear.getText();

		try {
			simGearing.setGear(Integer.parseInt(newGear));
		} catch (NumberFormatException ex) {
			simGearing.setGear(10); // set to highest gear
		}

		testSelectedGear.setText(new Integer(simGearing.getGear()).toString());
	}

}
