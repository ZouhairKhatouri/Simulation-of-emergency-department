package core;

import java.util.ArrayList;
import auxiliary.Unif;
import auxiliary.ProbabilityDensity;
import auxiliary.Queue;
import event.Event;
import event.Outcome;
import human.Patient;
import patientStrategy.StrategyFactory;
import throwables.InfiniteLoopException;

/**
 * 
 * This Class is used to simulate the evolution of an emergency departement.
 * 
 *@author Zouhair KHATOURI
 *
 */

public class SimErgy {
	
	/**
	 * @param ed : the emergency departement that this class will simulate the evolution.
	 * @param history: a queue of events storing the events that has occured during the evolution.
	 * @param probL1: the probability distribution of the time-arrival of a patient with severity-level L1.
	 * @param probL2: the probability distribution of the time-arrival of a patient with severity-level L2.
	 * @param probL3: the probability distribution of the time-arrival of a patient with severity-level L3.
	 * @param probL4: the probability distribution of the time-arrival of a patient with severity-level L4.
	 * @param probL5: the probability distribution of the time-arrival of a patient with severity-level L5.
	 */

	//Attributes
	
	public ED ed;
	public Queue<Event> history = new Queue<Event>();
	private ProbabilityDensity probL1 = new Unif(0,(60*24)/3); // we suppose that 3 patients per day arrive at an emergency departement with severity level : "resuscitation"
	private ProbabilityDensity probL2 = new Unif(0,(60*24)/10); // we suppose that 10 patients per day arrive at an emergency departement with severity level : "emergency"
	private ProbabilityDensity probL3 = new Unif(0,(60*24)/15); // we suppose that 15 patients per day arrive at an emergency departement with severity level : "urgent"
	private ProbabilityDensity probL4 = new Unif(0,(60*24)/20); // we suppose that 20 patients per day arrive at an emergency departement with severity level : "less urgent"
	private ProbabilityDensity probL5 = new Unif(0,(60*24)/15); // we suppose that 15 patients per day arrive at an emergency departement with severity level : "non-urgent"
	public double simulatedtime = 1;
	public boolean isManual = false;
	
	// Constructor1 (full constructor)
	
	public SimErgy(ED ed, Queue<Event> history) {
		this.ed = ed;
		this.history = history;
	}
	
	// Constructor2 (constructor using the default arrival's timestamp probability distribution that i supposed above)
	public SimErgy(ED ed) {
		this.ed=ed;
	}
	
	public SimErgy(ED ed, double simulatedtime) {
		this.ed=ed;
		this.simulatedtime=simulatedtime;
	}
	//Getters and Setters for time-probabilities
	
	public ProbabilityDensity getProbL1() {
		return probL1;
	}


	public void setProbL1(ProbabilityDensity probL1) {
		this.probL1 = probL1;
	}


	public ProbabilityDensity getProbL2() {
		return probL2;
	}


	public void setProbL2(ProbabilityDensity probL2) {
		this.probL2 = probL2;
	}


	public ProbabilityDensity getProbL3() {
		return probL3;
	}


	public void setProbL3(ProbabilityDensity probL3) {
		this.probL3 = probL3;
	}


	public ProbabilityDensity getProbL4() {
		return probL4;
	}


	public void setProbL4(ProbabilityDensity probL4) {
		this.probL4 = probL4;
	}


	public ProbabilityDensity getProbL5() {
		return probL5;
	}


	public void setProbL5(ProbabilityDensity probL5) {
		this.probL5 = probL5;
	}
	
	// Basic fonctionnalities of SimErgy
	
	/**
	 * This method does not invoke an ArrivalGenerator, hence it is a simulation mode where the user has to add manually patients to the emergency departement.
	 * @param T : the horizon time of the simulation.
	 * 
	 */
	
