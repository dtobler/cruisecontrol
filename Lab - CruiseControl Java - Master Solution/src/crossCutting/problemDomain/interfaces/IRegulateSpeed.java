package crossCutting.problemDomain.interfaces;

import java.util.Observer;

/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

/**
 * This interface abstracts the functionality of a speed regulator.
 * It can be obtained by the SpeedRegulatorFactory.
 */
public interface IRegulateSpeed {

	/**
	 * Calculates the correcting measurements to be taken according the
	 * difference between target and actual speed.
	 * Is designed to be called regularly at a rate of 100ms
	 */
	void regulateSpeed(float currentSpeed);

	/**
	 * Sets the target speed to given value. Calling this operation will notify
	 * all observers attached to target speed changes.
	 * 
	 * @param newTargetSpeed
	 *            new target speed in km/h
	 */
	void setTargetSpeed(float newTargetSpeed);

	/**
	 * Get the target speed
	 * @return target speed in km/h
	 */
	float getTargetSpeed();

	/**
	 * Attaches (or adds) a target speed observer
	 * Observer-Pattern, the observer is notified about changes of the target
	 * speed.
	 */
	void addTargetSpeedObserver(Observer targetSpeedObserver);
}
