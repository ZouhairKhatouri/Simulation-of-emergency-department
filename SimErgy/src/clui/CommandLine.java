package clui;

import commands.*;
import throwables.ArgumentsMismatchException;
import throwables.CommandNotFoundException;
import throwables.SyntaxErrorException;

/**
 * 
 * This class represents the command that is written by the user. It is also the factory of the type Command.
 *
 */

public class CommandLine {
	
	private SimErgyCLUI clui;
	private String line;

	/**
	 * @param clui command line simulation system user interface.
	 * @param line is the command-line written by the user.
	 */
	public CommandLine(SimErgyCLUI clui,String line) {
		this.clui=clui;
		this.line = line;
	}
	
	/**
	 * The method proceeds the data inputed by the user.
	 * 
	 * @return a comand that is then executed.
	 * @throws SyntaxErrorException
	 * @throws CommandNotFoundException
	 * @throws ArgumentsMismatchException
	 */
	
	public Command interpret() throws SyntaxErrorException, CommandNotFoundException, ArgumentsMismatchException {
		
			if(line.isEmpty()) {
				
				// The user has pressed enter.
			
				return new NullCommand(clui,new String[0]);
			
			}
			
			else {
				
				// Proceeding data inputed by the user.
				
				String mainQuery = "";
				
				int i = 0;
				
				while( line.charAt(i) != ' ' && i<line.length()) {
					mainQuery += line.charAt(i);
					i++;
				}
				
				while(line.charAt(i) == ' ' && i<line.length()) {
					i++;
				}
				
				String param = line.substring(i,line.length());
				
				if(param.charAt(0)!= '<'  || param.charAt(param.length()-1)!= '>') {
					
					throw new SyntaxErrorException(null);
					
				}
				
				param = line.substring(i+1,line.length()-1);
				
				String[] args = param.split(",");
				
				
				// Returning the right command.
				
				switch(mainQuery) {
				
				case "createED":
				
					return new CreateED(clui,args);
					
				case "addRoom":
					
					return new AddRoom(clui,args);
					
				case "addArrival":
					
					return new AddArrival(clui,args);
					
				case "addConsultation":
					
					return new AddConsultation(clui,args);
					
				case "addRadioService":
					
					return new AddRadioService(clui,args);
					
				case "addMRI":
					
					return new AddMRI(clui,args);
					
				case "addBloodTest":
					
					return new AddBloodTest(clui,args);
					
				case "addTransporter":
					
					return new AddTransporter(clui,args);
					
				case "addStretcher":
					
					return new AddStretcher(clui,args);
				
				case "addNurse":
				
					return new AddNurse(clui,args);
					
				case "addPhysi":
				
					return new AddPhysi(clui,args);
					
				case "setL1ArrivalDist":
					
					return new SetL1ArrivalDist(clui,args);
					
				case "setL2ArrivalDist":
					
					return new SetL2ArrivalDist(clui,args);
					
				case "setL3ArrivalDist":
					
					return new SetL3ArrivalDist(clui,args);
					
				case "setL4ArrivalDist":
					
					return new SetL4ArrivalDist(clui,args);
				
				case "setL5ArrivalDist":
					
					return new SetL5ArrivalDist(clui,args);
					
				case "setCommandType":
					
					return new SetCommandType(clui,args);
					
				case "addPatient":
					
					return new AddPatient(clui,args);
				
				case "setPatientInsurance":
					
					return new SetPatientInsurance(clui,args);
					
				case "executeEvent" :
					
					return new ExecuteEvent(clui,args);
					
				case "computeLOS":
					
					return new ComputeLOS(clui,args);
					
				case "computeDTDT":
					
					return new ComputeDTDT(clui,args);
				
				
				case "displayEDState":
					
					return new DisplayEDState(clui,args);
					
				case "displayPatientState":
					
					return new DisplayPatientState(clui,args);
				
				case "displayHealthServiceState":
					
					return new DisplayHealthServiceState(clui,args);
					
				case "displayPhysicianState":
					
					return new DisplayPhysicianState(clui,args);
				
				case "displayNurseState":
					
					return new DisplayNurseState(clui,args);
				
				case "displayTransporterState":
					
					return new DisplayTransporterState(clui,args);
			
				case "startManualSimulation":
					
					return new StartManualSimulation(clui,args);
					
				case "startDrivenSimulation":
					
					return new StartDrivenSimulation(clui,args);
					
				case "loadConfiguration":
					
					return new LoadConfiguration(clui,args);
					
				case "runtest":
					
					return new RunTest(clui,args);
					
				case "showHistory":
					
					return new ShowHistory(clui,args);
					
				case "saveConfiguration":
					
					return new SaveConfiguration(clui,args);
					
				case "saveOutputs":
					
					return new SaveOutputs(clui,args);
					
				case "help":
					
					return new Help(clui,args);
					
				case "end":
					
					return new End(clui,args);
					
				default:
					
					throw new CommandNotFoundException(line);
				}
			}
			
	}
	
	/**
	 * This command execute the query of the user by creating and executing the right Command instance.
	 * 
	 * @throws SyntaxErrorException
	 * @throws CommandNotFoundException
	 * @throws ArgumentsMismatchException
	 */
	
	public void execute() throws SyntaxErrorException, CommandNotFoundException, ArgumentsMismatchException {
		
		clui.getCommandsHistory().add(line);
		
		Command command = this.interpret();
		
		command.execute();
		
		return;
		
	}

}
