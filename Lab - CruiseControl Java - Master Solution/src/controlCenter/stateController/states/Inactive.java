/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController.states;

import crossCutting.problemDomain.interfaces.IGearBox;
import crossCutting.problemDomain.interfaces.IPedal;
import crossCutting.problemDomain.interfaces.IRegulateSpeed;
import crossCutting.problemDomain.interfaces.ISpeedProvider;
import problemDomain.car.CarElementStructurer;

public class Inactive extends On {
	private ISpeedProvider speedProvider;
	private IRegulateSpeed speedRegulator;
	private IPedal brake;
	private IPedal clutch;
	private IGearBox gearbox;

	public Inactive(IStateChanger stateChanger, IRegulateSpeed speedRegulator,
			CarElementStructurer ceStruct) {
		super(stateChanger, ceStruct);
		this.speedRegulator = speedRegulator;
		this.speedProvider = ceStruct.getSpeedProvider();
		this.brake = ceStruct.getBrake();
		this.gearbox = ceStruct.getGearbox();
		this.clutch = ceStruct.getClutch();
	}

	@Override
	public void resumeSpeedRegulation() {
		if (brake.isPressed())
			return;
		
		if(clutch.isPressed())
			return;

		if (!gearbox.isHighestGearEngaged())
			return;

		changeState(StateKind.Active);
	}

	@Override
	public void cruiseControlOn() {
		float currentSpeed = speedProvider.getCurrentSpeed();
		if(areConditionsMetToActiveSpeedRegulation(currentSpeed)) {
			speedRegulator.setTargetSpeed(currentSpeed);
			this.changeState(StateKind.Active);
		}
	}

	@Override
	public String toString() {
		return "Inactive";
	}

	@Override
	public StateKind getState() {
		return StateKind.Inactive;
	}
}
