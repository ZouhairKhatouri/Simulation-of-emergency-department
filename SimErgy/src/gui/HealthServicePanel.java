package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import human.Patient;
import resource.HealthService;

/**
 * 
 * This class generates a panel that is added to the gui and containing all relevent informations about a health-service.
 *
 */

public class HealthServicePanel {
	
	private HealthService service;
	private JPanel panel;
	
	public JPanel getPanel() {
		return panel;
	}

	public HealthServicePanel(HealthService service) {
		
		this.service = service;
		
		this.panel = new JPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder(service.getType()));
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel label = new JLabel("Time-service probability law: "+service.servicetime.getType());
		
		JButton button = new JButton("Details");
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showDetails();
			}
			
		});
		
		panel.add(label);
		panel.add(button);
	}
	
	/**
	 * This method launches a new frame where the user can see details about waiting-queue for this service.
	 */

	protected void showDetails() {
		
		JFrame frame = new JFrame(service.getType()+" department");
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel DetailsPanel = new JPanel();
		
		DetailsPanel.setBorder(BorderFactory.createTitledBorder(service.getType()));
		
		DetailsPanel.setLayout(new BoxLayout(DetailsPanel,BoxLayout.PAGE_AXIS));
		
		JPanel lawPanel = new JPanel();
		
		JLabel label = new JLabel("Time-service probability law: "+service.servicetime.getType());
		
		lawPanel.add(label);
		
		JPanel queuePanel = new JPanel();
		
		queuePanel.setBorder(BorderFactory.createTitledBorder("Patients waiting for this service"));
		
		queuePanel.setLayout(new GridLayout(0,3));
		
		if(service.waitingQueue.container.isEmpty()) {
			
			JLabel emptyLabel = new JLabel("No one is waiting in this queue");
			
			queuePanel.add(emptyLabel);
			
		}
		else {
			
			for(Patient patient : service.waitingQueue.container) {
				
				JPanel patientPanel = new JPanel();
				patientPanel.setLayout(new BoxLayout(patientPanel,BoxLayout.PAGE_AXIS));
				JLabel label1 = new JLabel("------------------------------------------------------");
				JLabel label2 = new JLabel("\nName: "+patient.getName());
				JLabel label3 = new JLabel("\nSurname: "+patient.getSurname());
				JLabel label4 = new JLabel("\nID number: "+patient.getID());
				JLabel label5 = new JLabel("\n------------------------------------------------------");
				patientPanel.add(label1);
				patientPanel.add(label2);
				patientPanel.add(label3);
				patientPanel.add(label4);
				patientPanel.add(label5);
				
				queuePanel.add(patientPanel);
			}
		}
		
		DetailsPanel.add(lawPanel);
		DetailsPanel.add(queuePanel);
		
		frame.setContentPane(DetailsPanel);
		frame.pack();
		frame.setVisible(true);
	}

}
