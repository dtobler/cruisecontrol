/* Course Object Oriented Design for Practitioners
 * (c) 2011 by Zuehlke Engineering AG, Daniel Tobler
 */ 

package userInterface.cruiseControlGUI;

import infrastructure.carSimulation.BrakeSimulation;
import infrastructure.carSimulation.ClutchSimulation;
import infrastructure.carSimulation.GearingSimulation;
import infrastructure.carSimulation.SpeedModel;
import infrastructure.carSimulation.ThrottleSimulation;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import crossCutting.controlCenter.interfaces.ICruiseControlTransitionModel;
import crossCutting.controlCenter.interfaces.ICruiseController;
import crossCutting.controlCenter.interfaces.IServiceInterface;
import crossCutting.problemDomain.interfaces.IRegulateSpeed;

/**
 * @author Daniel Tobler
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class CruiseControlWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4535552836892670132L;

	// View Cruise Control Attributes
	private JPanel panelCruiseControlButton;
	private JButton buttonCCOn;
	private JButton buttonCCOff;
	private JButton buttonCCResume;

	private JPanel panelCruiseControlDisplay;
	private JPanel panelTargetSpeed;
	private JLabel labelTargetSpeed;

	private JPanel panelCCState;
	private JLabel labelCCStateOff;
	private JLabel labelCCStateOn;
	private JLabel labelCCStateActive;

	// View Car Attributes
	private JPanel panelCar;
	private JButton buttonBrake;
	private JButton buttonClutch;
	private JButton buttonThrottle;

	private JPanel panelEngagedGear;

	private JPanel panelCurrentSpeed;
	private JLabel labelCurrentSpeed;

	private JPanel panelDisturbance;
	private JTextField textDisturbance;

	private JPanel panelCommand;
	private JTextField textCommand;

	// Model Attributes
	private ICruiseController cruiseController;

	private IRegulateSpeed speedRegulator;

	private SpeedModel speedModel;

	private ThrottleSimulation simThrottle;

	private GearingSimulation simGearing;

	private BrakeSimulation simBrake;

	private ClutchSimulation simClutch;

	public CruiseControlWindow(ICruiseController iCruiseController,
			                   ICruiseControlTransitionModel cruiseControlStateModel,
			                   IServiceInterface serviceInterface,
			                   IRegulateSpeed iSpeedRegulator,
			                   SpeedModel iSpeedModel,
			                   ThrottleSimulation iSimThrottle,
			                   GearingSimulation iSimGearing,
			                   BrakeSimulation iSimBrake,
			                   ClutchSimulation iSimClutch) {
		super("CruiseControl");

		// Build Model
		// -----------
		cruiseController = iCruiseController;
		speedRegulator = iSpeedRegulator;
		speedModel = iSpeedModel;
		simThrottle = iSimThrottle;
		simGearing = iSimGearing;
		simBrake = iSimBrake;
		simClutch = iSimClutch;

		getContentPane().setLayout(new GridLayout(3, 3));

		// Build Cruise Control View
		// -------------------------
		panelCruiseControlButton = new JPanel();
		getContentPane().add(panelCruiseControlButton);
		buttonCCOn = new JButton("ON");
		panelCruiseControlButton.add(buttonCCOn);
		buttonCCOn.addActionListener(new CCOnListener(cruiseController));
		buttonCCOff = new JButton("OFF");
		panelCruiseControlButton.add(buttonCCOff);
		buttonCCOff.addActionListener(new CCOffListener(cruiseController));
		buttonCCResume = new JButton("Resume");
		panelCruiseControlButton.add(buttonCCResume);
		buttonCCResume.addActionListener(new CCResumeListener(cruiseController));

		panelCruiseControlDisplay = new JPanel();
		getContentPane().add(panelCruiseControlDisplay);
		panelCCState = new JPanel();
		panelCruiseControlDisplay.add(panelCCState);
		labelCCStateOff = new JLabel("X");
		labelCCStateOn = new JLabel(" ");
		labelCCStateActive = new JLabel(" ");
		panelCCState.add(new JLabel("OFF: "), JLabel.CENTER);
		panelCCState.add(labelCCStateOff);
		panelCCState.add(new JLabel("ON: "));
		panelCCState.add(labelCCStateOn);
		panelCCState.add(new JLabel("ACTIVE: "));
		panelCCState.add(labelCCStateActive);
		CCStateObserver ccStateObs = new CCStateObserver(labelCCStateOff,
				labelCCStateOn, labelCCStateActive);
		cruiseControlStateModel.addTransitionObserver(ccStateObs);

		panelTargetSpeed = new JPanel();
		panelCruiseControlDisplay.add(panelTargetSpeed);
		panelTargetSpeed.add(new JLabel("Target Speed: "), JLabel.CENTER);
		labelTargetSpeed = new JLabel("0");
		panelTargetSpeed.add(labelTargetSpeed);
		panelTargetSpeed.add(new JLabel(" km/h"));
		getContentPane().add(panelTargetSpeed);
		TargetSpeedObserver tsObs = new TargetSpeedObserver(labelTargetSpeed);
		speedRegulator.addTargetSpeedObserver(tsObs);

		// Build Car View
		// --------------
		panelEngagedGear = new JPanel();
		panelEngagedGear.add(new JLabel("Engaged Gear: "), JLabel.CENTER);
		JTextField mSelectedGear = new JTextField(simGearing.getGear());
		panelEngagedGear.add(mSelectedGear);
		getContentPane().add(panelEngagedGear);
		mSelectedGear.addActionListener(new GearListener(simGearing,
				mSelectedGear));
		mSelectedGear.setEditable(false);

		panelDisturbance = new JPanel();
		panelDisturbance.add(new JLabel("Disturbance: "), JLabel.CENTER);
		textDisturbance = new JTextField("    0");
		textDisturbance.addActionListener(new DisturbanceListener(
				textDisturbance, speedModel));
		panelDisturbance.add(textDisturbance);
		panelDisturbance.add(new JLabel(" km/h"));
		getContentPane().add(panelDisturbance);

		panelCurrentSpeed = new JPanel();
		panelCurrentSpeed.add(new JLabel("Current Speed: "), JLabel.CENTER);
		labelCurrentSpeed = new JLabel("0");
		panelCurrentSpeed.add(labelCurrentSpeed);
		panelCurrentSpeed.add(new JLabel(" km/h"));
		getContentPane().add(panelCurrentSpeed);
		CurrentSpeedObserver asObs = new CurrentSpeedObserver(labelCurrentSpeed);
		speedModel.addObserver(asObs);
		speedModel.setDisturbance(0); // force a change notification

		panelCar = new JPanel();
		buttonClutch = new JButton("Clutch");
		panelCar.add(buttonClutch);
		buttonClutch.addActionListener(new ClutchListener(simClutch, buttonClutch, mSelectedGear));
		buttonBrake = new JButton("Brake");
		panelCar.add(buttonBrake);
		buttonBrake.addActionListener(new BrakeListener(simBrake, buttonBrake));
		buttonThrottle = new JButton("Throttle");
		panelCar.add(buttonThrottle);
		buttonThrottle.addActionListener(new ThrottleListener(simThrottle,
				buttonThrottle));
		getContentPane().add(panelCar);
		
		panelCommand = new JPanel();
		textCommand = new JTextField(20);
		textCommand.addActionListener(new CommandListener(textCommand, serviceInterface));
		panelCommand.add(new JLabel("Enter Command:"));
		panelCommand.add(textCommand);
		getContentPane().add(panelCommand);
		
		

		addWindowListener(new CruiseControlWindowListener());
		pack();
		setVisible(true);
	}
}
