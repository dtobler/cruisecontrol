/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import crossCutting.controlCenter.interfaces.ICruiseController;

public class CCOnListener implements ActionListener {

	private ICruiseController cruiseController;

	public CCOnListener(ICruiseController iCruiseController) {
		cruiseController = iCruiseController;
	}

	public void actionPerformed(ActionEvent e) {
		cruiseController.cruiseControlOn();
	}
}
