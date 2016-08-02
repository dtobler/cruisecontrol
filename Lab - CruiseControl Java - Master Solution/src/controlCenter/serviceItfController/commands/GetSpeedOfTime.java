/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.serviceItfController.commands;

import crossCutting.controlCenter.interfaces.IOutputter;

class GetSpeedOfTime extends Command {

	public GetSpeedOfTime() {
		super("GetSpeedOfTime");
	}

	public void execute(IOutputter outputter) {
		outputter.println("GetSpeedOfTime executed");
	}
}
