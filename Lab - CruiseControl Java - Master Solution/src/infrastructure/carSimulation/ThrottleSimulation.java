/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.carSimulation;

public class ThrottleSimulation {
	private static boolean throttlePressed = false;
	private SpeedModel speedModel;

	public ThrottleSimulation(SpeedModel speedModel) {
		this.speedModel = speedModel;
	}

	public boolean isThrottlePressed() {
		return throttlePressed;
	}

	public void changeRelativePosition(int deltaPosition) {
		speedModel.setRelativeThrottleValvePosition(deltaPosition);
	}

	public void changeAbsolutePosition(int pAbsolutePosition) {
		speedModel.setAbsoluteThrottleValvePosition(pAbsolutePosition);
	}

	public void pressThrottle(boolean pressed) {
		if (pressed)
			System.out.println("SimulatedThrottle pressed");
		else
			System.out.println("SimulatedThrottle released");

		throttlePressed = pressed;
	}

	public void calculateSpeed() {
		if (throttlePressed) {
			speedModel.setRelativeThrottleValvePosition(1);
		}
	}
}
