/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.speedRegulator;

import crossCutting.problemDomain.interfaces.IThrottleValve;
import crossCutting.problemDomain.interfaces.RegulatorType;

/**
 * Implementation of the PID Regulator
 */
class SpeedRegulatorPID extends SpeedRegulatorAlgorithm {

	public SpeedRegulatorPID(IThrottleValve iThrottleValve) {
		super(iThrottleValve);
	}

	public void regulateSpeed(float currentSpeed) {
		// This algorithm is not a true PDI algorithm!

		double correction = 0;

		if (currentSpeed > targetSpeed) {
			correction = 0.05 * (targetSpeed - currentSpeed);
			if (correction > -1)
				correction = -1;
			throttleValve.changeThrottleValvePos((int) correction);
		}
		if (currentSpeed < targetSpeed) {
			correction = 0.05 * (targetSpeed - currentSpeed);
			if (correction < 1)
				correction = 1;
			throttleValve.changeThrottleValvePos((int) correction);
		}
	}

	@Override
	public RegulatorType getRegulatorType() {
		return RegulatorType.PID;
	}
}
