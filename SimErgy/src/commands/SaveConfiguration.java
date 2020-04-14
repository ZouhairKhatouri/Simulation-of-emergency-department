package commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import clui.SimErgyCLUI;
import throwables.SyntaxErrorException;

/**
 * This command saves the current state of the system on text-file chosen by the user.
 *
 */

public class SaveConfiguration extends Command{

	private String path;

	public SaveConfiguration(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
		super(clui, args);
		
		// Proceeding data
		
		if(args.length==1) {
			this.path=args[0];
		}
		else {
			throw new SyntaxErrorException(args);
		}
	}

	@Override
	public void execute() {
		try {
			FileWriter fw = new FileWriter (path);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fileOUT = new PrintWriter (bw);  
			getClui().getCommandsHistory().remove(getClui().getCommandsHistory().size()-1);
			for(String line:getClui().getCommandsHistory()) {
				fileOUT.println (line);
			}
			fileOUT.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
