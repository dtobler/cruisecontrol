/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.car;

import crossCutting.infrastructure.interfaces.ICar;
import crossCutting.problemDomain.interfaces.PedalState;

public class Clutch extends Pedal {
	private ICar car;

	public Clutch(ICar car) {
		this.car = car;
	}

	@Override
	public PedalState state() {
		return car.isClutchPedalPressed() ? PedalState.Pressed : PedalState.Released;
	}
}
