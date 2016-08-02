/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.timer;

import java.util.TimerTask;
import infrastructure.carSimulation.BrakeSimulation;
import infrastructure.carSimulation.ThrottleSimulation;
import crossCutting.controlCenter.interfaces.ICarEventPoller;
import crossCutting.problemDomain.interfaces.ISpeedProvider;

public class CruiseControlThread extends TimerTask {
	
	private ICarEventPoller carEventPoller;
	private BrakeSimulation simBrake;

	private ThrottleSimulation simThrottle;

	public CruiseControlThread(ISpeedProvider speedProvider, ICarEventPoller iCarEventPoller,
			BrakeSimulation iSimBrake, ThrottleSimulation iSimThrottle) {
		carEventPoller = iCarEventPoller;
		simThrottle = iSimThrottle;
		simBrake = iSimBrake;
	}

	public void run() {
		simBrake.calculateSpeed();
		simThrottle.calculateSpeed();
		
		carEventPoller.controlCarEvents();
	}
}
