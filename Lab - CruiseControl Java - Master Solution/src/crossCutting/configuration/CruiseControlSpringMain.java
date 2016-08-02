/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package crossCutting.configuration;

import java.io.IOException;
import java.util.Timer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import infrastructure.carSimulation.CarSimulation;
import problemDomain.speedRegulator.SpeedRegulator;
import userInterface.cruiseControlGUI.CruiseControlWindow;
import userInterface.timer.CruiseControlThread;
import controlCenter.stateController.StateController;
import crossCutting.controlCenter.interfaces.IServiceInterface;
import crossCutting.problemDomain.interfaces.ISpeedProvider;

/**
 * Cruise control starter that uses Spring dependency injection (see
 * context.xml)
 * 
 */
public class CruiseControlSpringMain {

	private static final int scCruiseControlFrequency = 200; // ms

	private ApplicationContext context;
	private CruiseControlThread ccThread;
	private Timer ccTask;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		CruiseControlSpringMain main = new CruiseControlSpringMain();
		main.startupCruiseControl();
		main.startCruiseControlThread();
	}
	
	public void shutdownCruiseControl() {
		stopCruiseControlThread();
	}


	private void startupCruiseControl() throws IOException {
		CarSimulation carSimulation = getBean("car");
		ISpeedProvider speedProvider = getBean("speedProvider");
		StateController stateController = getBean("stateController");
		IServiceInterface serviceItfCtrl = getBean("serviceItfCtrl");
		SpeedRegulator rglSpeed = getBean("rglSpeed");
		ccThread = new CruiseControlThread(speedProvider, stateController,
				   carSimulation.getSimulatedBrake(),
				   carSimulation.getSimulatedThrottle());
		ccTask = new Timer(true);
		new CruiseControlWindow(stateController, stateController,
                serviceItfCtrl,
				  rglSpeed,
				  carSimulation.getSpeedModel(),
				  carSimulation.getSimulatedThrottle(),
				  carSimulation.getSimulatedGearing(),
				  carSimulation.getSimulatedBrake(),
				  carSimulation.getSimulatedClutch());

	}
	
	private void startCruiseControlThread() {
		if (ccTask != null)
			ccTask.scheduleAtFixedRate(ccThread, 0, scCruiseControlFrequency);
	}

	private void stopCruiseControlThread() {
		if (ccTask != null)
			ccTask.cancel();
	}

	@SuppressWarnings("unchecked")
	private <T> T getBean(String name) {
		return (T) getContext().getBean(name);
	}
	
	private ApplicationContext getContext() {
		// lazy initialization
		if(context == null) {
			context = new ClassPathXmlApplicationContext("classpath:userInterface/configuration/context.xml" );
		}
		return context;
	}

}
