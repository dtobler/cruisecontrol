/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController.states;

import problemDomain.car.CarElementStructurer;
import crossCutting.controlCenter.interfaces.CruiseControlTransition;

public abstract class On extends StateBase {

	protected On(IStateChanger stateChanger, CarElementStructurer ceStruct) {
		super(stateChanger, ceStruct);
	}

	@Override
	public void cruiseControlOff() {
		changeState(StateKind.Off);
	}

	@Override
	public void onEntry() {
		setChanged();
		notifyObservers(CruiseControlTransition.OnEntry);
	}

	@Override
	public void onExit() {
		setChanged();
		notifyObservers(CruiseControlTransition.OnExit);
	}
}
