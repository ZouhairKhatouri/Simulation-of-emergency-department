package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import auxiliary.Det;
import auxiliary.Exp;
import auxiliary.ProbabilityDensity;
import auxiliary.Unif;
import core.ED;
import human.Nurse;
import human.Patient;
import human.Physician;
import human.Transporter;
import throwables.InvalidDistributionException;
import throwables.SyntaxErrorException;

/**
 * 
 * This class contains a panel that is shown when the GUI is launched.
 * The panel contains fields and components that allows to set a configuration of an ED that is then saved in the attribute ed.
 *
 */

public class MainPanel extends Observable {
	
	/**
	 *   Return parameters.
	 */
	
	private JPanel mainPanel;   
	private ED ed = null;
	
	/**
	 * Data getted from the user input.
	 */
	
	private String edName = null;
	private int NgenPhy;
	private int NgenNurse;
	private int NgenTransp;
	private double Varg11 = -1;
	private double Varg21 = -1;
	private double Varg12 = -1;
	private double Varg22 = -1;
	private double Varg13 = -1;   // Internal parameters
	private double Varg23 = -1;
	private double Varg14 = -1;
	private double Varg24 = -1;
	private double Varg15 = -1;
	private double Varg25 = -1;
	private int Nwait ;
	private int Nshock ;
	private int Nbox ;
	private int Nradio ;
	private int Nmri ;
	private int Nblood ;
	private String arrivalDistType = "Unif";
	private String consultationDistType = "Unif";
	private String radiographyDistType = "Unif";
	private String bloodtestDistType = "Unif";
	private String mriDistType = "Unif";
	public ArrayList<Patient> newPatients = new ArrayList<Patient>();
	public ArrayList<Physician> newPhysicians = new ArrayList<Physician>();  
	public ArrayList<Nurse> newNurses = new ArrayList<Nurse>();
	public ArrayList<Transporter> newTransporters = new ArrayList<Transporter>();
	
	/**
	 * Containers for fields.
	 */
	
	private static String cont1 = null;
	private static String cont2 = null; 
	
