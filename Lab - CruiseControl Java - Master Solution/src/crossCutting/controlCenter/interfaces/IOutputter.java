/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package crossCutting.controlCenter.interfaces;

/**
 * This interface defines the functionalities for an output channel.
 */
public interface IOutputter {
	void println(String outputString);
	void print(String outputString);
	void newline();
}
