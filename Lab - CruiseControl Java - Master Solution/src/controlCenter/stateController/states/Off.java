/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController.states;

import crossCutting.controlCenter.interfaces.CruiseControlTransition;
import crossCutting.problemDomain.interfaces.IRegulateSpeed;
import crossCutting.problemDomain.interfaces.ISpeedProvider;
import problemDomain.car.CarElementStructurer;

public class Off extends StateBase {
	private ISpeedProvider speedProvider;
	private IRegulateSpeed speedRegulator;

	public Off(IStateChanger stateChanger, IRegulateSpeed speedRegulator,
			CarElementStructurer ceStruct) {
		super(stateChanger, ceStruct);
		this.speedRegulator = speedRegulator;
		this.speedProvider = ceStruct.getSpeedProvider();
	}

	@Override
	public void cruiseControlOn() {
		float currentSpeed = speedProvider.getCurrentSpeed();
		if(this.areConditionsMetToActiveSpeedRegulation(currentSpeed)) {
			speedRegulator.setTargetSpeed(currentSpeed);
			this.changeState(StateKind.Active);
		}
	}

	@Override
	public void onEntry() {
		speedRegulator.setTargetSpeed(0);
		setChanged();
		notifyObservers(CruiseControlTransition.OffEntry);
	}

	@Override
	public void onExit() {
		setChanged();
		notifyObservers(CruiseControlTransition.OffExit);
	}

	@Override
	public String toString() {
		return "Off";
	}

	@Override
	public StateKind getState() {
		return StateKind.Off;
	}
}