	public MainPanel() {
		
				// Definition of the main panel
		
				this.mainPanel = new JPanel();
				
				mainPanel.setBackground(Color.WHITE);
				
				mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
				
				mainPanel.setBorder(BorderFactory.createTitledBorder(""));
				
				
				
				
				// Panels for human resources:
				
				JPanel hrPanel = new JPanel();
				
				hrPanel.setBackground(Color.WHITE);
				
				hrPanel.setBorder(BorderFactory.createTitledBorder("Human-Resources"));
				
				
				
				
					// Panel for physicians
				
					JPanel physiciansPanel = new JPanel();
					
					physiciansPanel.setBackground(Color.WHITE);
				
					physiciansPanel.setLayout(new BoxLayout(physiciansPanel,BoxLayout.PAGE_AXIS));
					
					physiciansPanel.setBorder(BorderFactory.createTitledBorder("Physicians"));
				

						// Automatique Initialization
				
						JPanel genPhyPanel = new JPanel();
						
						genPhyPanel.setBackground(Color.WHITE);
					
						JLabel genPhyLabel = new JLabel("Generate a new physician: ");
					
						JButton genPhyButton = new JButton("Generate");
						
						JLabel NgenPhyLabel = new JLabel("");
							
						genPhyButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								NgenPhy++;
								NgenPhyLabel.setText(Integer.toString(NgenPhy));
							}});
						
					
					
						genPhyPanel.add(genPhyLabel);
						genPhyPanel.add(genPhyButton);
						genPhyPanel.add(NgenPhyLabel);
				
						// Full Initialization
			
						JPanel createPhyPanel = new JPanel();
						
						createPhyPanel.setBackground(Color.WHITE);
						
						JLabel createPhyLabel = new JLabel("Create manually your own physician: ");
						
						JButton createPhyButton = new JButton("create");
						
						createPhyButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								newPhysician();
								
							}});
						
						createPhyPanel.add(createPhyLabel);
						createPhyPanel.add(createPhyButton);
				
					physiciansPanel.add(createPhyPanel);
					physiciansPanel.add(genPhyPanel);
					
					
					
					
					
					// Panel for nurses
				
					JPanel nursesPanel = new JPanel();
					
					nursesPanel.setBackground(Color.WHITE);
					
					nursesPanel.setLayout(new BoxLayout(nursesPanel,BoxLayout.PAGE_AXIS));
					
					nursesPanel.setBorder(BorderFactory.createTitledBorder("Nurses"));
					
					
						// Automatique Initialization
					
						JPanel genNursePanel = new JPanel();
						
						genNursePanel.setBackground(Color.WHITE);
					
						JLabel genNurseLabel = new JLabel("Generate a new nurse: ");
				
						JButton genNurseButton = new JButton("Generate");
						
						JLabel NgenNurseLabel = new JLabel("");
						
						genNurseButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								NgenNurse++;
								NgenNurseLabel.setText(Integer.toString(NgenNurse));
							}});
						
						
				
						genNursePanel.add(genNurseLabel);
						genNursePanel.add(genNurseButton);
						genNursePanel.add(NgenNurseLabel);
					
						// Full Initialization
				
						JPanel createNursePanel = new JPanel();
						
						createNursePanel.setBackground(Color.WHITE);
					
						JLabel createNurseLabel = new JLabel("Create manually your own nurse: ");
					
						JButton createNurseButton = new JButton("create");
						
						createNurseButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								newNurse();	
							}});
					
						createNursePanel.add(createNurseLabel);
						createNursePanel.add(createNurseButton);
			
					nursesPanel.add(createNursePanel);
					nursesPanel.add(genNursePanel);
					
				
					
					
					
					
					// Panel for transporters
				
					JPanel transportersPanel = new JPanel();
					
					transportersPanel.setBackground(Color.WHITE);
					
					transportersPanel.setLayout(new BoxLayout(transportersPanel,BoxLayout.PAGE_AXIS));
					
					transportersPanel.setBorder(BorderFactory.createTitledBorder("Transporters"));
					
					
					
						// Automatique Initialization
					
						JPanel genTransPanel = new JPanel();
						
						genTransPanel.setBackground(Color.WHITE);
					
						JLabel genTransLabel = new JLabel("Generate a new transporter: ");
			
						JButton genTransButton = new JButton("Generate");
						
						JLabel NgenTransLabel = new JLabel("");
						
						genTransButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								NgenTransp++;
								NgenTransLabel.setText(Integer.toString(NgenTransp));
							}});
						
						
			
						genTransPanel.add(genTransLabel);
						genTransPanel.add(genTransButton);
						genTransPanel.add(NgenTransLabel);
					
					
						// Full Initialization
					
						JPanel createTransPanel = new JPanel();
						
						createTransPanel.setBackground(Color.WHITE);
					
						JLabel createTransLabel = new JLabel("Create manually your own transporter: ");
				
						JButton createTransButton = new JButton("create");
						
						createTransButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								newTransporter();
							}});
				
						createTransPanel.add(createTransLabel);
						createTransPanel.add(createTransButton);
				
					
					transportersPanel.add(createTransPanel);
					transportersPanel.add(genTransPanel);
						
						
						
						
				
					
				hrPanel.add(physiciansPanel);
				hrPanel.add(nursesPanel);
				hrPanel.add(transportersPanel);
				
				
				
				// Panel for none human resources:
				
				JPanel nhrPanel = new JPanel();
				
				nhrPanel.setBackground(Color.WHITE);
				
				nhrPanel.setBorder(BorderFactory.createTitledBorder("None-Human-Resources"));
				
				
					// Panel for Health-Services
				
					JPanel servicesPanel = new JPanel();
					
					servicesPanel.setBackground(Color.WHITE);
				
					servicesPanel.setLayout(new BoxLayout(servicesPanel,BoxLayout.PAGE_AXIS));
					
					servicesPanel.setBorder(BorderFactory.createTitledBorder("Health-Services"));
					
					
					
					
						// Panel for Arrival
					
						JPanel arrivalPanel = new JPanel();
						
						arrivalPanel.setBackground(Color.WHITE);
					
						JLabel arrivalLabel = new JLabel("Arrival department parameters: ");
					
						JRadioButton Unif1 = new JRadioButton("Unif",true);
						
						JRadioButton Exp1 = new JRadioButton("Exp");
						
						JRadioButton Det1 = new JRadioButton("Det");
							
						JTextField arg11 = new JTextField("",3);
							
						JTextField arg21 = new JTextField("",3);
						
						ButtonGroup group1 = new ButtonGroup();
					
						group1.add(Unif1);
					
						group1.add(Exp1);
					
						group1.add(Det1);
						
						Unif1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg21.setEnabled(true);
								arrivalDistType = "Unif";
							}});
					
						Exp1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg21.setEnabled(false);
								arrivalDistType = "Exp";
							}});
					
						Det1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg21.setEnabled(false);
								arrivalDistType = "Det";
							}});
						
						arg11.addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent e) {	
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg11.getText().equals("")) {
										Varg11 = Double.parseDouble(arg11.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						
						arg21.addFocusListener(new FocusListener() {
							@Override
							public void focusGained(FocusEvent e) {
							}

							@Override
							public void focusLost(FocusEvent e) {

								try {
									if(!arg21.getText().equals("")) {
										Varg21 = Double.parseDouble(arg21.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						// Adding Component
					
						arrivalPanel.add(arrivalLabel);
						arrivalPanel.add(Unif1);
						arrivalPanel.add(Exp1);
						arrivalPanel.add(Det1);
						arrivalPanel.add(arg11);
						arrivalPanel.add(arg21);
						
					
						
						
						
						// Panel for Consultation
						
						JPanel consultationPanel= new JPanel();
						
						consultationPanel.setBackground(Color.WHITE);
						
						JLabel consultationLabel = new JLabel("Consultation department parameters: ");
					
						JRadioButton Unif2 = new JRadioButton("Unif",true);
						
						JRadioButton Exp2 = new JRadioButton("Exp");
						
						JRadioButton Det2 = new JRadioButton("Det");
						
						ButtonGroup group2 = new ButtonGroup();
					
						group2.add(Unif2);
					
						group2.add(Exp2);
					
						group2.add(Det2);
						
						JTextField arg12 = new JTextField("",3);
						
						JTextField arg22 = new JTextField("",3);
						
						Unif2.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg22.setEnabled(true);
								consultationDistType = "Unif";
							}});
					
						
						Exp2.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg22.setEnabled(false);
								consultationDistType = "Exp";
							}});
					
						
						
						Det2.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg22.setEnabled(false);
								consultationDistType = "Det";
							}});
					
						
						
						arg12.addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg12.getText().equals("")) {
										Varg12 = Double.parseDouble(arg11.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						
						arg22.addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg22.getText().equals("")) {
										Varg22 = Double.parseDouble(arg11.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						// Adding Component
						
						consultationPanel.add(consultationLabel);
						consultationPanel.add(Unif2);
						consultationPanel.add(Exp2);
						consultationPanel.add(Det2);
						consultationPanel.add(arg12);
						consultationPanel.add(arg22);
					
					
						
						
						
					
						// Panel for Radiography
						
						JPanel radiographyPanel = new JPanel();
						
						radiographyPanel.setBackground(Color.WHITE);
						
						JLabel radiographyLabel = new JLabel("Radiography department parameters: ");
					
						JRadioButton Unif3 = new JRadioButton("Unif",true);
					
						JRadioButton Exp3 = new JRadioButton("Exp");
						
						JRadioButton Det3 = new JRadioButton("Det");
					
						ButtonGroup group3 = new ButtonGroup();
					
						group3.add(Unif3);
					
						group3.add(Exp3);
					
						group3.add(Det3);
						
						JTextField arg13 = new JTextField("",3);
						
						JTextField arg23 = new JTextField("",3);
						
						Unif3.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg23.setEnabled(true);
								radiographyDistType = "Unif";
							}});
						
						Exp3.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg23.setEnabled(false);
								radiographyDistType = "Exp";
							}});
						
						Det3.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg23.setEnabled(false);
								radiographyDistType = "Det";
							}});
						
						arg13.addFocusListener(new FocusListener() {
							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg13.getText().equals("")) {
										Varg13 = Double.parseDouble(arg13.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						arg23.addFocusListener(new FocusListener() {
							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg23.getText().equals("")) {
										Varg23 = Double.parseDouble(arg23.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						// Adding Component
						
						radiographyPanel.add(radiographyLabel);
						radiographyPanel.add(Unif3);
						radiographyPanel.add(Exp3);
						radiographyPanel.add(Det3);
						radiographyPanel.add(arg13);
						radiographyPanel.add(arg23);
					
						
						
						
					
						// Panel for Blood-Test
						
						JPanel bloodTestPanel = new JPanel();
						
						bloodTestPanel.setBackground(Color.WHITE);
						
						JLabel bloodTestLabel = new JLabel("Blood-Test department parameters: ");
					
						JRadioButton Unif4 = new JRadioButton("Unif",true);
						
						JRadioButton Exp4 = new JRadioButton("Exp");
					
						JRadioButton Det4 = new JRadioButton("Det");
						
						ButtonGroup group4 = new ButtonGroup();
					
						group4.add(Unif4);
					
						group4.add(Exp4);
					
						group4.add(Det4);
						
						JTextField arg14 = new JTextField("",3);
						
						JTextField arg24 = new JTextField("",3);
						
						Unif4.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg24.setEnabled(true);
								bloodtestDistType = "Unif";
							}});
						
						Exp4.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg24.setEnabled(false);
								bloodtestDistType = "Exp";
							}});
						
						Det4.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg24.setEnabled(false);
								bloodtestDistType = "Det";
							}});
						
						arg14.addFocusListener(new FocusListener() {
							
							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg14.getText().equals("")) {
										Varg14 = Double.parseDouble(arg14.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						arg24.addFocusListener(new FocusListener() {
							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {

								try {
									if(!arg24.getText().equals("")) {
										Varg24 = Double.parseDouble(arg24.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						// Adding Component
						
						bloodTestPanel.add(bloodTestLabel);
						bloodTestPanel.add(Unif4);
						bloodTestPanel.add(Exp4);
						bloodTestPanel.add(Det4);
						bloodTestPanel.add(arg14);
						bloodTestPanel.add(arg24);
					
						
						
					
						// Panel for MRI
						
						JPanel mriPanel = new JPanel();
						
						mriPanel.setBackground(Color.WHITE);
						
						JLabel mriLabel = new JLabel("MRI department parameters: ");
					
						JRadioButton Unif5 = new JRadioButton("Unif",true);				
					
						JRadioButton Exp5 = new JRadioButton("Exp");
						
						JRadioButton Det5 = new JRadioButton("Det");
						
						ButtonGroup group5 = new ButtonGroup();
					
						group5.add(Unif5);
					
						group5.add(Exp5);
					
						group5.add(Det5);
						
						JTextField arg15 = new JTextField("",3);
						
						JTextField arg25 = new JTextField("",3);
						
						Unif5.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg25.setEnabled(true);
								mriDistType = "Unif";
							}});
							
						Exp5.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg25.setEnabled(false);
								mriDistType = "Exp";
							}});
						
						Det5.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								arg25.setEnabled(false);
								mriDistType = "Det";
							}});
						
						arg15.addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg15.getText().equals("")) {
										Varg15 = Double.parseDouble(arg15.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						arg25.addFocusListener(new FocusListener() {
							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								
								try {
									if(!arg25.getText().equals("")) {
										Varg25 = Double.parseDouble(arg25.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (double)");
								}
							}});
						
						// Adding Component
						
						mriPanel.add(mriLabel);
						mriPanel.add(Unif5);
						mriPanel.add(Exp5);
						mriPanel.add(Det5);
						mriPanel.add(arg15);
						mriPanel.add(arg25);
						
						
					
					
					servicesPanel.add(arrivalPanel)	;
					servicesPanel.add(consultationPanel)	;
					servicesPanel.add(bloodTestPanel)	;
					servicesPanel.add(radiographyPanel)	;
					servicesPanel.add(mriPanel)	;
					
					// Panel for Rooms
				
					JPanel roomsPanel = new JPanel();
					
					roomsPanel.setBackground(Color.WHITE);
					
					roomsPanel.setLayout(new BoxLayout(roomsPanel,BoxLayout.PAGE_AXIS));
					
					roomsPanel.setBorder(BorderFactory.createTitledBorder("Rooms"));
					
					
						// Panel for Waiting-room
					
						JPanel waitingRoomPanel = new JPanel();
						
						waitingRoomPanel.setBackground(Color.WHITE);
						
						JLabel waitingRoomLabel = new JLabel("Set the number of initial waiting-rooms: ");
						
						JTextField arg1 = new JTextField("",3);
						
						arg1.addFocusListener(new FocusListener() {
							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg1.getText().equals("")) {
										Nwait=Integer.parseInt(arg1.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (int)");
								}
							}});
						
						waitingRoomPanel.add(waitingRoomLabel);
						
						waitingRoomPanel.add(arg1);

					
					
						// Panel for Box-room
						
						JPanel boxRoomPanel = new JPanel();
						
						boxRoomPanel.setBackground(Color.WHITE);
					
						JLabel boxRoomLabel = new JLabel("Set the number of initial box-rooms: ");
						
						JTextField arg2 = new JTextField("",3);
						
						arg2.addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg2.getText().equals("")) {
										Nbox=Integer.parseInt(arg2.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (int)");
								}
							}});
						
						boxRoomPanel.add(boxRoomLabel);
						
						boxRoomPanel.add(arg2);
					
					
						// Panel for Shock-room
					
						JPanel shockRoomPanel = new JPanel();
						
						shockRoomPanel.setBackground(Color.WHITE);
						
						JLabel shockRoomLabel = new JLabel("Set the number of initial shock-rooms: ");
						
						JTextField arg3 = new JTextField("",3);
						
						arg3.addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg3.getText().equals("")) {
										Nshock=Integer.parseInt(arg3.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (int)");
								}
							}});
						
						shockRoomPanel.add(shockRoomLabel);
						
						shockRoomPanel.add(arg3);
					
					
						// Panel for MRI-room
					
						JPanel mriRoomPanel = new JPanel();
						
						mriRoomPanel.setBackground(Color.WHITE);
						
						JLabel mriRoomLabel = new JLabel("Set the number of initial mri-rooms: ");
						
						JTextField arg4 = new JTextField("",3);
						
						arg4.addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {
								try {
									if(!arg4.getText().equals("")) {
										Nmri=Integer.parseInt(arg4.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (int)");
								}
							}});
						
						mriRoomPanel.add(mriRoomLabel);
						
						mriRoomPanel.add(arg4);
					
					
						// Panel for Radiography-room
					
						JPanel radiographyRoomPanel = new JPanel();
						
						radiographyRoomPanel.setBackground(Color.WHITE);
						
						JLabel radiographyRoomLabel = new JLabel("Set the number of initial radiography-rooms: ");
						
						JTextField arg5 = new JTextField("",3);
						
						arg5.addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {

								try {
									if(!arg5.getText().equals("")) {
										Nradio=Integer.parseInt(arg5.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (int)");
								}
							}});
						
						radiographyRoomPanel.add(radiographyRoomLabel);
						
						radiographyRoomPanel.add(arg5);
						
					
					
						// Panel for BloodTest Laboratory
				
						JPanel bloodTestLabPanel = new JPanel();
						
						bloodTestLabPanel.setBackground(Color.WHITE);
						
						JLabel bloodTestLabLabel = new JLabel("Set the number of initial blood-test laboratories: ");
						
						JTextField arg6 = new JTextField("",3);
						
						arg6.addFocusListener(new FocusListener() {

							@Override
							public void focusGained(FocusEvent e) {
								
							}

							@Override
							public void focusLost(FocusEvent e) {

								try {
									if(!arg6.getText().equals("")) {
										Nblood=Integer.parseInt(arg6.getText());
									}
									return;
								}
								catch (java.lang.NumberFormatException f) {
									notifyObservers("syntax error (int)");
								}
							}});
						
						bloodTestLabPanel.add(bloodTestLabLabel);
						
						bloodTestLabPanel.add(arg6);
					
					
					roomsPanel.add(waitingRoomPanel);
					roomsPanel.add(shockRoomPanel);
					roomsPanel.add(boxRoomPanel);
					roomsPanel.add(radiographyRoomPanel);
					roomsPanel.add(mriRoomPanel);
					roomsPanel.add(bloodTestLabPanel);
						
					
				nhrPanel.add(servicesPanel);
				nhrPanel.add(roomsPanel);
				
				
				
				
				// Panel for ED creation
				
				JPanel edPanel = new JPanel();
				
				edPanel.setBackground(Color.WHITE);
				
				edPanel.setBorder(BorderFactory.createTitledBorder(""));
				
				JLabel edLabel = new JLabel("Choise the emergency department name: ");
				
				JTextField edNameField = new JTextField("", 20);
				
				JButton createEDButton = new JButton("Start");
				
				JButton clearEDButton = new JButton("Clear");
				
				edNameField.addFocusListener(new FocusListener() {
					
					@Override
					public void focusGained(FocusEvent e) {
						
					}

					@Override
					public void focusLost(FocusEvent e) {
						edName = edNameField.getText();
					}
					
				});
				
				createEDButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String hasfailed = "";
						hasfailed = tryStart();
						notifyObservers("Starting "+hasfailed);
					}});
				
				clearEDButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						notifyObservers("Clear");
					}
					});
				
				edPanel.add(edLabel);
				edPanel.add(edNameField);
				edPanel.add(createEDButton);
				edPanel.add(clearEDButton);
				
				
				
				
				mainPanel.add(edPanel);
				mainPanel.add(hrPanel);
				mainPanel.add(nhrPanel);
				
	}
	
	/**
	 * 
	 * A nested class for generating Distributions given the fields.
	 *
	 */
	
	public static class DistributionFactory {
		
		public ProbabilityDensity makeDistribution(String distType,double[] args) throws InvalidDistributionException {
			
				if(distType.equals("Unif") && args[0]<=args[1] && args[0]>=0) {
					return new Unif(args[0],args[1]);
				}

				if(distType.equals("Exp") && args[0]>=0) {
					return new Exp(args[0]);
				}

				if(distType.equals("Det") && args[0]>=0) {
					return new Det(args[0]);
				}

				else {
					throw new InvalidDistributionException(distType,args);
				}
			
		}

	}

	/**
	 * This method initialises a frame in order to add manual a new physician to the ed.
	 */
	
	private void newPhysician() {
		JFrame frame = new JFrame("Added physician");
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.WHITE);
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JPanel panel1 = new JPanel();
		
		panel1.setBackground(Color.WHITE);
		
		JPanel panel2 = new JPanel(); // Each panel is for getting an attribute

		panel2.setBackground(Color.WHITE);
		
		JLabel label1 = new JLabel("Name: ");
		JLabel label2 = new JLabel("Surname: ");

		
		JTextField field1 = new JTextField("",10);
		JTextField field2 = new JTextField("",10);

		
		panel1.add(label1);
		panel1.add(field1);
		
		panel2.add(label2);
		panel2.add(field2);	
		
		panel.add(panel1);
		panel.add(panel2);
		
		JButton add = new JButton("Add");
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					Physician newPhysician = new Physician(cont1,cont2,0);
					
					newPhysicians.add(newPhysician);
					
					frame.dispose();
					
				}
				catch(java.lang.NumberFormatException f) {
					
					notifyObservers("syntax error (double)");
					
				}
				catch(SyntaxErrorException f) {
					
					notifyObservers("Incomplete Input");
					
				}
			}
			
		});
		
		panel.add(add);
		
		
		field1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				MainPanel.cont1 = field1.getText();
			}
			
		});
		field2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				MainPanel.cont2 = field2.getText();
			}
			
		});
		
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		return;
	}
	
	/**
	 * This method initialises a frame in order to add manual a new nurse to the ed.
	 */
	
	private void newNurse() {
		JFrame frame = new JFrame("Added nurse");
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.WHITE);
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JPanel panel1 = new JPanel();
		
		panel1.setBackground(Color.WHITE);
		
		JPanel panel2 = new JPanel(); // Each panel is for getting an attribute

		panel2.setBackground(Color.WHITE);
		
		JLabel label1 = new JLabel("Name: ");
		JLabel label2 = new JLabel("Surname: ");

		
		JTextField field1 = new JTextField("",10);
		JTextField field2 = new JTextField("",10);
		
		JButton add = new JButton("Add");
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					Nurse newNurse = new Nurse(cont1,cont2,0);
					
					newNurses.add(newNurse);
					
					frame.dispose();
					
				}
				catch(java.lang.NumberFormatException f) {
					
					notifyObservers("syntax error (double)");
					
				}
				catch(SyntaxErrorException f) {
					
					notifyObservers("Incomplete Input");
					
				}
			}
			
		});


		
		panel1.add(label1);
		panel1.add(field1);
		
		
		panel2.add(label2);
		panel2.add(field2);	
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(add);
		
		field1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				MainPanel.cont1 = field1.getText();
			}
			
		});
		field2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				MainPanel.cont2 = field2.getText();
			}
			
		});
		
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		return;
	}
	
	/**
	 * This method initialises a frame in order to add manual a new transporter to the ed.
	 */
	
	private void newTransporter() {
		JFrame frame = new JFrame("Added transporter");
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.WHITE);
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JPanel panel1 = new JPanel();
		
		panel1.setBackground(Color.WHITE);
		
		JPanel panel2 = new JPanel(); // Each panel is for getting an attribute

		panel2.setBackground(Color.WHITE);
		
		JLabel label1 = new JLabel("Name: ");
		JLabel label2 = new JLabel("Surname: ");

		
		JTextField field1 = new JTextField("",10);
		JTextField field2 = new JTextField("",10);
		
		JButton add = new JButton("Add");
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					Transporter newTransporter = new Transporter(cont1,cont2,0);
					
					newTransporters.add(newTransporter);
					
					frame.dispose();
					
				}
				catch(java.lang.NumberFormatException f) {
					
					notifyObservers("syntax error (double)");
					
				}
				catch(SyntaxErrorException f) {
					
					notifyObservers("Incomplete Input");
					
				}
			}
			
		});

		
		panel1.add(label1);
		panel1.add(field1);
		
		
		panel2.add(label2);
		panel2.add(field2);	
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(add);
		
		field1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				MainPanel.cont1 = field1.getText();
			}
			
		});
		field2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				MainPanel.cont2 = field2.getText();
			}
			
		});
		
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		return;
	}

	/**
	 * 
	 * This method tries to launch a new ed in the GUI by notifiying the GUI frame.
	 * It can leads to warnings if there is a wrong distribution input or a missing mandatory field.
	 * 
	 */
	
	private String tryStart() {
		try {
			
			this.ed = new ED(edName,NgenPhy,NgenNurse,NgenTransp,(new DistributionFactory()).makeDistribution(arrivalDistType,
					new double[] {Varg11,Varg21}),(new DistributionFactory()).makeDistribution(consultationDistType
							,new double[] {Varg12,Varg22}),(new DistributionFactory()).makeDistribution(bloodtestDistType
					,new double[] {Varg14,Varg24}),(new DistributionFactory()).makeDistribution(radiographyDistType,new double[] {Varg13,Varg23}),
					(new DistributionFactory()).makeDistribution(mriDistType,new double[] {Varg15,Varg25}),Nblood,Nradio,Nmri,Nwait,Nbox,Nshock,0);
			
			for(Patient patient : newPatients) {
				ed.patients.add(patient);
				patient.setID(ed.nextID());
			}
			for(Physician physician : newPhysicians) {
				ed.physicians.add(physician);
				physician.setID(ed.nextID());
			}
			for(Nurse nurse : newNurses) {
				ed.nurses.add(nurse);
				nurse.setID(ed.nextID());
			}
			for(Transporter transporter : newTransporters) {
				ed.transporters.add(transporter);
				transporter.setID(ed.nextID());
			}
			return "didn't failed";
		}
		catch(Exception e) {
			
			return "has failed (E)";
			
		}
		catch(InvalidDistributionException e) {
			
			return "has failed (D)";
			
		}
	}
	
	// Getters and setters.
	
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public ED getEd() {
		return ed;
	}

	public void setEd(ED ed) {
		this.ed = ed;
	}

}
