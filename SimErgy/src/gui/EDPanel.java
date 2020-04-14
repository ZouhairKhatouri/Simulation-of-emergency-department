package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import core.ED;
import human.Nurse;
import human.Patient;
import human.Physician;
import human.Transporter;
import resource.BloodTestLab;
import resource.BoxRoom;
import resource.MRIroom;
import resource.RadiographyRoom;
import resource.ShockRoom;
import resource.WaitingRoom;
import throwables.SyntaxErrorException;

/**
 * 
 * 
 * This class generates a panel that is added to the gui and containing all relevent informations about resources and patients whitin a given ed.
 * It also gives the possibility to perform changes on the contained ed using commands buttons.
 * 
 *
 */


public class EDPanel extends Observable {
	
	/**
	 * 
	 * @param ed : The emergency department viewed through this panel.
	 * @param edPanel : the container of all used components 
	 * @param dtdtValueLabel : a label containing the DTDT value of the ed.
	 * @param losValueLabel : a label containing the LOS value of the ed.
	 * @param commandType : the type of the simulation used for this ed.
	 * @param T : the horizon time for a simulation when launched.
	 * @param patientsPanel : a panel containing the patients within the ed.
	 * @param clockLabel : a label showing out the current time in the ed.
	 * @param simLabel : a label showing out the type off simulation used for the ed.
	 *    
	 */

	private ED ed;
	private JPanel edPanel = new JPanel();
	public JLabel dtdtValueLabel = new JLabel();
	public JLabel losValueLabel = new JLabel();
	public JPanel eventsPanel;
	private String commandType = "Driven";
	public double T = 1440;
	public JPanel patientsPanel;
	public JLabel clockLabel;
	public JLabel simLabel;

	/**
	 * Containers for fields informations about the new patient.
	 */
	
	private static String cont1 = null;
	private static String cont2 = null; 
	private static String cont3 = null;
	private static String cont4 = null;
	private static double cont5 = -1;
	
