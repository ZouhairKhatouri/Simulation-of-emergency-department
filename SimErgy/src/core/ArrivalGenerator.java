package core;

import auxiliary.L1;
import auxiliary.L2;
import auxiliary.L3;
import auxiliary.L4;
import auxiliary.L5;
import auxiliary.SpecificArrivalGenerator;

/**
 * 
 * This class generates the arrival of several patients at different timestamp and on different severity-levels.
 *
 */

public class ArrivalGenerator {
	
	//Attributes
	
	/**
	 * @param sim : simulation that uses this generator.
	 * @param genL1 : specific generator for patients of severity level L1.
	 * @param genL2 : specific generator for patients of severity level L2.
	 * @param genL3 : specific generator for patients of severity level L3.
	 * @param genL4 : specific generator for patients of severity level L4.
	 * @param genL5 : specific generator for patients of severity level L5.
	 */
	
	public SimErgy sim;
	private SpecificArrivalGenerator genL1 ;
	private SpecificArrivalGenerator genL2 ;
	private SpecificArrivalGenerator genL3 ;
	private SpecificArrivalGenerator genL4 ;
	private SpecificArrivalGenerator genL5 ;
	
	//Constructor
	
	/**
	 * Remark that the constructor of an ArrivalGenerator needs only a simulation (@param sim) because other parameters are deduced from the simulation.
	 * @param sim is the simulation that uses this patients generator.
	 */
	
	public ArrivalGenerator(SimErgy sim) {
		this.sim=sim;
		this.genL1 = new SpecificArrivalGenerator(sim.ed,new L1(sim.getProbL1()));
		this.genL2 = new SpecificArrivalGenerator(sim.ed,new L2(sim.getProbL2()));
		this.genL3 = new SpecificArrivalGenerator(sim.ed,new L3(sim.getProbL3()));
		this.genL4 = new SpecificArrivalGenerator(sim.ed,new L4(sim.getProbL4()));
		this.genL5 = new SpecificArrivalGenerator(sim.ed,new L5(sim.getProbL5()));
	}
	
	//Methods
	
	/**
	 * This method tries to add patients on the five types of severity-levels.
	 */
	
	public void tryAddingNewPatients() {
		try {
			genL1.tryAddingNewPatient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			genL2.tryAddingNewPatient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			genL3.tryAddingNewPatient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			genL4.tryAddingNewPatient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			genL5.tryAddingNewPatient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
