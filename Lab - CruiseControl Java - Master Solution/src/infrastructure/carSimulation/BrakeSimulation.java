/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.carSimulation;

public class BrakeSimulation {
	private static boolean brakePressed = false;
	private SpeedModel speedModel;

	public BrakeSimulation(SpeedModel iSpeedModel) {
		speedModel = iSpeedModel;
	}

	public boolean isBrakePedalPressed() {
		return brakePressed;
	}

	public void pressBrake(boolean pressed) {
		if (pressed)
			System.out.println("SimulatedBrake pressed");
		else
			System.out.println("SimulatedBrake released");

		brakePressed = pressed;
	}

	public void calculateSpeed() {
		if (brakePressed) {
			speedModel.setRelativeThrottleValvePosition(-1); // brake
		}
	}
}
