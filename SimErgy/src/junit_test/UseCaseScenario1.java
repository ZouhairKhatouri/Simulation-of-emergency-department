package junit_test;

import core.ED;
import core.SimErgy;
import human.Patient;
import view.EDView;
import view.View;

public class UseCaseScenario1 {
	
	public static void main(String[] args) throws Exception {
		
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

		//Manual arrival of a new patient
		
		Patient Paul = new Patient("Paul","Sarter",ed.nextID(),"Gold","L3",0);
		
		SimErgy sim = new SimErgy(ed);
		
		ed.patients.add(Paul);
		sim.manualSimulation(1440);
		
		sim.showhistory();
		
		View view = new EDView(ed);
		view.display();
		
		System.out.println("\nDTDT average: "+sim.currentDTDTAverage()+"min");
		System.out.println("LOS average: "+sim.currentLOSAverage()+"min");
	}

}