	public void manualSimulation(double T) {
		
		/*
		 * I assume that an simulation cycle has two big component: 
		 * 
		 * 			- ending of on going event. (Comp1)
		 * 			- performing strategy for every patient in the emergency departement. (Comp2)
		 * 
		 * By the way, i chosed 1min as the lenght of an simulation cycle, wich pure random choice.
		 * 
		 */
		
		double T0 = this.ed.clock;
		
		while(this.ed.clock-T0<T) {
			
			// (Comp1)
			
			for(Event event:this.history.container) { 
				
				if(event.isOngoin() && event.getTimestamp()+event.getDuration()<this.ed.clock) {
					
					event.end(); // We end every on going event in the history of this simulation witch his delay has expired. 
					
				}
				
			}
			
			// (Comp2)
			
			if(this.ed.patients.isEmpty()==false) {
				
				ArrayList<StrategyFactory> factories = new ArrayList<StrategyFactory>(); //This is a container from strategy-factory of every patients present in the emergency departement. (Factory pattern)
				
				for(Patient patient:this.ed.patients) {
					
					StrategyFactory factory = new StrategyFactory(this,patient); // Creating the Strategy's factory of "patient".
					
					factories.add(factory); // adding it to the container.
					
				}
				
				for(StrategyFactory factory:factories) {
					
					factory.createStrategy().perform(); // Creating the strategy and performing it of every factory in the container (Strategy pattern)
					
				}
				
			}
			
			// ending this simulation cycle.
			
			this.ed.clock+=simulatedtime ;
		}
	}
	
	/**
	 * This method invokes an ArrivalGenerator, hence this simulation mode does not require necesseraly the intervention of the user by adding patients to the emergency departement.
	 * @param T : the horizon time of the simulation.
	 * @throws InterruptedException 
	 * 
	 */
	
	public void drivenSimulation(double T) {
		
		
		/*
		 * I assume that an simulation cycle has two big component: 
		 * 
		 * 			- ending of on going event. (Comp1)
		 * 			- performing strategy for every patient in the emergency departement. (Comp2)
		 * 
		 * By the way, i chosed 1min as the lenght of an simulation cycle, wich pure random choice.
		 * 
		 */
		
		// This generator will be used during all cycles to generate the arrival of new patients will several severity-levels.
		
		ArrivalGenerator gen = new ArrivalGenerator(this);
		
		double T0 = this.ed.clock;
		
		while(this.ed.clock-T0<T) {
			
			// (Comp1)
			
			for(Event event:this.history.container) {
				
				if(event.isOngoin() && event.getTimestamp()+event.getDuration()<this.ed.clock) {
					
					event.end(); // We end every on going event in the history of this simulation witch his delay has expired. 
					
				}
				
			}
			
			// (Comp2)
			
			if(this.ed.patients.isEmpty()==false) {
				
				ArrayList<StrategyFactory> factories = new ArrayList<StrategyFactory>(); //This is a container from strategy-factory of every patients present in the emergency departement. (Factory pattern)
				
				for(Patient patient:this.ed.patients) {
					
					StrategyFactory factory = new StrategyFactory(this,patient); // Creating the Strategy's factory of "patient".
					
					factories.add(factory); // adding it to the container.
					
				}
				
				for(StrategyFactory factory:factories) {
					
					factory.createStrategy().perform(); // Creating the strategy and performing it of every factory in the container (Strategy pattern)
					
				}
				
			}
			
			// here we try to generate the arrival of new patients to the emergency departement.
			
			gen.tryAddingNewPatients();
			
			// ending this simulation cycle.
			
			this.ed.clock+= simulatedtime;
		}
	}
	
	/**
	 * This method diplays the history of the simulation.
	 */
	
	public void showhistory() {
		
		for(Event event:this.history.container) {
			
			System.out.println(event.show());
			
		}
		
		return;
	}
	
	/**
	 * @return the current door-to-doctor-time average within the simulation.
	 */
	
	public double currentDTDTAverage() {
		
		if(this.ed.patients.isEmpty()) { // No patient found in the emergency departement.
			
			return -1;
			
		}
		
		else {
			
			int n = 0; // this integer stores the number of patients in the ed with available DTDT.
			double S = 0; // this double stores the sum of available DTDT.
			
			for(Patient patient:this.ed.patients) { // We try to get, if it is available, the DTDT of every patient present in the emergency departement.
				if(patient.DTDTisavailable) {
					n+=1;
					S+=patient.getDTDT();
				}
			}
			
			if(n==0) { // In this case no patient has quite the emergency departement. By the way, this allow us to avoid the division by 0 exception.

				return -1;
				
			}
			
			else {
				return S/n; //returning the DTDT average.
			} 
			
		}
		
	}
	
