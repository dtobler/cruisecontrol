/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import crossCutting.controlCenter.interfaces.ICruiseController;

public class CCOffListener implements ActionListener {

	private ICruiseController cruiseController;

	public CCOffListener(ICruiseController iCruiseController) {
		cruiseController = iCruiseController;
	}

	public void actionPerformed(ActionEvent e) {
		cruiseController.cruiseControlOff();
	}
}
