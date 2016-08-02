/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package problemDomain.speedRegulator.stub;

import crossCutting.problemDomain.interfaces.IThrottleValve;

public class ThrottleValveTestStub implements IThrottleValve {
	public boolean throttleState = true;
	public int throttleValvePosDelta = 0;

	public boolean isThrottlePressed() {
		return throttleState;
	}

	public void changeThrottleValvePos(int delta) {
		throttleValvePosDelta = delta;
	}
}
