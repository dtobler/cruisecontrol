/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.car;

import static org.junit.Assert.assertEquals;
import infrastructure.carSimulation.ClutchSimulation;

import org.junit.Before;
import org.junit.Test;

public class ClutchTest  {
	private ClutchSimulation clutch;

	@Before
	public void setUp() {
		clutch = new ClutchSimulation();
	}
	
	@Test
	public void isClutchPedalPressed_VariousTests() {
		assertEquals(false, clutch.isClutchPedalPressed());
		clutch.pressClutchPedal(true);
		assertEquals(true, clutch.isClutchPedalPressed());
		clutch.pressClutchPedal(false);
		assertEquals(false, clutch.isClutchPedalPressed());
	}
}
