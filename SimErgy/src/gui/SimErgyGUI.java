package gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.ED;
import core.SimErgy;
import event.Event;

/**
 * 
 * This class extends the class SimErgy used in the first part of the project
 *
 */

public class SimErgyGUI extends SimErgy {
	
	/**
	 * @param commandType: the type of simulation used.
	 * @param T: the horizon of simulation.
	 */

	private String commandType;
	private double T;

	public SimErgyGUI(ED ed, String commandType, double simulatedtime, double T) {
		super(ed,simulatedtime);
		this.commandType = commandType;
		this.T=T;
	}
	
	/**
	 * This method returns a panel filled up with described events that occured in the ed.
	 * 
	 * @return JPanel containing the history of events occured in the ed.
	 */
	
	public JPanel eventsPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		JLabel eventLabel;
		
		for(Event event:this.history.container) {
			eventLabel = new JLabel(event.show());
			panel.add(eventLabel);
		}
		
		return panel;
	}

	/**
	 * This method runs the simulation.
	 */

	public void run() {
		
			if(commandType=="Manual") {

				manualSimulation(T);
					
				return;
				
			}
			
			else {	
				
				drivenSimulation(T);
				
				return;
						
			}

	}

	public double getT() {
		return T;
	}

	public void setT(double t) {
		T = t;
	}
}
