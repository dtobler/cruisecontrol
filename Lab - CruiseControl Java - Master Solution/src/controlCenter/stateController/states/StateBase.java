/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController.states;

import java.util.Observable;
import crossCutting.problemDomain.interfaces.IGearBox;
import crossCutting.problemDomain.interfaces.IPedal;
import problemDomain.car.CarElementStructurer;

/**
 * Base class for all state classes
 * Patterns: State, Observer (Observable for state changes)
 */
public abstract class StateBase extends Observable {
	protected IStateChanger stateChanger;
	private IPedal clutch;
	private IPedal brake;
	private IGearBox gearbox;

	protected StateBase(IStateChanger stateChanger, CarElementStructurer ceStruct) {
		this.stateChanger = stateChanger;
		this.clutch = ceStruct.getClutch();
		this.brake = ceStruct.getBrake();
		this.gearbox = ceStruct.getGearbox();
	}

	public void cruiseControlOn() {
		// empty by intention
	}

	public void cruiseControlOff() {
		// empty by intention
	}

	public void resumeSpeedRegulation() {
		// empty by intention
	}

	public void clutchPressed() {
		// empty by intention
	}

	public void highestGearNotEngaged() {
		// empty by intention
	}

	public void brakePressed() {
		// empty by intention
	}

	public void throttlePressed() {
		// empty by intention
	}

	public void throttleReleased() {
		// empty by intention
	}

	public void doRegulateSpeed() {
		// empty by intention
	}

	public void onEntry() {
		// empty by intention
	}

	public void onExit() {
		// empty by intention
	}

	protected void changeState(StateKind newState) {
		stateChanger.changeState(newState);
	}

	protected boolean areConditionsMetToActiveSpeedRegulation(float currentSpeed) {

		if (this.brake.isPressed())
			return false;

		if (this.clutch.isPressed())
			return false;

		if (!this.gearbox.isHighestGearEngaged())
			return false;

		if (currentSpeed < 50)
			return false;
		
		return true;
	}

	public abstract String toString();

	public abstract StateKind getState();
}
