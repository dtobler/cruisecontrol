/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import crossCutting.controlCenter.interfaces.CruiseControlTransition;

public class CCStateObserver implements Observer {
	private JLabel labelCCStateOff;

	private JLabel labelCCStateOn;

	private JLabel labelCCStateActive;

	public CCStateObserver(JLabel iCCStateOff, JLabel iCCStateOn,
			JLabel iCCStateActive) {
		labelCCStateOff = iCCStateOff;
		labelCCStateOn = iCCStateOn;
		labelCCStateActive = iCCStateActive;
	}

	public void update(Observable o, Object iTransition) {
		CruiseControlTransition transition = ((CruiseControlTransition) iTransition);

		switch(transition)  {
			case ActiveExit:
				labelCCStateActive.setText("  ");
				break;
			case ActiveEntry:
				labelCCStateActive.setText("X");
				break;
			case OffExit:
				labelCCStateOff.setText("  ");
				break;
			case OffEntry:
				labelCCStateOff.setText("X");
				break;
			case OnExit:
				labelCCStateOn.setText("  ");
				break;
			case OnEntry:
				labelCCStateOn.setText("X");
				break;
		}
	}
}