	public EDPanel(ED ed) {
		
		this.ed=ed;
		
		this.edPanel = new JPanel();
		
		edPanel.setBackground(Color.WHITE);
		
		edPanel.setLayout(new BoxLayout(edPanel,BoxLayout.PAGE_AXIS));
		
		edPanel.setBorder(BorderFactory.createTitledBorder(ed.name));
		
		// Panel for Settings 
		
		JPanel settingsPanel = new JPanel();
		
		settingsPanel.setBackground(Color.WHITE);
		
		settingsPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
		
		JRadioButton buttonD = new JRadioButton("Driven simulation",true);
		
		JRadioButton buttonM = new JRadioButton("Manual simulation");
		
		ButtonGroup group = new ButtonGroup();
		
		group.add(buttonM);
		
		group.add(buttonD);
		
		buttonM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				commandType = "Manual";
				notifyObservers("Set me "+ed.name);
				return;
			}
			
		});
		
		buttonD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				commandType = "Driven";
				notifyObservers("Set me "+ed.name);
				return;
			}
			
		});
		
		JButton arrivalL1Button = new JButton("L1 new arrival settings");
		
		arrivalL1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrivalL_iFrame frameL1 = new ArrivalL_iFrame(ed.name,"L1");
				
				notifyObservers(frameL1);
				
			}
			
		});
		
		JButton arrivalL2Button = new JButton("L2 new arrival settings");
		
		arrivalL2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrivalL_iFrame frameL2 = new ArrivalL_iFrame(ed.name,"L2");
				
				notifyObservers(frameL2);
				
			}
			
		});
		
		JButton arrivalL3Button = new JButton("L3 new arrival settings");
		
		arrivalL3Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrivalL_iFrame frameL3 = new ArrivalL_iFrame(ed.name,"L3");
				
				notifyObservers(frameL3);
				
			}
			
		});
		
		JButton arrivalL4Button = new JButton("L4 new arrival settings");
		
		arrivalL4Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrivalL_iFrame frameL4 = new ArrivalL_iFrame(ed.name,"L4");
				
				notifyObservers(frameL4);
				
			}
			
		});
		
		JButton arrivalL5Button = new JButton("L5 new arrival settings");
		
		arrivalL5Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrivalL_iFrame frameL5 = new ArrivalL_iFrame(ed.name,"L5");
				
				notifyObservers(frameL5);
				
			}
			
		});
		
		// Showing the ed clock
		
		JLabel label1 = new JLabel("The current time in the ED is: ");
		this.clockLabel = new JLabel(Double.toString(ed.clock));
		JLabel label3 = new JLabel("min");
		
		settingsPanel.add(buttonD);
		settingsPanel.add(buttonM);
		settingsPanel.add(arrivalL1Button);
		settingsPanel.add(arrivalL2Button);
		settingsPanel.add(arrivalL3Button);
		settingsPanel.add(arrivalL4Button);
		settingsPanel.add(arrivalL5Button);
		settingsPanel.add(label1);
		settingsPanel.add(clockLabel);
		settingsPanel.add(label3);
		
		
		// Panel for commands
		
		JPanel commandPanel = new JPanel();
		
		commandPanel.setBackground(Color.WHITE);
		
		commandPanel.setLayout(new BoxLayout(commandPanel,BoxLayout.PAGE_AXIS));
		
		commandPanel.setBorder(BorderFactory.createTitledBorder("Commands"));
		
		JPanel simPanel = new JPanel();
		
		simPanel.setBackground(Color.WHITE);
		
		if(commandType =="Manual") {
			
			// Manual Simulation
			
			
			this.simLabel = new JLabel("Start a manual simulation with horizon-time: ");
			
			JTextField simFieldM = new JTextField("",3);
			
			simFieldM.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					try {
						if(!simFieldM.getText().equals("")) {
							T = Double.parseDouble(simFieldM.getText());
						}
						return;
					}
					catch (java.lang.NumberFormatException f) {
						notifyObservers("syntax error (double)");
					}
				}
				
			});
			
			JButton simButtonM = new JButton("Start");
			
			simButtonM.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					notifyObservers("start simulation "+ed.name);
					
				}
				
			});
			
			
			simPanel.add(simLabel);
			simPanel.add(simFieldM);
			simPanel.add(simButtonM);
				
				
		}
		else {
			
			// Driven Simulation 
			
			this.simLabel = new JLabel("Start a driven simulation with a horizon-time: ");
			
			JTextField simFieldD = new JTextField("",3);
			
			simFieldD.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					try {
						if(!simFieldD.getText().equals("")) {
							T = Double.parseDouble(simFieldD.getText());
						}
						return;
					}
					catch (java.lang.NumberFormatException f) {
						notifyObservers("syntax error (double)");
					}
				}
				
			});
		
			JButton simButtonD = new JButton("Start");
		
			simButtonD.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					notifyObservers("start simulation "+ed.name);
					
				}
				
			});
			
			simPanel.add(simLabel);
			simPanel.add(simFieldD);
			simPanel.add(simButtonD);
			
		}
		
		// frame for adding new patients
		
		JButton addPatient = new JButton("New patient");
				
		addPatient.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				newPatient();
		}
		});
				
		simPanel.add(addPatient);
		
		// A button for moving to the next event
		
		JButton next = new JButton("Next Event");
		
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				notifyObservers("NextEvent "+ed.name);
				
			}
			
		});
		
		simPanel.add(next);
		
		commandPanel.add(simPanel);
		
		// DTDT
		
		JPanel dtdtPanel = new JPanel();
		
		dtdtPanel.setBackground(Color.WHITE);
		
		JLabel dtdtLabel = new JLabel("DTDT current average: ");
		
		this.dtdtValueLabel = new JLabel("");
		
		dtdtPanel.add(dtdtLabel);
		dtdtPanel.add(dtdtValueLabel);
		
		commandPanel.add(dtdtPanel);
		
		// LOS
		
		JPanel losPanel = new JPanel();
		
		losPanel.setBackground(Color.WHITE);
		
		JLabel losLabel = new JLabel("LOS current average: ");
		
		this.losValueLabel = new JLabel("");
		
		losPanel.add(losLabel);
		losPanel.add(losValueLabel);
		
		commandPanel.add(losPanel);
		
		// History
		
		JPanel historyPanel = new JPanel();
		
		historyPanel.setBackground(Color.WHITE);
		
		historyPanel.setLayout(new BoxLayout(historyPanel,BoxLayout.PAGE_AXIS));
		
		historyPanel.setBorder(BorderFactory.createTitledBorder("History"));
		
		JButton showHistory = new JButton("Show"); 
		
		this.eventsPanel = new JPanel();
		
		eventsPanel.setBackground(Color.WHITE);
		
		eventsPanel.setLayout(new BoxLayout(eventsPanel,BoxLayout.PAGE_AXIS));
		
		eventsPanel.setVisible(false);
		
		showHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(showHistory.getText().equals("Show")) {
					showHistory.setText("Hide");
					eventsPanel.setVisible(true);
					return;
				}
				else {
					showHistory.setText("Show");
					eventsPanel.setVisible(false);
					return;
				}
				
			}
			
		});
				
		historyPanel.add(showHistory);
		historyPanel.add(eventsPanel);
		
		commandPanel.add(historyPanel);
		
		// Panel for patients
		
		this.patientsPanel = new JPanel();
		
		patientsPanel.setBackground(Color.WHITE);
		
		patientsPanel.setLayout(new GridLayout(0,3));
		
		patientsPanel.setBorder(BorderFactory.createTitledBorder("Patients"));
		
		JPanel Ppanel;
	
		for(Patient patient : ed.patients) {
			Ppanel = (new PatientPanel(patient)).getPanel();
			
			Ppanel.setBackground(Color.WHITE);
			
			patientsPanel.add(Ppanel);
		}
		
		
		// Panels for human resources:
		
		JPanel hrPanel = new JPanel();
		
		hrPanel.setBackground(Color.WHITE);
		
		hrPanel.setLayout(new BoxLayout(hrPanel,BoxLayout.PAGE_AXIS));
		
		hrPanel.setBorder(BorderFactory.createTitledBorder("Human-Resources"));
		
		JPanel Hpanel;
		
		
			// Panel for physicians
		
			JPanel physiciansPanel = new JPanel();
			
			physiciansPanel.setBackground(Color.WHITE);
		
			physiciansPanel.setLayout(new GridLayout(0,3));
			
			physiciansPanel.setBorder(BorderFactory.createTitledBorder("Physicians"));
		
			for(Physician physician : ed.physicians) {
				Hpanel = (new PhysicianPanel(physician)).getPanel();
				
				Hpanel.setBackground(Color.WHITE);
				
				physiciansPanel.add(Hpanel);
			}
			
			
			
			
			// Panel for nurses
		
			JPanel nursesPanel = new JPanel();
			
			nursesPanel.setBackground(Color.WHITE);
			
			nursesPanel.setLayout(new GridLayout(0,3));
			
			nursesPanel.setBorder(BorderFactory.createTitledBorder("Nurses"));
			
			for(Nurse nurse : ed.nurses) {
				Hpanel = (new NursePanel(nurse)).getPanel();
				
				Hpanel.setBackground(Color.WHITE);
				
				nursesPanel.add(Hpanel);
			}
				
			
		
			
			
			
			
			// Panel for transporters
		
			JPanel transportersPanel = new JPanel();
			
			transportersPanel.setBackground(Color.WHITE);
			
			transportersPanel.setLayout(new GridLayout(0,3));
			
			transportersPanel.setBorder(BorderFactory.createTitledBorder("Transporters"));
			
			for(Transporter transporter : ed.transporters) {
				Hpanel = (new TransporterPanel(transporter)).getPanel();
				
				Hpanel.setBackground(Color.WHITE);
				
				transportersPanel.add(Hpanel);
			}
				
				
				
		
			
		hrPanel.add(physiciansPanel);
		hrPanel.add(nursesPanel);
		hrPanel.add(transportersPanel);
		
		
		
		// Panel for none human resources:
		
		JPanel nhrPanel = new JPanel();
		
		nhrPanel.setBackground(Color.WHITE);
		
		nhrPanel.setLayout(new BoxLayout(nhrPanel,BoxLayout.PAGE_AXIS));
		
		nhrPanel.setBorder(BorderFactory.createTitledBorder("None-Human-Resources"));
		
		
			// Panel for Health-Services
		
			JPanel servicesPanel = new JPanel();
			
			servicesPanel.setBackground(Color.WHITE);
			
			servicesPanel.setBorder(BorderFactory.createTitledBorder("Health-Services"));
			
			
				// Panel for Arrival
			
				JPanel arrivalPanel = (new HealthServicePanel(this.ed.arrival)).getPanel();
				
				arrivalPanel.setBackground(Color.WHITE);
				
				// Panel for Consultation
				
				JPanel consultationPanel = (new HealthServicePanel(this.ed.consultationDepartment)).getPanel();
				
				consultationPanel.setBackground(Color.WHITE);
			
				// Panel for Radiography
				
				JPanel radiographyPanel = (new HealthServicePanel(this.ed.radiographyDepartment)).getPanel();
				
				radiographyPanel.setBackground(Color.WHITE);
			
				// Panel for Blood-Test
				
				JPanel bloodtestPanel = (new HealthServicePanel(this.ed.bloodTestDepartment)).getPanel();
				
				bloodtestPanel.setBackground(Color.WHITE);
			
				// Panel for MRI
				
				JPanel mriPanel = (new HealthServicePanel(this.ed.mriDepartment)).getPanel();
				
				mriPanel.setBackground(Color.WHITE);
				
				
			servicesPanel.add(arrivalPanel);
			servicesPanel.add(consultationPanel);
			servicesPanel.add(radiographyPanel);
			servicesPanel.add(bloodtestPanel);
			servicesPanel.add(mriPanel);
			
			
			// Panel for Rooms
		
			JPanel roomsPanel = new JPanel();
			
			roomsPanel.setBackground(Color.WHITE);
			
			roomsPanel.setBorder(BorderFactory.createTitledBorder("Rooms"));
			
			RoomPanel roomPanel;
			
			
				// Panel for Waiting-room
			
				JPanel waitingRoomPanel = new JPanel();
				
				waitingRoomPanel.setBackground(Color.WHITE);
				
				waitingRoomPanel.setLayout(new GridLayout(0,3));
				
				waitingRoomPanel.setBorder(BorderFactory.createTitledBorder("Waiting-rooms"));
				
				for(WaitingRoom room: ed.waitingRooms) {
					
					roomPanel = new RoomPanel(room);
					
					waitingRoomPanel.add(roomPanel.panel);
					
				}
				
							
				// Panel for Box-room
				
				JPanel boxRoomPanel = new JPanel();
				
				boxRoomPanel.setBackground(Color.WHITE);
			
				boxRoomPanel.setLayout(new GridLayout(0,3));
				
				boxRoomPanel.setBorder(BorderFactory.createTitledBorder("Box-rooms"));
				
				for(BoxRoom room: ed.boxRooms) {
					
					roomPanel = new RoomPanel(room);
					
					boxRoomPanel.add(roomPanel.panel);
					
				}
			
			
				// Panel for Shock-room
			
				JPanel shockRoomPanel = new JPanel();
				
				shockRoomPanel.setBackground(Color.WHITE);
				
				shockRoomPanel.setLayout(new GridLayout(0,3));
				
				shockRoomPanel.setBorder(BorderFactory.createTitledBorder("Shock-rooms"));
				
				for(ShockRoom room: ed.shockRooms) {
					
					roomPanel = new RoomPanel(room);
					
					shockRoomPanel.add(roomPanel.panel);
					
				}
			
			
				// Panel for MRI-room
			
				JPanel mriRoomPanel = new JPanel();
				
				mriRoomPanel.setBackground(Color.WHITE);
				
				mriRoomPanel.setLayout(new GridLayout(0,3));
				
				mriRoomPanel.setBorder(BorderFactory.createTitledBorder("MRI-rooms"));
				
				for(MRIroom room: ed.mriRooms) {
					
					roomPanel = new RoomPanel(room);
					
					mriRoomPanel.add(roomPanel.panel);
					
				}
			
				
			
				// Panel for Radiography-room
			
				JPanel radiographyRoomPanel = new JPanel();
				
				radiographyRoomPanel.setBackground(Color.WHITE);
				
				radiographyRoomPanel.setLayout(new GridLayout(0,3));
				
				radiographyRoomPanel.setBorder(BorderFactory.createTitledBorder("Radiography-rooms"));
				
				for(RadiographyRoom room: ed.radiographyRooms) {
					
					roomPanel = new RoomPanel(room);
					
					radiographyRoomPanel.add(roomPanel.panel);
					
				}
				
			
			
				// Panel for BloodTest Laboratory
		
				JPanel bloodTestLabPanel = new JPanel();
				
				bloodTestLabPanel.setBackground(Color.WHITE);
				
				bloodTestLabPanel.setLayout(new GridLayout(0,3));
				
				bloodTestLabPanel.setBorder(BorderFactory.createTitledBorder("Blood-Test Laboratories"));

				for(BloodTestLab room: ed.bloodTestLabs) {
					
					roomPanel = new RoomPanel(room);
					
					bloodTestLabPanel.add(roomPanel.panel);
					
				}
				
			
			
			roomsPanel.add(waitingRoomPanel);
			roomsPanel.add(boxRoomPanel);
			roomsPanel.add(shockRoomPanel);
			roomsPanel.add(radiographyRoomPanel);
			roomsPanel.add(mriRoomPanel);
			roomsPanel.add(bloodTestLabPanel);
			
			
				
			
		nhrPanel.add(servicesPanel);
		nhrPanel.add(roomsPanel);
		
		
		
		edPanel.add(settingsPanel);
		edPanel.add(commandPanel);
		edPanel.add(patientsPanel);
		edPanel.add(hrPanel);
		edPanel.add(nhrPanel);
	}
	
	/**
	 * This method initialises a frame in order to add manual a new patient to the ed.
	 */
	
	
	private void newPatient() {
		
		JFrame frame = new JFrame("New patient");
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel(); // Each panel is for getting an attribute
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		
		JLabel label1 = new JLabel("Name: ");
		JLabel label2 = new JLabel("Surname: ");
		JLabel label3 = new JLabel("HealthInsurance: ");
		JLabel label4 = new JLabel("Severity");
		JLabel label5 = new JLabel("Arrival timestamp:");
		
		JTextField field1 = new JTextField("",10);
		JTextField field2 = new JTextField("",10);
		JTextField field3 = new JTextField("",10);
		JTextField field4 = new JTextField("",10);
		JTextField field5 = new JTextField("",10);
		
		panel1.add(label1);
		panel1.add(field1);
		
		panel2.add(label2);
		panel2.add(field2);
		
		panel3.add(label3);
		panel3.add(field3);
		
		panel4.add(label4);
		panel4.add(field4);
		
		panel5.add(label5);
		panel5.add(field5);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		
		JButton add = new JButton("Add");
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cont3.equals("") || cont3.equals("Silver") || cont3.equals("Gold")) {
					if(cont4.equals("L1") || cont4.equals("L2") ||cont4.equals("L3") ||cont4.equals("L4") ||cont4.equals("L5")) {
						
						try {
							
							Patient newPatient = new Patient(cont1,cont2,0,cont3,cont4,cont5);
							
							ed.patients.add(newPatient);
							
							newPatient.setID(ed.nextID());
							
							JPanel newPanel = (new PatientPanel(newPatient)).getPanel();
							
							patientsPanel.add(newPanel);
							
							patientsPanel.updateUI();
							
							frame.dispose();
							
						}
						catch(java.lang.NumberFormatException f) {
							
							notifyObservers("syntax error (double)");
							
						}
						catch(SyntaxErrorException f) {
							
							notifyObservers("Incomplete Input");
							
						}
						
					}
					else {
						notifyObservers("bad severity input");
					}
				}
				else {
					notifyObservers("bad healthInsurance input");
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
				cont1 = field1.getText();
			}
			
		});
		field2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				cont2 = field2.getText();
			}
			
		});
		field3.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				cont3 = field3.getText();
			}
			
		});
		field4.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				cont4 = field4.getText();
			}
			
		});
		field5.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if(!field4.getText().equals("")) {
						cont5 = Double.parseDouble(field5.getText());
					}
				}
				catch(java.lang.NumberFormatException f) {
					notifyObservers("syntax error (double)");
				}
			}
			
		});
		
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		return;
	}
	
	// Getters and Setters
	
	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}
	
	public JPanel getEdPanel() {
		return edPanel;
	}
	public ED getEd() {
		return ed;
	}


}
