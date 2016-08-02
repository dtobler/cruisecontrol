/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.speedRegulator;

import crossCutting.problemDomain.interfaces.IThrottleValve;
import crossCutting.problemDomain.interfaces.RegulatorType;

/**
 * Implementation of the 2 Point Regulator Also known as
 * Bang-Bang-Controller.
 */
class SpeedRegulator2Point extends SpeedRegulatorAlgorithm {

	public SpeedRegulator2Point(IThrottleValve throttleValve) {
		super(throttleValve);
	}

	@Override
	public void regulateSpeed(float currentSpeed) {
		assert(currentSpeed >= 0);
		int correction = 0;

		float speedDiff = currentSpeed - targetSpeed;

		if (speedDiff > 2) {
			correction = -5;
		}
		if (speedDiff < -2) {
			correction = 5;
		}

		throttleValve.changeThrottleValvePos(correction);
	}

	@Override
	public RegulatorType getRegulatorType() {
		return RegulatorType.TwoPoint;
	}
}
