package core;

import java.io.Serializable;
import java.util.*;

import auxiliary.Det;
import auxiliary.ID;
import auxiliary.ProbabilityDensity;
import auxiliary.Queue;
import auxiliary.Unif;
import human.*;
import resource.*;
import throwables.SyntaxErrorException;

/**
 * 
 * This class represents an emergency departement with all his relevant caractestics.
 * 
*/

public class ED implements Serializable {
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 13252626L;
	
	public String name = "";
	public ArrayList<Physician> physicians;
	public ArrayList<Nurse> nurses;
	public ArrayList<Transporter> transporters;
	public ArrayList<Patient> patients;
	public Radiography radiographyDepartment;
	public BloodTest bloodTestDepartment;
	public MRI mriDepartment;
	public Arrival arrival;
	public Consultation consultationDepartment;
	public ArrayList<RadiographyRoom> radiographyRooms;
	public ArrayList<BloodTestLab> bloodTestLabs;
	public ArrayList<MRIroom> mriRooms;
	public ArrayList<ShockRoom> shockRooms;
	public ArrayList<WaitingRoom> waitingRooms;
	public ArrayList<BoxRoom> boxRooms;
	public ArrayList<Stretcher> stretchers;
	public double clock; // I imagined this as a concrete clock inside an emergency departement.
 
	//Constructor 1 (full constructor)
	
	public ED(String name, ArrayList<Patient> patients,ArrayList<Physician> physicians, ArrayList<Nurse> nurses, ArrayList<Transporter> transporters,
			Radiography radiographyDepartement, BloodTest bloodTestDepartement,
			MRI mriDepartement,Arrival arrival,Consultation consultationDepartement, ArrayList<RadiographyRoom> radiographyRooms,
			 ArrayList<BloodTestLab> bloodTestLabs,
			ArrayList<MRIroom> mriRooms, ArrayList<ShockRoom> shockRooms, ArrayList<WaitingRoom> waitingRooms,
			ArrayList<BoxRoom> boxRooms,ArrayList<Stretcher> stretchers) {
		this.name=name;
		this.physicians = physicians;
		this.nurses = nurses;
		this.transporters = transporters;
		this.radiographyDepartment = radiographyDepartement;
		this.bloodTestDepartment = bloodTestDepartement;
		this.mriDepartment = mriDepartement;
		this.arrival=arrival;
		this.consultationDepartment=consultationDepartement;
		this.radiographyRooms = radiographyRooms;
		this.bloodTestLabs = bloodTestLabs;
		this.mriRooms = mriRooms;
		this.shockRooms = shockRooms;
		this.waitingRooms = waitingRooms;
		this.boxRooms = boxRooms;
		this.stretchers=stretchers;
		this.patients= patients;
		this.clock=0;
	}
	
	//Constructor 2 (A constructor were we have to specify the number of each entity inside the emergency department and also the arrival-time distribution probability)
	
