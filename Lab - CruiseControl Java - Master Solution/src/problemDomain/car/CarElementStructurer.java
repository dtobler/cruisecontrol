/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.car;

import crossCutting.problemDomain.interfaces.IGearBox;
import crossCutting.problemDomain.interfaces.IPedal;
import crossCutting.problemDomain.interfaces.ISpeedProvider;

public class CarElementStructurer {
	private ISpeedProvider speedProvider;
	private IPedal brake;
	private IPedal throttle;
	private IPedal clutch;
	private IGearBox gearbox;

	public CarElementStructurer(ISpeedProvider speedProvider, Brake brake,
			Throttle throttle, Clutch clutch, IGearBox gearing) {
		this.speedProvider = speedProvider;
		this.brake = brake;
		this.throttle = throttle;
		this.clutch = clutch;
		this.gearbox = gearing;
	}

	public IPedal getBrake() {
		return brake;
	}

	public IPedal getClutch() {
		return clutch;
	}

	public IGearBox getGearbox() {
		return gearbox;
	}

	public ISpeedProvider getSpeedProvider() {
		return speedProvider;
	}

	public IPedal getThrottle() {
		return throttle;
	}
}
