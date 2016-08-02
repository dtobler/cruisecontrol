/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController.stub;

import crossCutting.infrastructure.interfaces.ICar;
import problemDomain.car.SpeedProvider;

public class SpeedProviderTestStub extends SpeedProvider {
	private float speed;

	public SpeedProviderTestStub(ICar car) {
		super(car);
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getCurrentSpeed() {
		return this.speed;
	}
}
