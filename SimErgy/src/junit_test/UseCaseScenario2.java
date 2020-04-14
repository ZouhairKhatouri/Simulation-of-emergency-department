package junit_test;

import core.ED;
import core.SimErgy;
import human.Patient;
import human.Physician;
import view.PhysicianView;

public class UseCaseScenario2 {
	
	public static void main(String[] args) throws Exception {
		
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

		Patient Paul0 = new Patient("Paul0","Sarter",0,"Gold","L3",0);
		Patient Paul1 = new Patient("Paul1","Sarter",10,"","L2",0);
		Patient Paul2 = new Patient("Paul2","Sarter",1,"Gold","L1",0);
		Patient Paul3 = new Patient("Paul3","Sarter",58,"","L5",0);
		Patient Paul4 = new Patient("Paul4","Sarter",0100,"Gold","L4",0);
		Patient Paul5 = new Patient("Paul5","Sarter",450,"Gold","L2",0);
		Patient Paul6 = new Patient("Paul6","Sarter",780,"","L3",0);
		Patient Paul7 = new Patient("Paul7","Sarter",100,"Silver","L4",0);
		Patient Paul8 = new Patient("Paul8","Sarter",30,"Gold","L1",0);
		Patient Paul9 = new Patient("Paul9","Sarter",80,"Silver","L2",0);
		
		ed.patients.add(Paul0);
		ed.patients.add(Paul1);
		ed.patients.add(Paul2);
		ed.patients.add(Paul3);
		ed.patients.add(Paul4);
		ed.patients.add(Paul5);
		ed.patients.add(Paul6);
		ed.patients.add(Paul7);
		ed.patients.add(Paul8);
		ed.patients.add(Paul9);
		
		SimErgy sim = new SimErgy(ed);
		
		//Starting a simulation over a day
		
		sim.manualSimulation(1440);
		
		sim.showhistory();
		
		Physician physician = ed.physicians.get(0);
		PhysicianView view = new PhysicianView(physician);
		view.display();
		
		Physician physician2 = ed.physicians.get(1);
		PhysicianView view2 = new PhysicianView(physician2);
		view2.display();
		
		System.out.println("\nDTDT average: "+sim.currentDTDTAverage()+"min");
		System.out.println("LOS average: "+sim.currentLOSAverage()+"min");
		
	}
}
