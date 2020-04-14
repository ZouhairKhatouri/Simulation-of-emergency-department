package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import resource.Room;

/**
 * 
 * This class generates a panel that is added to the gui and containing all relevent informations about a given room.
 *
 */

public class RoomPanel implements Observer{
	
	/**
	 * @param room : the room viewed through this class.
	 * @param panel : the panel containing the relevent informations about the room.
	 * @param subpanel : a subpanel which his color indicates if the room is occuped or not.
	 * 
	 */
	
	public JPanel panel;
	private JPanel subpanel;
	private Room room;
	
	
	public RoomPanel(Room room) {
		
		this.room = room;
		this.room.addObserver(this);
		this.subpanel = new JPanel();
		
		subpanel.setPreferredSize(new Dimension(20, 20));
		this.panel = new JPanel();
		panel.setPreferredSize(new Dimension(30, 30));
		
		if(room.isAvailable()) {
			subpanel.setBackground(Color.green);
		}
		else {
			panel.setBackground(Color.red);
		}
		
		panel.add(subpanel);
		
	}
	
	
	public Room getRoom() {
		return room;
	}

	@Override
	public void update(Object obj) {
		
		if((boolean)obj) {
			
			subpanel.setBackground(Color.green);
			
			subpanel.updateUI();
			
		}
		
		else {
			
			subpanel.setBackground(Color.red);
			
			subpanel.setVisible(true);
			
			subpanel.updateUI();
			
		}
	}

}
