package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import auxiliary.Message;
import event.Event;
import human.Patient;

/**
 * 
 * This class extends HumanPanel by overriding the showDetails() method.
 * It modelises the GUI view of a patient by launching a new frame containing details about the patient state.
 *
 */

public class PatientPanel extends HumanPanel{
	
	/**
	 * @param patient : the patient viewed through this class.
	 */
	
	private Patient patient;

	public PatientPanel(Patient patient) {
		super(patient);
		this.patient = patient;
	}

	@Override
	public void showDetails() {
		
		JFrame frame = new JFrame(patient.name+" "+patient.surname+" informations details");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		
		// General Panel
		
		JPanel generalPanel = new JPanel();
		generalPanel.setBackground(Color.WHITE);
		generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.PAGE_AXIS));
		generalPanel.setBorder(BorderFactory.createTitledBorder("General informations"));

		JLabel label1 = new JLabel("Name: " + patient.name);
		JLabel label2 = new JLabel("Surname: " +patient.surname);
		JLabel label3 = new JLabel("ID number: " +patient.ID);
		JLabel label4 = new JLabel("Health-Insurance: " +patient.healthInsurance);
		JLabel label5 = new JLabel("State: "+patient.state);
		JLabel label6 = new JLabel("Current location: "+patient.getLocation());
		JLabel label7 = new JLabel("Arrival-time: "+patient.getArrivalTime());
		JLabel label8 = new JLabel("Current charges: "+patient.charges());
		JLabel label9;
		if(patient.DTDTisavailable) {
			label9 = new JLabel("DTDT: "+patient.getDTDT());
		}
		else {
			label9 = new JLabel("DTDT isn't available");
		}
		JLabel label10;
		if(patient.LOSisavailable) {
			label10 = new JLabel("LOS: "+patient.getLOS());
		}
		else {
			label10 = new JLabel("LOS isn't available");
		}
		
		generalPanel.add(label1);
		generalPanel.add(label2);
		generalPanel.add(label3);
		generalPanel.add(label4);
		generalPanel.add(label5);
		generalPanel.add(label6);
		generalPanel.add(label7);
		generalPanel.add(label8);
		generalPanel.add(label9);
		generalPanel.add(label10);
		
		contentPane.add(generalPanel);
		
		// History Panel
		
		JPanel historyPanel = new JPanel();
		historyPanel.setBackground(Color.WHITE);
		historyPanel.setLayout(new BoxLayout(historyPanel,BoxLayout.PAGE_AXIS));
		historyPanel.setBorder(BorderFactory.createTitledBorder("History"));
		
		JButton showHistory = new JButton("Show");
		
		JPanel eventsPanel = new JPanel();
		eventsPanel.setBackground(Color.WHITE);
		eventsPanel.setLayout(new BoxLayout(eventsPanel,BoxLayout.PAGE_AXIS));
		eventsPanel.setVisible(false);
		
		JLabel eventLabel;
		for(Event event:patient.getHistory().container) {
			eventLabel = new JLabel("( "+this.patient.name+" "+this.patient.surname+" ,"+event.getName()+" ,"+event.getTimestamp()+"min )");
			eventsPanel.add(eventLabel);
		}
		
		showHistory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(showHistory.getText().equals("Show")) {
					showHistory.setText("Hide");
					eventsPanel.setVisible(true);
					frame.pack();
				}
				else {
					showHistory.setText("Show");
					eventsPanel.setVisible(false);
					frame.pack();
				}
			}});
		
		historyPanel.add(showHistory);
		historyPanel.add(eventsPanel);
		
		// Message-Box Panel
		
		JPanel mBoxPanel = new JPanel();
		mBoxPanel.setBackground(Color.WHITE);
		mBoxPanel.setLayout(new BoxLayout(mBoxPanel,BoxLayout.PAGE_AXIS));
		mBoxPanel.setBorder(BorderFactory.createTitledBorder("Message-Box"));
		
		JButton showMessageBox = new JButton("Show");

		JPanel boxPanel = new JPanel();
		boxPanel.setBackground(Color.WHITE);
		boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.PAGE_AXIS));
		boxPanel.setBorder(BorderFactory.createTitledBorder("hello "+patient.getName()+" "+patient.getSurname()+" here is the messages that you have received: "));
		boxPanel.setVisible(false);
		
		JLabel label_0 = new JLabel();
		JLabel label_1;
		JLabel label_2;
		JLabel label_3;
		JLabel label_4 = new JLabel();
		for(Message m: patient.messageBox.box.container) {
			label_1 = new JLabel("sent by: "+m.sender.getName()+" "+m.sender.getSurname());
			label_2 = new JLabel("received at: "+m.timestamp+"min ");
			label_3 = new JLabel(m.content);
			boxPanel.add(label_0);
			boxPanel.add(label_1);
			boxPanel.add(label_2);
			boxPanel.add(label_3);
			boxPanel.add(label_4);
		}
		
		showMessageBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(showMessageBox.getText().equals("Show")) {
					showMessageBox.setText("Hide");
					boxPanel.setVisible(true);
					frame.pack();
				}
				else {
					showMessageBox.setText("Show");
					boxPanel.setVisible(false);
					frame.pack();
				}
			}});
		
		mBoxPanel.add(showMessageBox);
		mBoxPanel.add(boxPanel);
		
		contentPane.add(generalPanel);
		contentPane.add(historyPanel);
		contentPane.add(mBoxPanel);
		
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
