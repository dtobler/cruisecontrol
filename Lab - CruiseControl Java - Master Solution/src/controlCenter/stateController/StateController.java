/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;

import problemDomain.car.CarElementStructurer;
import controlCenter.stateController.states.Active;
import controlCenter.stateController.states.Inactive;
import controlCenter.stateController.states.Off;
import controlCenter.stateController.states.StateBase;
import controlCenter.stateController.states.IStateChanger;
import controlCenter.stateController.states.StateKind;
import controlCenter.stateController.states.ThrottlePressed;
import crossCutting.controlCenter.interfaces.ICarEventPoller;
import crossCutting.controlCenter.interfaces.ICruiseControlTransitionModel;
import crossCutting.controlCenter.interfaces.ICruiseController;
import crossCutting.problemDomain.interfaces.IGearBox;
import crossCutting.problemDomain.interfaces.IPedal;
import crossCutting.problemDomain.interfaces.IRegulateSpeed;
import crossCutting.problemDomain.interfaces.ISpeedProvider;

/**
 * Handles all events concerning the car states and user input.
 * Patterns: State, acts as the role context.
 */
public class StateController implements IStateChanger, ICruiseController, ICarEventPoller, ICruiseControlTransitionModel {
	private ArrayList<StateBase> states;
	private StateBase activeState;
	private IPedal brake;
	private IPedal throttle;
	private IPedal clutch;
	private IGearBox gearbox;
	@SuppressWarnings("unused")
	private ISpeedProvider speedProvider;
	private TransitionObserverHelper transitionObserverHelper;

	public StateController(IRegulateSpeed speedRegulator,
			CarElementStructurer ceStruct) {
		this.brake = ceStruct.getBrake();
		this.throttle = ceStruct.getThrottle();
		this.clutch = ceStruct.getClutch();
		this.gearbox = ceStruct.getGearbox();
		this.speedProvider = ceStruct.getSpeedProvider();

		states = new ArrayList<StateBase>();
		states.add(StateKind.Off.ordinal(),
				   new Off(this, speedRegulator, ceStruct));
		states.add(StateKind.Active.ordinal(),
				   new Active(this, speedRegulator, ceStruct));
		states.add(StateKind.Inactive.ordinal(),
				   new Inactive(this, speedRegulator, ceStruct));
		states.add(StateKind.ThrottlePressed.ordinal(),
				   new ThrottlePressed(this, ceStruct));
		activeState = states.get(StateKind.Off.ordinal());
		
		transitionObserverHelper =  new TransitionObserverHelper();
		this.addStateObserver(transitionObserverHelper);
	}

	@Override
	public void cruiseControlOn() {
		activeState.cruiseControlOn();
	}

	@Override
	public void cruiseControlOff() {
		activeState.cruiseControlOff();
	}

	@Override
	public void cruiseControlResume() {
		activeState.resumeSpeedRegulation();
	}

	@Override
	public void controlCarEvents() {
		if (brake.isPressed())
			activeState.brakePressed();

		if (throttle.isPressed())
			activeState.throttlePressed();
		else
			activeState.throttleReleased();

		if (clutch.isPressed())
			activeState.clutchPressed();

		if (!gearbox.isHighestGearEngaged())
			activeState.highestGearNotEngaged();

		activeState.doRegulateSpeed();
	}

	public void changeState(StateKind newState) {
		synchronized(this) {
			System.out.println("Change state from " + activeState.toString()
					+ " to " + states.get(newState.ordinal()).toString());
	
			activeState.onExit();
			activeState = states.get(newState.ordinal());
			activeState.onEntry();
		}
	}

	public void addStateObserver(Observer pStateObserver) {
		Iterator<StateBase> it = states.iterator();
		StateBase sb;
		while (it.hasNext()) {
			sb = (StateBase) it.next();
			sb.addObserver(pStateObserver);
		}
	}

	public StateKind getActiveState() {
		return activeState.getState();
	}

	@Override
	public void addTransitionObserver(Observer transitionObserver) {
		transitionObserverHelper.addTransitionObserver(transitionObserver);
	}
}
