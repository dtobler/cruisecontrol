/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package crossCutting.controlCenter.interfaces;

public interface IServiceInterface {
	void executeCommand(String cmdString, IOutputter outputter);
}