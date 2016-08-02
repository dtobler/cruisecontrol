/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.car;

import crossCutting.infrastructure.interfaces.ICar;

class WheelSensor {
	private ICar car;

	public WheelSensor(ICar car) {
		this.car = car;
	}

	public long getWheelIncrements() {
		return car.getWheelIncrements();
	}

	public void resetWheelTurnCounter() {
		car.resetWheelTurnCounter();
	}
}
