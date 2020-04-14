package gui;

import javax.swing.SwingUtilities;

/**
 * 
 * Main class on the GUI part of the project.
 *
 */

public class GUI {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GUIframe();
			}
			
		});
		
	}
	
}
