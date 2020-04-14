package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import human.Human;

/**
 * 
 * This abstract class modelises the GUI view of a human.
 *
 */

public abstract class HumanPanel {
	
	/**
	 * @param human : the human viewed through this class.
	 * @param panel : the panel containing the relevent informations about this human.
	 */
	
	protected JPanel panel;
	protected Human human;

	
	public HumanPanel(Human human) {
		
		this.human = human;
		this.panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		JLabel label1 = new JLabel("------------------------------------------------------");
		JLabel label2 = new JLabel("\nName: "+human.getName());
		JLabel label3 = new JLabel("\nSurname: "+human.getSurname());
		JLabel label4 = new JLabel("\nID number: "+human.getID());
		JLabel label5 = new JLabel("\n------------------------------------------------------");
		JButton button = new JButton("See details");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showDetails();
			}
			
		});
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(button);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	protected abstract void showDetails();

}