	/**
	 * This method generates and executes the next event of the workflow.
	 * @throws InfiniteLoopException 
	 */
	
	public void nextEvents() throws InfiniteLoopException {
		
		if(isManual) {
			
			int N = history.container.size();
			
			// keep incrementing the clock of the ed while no event was generated
			
			while(N == history.container.size()) {
								
				// (Comp1)
				
				for(Event event:this.history.container) { 
					
					if(event.isOngoin()) {
						
							if(event.getTimestamp()+event.getDuration()<this.ed.clock) {
						
								event.end(); // We end every on going event in the history of this simulation witch his delay has expired. 
						
							}
							
					}
					
				}
				
				// (Throwing infinitloopexception)
				
				int i = 0; // This counter counts the number outcomes. If at the end i == history.container.size the we should break the while loop because it will be infinite.
				
				for(Event event:this.history.container) { 
					
					if(event instanceof Outcome) {
						i++;
					}
					
				}
				
				if(i==ed.patients.size()) {
					
					throw new InfiniteLoopException();
					
				}
				
				// (Comp2)
				
				if(this.ed.patients.isEmpty()==false) {
					
					ArrayList<StrategyFactory> factories = new ArrayList<StrategyFactory>(); //This is a container from strategy-factory of every patients present in the emergency departement. (Factory pattern)
					
					for(Patient patient:this.ed.patients) {
						
						StrategyFactory factory = new StrategyFactory(this,patient); // Creating the Strategy's factory of "patient".
						
						factories.add(factory); // adding it to the container.
						
					}
					
					for(StrategyFactory factory:factories) {
						
						factory.createStrategy().perform(); // Creating the strategy and performing it of every factory in the container (Strategy pattern)
						
					}
					
				}
				
				// ending this simulation cycle.
				
				this.ed.clock+=simulatedtime ;
				
			}
			
		}
		else {
			
			int N = history.container.size();
			
			ArrivalGenerator gen = new ArrivalGenerator(this);
			
			// keep incrementing the clock of the ed while no event was generated
			
			while(N == history.container.size()) {
				
				// (Comp1)
				
				for(Event event:this.history.container) {
					
					if(event.isOngoin() && event.getTimestamp()+event.getDuration()<this.ed.clock) {
						
						event.end(); // We end every on going event in the history of this simulation witch his delay has expired. 
						
					}
					
				}
				
				// (Comp2)
				
				if(this.ed.patients.isEmpty()==false) {
					
					ArrayList<StrategyFactory> factories = new ArrayList<StrategyFactory>(); //This is a container from strategy-factory of every patients present in the emergency departement. (Factory pattern)
					
					for(Patient patient:this.ed.patients) {
						
						StrategyFactory factory = new StrategyFactory(this,patient); // Creating the Strategy's factory of "patient".
						
						factories.add(factory); // adding it to the container.
						
					}
					
					for(StrategyFactory factory:factories) {
						
						factory.createStrategy().perform(); // Creating the strategy and performing it of every factory in the container (Strategy pattern)
						
					}
					
				}
				
				// here we try to generate the arrival of new patients to the emergency departement.
				
				gen.tryAddingNewPatients();
				
				// ending this simulation cycle.
				
				this.ed.clock+= simulatedtime;
				
			}
			
		}
	}
	
	/**
	 * @return the current lenght-of-stay average within the simulation.
	 */
	
	public double currentLOSAverage() {
		
		if(this.ed.patients.isEmpty()) { // No patient found in the emergency departement.
			
			return -1;
			
		}
		
		else {
			
			int n = 0; // this integer stores the number of patients in the ed with available LOS.
			double S = 0; // this double stores the sum of available LOS.
			
			for(Patient patient:this.ed.patients) { // We try to get, if it is available, the DTDT of every patient present in the emergency departement.
				if(patient.LOSisavailable) {
					n+=1;
					S+=patient.getLOS();
				}
			}
			
			if(n==0) { // In this case no patient has quite the emergency departement. By the way, this allow us to avoid the division by 0 exception.
				return -1;
			}
			
			else {
				return S/n;  //returning the LOS average.
			}
			
		}
		
	}
}
			
