/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.carSimulation;

public class WheelSensorSimulation {
	private SpeedModel speedModel;

	public WheelSensorSimulation(SpeedModel speedModel) {
		this.speedModel = speedModel;
	}

	public long getWheelIncrements() {
		return speedModel.getWheelIncrements();
	}

	public void resetWheelTurnCounter() {
		// Nothing to do with this in Speed Model
	}
}
