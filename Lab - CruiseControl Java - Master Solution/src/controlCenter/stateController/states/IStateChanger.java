/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController.states;

public interface IStateChanger {
	void changeState(StateKind newState);
}
