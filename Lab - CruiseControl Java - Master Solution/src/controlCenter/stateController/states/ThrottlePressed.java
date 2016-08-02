package controlCenter.stateController.states;

import problemDomain.car.CarElementStructurer;

public class ThrottlePressed extends On {

	public ThrottlePressed(IStateChanger stateChanger, CarElementStructurer ceStruct) {
		super(stateChanger, ceStruct);
	}

	@Override
	public void throttleReleased() {
		changeState(StateKind.Active);
	}
	
	@Override
	public void brakePressed() {
		changeState(StateKind.Inactive);
	}

	@Override
	public void clutchPressed() {
		changeState(StateKind.Inactive);
	}

	@Override
	public String toString() {
		return "ThrottlePressed";
	}

	@Override
	public StateKind getState() {
		return StateKind.ThrottlePressed;
	}
}
