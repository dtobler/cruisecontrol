/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package crossCutting.controlCenter.interfaces;

import java.util.Observer;

public interface ICruiseControlTransitionModel {
	void addTransitionObserver(Observer transitionObserver);
}
