package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import auxiliary.Message;
import human.Patient;
import human.Physician;

/**
 * 
 * This class extends HumanPanel by overriding the showDetails() method.
 * It modelises the GUI view of a physician by launching a new frame containing details about the physician state.
 *
 */

public class PhysicianPanel extends HumanPanel{
	
	/**
	 * @param physician : the physician viewed through this class.
	 */
	
	private Physician physician;

	public PhysicianPanel(Physician physician) {
		super(physician);
		this.physician = physician;
	}

	@Override
	public void showDetails() {
		
		JFrame frame = new JFrame(physician.name+" "+physician.surname+" informations details");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		contentPane.setBackground(Color.WHITE);
		
		// General Panel
		
		JPanel generalPanel = new JPanel();
		generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.PAGE_AXIS));
		generalPanel.setBackground(Color.WHITE);
		generalPanel.setBorder(BorderFactory.createTitledBorder("General informations"));

		JLabel label1 = new JLabel("Name: "+ physician.name);
		JLabel label2 = new JLabel("Surname: "+physician.surname);
		JLabel label3 = new JLabel("ID number: "+physician.getID());
		JLabel label4 = new JLabel("State: "+physician.getState());
		
		generalPanel.add(label1);
		generalPanel.add(label2);
		generalPanel.add(label3);
		generalPanel.add(label4);
		
		PatientPanel patientPanel;
		
		// Patient being overseen
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(new GridLayout(0,3));
		panel1.setBorder(BorderFactory.createTitledBorder("Patients being overseen"));
		
		for(Patient patient:physician.patientsBeenOverseen) {
			patientPanel = new PatientPanel(patient);
			panel1.add(patientPanel.getPanel());
		}
		
		// Patient already treated
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setLayout(new GridLayout(0,3));
		panel2.setBorder(BorderFactory.createTitledBorder("Patients already treated"));
		
		for(Patient patient:physician.patientsTreated) {
			patientPanel = new PatientPanel(patient);
			panel2.add(patientPanel.getPanel());
		}
		
		// Message-Box Panel
		
		JPanel mBoxPanel = new JPanel();
		mBoxPanel.setBackground(Color.WHITE);
		mBoxPanel.setLayout(new BoxLayout(mBoxPanel,BoxLayout.PAGE_AXIS));
		mBoxPanel.setBorder(BorderFactory.createTitledBorder("Message-Box"));
		
		JButton showMessageBox = new JButton("Show");

		JPanel boxPanel = new JPanel();
		boxPanel.setBackground(Color.WHITE);
		boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.PAGE_AXIS));
		boxPanel.setBorder(BorderFactory.createTitledBorder("hello "+physician.getName()+" "+physician.getSurname()+" here is the messages that you have received: "));
		boxPanel.setVisible(false);
		
		JLabel label_0 = new JLabel();
		JLabel label_1;
		JLabel label_2;
		JLabel label_3;
		JLabel label_4 = new JLabel();
		for(Message m: physician.messageBox.box.container) {
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
		contentPane.add(panel1);
		contentPane.add(panel2);
		contentPane.add(mBoxPanel);
		
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setVisible(true);
		
	}
}
