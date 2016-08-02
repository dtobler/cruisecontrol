package controlCenter.stateController;

import infrastructure.carSimulation.CarSimulation;
import problemDomain.car.Brake;
import problemDomain.car.CarElementStructurer;
import problemDomain.car.Clutch;
import problemDomain.car.GearBox;
import problemDomain.car.Throttle;
import controlCenter.stateController.stub.SpeedProviderTestStub;
import controlCenter.stateController.stub.SpeedRegulatorTestStub;
import crossCutting.infrastructure.interfaces.ICar;

public abstract class StateControllerTest {
	protected StateController stateController;
	protected ICar car;
	protected Brake brake;
	protected Clutch clutch;
	protected GearBox gearbox;
	protected Throttle throttle;
	protected SpeedProviderTestStub speedProviderStub;
	protected SpeedRegulatorTestStub speedRegulatorStub;
	protected CarSimulation carSimulation;

	protected void setUp() {
		this.carSimulation = new CarSimulation();
		this.car = carSimulation;
		this.brake = new Brake(car);
		this.clutch = new Clutch(car);
		this.gearbox = new GearBox(car);
		this.throttle = new Throttle(car);
		this.speedProviderStub = new SpeedProviderTestStub(car);
		CarElementStructurer ceStruct = new CarElementStructurer(speedProviderStub,
				brake, throttle, clutch, gearbox);
		this.speedRegulatorStub = new SpeedRegulatorTestStub();

		this.stateController = new StateController(speedRegulatorStub, ceStruct);

		initialize();
	}

	protected void tearDown() {
		initialize();
	}

	private void initialize() {
		this.carSimulation.setBrake(false);
		this.carSimulation.setClutchPedal(false);
		this.carSimulation.setThrottlePressed(false);
		this.carSimulation.setGear(5);
		this.speedProviderStub.setSpeed((float)50.0);
	}
}
