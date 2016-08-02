package controlCenter.stateController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlCenter.stateController.states.StateKind;

public class StateControllerBasicTest extends StateControllerTest  {
	@Before
	public void setUp() {
		super.setUp();
	}

	@After
	public void tearDown() {
		super.tearDown();
	}

	@Test
	public void cruiseControlOn_FromOffStateAnd60kmh_NewStateIsActive() {
		// given
		this.stateController.changeState(StateKind.Off);
		this.speedProviderStub.setSpeed(60);
		// when
		this.stateController.cruiseControlOn();
		// then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlOn_FromOffStateAndBrakePressed_StateRemainsOff() {
		// given
		this.stateController.changeState(StateKind.Off);
		this.carSimulation.setBrake(true);
		this.speedProviderStub.setSpeed(60);
		// when
		this.stateController.cruiseControlOn();
        // then
		assertEquals(StateKind.Off, this.stateController.getActiveState());
	}
	
	@Test
	public void cruiseControlOn_FromOffStateAndClutchPressed_StateRemainsOff() {
		// given
		this.stateController.changeState(StateKind.Off);
		this.carSimulation.setBrake(false);
		this.carSimulation.setClutchPedal(true);
		// when
		this.stateController.cruiseControlOn();
		// then
		assertEquals(StateKind.Off, this.stateController.getActiveState());
	}
	
	@Test
	public void cruiseControlOn_FromOffStateAndClutchNotPressed_NewStateIsActive() {
		// given
		this.stateController.changeState(StateKind.Off);
		this.carSimulation.setClutchPedal(false);
		this.speedProviderStub.setSpeed(60);
        // when
		this.stateController.cruiseControlOn();
        // then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
	}
	
