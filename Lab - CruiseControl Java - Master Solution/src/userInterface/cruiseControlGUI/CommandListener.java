/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import crossCutting.controlCenter.interfaces.IOutputter;
import crossCutting.controlCenter.interfaces.IServiceInterface;

public class CommandListener implements ActionListener, IOutputter {

		private JTextField textCommand;

		private IServiceInterface serviceInterface;
		
		public CommandListener(JTextField iTextCommand, IServiceInterface iServiceInterface) {
			textCommand = iTextCommand;
			serviceInterface = iServiceInterface;
		}

		public void actionPerformed(ActionEvent e) {
			String command = textCommand.getText();
			textCommand.setText("");
			
			serviceInterface.executeCommand(command, (IOutputter)this);
		}
		
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
