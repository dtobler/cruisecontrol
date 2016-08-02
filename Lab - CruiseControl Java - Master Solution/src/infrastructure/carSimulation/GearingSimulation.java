/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package infrastructure.carSimulation;

public class GearingSimulation {
	private static final int HighestGear = 5;
	private static int activeGear = HighestGear;

	public GearingSimulation() {
		activeGear = HighestGear;
	}

	public boolean isHighestGearActive() {
		if (activeGear == HighestGear)
			return true;
		else
			return false;
	}

	public int getGear() {
		return activeGear;
	}

	public void setGear(int selectedGear) {
		if (selectedGear > HighestGear)
			activeGear = HighestGear;
		else if (selectedGear < 1)
			activeGear = 1;
		else
			activeGear = selectedGear;
		System.out.println("Gear changed to " + activeGear);
	}
}
