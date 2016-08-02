/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package controlCenter.stateController;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

class TransitionObserverHelper implements Observer {

	private ArrayList<Observer> transitionObservers = new ArrayList<Observer>();

	public TransitionObserverHelper() {
		
	}
	@Override
	public void update(Observable unused, Object argument) {
		for(Observer observer: transitionObservers) {
			observer.update(null, argument);
		}
	}

	public void addTransitionObserver(Observer transitionObserver) {
		transitionObservers.add(transitionObserver);
	}
}
