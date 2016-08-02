/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController.stub;

import java.util.Observer;

import crossCutting.problemDomain.interfaces.IRegulateSpeed;


public class SpeedRegulatorTestStub implements IRegulateSpeed {

	private int regulateSpeedCalls = 0;
	private float targetSpeed = 0;
	
	@Override
	public void addTargetSpeedObserver(Observer targetSpeedObserver) {
	}

	@Override
	public float getTargetSpeed() {
		return this.targetSpeed ;
	}

	@Override
	public void regulateSpeed(float currentSpeed) {
		this.regulateSpeedCalls++;
	}
	
	public int regulateSpeedCalls() {
		return this.regulateSpeedCalls;
	}

	public void resetRegulateSpeedCalls() {
		this.regulateSpeedCalls = 0;
	}

	@Override
	public void setTargetSpeed(float newTargetSpeed) {
		this.targetSpeed = newTargetSpeed;
		
	}

}
