/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.serviceItfController.commands;

import crossCutting.controlCenter.interfaces.IOutputter;

class GetSpeedLarger extends Command {

	public GetSpeedLarger() {
		super("GetSpeedLarger");
	}

	public void execute(IOutputter outputter) {
		outputter.println("GetSpeedLarger executed");
	}
}
