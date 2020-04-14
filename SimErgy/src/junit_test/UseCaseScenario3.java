package junit_test;

import core.ED;
import core.SimErgy;
import view.EDView;
import view.View;


public class UseCaseScenario3 {

	public static void main(String[] args) {
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);
		
		SimErgy sim = new SimErgy(ed);
		
		sim.drivenSimulation(600);
		
		View view = new EDView(ed);
		view.display();
		
		sim.showhistory();
		
		
		System.out.println("\nDTDT average: "+sim.currentDTDTAverage()+"min");
		System.out.println("LOS average: "+sim.currentLOSAverage()+"min");
		
	}
}
