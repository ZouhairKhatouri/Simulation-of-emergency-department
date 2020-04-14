package clui;

/**
 * 
 * Main class on the CLUI part of the project.
 *
 */

public class CLUI {
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("\nAuthor: Zouhair KHATOURI.\nYou can start writing commands.\nFor help, please enter the following command : help <> \nFor resuming the session enter the following command : end <> ");
		System.out.println("To load an initial configuration please enter the following command : loadConfiguration <my_simergy.init.txt>\n\n");
		SimErgyCLUI clui = new SimErgyCLUI();
		clui.start();
		clui.join();
		System.out.println("\n\n****************************************************************");
		System.out.println("END OF COMMANDS");
		
	}
}
