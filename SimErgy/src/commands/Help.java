package commands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import clui.SimErgyCLUI;
import throwables.ArgumentsMismatchException;
import throwables.SyntaxErrorException;

/**
 * This command print out a page of helping directives.
 *
 */

public class Help extends Command{

	public Help(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException, SyntaxErrorException {
		super(clui,args);
		
		// Proceeding data
		
		if(args.length!=1) {
			throw new ArgumentsMismatchException(args);
		}
		else if(args[0].isEmpty()==false) {
			throw new SyntaxErrorException(args);
		}
	}

	@Override
	public void execute() {
		try{
			InputStream ips=new FileInputStream("help.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line;
			while ((line=br.readLine())!=null){
				System.out.println(line);
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
