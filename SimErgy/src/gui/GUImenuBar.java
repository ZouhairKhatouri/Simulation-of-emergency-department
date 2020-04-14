package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * This class generates a JMenuBar that is used for the inialisation of the JMenuBar of the GUI frame.
 *
 */

public class GUImenuBar extends Observable {
	
	/**
	 * @param menuBar : the menuBar used in the GUI frame.
	 */
	
	private JMenuBar menuBar ;
	
	public GUImenuBar() {
		
	// Definition of the menu bar
		
		this.menuBar = new JMenuBar();
		
		JMenu menu1 = new JMenu("Settings");
		
		JMenuItem item1 = new JMenuItem("Create a new Emergency department");
		
		JMenuItem item2 = new JMenuItem("Clear Session");
		
		JMenuItem item3 = new JMenuItem("Launch initial configuration");
		
		JMenuItem item4 = new JMenuItem("Show departments");
		
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				notifyObservers("create ED");
			}
			
		});
		
		item4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				notifyObservers("Show EDs");
				
			}
			
		});
		
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				notifyObservers("Clear Session");
				
			}
			
		});
		
		item3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				notifyObservers("Launch");
				
			}
			
		});
		
		menu1.add(item1);
		menu1.add(item4);
		menu1.add(item2);
		menu1.add(item3);
		
		menuBar.add(menu1);
	}
	
	// Getter and Setter
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}


}
