/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.serviceItfController.commands;

import crossCutting.controlCenter.interfaces.IOutputter;

public abstract class Command {
	private String cmdName;

	public Command(String cmdName) {
		this.cmdName = cmdName;
	}

	public boolean compareCmdName(String cmdName) {
		if (this.cmdName.compareToIgnoreCase(cmdName) == 0)
			return true;
		else
			return false;
	}

	public abstract void execute(IOutputter outputter);
}
