package controlCenter.stateController;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlCenter.stateController.states.StateKind;

public class StateControllerAdvancedTest extends StateControllerTest {
	@Before
	public void setUp() {
		super.setUp();
	}

	@After
	public void tearDown() {
		super.tearDown();
	}

	@Test
	public void cruiseControlOn_FromThrottlePressedState_StateRemainsThrottlePressed() {
		// given
		this.stateController.changeState(StateKind.ThrottlePressed);
		// when
		this.stateController.cruiseControlOn();
		// then
		assertEquals(StateKind.ThrottlePressed, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlOff_FromThrottlePressedState_IsOff() {
		// given
		this.stateController.changeState(StateKind.ThrottlePressed);
		// when
		this.stateController.cruiseControlOff();
		// then
		assertEquals(StateKind.Off, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlResume_FromThrottlePressedState_RemainsInThrottlePressedState() {
		// given
		this.stateController.changeState(StateKind.ThrottlePressed);
		// when
		this.stateController.cruiseControlResume();
		// then
		assertEquals(StateKind.ThrottlePressed, this.stateController.getActiveState());
	}

	@Test
	public void controlCarEvents_StateThrottlePressed_RegulateSpeedIsNotCalled() {
		// given
		this.stateController.cruiseControlOn();
		this.carSimulation.setThrottlePressed(true);
		this.stateController.controlCarEvents();
		assertEquals(StateKind.ThrottlePressed, this.stateController.getActiveState());
		// when
		this.stateController.controlCarEvents();
        // then
		assertEquals(StateKind.ThrottlePressed, this.stateController.getActiveState());
		assertEquals(0, this.speedRegulatorStub.regulateSpeedCalls());
	}

	@Test
	public void controlCarEvents_StateActiveAndThrottlePressed_RegulateSpeedIsNotCalled() {
		// given
		this.stateController.changeState(StateKind.Active);
		// when
		this.carSimulation.setThrottlePressed(true);
		this.stateController.controlCarEvents();
		// then
		assertEquals(StateKind.ThrottlePressed, this.stateController.getActiveState());
		assertEquals(0, this.speedRegulatorStub.regulateSpeedCalls());
	}


}
