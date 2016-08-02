/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.car;

import crossCutting.problemDomain.interfaces.IPedal;
import crossCutting.problemDomain.interfaces.PedalState;

abstract class Pedal implements IPedal {

	@Override
	public final boolean isPressed() {
		return this.state() == PedalState.Pressed;
	}

	@Override
	public final boolean isReleased() {
		return this.state() == PedalState.Released;
	}
}
