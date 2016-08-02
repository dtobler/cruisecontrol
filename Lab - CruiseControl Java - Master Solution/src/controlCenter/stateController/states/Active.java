/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController.states;

import crossCutting.controlCenter.interfaces.CruiseControlTransition;
import crossCutting.problemDomain.interfaces.IRegulateSpeed;
import crossCutting.problemDomain.interfaces.ISpeedProvider;
import problemDomain.car.CarElementStructurer;

public class Active extends On {
	private ISpeedProvider speedProvider;
	private IRegulateSpeed speedRegulator;

	public Active(IStateChanger stateChanger, IRegulateSpeed speedRegulator,
			CarElementStructurer ceStruct) {
		super(stateChanger, ceStruct);
		this.speedProvider = ceStruct.getSpeedProvider();
		this.speedRegulator = speedRegulator;
	}

	@Override
	public void throttlePressed() {
		changeState(StateKind.ThrottlePressed);
	}

	@Override
	public void doRegulateSpeed() {
		float currentSpeed = speedProvider.getCurrentSpeed();
		speedRegulator.regulateSpeed(currentSpeed);
	}

	@Override
	public void clutchPressed() {
		changeState(StateKind.Inactive);
	}

	@Override
	public void highestGearNotEngaged() {
		changeState(StateKind.Inactive);
	}

	@Override
	public void brakePressed() {
		changeState(StateKind.Inactive);
	}

	@Override
	public void onEntry() {
		super.onEntry();

		setChanged();
		notifyObservers(CruiseControlTransition.ActiveEntry);
	}

	@Override
	public void onExit() {
		super.onExit();

		setChanged();
		notifyObservers(CruiseControlTransition.ActiveExit);
	}

	@Override
	public String toString() {
		return "Active";
	}

	@Override
	public StateKind getState() {
		return StateKind.Active;
	}
}
