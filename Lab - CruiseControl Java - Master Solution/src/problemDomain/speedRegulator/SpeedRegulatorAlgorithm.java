/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.speedRegulator;

import crossCutting.problemDomain.interfaces.IThrottleValve;
import crossCutting.problemDomain.interfaces.RegulatorType;

/**
 * Base class for all implementations of regulator algorithms.
 */
abstract class SpeedRegulatorAlgorithm {
	protected static float targetSpeed = 0;
	protected IThrottleValve throttleValve;

	protected SpeedRegulatorAlgorithm(IThrottleValve throttleValve) {
		assert(throttleValve != null);
		this.throttleValve = throttleValve;
	}

	public abstract void regulateSpeed(float currentSpeed);

	public void setTargetSpeed(float newTargetSpeed) {
		assert(newTargetSpeed >= 0);
		targetSpeed = newTargetSpeed;
	}

	public float getTargetSpeed() {
		return targetSpeed;
	}

	public abstract RegulatorType getRegulatorType();
}
