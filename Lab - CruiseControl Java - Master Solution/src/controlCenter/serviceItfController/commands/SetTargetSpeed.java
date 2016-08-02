/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.serviceItfController.commands;

import crossCutting.controlCenter.interfaces.IOutputter;

public class SetTargetSpeed extends Command {
	
	public SetTargetSpeed() {
		super("SetTargetSpeed");
	}
	
	public void execute(IOutputter outputter) {
		outputter.println("Set Target Speed Executed");
	}

}