	@Test
	public void cruiseControlOn_FromOffStateAnd40kmh_StateRemainsOff() {
		// given
		this.stateController.changeState(StateKind.Off);
		this.speedProviderStub.setSpeed((float)40.0);
		// when
		this.stateController.cruiseControlOn();
		// then
		assertEquals(StateKind.Off, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlOn_FromOffStateAnd4thGear_StateRemainsOff() {
		// given
		this.stateController.changeState(StateKind.Off);
		this.carSimulation.setGear(4);
		assertTrue(!this.gearbox.isHighestGearEngaged());
		// when
		this.stateController.cruiseControlOn();
		// then
		assertEquals(StateKind.Off, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlOn_FromOffStateAndThrottlePressed_ChangesToStateActive() {
		// given
		this.stateController.changeState(StateKind.Off);
		this.carSimulation.setThrottlePressed(true);
		// when
		this.stateController.cruiseControlOn();
		// then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlOn_FromActiveState_StateRemainsUnchainged() {
		// given
		this.stateController.changeState(StateKind.Active);
		// when
		this.stateController.cruiseControlOn();
		// then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlOn_FromInactiveState_StateActiveAndNewTargetSpeed() {
		// given
		this.stateController.changeState(StateKind.Inactive);
		// when
		this.speedProviderStub.setSpeed((float)60);
		this.stateController.cruiseControlOn();
		// then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
		assertEquals(60.0f, this.speedRegulatorStub.getTargetSpeed(), 1E-5);
	}

	@Test
	public void cruiseControlOff_FromActiveState_IsOff() {
		// given
		this.stateController.changeState(StateKind.Active);
		// when
		this.stateController.cruiseControlOff();
		// then
		assertEquals(StateKind.Off, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlOff_FromInactiveState_IsOff() {
		// given
		this.stateController.changeState(StateKind.Inactive);
		// when
		this.stateController.cruiseControlOff();
		// then
		assertEquals(StateKind.Off, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlResume_FromOffState_RemainsInOffState() {
		// given
		this.stateController.changeState(StateKind.Off);
		// when
		this.stateController.cruiseControlResume();
		// then
		assertEquals(StateKind.Off, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlResume_FromActiveState_RemainsInActiveState() {
		// given
		this.stateController.changeState(StateKind.Active);
		// when
		this.stateController.cruiseControlResume();
		// then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlResume_FromInactiveState_ChangesToActive() {
		// given
		this.stateController.changeState(StateKind.Inactive);
		// when
		this.stateController.cruiseControlResume();
		// then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlResume_FromInactiveStateAndSpeedIs40kmh_ChangesToActiveState() {
		// given
		this.stateController.changeState(StateKind.Inactive);
		this.speedProviderStub.setSpeed(40.0f);
		this.speedRegulatorStub.setTargetSpeed(50.0f);
		// when
		this.stateController.cruiseControlResume();
		// then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
		assertEquals(50.0f, this.speedRegulatorStub.getTargetSpeed(), 1E-6);
	}

	@Test
	public void cruiseControlResume_FromInactiveStateAnd4thGear_RemainsInInactive() {
		// given
		this.stateController.changeState(StateKind.Inactive);
		this.carSimulation.setGear(4);
		// when
		this.stateController.cruiseControlResume();
		// then
		assertEquals(StateKind.Inactive, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlResume_FromInactiveStateAndBrakePressed_RemainsInInactive() {
		// given
		this.stateController.changeState(StateKind.Inactive);
		this.carSimulation.setBrake(true);
		// when
		this.stateController.cruiseControlResume();
		// then
		assertEquals(StateKind.Inactive, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlResume_FromInactiveStateAndClutchPressed_RemainsInInactive() {
		// given
		this.stateController.changeState(StateKind.Inactive);
		this.carSimulation.setClutchPedal(true);S
		// when
		this.stateController.cruiseControlResume();
		// then
		assertEquals(StateKind.Inactive, this.stateController.getActiveState());
	}

	@Test
	public void cruiseControlResume_FromInactiveStateAndThrottlePressed_ChangesToActiveState() {
		// given
		this.stateController.changeState(StateKind.Inactive);
		this.carSimulation.setThrottlePressed(true);
		// when
		this.stateController.cruiseControlResume();
		// then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
	}

	@Test
	public void controlCarEvents_StateActive_RegulateSpeedIsCalled() {
		// given
		this.stateController.changeState(StateKind.Active);
		this.speedProviderStub.setSpeed(112);
		assertEquals(false, brake.isPressed());
		// when
		this.stateController.controlCarEvents();
        // then
		assertEquals(StateKind.Active, this.stateController.getActiveState());
		assertEquals(1, this.speedRegulatorStub.regulateSpeedCalls());
	}

	@Test
	public void controlCarEvents_StateInactive_RegulateSpeedIsNotCalled() {
		// given
		this.stateController.cruiseControlOn();
		this.carSimulation.setBrake(true);
		this.stateController.controlCarEvents();
		assertEquals(StateKind.Inactive, this.stateController.getActiveState());
		// when
		this.stateController.controlCarEvents();
        // then
		assertEquals(StateKind.Inactive, this.stateController.getActiveState());
		assertEquals(0, this.speedRegulatorStub.regulateSpeedCalls());
	}

	@Test
	public void controlCarEvents_StateOff_RegulateSpeedIsNotCalled() {
		// given
		assertEquals(StateKind.Off, this.stateController.getActiveState());
		// when
		this.stateController.controlCarEvents();
        // then
		assertEquals(StateKind.Off, this.stateController.getActiveState());
		assertEquals(0, this.speedRegulatorStub.regulateSpeedCalls());
	}

	@Test
	public void controlCarEvents_StateActiveAndBrakePressed_RegulateSpeedIsNotCalled() {
		// given
		this.stateController.changeState(StateKind.Active);
		// when
		this.carSimulation.setBrake(true);
		this.stateController.controlCarEvents();
		// then
		assertEquals(StateKind.Inactive, this.stateController.getActiveState());
		assertEquals(0, this.speedRegulatorStub.regulateSpeedCalls());
	}

	@Test
	public void controlCarEvents_StateActiveAndClutchPressed_RegulateSpeedIsNotCalled() {
		// given
		this.stateController.changeState(StateKind.Active);
		// when
		this.carSimulation.setClutchPedal(true);
		this.stateController.controlCarEvents();
		// then
		assertEquals(StateKind.Inactive, this.stateController.getActiveState());
		assertEquals(0, this.speedRegulatorStub.regulateSpeedCalls());
	}

	@Test
	public void controlCarEvents_StateActiveAndGearChanges_RegulateSpeedIsNotCalled() {
		// given
		this.stateController.changeState(StateKind.Active);
		// when
		this.carSimulation.setGear(4);
		this.stateController.controlCarEvents();
		// then
		assertEquals(StateKind.Inactive, this.stateController.getActiveState());
		assertEquals(0, this.speedRegulatorStub.regulateSpeedCalls());
	}
}
