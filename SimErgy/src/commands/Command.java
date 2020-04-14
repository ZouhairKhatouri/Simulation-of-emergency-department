package commands;

import clui.SimErgyCLUI;

/**
 * 
 * This class is the product part of a factory pattern.
 * Every instance of this abstract performs a transformation on the system using the string data inputed by the user.
 *
 */

public abstract class Command {
	
	private SimErgyCLUI clui;
	private String[] args;
	
	/**
	 * 
	 * @param clui : command line user interface simulation system.
	 * @param args : data needed to perform the transformation required by the user.
	 */
	
	public Command(SimErgyCLUI clui,String[] args) {
		this.clui = clui;
		this.args = args;
	}

	// Getters and Setters of the parameters

	public SimErgyCLUI getClui() {
		return clui;
	}


	public void setClui(SimErgyCLUI clui) {
		this.clui = clui;
	}

	
	public String[] getArgs() {
		return args;
	}


	public void setArgs(String[] args) {
		this.args = args;
	}

	/**
	 * This method performs the required transformation on the clui simulation system.
	 */
	
	public abstract void execute();
}
