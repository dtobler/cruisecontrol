/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.speedRegulator;

import java.util.Observable;
import java.util.Observer;

import crossCutting.problemDomain.interfaces.IChangeRglType;
import crossCutting.problemDomain.interfaces.IRegulateSpeed;
import crossCutting.problemDomain.interfaces.IThrottleValve;
import crossCutting.problemDomain.interfaces.RegulatorType;

/**
 * Implementation of the RegulateSpeed. Controls the two
 * different regulators.
 * Patterns: Strategy, Observer (Observable for target speed changes)
 */
public class SpeedRegulator extends Observable implements IRegulateSpeed, IChangeRglType {
	private SpeedRegulatorAlgorithm activeRgl;
	private SpeedRegulatorAlgorithm concreteRgl[];
	
	public SpeedRegulator(IThrottleValve iThrottleValve) {
		concreteRgl = new SpeedRegulatorAlgorithm[2];
		concreteRgl[RegulatorType.TwoPoint.ordinal()] = new SpeedRegulator2Point(iThrottleValve);
		concreteRgl[RegulatorType.PID.ordinal()] = new SpeedRegulatorPID(iThrottleValve);

		activeRgl = concreteRgl[RegulatorType.PID.ordinal()];
	}

	public void setRegulatorType(RegulatorType newRglType) {
		assert(newRglType != null);
		activeRgl = concreteRgl[newRglType.ordinal()];
	}

	public RegulatorType getRegulatorType() {
		return activeRgl.getRegulatorType();
	}

	public void setTargetSpeed(float newTargetSpeed) {
		assert(newTargetSpeed >= 0);
		activeRgl.setTargetSpeed(newTargetSpeed);
		setChanged();
		notifyObservers(new Float(activeRgl.getTargetSpeed()));
	}

	public float getTargetSpeed() {
		return activeRgl.getTargetSpeed();
	}

	public void regulateSpeed(float currentSpeed) {
		activeRgl.regulateSpeed(currentSpeed);
	}

	public void addTargetSpeedObserver(Observer targetSpeedObserver) {
		addObserver(targetSpeedObserver);
	}
}
