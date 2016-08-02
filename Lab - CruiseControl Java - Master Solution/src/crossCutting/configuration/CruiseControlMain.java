/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package crossCutting.configuration;

import java.util.Timer;

import infrastructure.carSimulation.CarSimulation;
import infrastructure.carToyota.CarToyota;
import problemDomain.car.Brake;
import problemDomain.car.CarElementStructurer;
import problemDomain.car.Clutch;
import problemDomain.car.GearBox;
import problemDomain.car.SpeedProvider;
import problemDomain.car.Throttle;
import problemDomain.car.ThrottleValve;
import problemDomain.speedRegulator.SpeedRegulator;
import userInterface.cruiseControlGUI.CruiseControlWindow;
import userInterface.serviceInterfaces.EthernetInterface;
import userInterface.timer.CruiseControlThread;
import controlCenter.serviceItfController.ServiceItfController;
import controlCenter.serviceItfController.commands.CommandFactory;
import controlCenter.stateController.StateController;
import crossCutting.controlCenter.interfaces.ICruiseControlTransitionModel;
import crossCutting.controlCenter.interfaces.ICruiseController;
import crossCutting.controlCenter.interfaces.IServiceInterface;
import crossCutting.infrastructure.interfaces.ICar;
import crossCutting.problemDomain.interfaces.IChangeRglType;
import crossCutting.problemDomain.interfaces.IGearBox;
import crossCutting.problemDomain.interfaces.IRegulateSpeed;
import crossCutting.problemDomain.interfaces.ISpeedProvider;
import crossCutting.problemDomain.interfaces.IThrottleValve;

public class CruiseControlMain {
	private static final int CruiseControlFrequency = 200; // ms
	private StateController stateController;
	private Timer ccTask;
	private IServiceInterface serviceInterface;
	private CruiseControlThread ccThread;

	public static void main(String[] args) {
		CruiseControlMain ccMain = new CruiseControlMain();
		ccMain.startupCruiseControl();
	}

	public CruiseControlMain() {
	}
	
	public void startupCruiseControl() {
		configureSystem();
		startCruiseControlThread();
	}
	
	public void shutdownCruiseControl() {
		stopCruiseControlThread();
	}

	private void configureSystem() {
		
		// Setup infrastructure layer
		CarSimulation carSimulation = new CarSimulation();
		@SuppressWarnings("unused")
		CarToyota carToyota = new CarToyota(); // Not used in simulation

		// Set active underlying car (Simulation, Toyota, ...) Enable only one line
		ICar car = carSimulation;
		// Car car = carToyota;

		// Setup problemDomain layer
		Brake brake = new Brake(car);
		Clutch clutch = new Clutch(car);
		IGearBox gearbox = new GearBox(car);
		Throttle throttle = new Throttle(car);
		IThrottleValve throttleValve = new ThrottleValve(car);
		ISpeedProvider speedProvider = new SpeedProvider(car);
		CarElementStructurer ceStruct = new CarElementStructurer(speedProvider,
				brake, throttle, clutch, gearbox);
		IRegulateSpeed rglSpeed = new SpeedRegulator(throttleValve);
		@SuppressWarnings("unused")
		IChangeRglType changeRglType = (IChangeRglType) rglSpeed;

		// Setup controlCenter layer
		stateController = new controlCenter.stateController.StateController(
				rglSpeed, ceStruct);
		CommandFactory cmdFactory = new CommandFactory(stateController);
		serviceInterface = new ServiceItfController(cmdFactory);

		// Setup userInterface layer, only if car simulation is available
		ccThread = new CruiseControlThread(speedProvider, stateController,
										   carSimulation.getSimulatedBrake(),
										   carSimulation.getSimulatedThrottle());
		ccTask = new Timer(true);
		@SuppressWarnings("unused")
		CruiseControlWindow wnd = new CruiseControlWindow((ICruiseController)stateController,
				                                          (ICruiseControlTransitionModel)stateController,
				                                          serviceInterface,
														  rglSpeed,
														  carSimulation.getSpeedModel(),
														  carSimulation.getSimulatedThrottle(),
														  carSimulation.getSimulatedGearing(),
														  carSimulation.getSimulatedBrake(),
														  carSimulation.getSimulatedClutch());
		
		@SuppressWarnings("unused")
		EthernetInterface ethItf = new EthernetInterface(serviceInterface, 4711);
	}

	private void startCruiseControlThread() {
		if (ccTask != null)
			ccTask.scheduleAtFixedRate(ccThread, 0, CruiseControlFrequency);
	}

	private void stopCruiseControlThread() {
		if (ccTask != null)
			ccTask.cancel();
	}
}
