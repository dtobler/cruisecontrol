/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.carSimulation;

public class ClutchSimulation {
	private static boolean clutchPedalPressed;

	public ClutchSimulation() {
		clutchPedalPressed = false;
	}

	public void pressClutchPedal(boolean pressed) {
		if (pressed)
			System.out.println("SimulatedClutch pressed");
		else
			System.out.println("SimulatedClutch released");

		clutchPedalPressed = pressed;
	}

	public boolean isClutchPedalPressed() {
		return clutchPedalPressed;
	}
}
