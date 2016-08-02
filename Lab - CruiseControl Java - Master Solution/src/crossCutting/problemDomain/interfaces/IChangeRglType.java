/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package crossCutting.problemDomain.interfaces;

public interface IChangeRglType {
	void setRegulatorType(RegulatorType newRglType);
	RegulatorType getRegulatorType();
}