	public ED(String name,int Nphysicians,int Nnurses,int Ntransporter,ProbabilityDensity servicetimeArrival,ProbabilityDensity servicetimeConsultation,ProbabilityDensity servicetimeBloodTest,ProbabilityDensity servicetimeRadiography,
			ProbabilityDensity servicetimeMRI,int Nbloodtestlabs,int Nradiographyrooms,int Nmrirooms,int Nwaitingrooms,int Nboxrooms,int Nshockrooms,int Nstreatchers) {
		this.name=name;
		this.physicians = new ArrayList<Physician>();
		this.nurses = new ArrayList<Nurse>();
		this.transporters = new ArrayList<Transporter>();
		this.radiographyRooms = new ArrayList<RadiographyRoom>();
		this.bloodTestLabs = new ArrayList<BloodTestLab>();
		this.mriRooms = new ArrayList<MRIroom>();
		this.shockRooms = new ArrayList<ShockRoom>();
		this.waitingRooms  = new ArrayList<WaitingRoom>();
		this.boxRooms  = new ArrayList<BoxRoom>();
		this.stretchers= new ArrayList<Stretcher>();
		this.patients = new ArrayList<Patient>();
		this.clock=0;
		for(int i=0;i<Nphysicians;i++) {
			int x = this.nextID();
			try {
				this.physicians.add(new Physician("physician"+x,"",x));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<Nnurses;i++) {
			int x = this.nextID();
			try {
				this.nurses.add(new Nurse("Nurse"+x,"",x));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<Ntransporter;i++) {
			int x=this.nextID();
			try {
				this.transporters.add(new Transporter("transporter"+x,"",x));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<Nbloodtestlabs;i++) {
			int x=this.nextID();
			this.bloodTestLabs.add(new BloodTestLab(x));
			
		}
		for(int i=0;i<Nradiographyrooms;i++) {
			int x=this.nextID();
			this.radiographyRooms.add(new RadiographyRoom(x));
			
		}
		for(int i=0;i<Nmrirooms;i++) {
			int x=this.nextID();
			this.mriRooms.add(new MRIroom(x));
			
		}
		for(int i=0;i<Nwaitingrooms;i++) {
			int x=this.nextID();
			this.waitingRooms.add(new WaitingRoom(x));
			
		}
		for(int i=0;i<Nboxrooms;i++) {
			int x=this.nextID();
			this.boxRooms.add(new BoxRoom(x));
			
		}
		for(int i=0;i<Nshockrooms;i++) {
			int x=this.nextID();
			this.shockRooms.add(new ShockRoom(x));
		}
		for(int i=0;i<Nstreatchers;i++) {
			int x=this.nextID();
			this.stretchers.add(new Stretcher(x));
		}
		this.arrival = new Arrival(this.nextID(),new Queue<Patient>(),servicetimeArrival);
		this.bloodTestDepartment = new BloodTest(this.nextID(),new Queue<Patient>(),servicetimeBloodTest);
		this.radiographyDepartment = new Radiography(this.nextID(),new Queue<Patient>(),servicetimeRadiography);
		this.mriDepartment = new MRI(this.nextID(),new Queue<Patient>(), servicetimeMRI);
		this.consultationDepartment = new Consultation(this.nextID(),new Queue<Patient>(),servicetimeConsultation);
	}
	
	//Constructor3 (A constructor were we only have to specify the number of each entity inside the emergency departement and the arrival-time distribution probability are chosen with respect to project specification)
	
	public ED(String name,int Nphysicians,int Nnurses,int Ntransporter,int Nbloodtestlabs,int Nradiographyrooms,int Nmrirooms,int Nwaitingrooms,int Nboxrooms,int Nshockrooms,int Nstreatchers) {
		this.name=name;
		this.physicians = new ArrayList<Physician>();
		this.nurses = new ArrayList<Nurse>();
		this.transporters = new ArrayList<Transporter>();
		this.radiographyRooms = new ArrayList<RadiographyRoom>();
		this.bloodTestLabs = new ArrayList<BloodTestLab>();
		this.mriRooms = new ArrayList<MRIroom>();
		this.shockRooms = new ArrayList<ShockRoom>();
		this.waitingRooms  = new ArrayList<WaitingRoom>();
		this.boxRooms  = new ArrayList<BoxRoom>();
		this.stretchers= new ArrayList<Stretcher>();
		this.patients = new ArrayList<Patient>();
		this.clock=0;
		for(int i=0;i<Nphysicians;i++) {
			int x = this.nextID();
				try {
					this.physicians.add(new Physician("physician"+x,"physician"+x,x));
				} catch (SyntaxErrorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		for(int i=0;i<Nnurses;i++) {
			int x = this.nextID();
			try {
				this.nurses.add(new Nurse("Nurse"+x,"Nurse"+x,x));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<Ntransporter;i++) {
			int x=this.nextID();
			try {
				this.transporters.add(new Transporter("transporter"+x,"transporter"+x,x));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<Nbloodtestlabs;i++) {
			int x=this.nextID();
			this.bloodTestLabs.add(new BloodTestLab(x));
			
		}
		for(int i=0;i<Nradiographyrooms;i++) {
			int x=this.nextID();
			this.radiographyRooms.add(new RadiographyRoom(x));
			
		}
		for(int i=0;i<Nmrirooms;i++) {
			int x=this.nextID();
			this.mriRooms.add(new MRIroom(x));
			
		}
		for(int i=0;i<Nwaitingrooms;i++) {
			int x=this.nextID();
			this.waitingRooms.add(new WaitingRoom(x));
			
		}
		for(int i=0;i<Nboxrooms;i++) {
			int x=this.nextID();
			this.boxRooms.add(new BoxRoom(x));
			
		}
		for(int i=0;i<Nshockrooms;i++) {
			int x=this.nextID();
			this.shockRooms.add(new ShockRoom(x));
		}
		for(int i=0;i<Nstreatchers;i++) {
			int x=this.nextID();
			this.stretchers.add(new Stretcher(x));
		}
		this.arrival = new Arrival(this.nextID(),new Queue<Patient>(),new Det(2));
		this.bloodTestDepartment = new BloodTest(this.nextID(),new Queue<Patient>() ,new Unif(15,90));
		this.radiographyDepartment = new Radiography(this.nextID(),new Queue<Patient>(), new Unif(10,20));
		this.mriDepartment = new MRI(this.nextID(),new Queue<Patient>(), new Unif(30,70));
		this.consultationDepartment = new Consultation(this.nextID(),new Queue<Patient>(),new Unif(5,20));
	}
	
	// Constructor3 (Empty one)
	
	public ED(String name) {
		this.name=name;
		this.physicians = new ArrayList<Physician>();
		this.nurses = new ArrayList<Nurse>();
		this.transporters = new ArrayList<Transporter>();
		this.radiographyRooms = new ArrayList<RadiographyRoom>();
		this.bloodTestLabs = new ArrayList<BloodTestLab>();
		this.mriRooms = new ArrayList<MRIroom>();
		this.shockRooms = new ArrayList<ShockRoom>();
		this.waitingRooms  = new ArrayList<WaitingRoom>();
		this.boxRooms  = new ArrayList<BoxRoom>();
		this.stretchers= new ArrayList<Stretcher>();
		this.patients = new ArrayList<Patient>();
		this.clock=0;
	}
	
	//Centralization of the singleton pattern
	
	/**
	* Remark that this is the class where the getting of an ID number is centralized.
	*/
	
	public int nextID() { 
		ID id = ID.getinstance();
		return id.Next();
	} 
	
	// Overriding Equals
	
	@Override
	
	public boolean equals(Object obj) {
		if(obj instanceof ED) {
			return name==((ED)obj).name;
		}
		return false;
	}
}
