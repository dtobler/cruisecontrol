/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.car;

import crossCutting.infrastructure.interfaces.ICar;
import crossCutting.problemDomain.interfaces.IThrottleValve;

public class ThrottleValve implements IThrottleValve {
	private ICar car;

	public ThrottleValve(ICar car) {
		this.car = car;
	}

	@Override
	public void changeThrottleValvePos(int pDelta) {
			car.changeRelativeThrottleValvePosition(pDelta);
	}
}
