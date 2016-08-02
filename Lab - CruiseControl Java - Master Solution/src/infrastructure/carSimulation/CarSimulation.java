/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.carSimulation;

import crossCutting.infrastructure.interfaces.ICar;

public class CarSimulation implements ICar {
	private BrakeSimulation simulatedBrake;
	private GearingSimulation simlatedGearing;
	private WheelSensorSimulation simulatedWheelSensor;
	private ThrottleSimulation simulatedThrottle;
	private ClutchSimulation simulatedClutch;
	private SpeedModel speedModel;

	public CarSimulation() {
		speedModel = new SpeedModel();
		simulatedBrake = new BrakeSimulation(speedModel);
		simlatedGearing = new GearingSimulation();
		simulatedWheelSensor = new WheelSensorSimulation(speedModel);
		simulatedThrottle = new ThrottleSimulation(speedModel);
		simulatedClutch = new ClutchSimulation();
	}

	public boolean isBrakePedalPressed() {
		return simulatedBrake.isBrakePedalPressed();
	}

	public long getWheelIncrements() {
		return simulatedWheelSensor.getWheelIncrements();
	}

	public void resetWheelTurnCounter() {
		simulatedWheelSensor.resetWheelTurnCounter();
	}

	public boolean isHighestGearSelected() {
		return simlatedGearing.isHighestGearActive();
	}

	public boolean isClutchPedalPressed() {
		return simulatedClutch.isClutchPedalPressed();
	}

	public boolean isThrottlePressed() {
		return simulatedThrottle.isThrottlePressed();
	}

	public void changeRelativeThrottleValvePosition(int pDelta) {
		simulatedThrottle.changeRelativePosition(pDelta);
	}

	public void setClutchPedal(boolean pPress) {
		simulatedClutch.pressClutchPedal(pPress);
	}

	public void setThrottlePressed(boolean pPress) {
		simulatedThrottle.pressThrottle(pPress);
	}

	public float getCurrentSpeed() {
		return 0;
	}

	public void setGear(int pGear) {
		simlatedGearing.setGear(pGear);
	}

	public void setBrake(boolean pPress) {
		simulatedBrake.pressBrake(pPress);
	}

	public void setCurrentSpeed(float pNewSpeed) {
		int valvePos = (int) (pNewSpeed / 2);
		simulatedThrottle.changeAbsolutePosition(valvePos);
	}

	public BrakeSimulation getSimulatedBrake() {
		return simulatedBrake;
	}

	public ClutchSimulation getSimulatedClutch() {
		return simulatedClutch;
	}

	public GearingSimulation getSimulatedGearing() {
		return simlatedGearing;
	}

	public ThrottleSimulation getSimulatedThrottle() {
		return simulatedThrottle;
	}

	public WheelSensorSimulation getSimulatedWheelSensor() {
		return simulatedWheelSensor;
	}

	public SpeedModel getSpeedModel() {
		return speedModel;
	}
}
