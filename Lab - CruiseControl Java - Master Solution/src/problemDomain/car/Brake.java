/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.car;

import crossCutting.infrastructure.interfaces.ICar;
import crossCutting.problemDomain.interfaces.PedalState;

public class Brake extends Pedal {
	private ICar car;

	public Brake(ICar car) {
		this.car = car;
	}

	@Override
	public PedalState state() {
		return car.isBrakePedalPressed() ? PedalState.Pressed : PedalState.Released;
	}
}
