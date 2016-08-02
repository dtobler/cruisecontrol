/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.car;

import crossCutting.infrastructure.interfaces.ICar;
import crossCutting.problemDomain.interfaces.IGearBox;

public class GearBox implements IGearBox {
	private ICar car;

	public GearBox(ICar car) {
		this.car = car;
	}

	@Override
	public boolean isHighestGearEngaged() {
		return car.isHighestGearSelected();
	}
}
