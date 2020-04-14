package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import human.Nurse;

/**
 * 
 * This class extends HumanPanel by overriding the showDetails() method.
 * It modelises the GUI view of a nurse by launching a new frame containing details about the nurse state.
 *
 */

public class NursePanel extends HumanPanel{
	
	/**
	 * @param nurse : the nurse viewed through this class.
	 */
	
	private Nurse nurse;

	public NursePanel(Nurse nurse) {
		super(nurse);
		this.nurse = nurse;
	}

	@Override
	public void showDetails() {
		
		JFrame frame = new JFrame(nurse.name+" "+nurse.surname+" informations details");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		contentPane.setBackground(Color.WHITE);
		
		// General Panel
		
		JPanel generalPanel = new JPanel();
		generalPanel.setBackground(Color.WHITE);
		generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.PAGE_AXIS));
		generalPanel.setBorder(BorderFactory.createTitledBorder("General informations"));

		JLabel label1 = new JLabel("Name: "+ nurse.name);
		JLabel label2 = new JLabel("Surname: "+nurse.surname);
		JLabel label3 = new JLabel("ID number: "+nurse.getID());
		JLabel label4 = new JLabel("State: "+nurse.getState());
		
		generalPanel.add(label1);
		generalPanel.add(label2);
		generalPanel.add(label3);
		generalPanel.add(label4);
		
		contentPane.add(generalPanel);
		
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setVisible(true);
		
	}
}
