/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.speedRegulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import crossCutting.problemDomain.interfaces.IChangeRglType;
import crossCutting.problemDomain.interfaces.IRegulateSpeed;
import crossCutting.problemDomain.interfaces.RegulatorType;

import problemDomain.speedRegulator.stub.ThrottleValveTestStub;

public class SpeedRegulatorTest   {

	private SpeedRegulator speedRegulator;
	private IChangeRglType changeRglType;
	private IRegulateSpeed regulateSpeed;
	private ThrottleValveTestStub throttleValveStub;

	@Before
	public void setUp() throws Exception {
		throttleValveStub = new ThrottleValveTestStub();
		speedRegulator = new SpeedRegulator(throttleValveStub);
		changeRglType = speedRegulator;
		regulateSpeed = speedRegulator;
	}

	@Test
	public void setRegulatorType_StartupDefault_IsPID() {
		assertEquals("This regulator type is set at startup", RegulatorType.PID, changeRglType.getRegulatorType());
	}

	@Test
	public void setRegulatorType_ToTwoPoint_Succeeds() {
		changeRglType.setRegulatorType(RegulatorType.TwoPoint);
		assertEquals("Test new set regualtor type", RegulatorType.TwoPoint, changeRglType.getRegulatorType());
	}

	@Test
	public void setRegulatorType_ToPIDPoint_Succeeds() {
		changeRglType.setRegulatorType(RegulatorType.TwoPoint);
		changeRglType.setRegulatorType(RegulatorType.PID);
		assertEquals("Test new set regualtor type", RegulatorType.PID, changeRglType.getRegulatorType());
}

	@Test
	public void setTargetSpeed_SetTo80_SetForAllRegulators() {
		// given
		regulateSpeed.setTargetSpeed(70.0f);
		assertEquals(70.0f, regulateSpeed.getTargetSpeed(), 1E-5);
		// when
		changeRglType.setRegulatorType(RegulatorType.TwoPoint);
		regulateSpeed.setTargetSpeed(80.0f);
		// then
		assertEquals(80.0f, regulateSpeed.getTargetSpeed(), 1E-5);
		// when
		changeRglType.setRegulatorType(RegulatorType.PID);
		// then
		assertEquals(80.0f, regulateSpeed.getTargetSpeed(), 1E-5);
		assertEquals(RegulatorType.PID, changeRglType.getRegulatorType());
	}

	@Test
	public void regulateSpeed_PID_CarFasterThanTargetSpeed_ThrottleValveCloses() {
		// given
		changeRglType.setRegulatorType(RegulatorType.PID);
		throttleValveStub.throttleValvePosDelta = 0;
		regulateSpeed.setTargetSpeed(60.0f);
		// when
		regulateSpeed.regulateSpeed(100.0f);
		regulateSpeed.regulateSpeed(100.0f);
		regulateSpeed.regulateSpeed(100.0f);
		// then
		assertTrue("throttle valve must close", throttleValveStub.throttleValvePosDelta < 0);
	}
	
	@Test
	public void regulateSpeed_PID_CarSlowerThanTargetSpeed_ThrottleValveOpens() {
		// given
		changeRglType.setRegulatorType(RegulatorType.PID);
		throttleValveStub.throttleValvePosDelta = 0;
		regulateSpeed.setTargetSpeed(60.0f);
		// when
		regulateSpeed.regulateSpeed(50.0f);
		regulateSpeed.regulateSpeed(50.0f);
		regulateSpeed.regulateSpeed(50.0f);
		// then
		assertTrue("throttle valve must open", throttleValveStub.throttleValvePosDelta > 0);
	}	

	@Test
	public void regulateSpeed_TwoPoint_CarFasterThanTargetSpeed_ThrottleValveCloses() {
		// given
		changeRglType.setRegulatorType(RegulatorType.TwoPoint);
		throttleValveStub.throttleValvePosDelta = 0;
		regulateSpeed.setTargetSpeed(60.0f);
		// when
		regulateSpeed.regulateSpeed(100.0f);
		regulateSpeed.regulateSpeed(100.0f);
		regulateSpeed.regulateSpeed(100.0f);
		// then
		assertTrue("throttle valve must close", throttleValveStub.throttleValvePosDelta < 0);
	}	

	@Test
	public void regulateSpeed_TwoPoint_CarSlowerThanTargetSpeed_ThrottleValveOpens() {
		// given
		changeRglType.setRegulatorType(RegulatorType.PID);
		throttleValveStub.throttleValvePosDelta = 0;
		regulateSpeed.setTargetSpeed(60.0f);
		// when
		regulateSpeed.regulateSpeed(50.0f);
		regulateSpeed.regulateSpeed(50.0f);
		regulateSpeed.regulateSpeed(50.0f);
		// then
		assertTrue("throttle valve must open", throttleValveStub.throttleValvePosDelta > 0);
	}	
}
