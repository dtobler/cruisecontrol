/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.carSimulation;

import java.util.Observable;

/**
 * Speedmodel:<br>
 * Throttlevalvepos 0..100%<br>
 * 0% -> 0km/h<br>
 * 100% -> 200km/h<br>
 * Wheelincrements:<br>
 * 0km/h -> 0 increments<br>
 * 200km/h -> 20000 increments<br>
 * Patterns: Observer (Observable for actual speed)
 */
public class SpeedModel extends Observable {
	private float disturbance;
	private int throttleValvePos;
	private static final int MaxThrottleValvePos = 100;
	private static final int MaxWheelInc = 20000;

	public SpeedModel() {
		disturbance = 0;
		throttleValvePos = MaxThrottleValvePos / 4; // = 50km/h;
		speedHasChanged();
	}

	public int getWheelIncrements() {
		int wheelInc = 0;

		wheelInc = throttleValvePos * (MaxWheelInc / MaxThrottleValvePos);
		wheelInc -= disturbance * 100;

		return wheelInc;
	}

	public void setAbsoluteThrottleValvePosition(int newAbsolutePosition) {
		if (newAbsolutePosition > MaxThrottleValvePos)
			newAbsolutePosition = MaxThrottleValvePos;

		if (newAbsolutePosition < 0)
			newAbsolutePosition = 0;

		throttleValvePos = newAbsolutePosition;
		speedHasChanged();
	}

	public void setRelativeThrottleValvePosition(int newDeltaPosition) {
		throttleValvePos += newDeltaPosition;

		if (throttleValvePos > MaxThrottleValvePos)
			throttleValvePos = MaxThrottleValvePos;

		if (throttleValvePos < 0)
			throttleValvePos = 0;

		speedHasChanged();
	}

	public int getThrottleValvePos() {
		return throttleValvePos;
	}

	public void setDisturbance(float newDisturbance) {
		disturbance = newDisturbance;
		speedHasChanged();
	}

	public float getDisturbance() {
		return disturbance;
	}

	private void speedHasChanged() {
		Float speed = new Float(getWheelIncrements() / 100);
		setChanged();
		notifyObservers(speed);
	}
}
