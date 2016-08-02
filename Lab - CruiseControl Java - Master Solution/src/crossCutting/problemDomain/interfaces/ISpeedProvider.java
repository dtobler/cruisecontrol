/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package crossCutting.problemDomain.interfaces;

public interface ISpeedProvider {

	/**
	 * Returns the currently driven speed in km/h
	 */
	float getCurrentSpeed();
	
	/**
	 * Must be called periodically to initiate readout of the wheel sensor increments
	 * and calculation of the speed.
	 */
	void calculateSpeed();
}
