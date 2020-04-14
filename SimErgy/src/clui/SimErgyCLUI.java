package clui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import core.ED;
import core.SimErgy;

/**
 * 
 * This Thread stores the current state of the system and dialogues with the user through the console.
 *
 */
public class SimErgyCLUI extends Thread{
	
	/**
	 * @param edMap : map between emergency departments and their names.
	 * @param simMap : map between emergency departments simulations and their names.
	 * @param on : caracterisation of the current state of the thread.
	 * @param commandsHistory : The history of commands printed by the user.
	 */
	
	public HashMap<String,ED> edMap = new HashMap<String,ED>();
	public HashMap<String,SimErgy> simMap = new HashMap<String,SimErgy>();
	private boolean on = true;
	private ArrayList<String> commandsHistory = new ArrayList<String>();
	
	public SimErgyCLUI() {
	}

	/**
	 * This parameter caracterizes the state of this thread: if on is true then it is runing.
	 * @param on
	 */
	
	public void setOn(boolean on) {
		this.on = on;
	}
	
	
	@Override
	public void run() {
		
		System.out.println("----------------------------------------------------------------");
		Scanner sc = new Scanner(System.in);
		
		// The loop scan line-command/execute
		
		while(on) {
				String line = sc.nextLine();
				CommandLine cmd = new CommandLine(this,line);
				try {
					cmd.execute();
				}
				catch(Throwable t) {
					t.printStackTrace();
				}
		}
		
		sc.close();
		return;
	}
	
	// These are getters and setters allowing saving of commands on a text file

	public ArrayList<String> getCommandsHistory() {
		return commandsHistory;
	}

	public void setCommandsHistory(ArrayList<String> commandsHistory) {
		this.commandsHistory = commandsHistory;
	}
	
}
