package gui;

import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import auxiliary.ProbabilityDensity;
import core.ED;
import human.Patient;
import throwables.InfiniteLoopException;

/**
 * 
 * This class representes the frame of the GUI .
 *
 */

public class GUIframe implements Observer{
	
	/**
	 * 
	 * @param mainPanelObj : the panel used for adding new EDs to the system.
	 * @param frame : the main frame of the GUI.
	 * @param tabbedpane : the tabbedpane used for the GUI.
	 * @param guimenuBar : the menu bar used for the GUI.
	 * @param EDmap : a map between the EDs names and their panels class.
	 * @param SIMmap : a map between the EDs names and their simulations class.
	 * @param simulatedtime : the time precision used for the simulations. It must be fixed by the designer.
	 * 
	 */
	
	private MainPanel mainPanelObj;
	public JFrame frame;
	private JTabbedPane tabbedpane;
	private GUImenuBar guimenuBar;
	public HashMap<String,EDPanel> EDmap = new HashMap<String,EDPanel>();
	public HashMap<String,SimErgyGUI> SIMmap = new HashMap<String,SimErgyGUI>();
	public double simulatedtime = 0.5;

	public GUIframe() {
		
		this.frame = new JFrame("Simulation of emergency departements");
		
		frame.setSize(1650,1080);
		
		this.tabbedpane = new JTabbedPane();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		// Definition of the menu bar
		
		this.guimenuBar = new GUImenuBar();
		
		guimenuBar.addObserver(this);
		
		
		
		// Definition of the starting panel
		
		this.mainPanelObj = new MainPanel();
		
		mainPanelObj.addObserver(this);
		
		JPanel mainPanel = mainPanelObj.getMainPanel();
		
		// Launching bloc
		
		Thread Opening = new Thread() {
			
			public synchronized void run() {
				
				frame.setContentPane(new JLabel(new ImageIcon("ED.jpg")));
				
				frame.setVisible(true);
				
				return;
				
			}
		};
		
		Thread MainThread = new Thread() {
			
			public synchronized void run() {
				
				try {
					SwingUtilities.invokeAndWait(Opening);
				} catch (InvocationTargetException | InterruptedException e2) {
					e2.printStackTrace();
				}
				
				try {
					wait(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				frame.setJMenuBar(guimenuBar.getMenuBar());
				
				frame.setContentPane(mainPanel);
				
				frame.setVisible(true);
				
				return;
				
			}
			
		};
		
		MainThread.start();
		
	}
	
	/**
	 * The updating occures when the observed panels sends messages to the frame.
	 */
	
	@Override
	public void update(Object obj) {
		
		if(obj instanceof String) {
			
			String string = (String)obj;
			
			switch(string)	{
			
			case "Clear":
				
				this.mainPanelObj = new MainPanel();
				
				mainPanelObj.addObserver(this);
				
				JPanel mainPanel = mainPanelObj.getMainPanel();
				
				frame.setContentPane(mainPanel);
				
				frame.setVisible(true);
				
				return;
				
			case "Starting didn't failed":
				
				addED();
				
				return;
				
			case "Starting has failed (E)":
				
				JOptionPane.showMessageDialog(this.frame,"You can't create this emergency department yet",
						"Impossible Creation",JOptionPane.ERROR_MESSAGE);
				
				return;
				
			case "Starting has failed (D)":
				
				JOptionPane.showMessageDialog(this.frame,"You imputed a syntax-wrong distribution",
						"Impossible Creation",JOptionPane.ERROR_MESSAGE);
				
				return;
			
			case "Incomplete Input":
				
				JOptionPane.showMessageDialog(this.frame,"Please fill all the blank spaces",
						"Incomplete Input",JOptionPane.WARNING_MESSAGE);
				
				return;
				
			case "syntax error (double)":
				
				JOptionPane.showMessageDialog(this.frame,"You can input only real numbers",
						"Wrong input",JOptionPane.WARNING_MESSAGE);
				
				return;
				
			case "bad severity input":

				JOptionPane.showMessageDialog(this.frame,"You can only choose L1, L2, L3, L4 or L5 for severity",
						"Wrong input",JOptionPane.WARNING_MESSAGE);
				
				return;
				
			case "bad healthInsurance input":

				JOptionPane.showMessageDialog(this.frame,"You can only choose Silver, Gold or let it empty",
						"Wrong input",JOptionPane.WARNING_MESSAGE);
				
				return;
				
			case "create ED":
				
				this.mainPanelObj = new MainPanel();
				
				mainPanelObj.addObserver(this);
				
				frame.setContentPane(mainPanelObj.getMainPanel());
				
				frame.setVisible(true);
				
				return;
				
			case "Show EDs":
				
				if(EDmap.isEmpty()) {

					JOptionPane.showMessageDialog(this.frame,"You have to create an emergency department",
							"No department available",JOptionPane.WARNING_MESSAGE);
					
					
				}
				
				else {
					
					frame.setContentPane(tabbedpane);
					
					frame.setVisible(true);
					
				}
				
				return;
				
			case "Launch":
				
				ED edInit = new ED("Hospital Saint Louis", 4, 4, 4, 1, 1, 1, 5, 5, 5, 10);
				
				EDPanel edPanelInit = new EDPanel(edInit);
				
				// Mapping
				
				EDmap.put(edInit.name,edPanelInit);
				
				SimErgyGUI simInit = new SimErgyGUI(edInit,edPanelInit.getCommandType() , simulatedtime, edPanelInit.T);
				
				SIMmap.put(edInit.name,simInit);
				
				edPanelInit.addObserver(this);
				
				JScrollPane sbr = new JScrollPane(edPanelInit.getEdPanel());
				
				tabbedpane.add(sbr,edInit.name);
				
				frame.setContentPane(tabbedpane);
				
				frame.setVisible(true);
				
				return;
				
			case "Clear Session":
				
				frame.dispose();
				
				new GUIframe();
				
				return;
				
			case "syntax error (int)":
				
				JOptionPane.showMessageDialog(this.frame,"You can input only integers",
						"Wrong input",JOptionPane.WARNING_MESSAGE);
				
				return;
			
			default:
				
				// Looking for the right edPanel
				
				for(String edName: EDmap.keySet()) {
					
						// When the message is "start simulation " +edName;
					
						if(string.equals("start simulation "+edName)) {
							
							EDPanel edPanel = EDmap.get(edName);
							
							ED ed = edPanel.getEd();
							
							// Lunching the simulation
							
							SimErgyGUI sim = SIMmap.get(edName);
							
							sim.setT(edPanel.T);
							
							sim.run();

							// Setting the ed panel
							
							edPanel.eventsPanel.removeAll();
							
							edPanel.eventsPanel.setLayout(new BoxLayout(edPanel.eventsPanel,BoxLayout.PAGE_AXIS));
							
							edPanel.eventsPanel.add(sim.eventsPanel());
							
							edPanel.dtdtValueLabel.setText(Double.toString(sim.currentDTDTAverage()));
							
							edPanel.losValueLabel.setText(Double.toString(sim.currentLOSAverage()));
							
							// Setting the panel for patients:
							
							edPanel.patientsPanel.removeAll();
							
							edPanel.patientsPanel.setLayout(new GridLayout(0,3));
							
							JPanel Ppanel;
						
							for(Patient patient : ed.patients) {
								
								Ppanel = (new PatientPanel(patient)).getPanel();
								
								edPanel.patientsPanel.add(Ppanel);
								
							}
							
							edPanel.clockLabel.setText(Double.toString(ed.clock));
							
							return;
							
							
						}
						
						// When the message is like "NextEvent "+edName
						
						else if (string.equals("NextEvent "+edName)) {
							
							SimErgyGUI sim = SIMmap.get(edName);
							
							try {
								
								sim.nextEvents();
								
							} catch (InfiniteLoopException e) {
								
								JOptionPane.showMessageDialog(this.frame,"No new event will occure, please create another emergency department or clear this session",
										"Last outcome occured",JOptionPane.WARNING_MESSAGE);
								
								return;
								
							}
							
							EDPanel edPanel = EDmap.get(edName);
							
							ED ed = edPanel.getEd();
							
							edPanel.getEdPanel().updateUI();
							
							// Setting the ed panel
							
							edPanel.eventsPanel.removeAll();
							
							edPanel.eventsPanel.setLayout(new BoxLayout(edPanel.eventsPanel,BoxLayout.PAGE_AXIS));
							
							edPanel.eventsPanel.add(sim.eventsPanel());
							
							edPanel.dtdtValueLabel.setText(Double.toString(sim.currentDTDTAverage()));
							
							edPanel.losValueLabel.setText(Double.toString(sim.currentLOSAverage()));
							
							// Setting the panel for patients:
							
							edPanel.patientsPanel.removeAll();
							
							edPanel.patientsPanel.setLayout(new GridLayout(0,3));
							
							JPanel Ppanel;
						
							for(Patient patient : ed.patients) {
								
								Ppanel = (new PatientPanel(patient)).getPanel();
								
								edPanel.patientsPanel.add(Ppanel);
								
							}
							
							edPanel.clockLabel.setText(Double.toString(ed.clock));
							
							return;

							
						}
						
						// When the message is like "Set me"+edName
						
						else if(string.equals("Set me "+edName)) {
							
							SimErgyGUI sim = SIMmap.get(edName);
							
							EDPanel edPanel = EDmap.get(edName);
							
							if(edPanel.getCommandType().equals("Manual")) {
								sim.isManual=true;
								edPanel.simLabel.setText("Start a manual simulation with a horizon-time: ");
							}
							
							else {
								sim.isManual=false;
								edPanel.simLabel.setText("Start a driven simulation with a horizon-time: ");
							}
							
							return;
						}
					
				}
				
				return;
				
			}
		}
		
		// The two other case is for setting the arrival time probability of patients within a given ED.
		
		else if(obj instanceof ArrivalL_iFrame) {
			
			((ArrivalL_iFrame)obj).addObserver(this);
			
		}
		
		else if(obj instanceof Object[] && ((Object[])obj).length == 3) {
			
			Object[] array = (Object[])obj;
			
			String edName = (String)array[0];
			
			String severity = (String)array[1];

			ProbabilityDensity dist = (ProbabilityDensity)array[2];
			
			SimErgyGUI sim = SIMmap.get(edName);

			switch(severity) {
			
			case "L1": 
				sim.setProbL1(dist);
				return;
				
			case "L2": 
				sim.setProbL2(dist);
				return;
				
			case "L3": 
				sim.setProbL3(dist);
				return;
				
			case "L4": 
				sim.setProbL4(dist);
				return;
				
			case "L5": 
				sim.setProbL5(dist);
				return;
				
			}
		}
		
		else {
			return;
		}
	}
	
	/**
	 * This method adds a new ED to the system.
	 */
	
	private void addED() {
		
		ED ed = mainPanelObj.getEd();
		
		EDPanel edPanel = new EDPanel(ed);
		
		SimErgyGUI sim = new SimErgyGUI(ed,edPanel.getCommandType() , simulatedtime, edPanel.T);
		
		SIMmap.put(ed.name,sim);
		
		// Mapping
		
		EDmap.put(ed.name,edPanel);
		
		edPanel.addObserver(this);
		
		JScrollPane sbr = new JScrollPane(edPanel.getEdPanel());
		
		tabbedpane.add(sbr,ed.name);
		
		frame.setContentPane(tabbedpane);
		
		return;
		
	}


}
