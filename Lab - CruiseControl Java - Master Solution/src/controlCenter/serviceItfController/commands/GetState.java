/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.serviceItfController.commands;

import controlCenter.stateController.StateController;
import controlCenter.stateController.states.StateKind;
import crossCutting.controlCenter.interfaces.IOutputter;

class GetState extends Command {
	private StateController stateCtrl;

	public GetState(StateController iStateCtrl) {
		super("GetState");
		stateCtrl = iStateCtrl;
	}

	public void execute(IOutputter outputter) {
		StateKind state =  stateCtrl.getActiveState();
		outputter.println("Current state: " + state.toString());
	}
}
