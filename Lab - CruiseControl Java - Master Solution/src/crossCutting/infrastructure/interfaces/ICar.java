package crossCutting.infrastructure.interfaces;

/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

/**
 * Determines which functionality is required by the cruise control application
 * from a car.
 */
public interface ICar {

	boolean isBrakePedalPressed();

	/**
	 * @return wheel increments since the last call to resetWheelTurnCounter.
	 *         For simulating reason, this implementation is simplified: It
	 *         returns a number, which has to be divided by 100 to get the speed
	 *         in km/h.
	 */
	long getWheelIncrements();

	void resetWheelTurnCounter();

	boolean isHighestGearSelected();

	boolean isClutchPedalPressed();

	boolean isThrottlePressed();

	/**
	 * Change the throttle valve position relatively to the current position
	 * 
	 * @param deltaPosition
	 *            relative change to the current position. Range: 0..100. If the
	 *            new valve position is out of range, it is adjusted to the
	 *            closer limit.
	 */
	void changeRelativeThrottleValvePosition(int deltaPosition);
}
