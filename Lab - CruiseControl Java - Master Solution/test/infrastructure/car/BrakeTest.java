/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.car;

import static org.junit.Assert.*;
import infrastructure.carSimulation.BrakeSimulation;
import infrastructure.carSimulation.SpeedModel;

import org.junit.Before;
import org.junit.Test;

public class BrakeTest  {
	private BrakeSimulation brake;

	@Before
	public void setUp() {
		SpeedModel speedModel = new SpeedModel();
		brake = new BrakeSimulation(speedModel);
	}
	
	@Test
	public void isBrakePedalPressed_VariousTests() {
		assertEquals(false, brake.isBrakePedalPressed());
		brake.pressBrake(true);
		assertEquals(true, brake.isBrakePedalPressed());
		brake.pressBrake(false);
		assertEquals(false, brake.isBrakePedalPressed());
	}
}
