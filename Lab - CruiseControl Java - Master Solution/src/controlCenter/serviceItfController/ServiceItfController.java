/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.serviceItfController;

import java.util.ArrayList;
import java.util.Iterator;

import controlCenter.serviceItfController.commands.Command;
import controlCenter.serviceItfController.commands.CommandFactory;
import crossCutting.controlCenter.interfaces.IOutputter;
import crossCutting.controlCenter.interfaces.IServiceInterface;

public class ServiceItfController implements IServiceInterface {
	private ArrayList<Command> commands;

	public ServiceItfController(CommandFactory cmdFactory) {
		commands = cmdFactory.getCommands();
	}

	@Override
	public void executeCommand(String cmdString, IOutputter outputter) {
		String cmdName = parseCmdName(cmdString);
		Command command = findCommandWithGivenName(cmdName);
		if(command != null)
			command.execute(outputter);
		else
			outputter.println("ERROR: Cmd \"" + cmdName + "\" not found");
	}

	private Command findCommandWithGivenName(String commandName) {
		Iterator<Command> it = commands.iterator();
		Command cmd;
		while (it.hasNext() == true) {
			cmd = (Command) it.next();
			if (cmd.compareCmdName(commandName) == true) {
				return cmd;
			}
		}
		return null;
	}

	private String parseCmdName(String cmdString) {
		return cmdString; // Assumed: cmdString with no parameters
	}
}
