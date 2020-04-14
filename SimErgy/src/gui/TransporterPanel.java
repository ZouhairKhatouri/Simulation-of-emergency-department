package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import human.Transporter;

/**
 * 
 * This class extends HumanPanel by overriding the showDetails() method.
 * It modelises the GUI view of a transporter by launching a new frame containing details about the transporter state.
 *
 */

public class TransporterPanel extends HumanPanel{
	
	/**
	 * @param transporter : the transporter viewed through this class.
	 */
	
	private Transporter transporter;

	public TransporterPanel(Transporter transporter) {
		super(transporter);
		this.transporter=transporter;
	}

	@Override
	public void showDetails() {
		
		JFrame frame = new JFrame(transporter.name+" "+transporter.surname+" informations details");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		
		// General Panel
		
		JPanel generalPanel = new JPanel();
		generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.PAGE_AXIS));
		generalPanel.setBackground(Color.WHITE);
		generalPanel.setBorder(BorderFactory.createTitledBorder("General informations"));

		JLabel label1 = new JLabel("Name: "+ transporter.name);
		JLabel label2 = new JLabel("Surname: "+transporter.surname);
		JLabel label3 = new JLabel("ID number: "+transporter.getID());
		JLabel label4 = new JLabel("State: "+transporter.getState());
		
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
