/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.car;

import crossCutting.infrastructure.interfaces.ICar;
import crossCutting.problemDomain.interfaces.ISpeedProvider;

public class SpeedProvider implements ISpeedProvider {
	private WheelSensor wheelSensor;

	public SpeedProvider(ICar car) {
		wheelSensor = new WheelSensor(car);
	}

	@Override
	public float getCurrentSpeed() {
		synchronized(this) {
			long increments = wheelSensor.getWheelIncrements();
			return increments / 100;
		}
	}

	@Override
	public void calculateSpeed() {
		synchronized(this) {
			wheelSensor.getWheelIncrements();
		}
	}
}
