/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package crossCutting.problemDomain.interfaces;

public interface IPedal {
	PedalState state();
	boolean isPressed();
	boolean isReleased();
}
