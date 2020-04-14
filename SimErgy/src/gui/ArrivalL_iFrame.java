package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import auxiliary.ProbabilityDensity;
import throwables.InvalidDistributionException;


/**
 * 
 * This class is used to launch a new frame where the user cas set the arrival probability distributions patients of differents severities.
 *
 */

public class ArrivalL_iFrame extends Observable{
	
	/**
	 * @param edName : the name of the emergency department for whom the setting operation is done.
	 * @param severity : the type of severity where the change may occure.
	 * @param frame : the frame launched.
	 * @param Varg1 : the first parameter of the new distribution.
	 * @param Varg2 : the second parameter of the new distribution (if it exist).
	 * 
	 */
	
	public String edName;
	public String severity;
	public JFrame frame;
	protected double Varg2;
	protected double Varg1;
	protected String DistType ="Unif" ;
	
	public ArrivalL_iFrame(String edName, String severity) {
		
		this.edName = edName;
		
		this.severity = severity;
		
		this.frame = new JFrame("Arrival-time probability of patients with severity "+severity+" settings");
		
		JPanel Panel = new JPanel();
		
		JLabel Label = new JLabel("Arrival-time probability of patients with severity "+severity+": ");
	
		JRadioButton Unif = new JRadioButton("Unif",true);				
	
		JRadioButton Exp = new JRadioButton("Exp");
		
		JRadioButton Det = new JRadioButton("Det");
		
		ButtonGroup group = new ButtonGroup();
	
		group.add(Unif);
	
		group.add(Exp);
	
		group.add(Det);
		
		JTextField arg1 = new JTextField("",3);
		
		JTextField arg2 = new JTextField("",3);
		
		Unif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				arg2.setEnabled(true);
				DistType = "Unif";
			}});
			
		Exp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				arg2.setEnabled(false);
				DistType = "Exp";
			}});
		
		Det.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				arg2.setEnabled(false);
				DistType = "Det";
			}});
		
		arg1.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				try {
					if(!arg1.getText().equals("")) {
						Varg1 = Double.parseDouble(arg1.getText());
					}
					return;
				}
				catch (java.lang.NumberFormatException f) {
					notifyObservers("syntax error (double)");
				}
			}});
		
		arg2.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				try {
					if(!arg2.getText().equals("")) {
						Varg2 = Double.parseDouble(arg2.getText());
					}
					return;
				}
				catch (java.lang.NumberFormatException f) {
					notifyObservers("syntax error (double)");
				}
			}});
		
		// Adding Component
		
		Panel.add(Label);
		Panel.add(Unif);
		Panel.add(Exp);
		Panel.add(Det);
		Panel.add(arg1);
		Panel.add(arg2);
		
		JButton Button = new JButton("Set");
		
		Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				set();
				
			}
			
		});
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		panel.add(Panel);
		
		panel.add(Button);
		
		frame.setContentPane(panel);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.pack();
		
		frame.setVisible(true);
		
	}

	public ProbabilityDensity getNewDist() {
		
				try {
					
					return (new MainPanel.DistributionFactory()).makeDistribution(DistType,new double[] {Varg1,Varg2});
					
				}
				
				catch(InvalidDistributionException e) {
					
					notifyObservers("Starting has failed (D)");
					
				}
				catch(Exception e) {
					
					notifyObservers("Starting has failed (D)");
					
				}
				
				return null;
				
	}
	
	protected void set() {
		
		ProbabilityDensity dist = getNewDist();
		
		Object[] data = {edName,severity,dist};
		
		notifyObservers(data);
		
		frame.dispose();
		
		return;
		
	}
	
}
