/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.serviceItfController.commands;

import java.util.ArrayList;

import controlCenter.stateController.StateController;

public class CommandFactory {
	private ArrayList<Command> cmdList;

	public CommandFactory(StateController iStateCtrl) {
		cmdList = new ArrayList<Command>();
		cmdList.add(new GetSpeedLarger());
		cmdList.add(new GetSpeedOfTime());
		cmdList.add(new GetState(iStateCtrl));
		cmdList.add(new SetTargetSpeed());
	}
	
	public ArrayList<Command> getCommands() {
		return cmdList;
	}
}
