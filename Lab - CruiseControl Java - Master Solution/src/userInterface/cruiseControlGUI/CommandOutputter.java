/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import crossCutting.controlCenter.interfaces.IOutputter;

public class CommandOutputter implements IOutputter {

	public void println(String outputString) {
		System.out.println(outputString);
	}

	public void print(String outputString) {
		System.out.print(outputString);
	}

	public void newline() {
		System.out.println("\n");
	}

}
