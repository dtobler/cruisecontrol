/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.carToyota;

import crossCutting.infrastructure.interfaces.ICar;

public class CarToyota implements ICar {
	private int throttleState = 0;

	public CarToyota() {
	}

	public boolean isBrakePedalPressed() {
		if(CarUtility.BrakeState() == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getWheelIncrements() {
		long wheelInc = CarUtility.WheelInc() * 40 / 100;
		resetWheelTurnCounter();
		return wheelInc;
	}

	public void resetWheelTurnCounter() {
		CarUtility.ResetWheelInc();
	}

	public boolean isHighestGearSelected() {
		if(CarUtility.GetActiveGear() == CarUtility.HighestGear)
			return true;
		else
			return false;
	}

	public boolean isClutchPedalPressed() {
		if(CarUtility.ClutchState() == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isThrottlePressed() {
		if(CarUtility.ThrottleState() == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public void changeRelativeThrottleValvePosition(int delta) {
		throttleState += delta;
		CarUtility.SetSpeed(throttleState);
	}
}
