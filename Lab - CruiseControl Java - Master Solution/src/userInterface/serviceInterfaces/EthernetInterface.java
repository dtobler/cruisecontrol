/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.serviceInterfaces;

import crossCutting.controlCenter.interfaces.IOutputter;
import crossCutting.controlCenter.interfaces.IServiceInterface;

public class EthernetInterface implements IOutputter {
	@SuppressWarnings("unused")
	private IServiceInterface serviceInterface;
	@SuppressWarnings("unused")
	private int socketPort;

	public EthernetInterface(IServiceInterface serviceInterface, int port) {
		this.serviceInterface = serviceInterface;
		socketPort = port;
	}
	
	public void println(String outputString) {
	}

	public void print(String outputString) {
	}

	public void newline() {
	}
}
