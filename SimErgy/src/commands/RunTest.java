package commands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import clui.CommandLine;
import clui.SimErgyCLUI;
import throwables.SyntaxErrorException;

public class RunTest extends Command {
	
	private String path;

	public RunTest(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
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
		try{
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line;
			while ((line=br.readLine())!=null){
				System.out.println(line);
				CommandLine cmd = new CommandLine(getClui(),line);
				try {
					cmd.execute();
				}
				catch(Throwable t) {
					t.printStackTrace();
				}
			}
			br.close(); 
			ips.close();
			ipsr.close();
			}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

}
