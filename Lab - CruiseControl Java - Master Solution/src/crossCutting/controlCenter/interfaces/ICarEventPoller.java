/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package crossCutting.controlCenter.interfaces;

public interface ICarEventPoller {

	/**
	 * Controls all car events. This operation must be called periodically
	 * therefore (best: all 100ms).
	 */
	void controlCarEvents();
}