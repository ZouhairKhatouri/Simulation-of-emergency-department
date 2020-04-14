package view;

import human.Human;

/**
 * 
 * This Class is the view of the type Human.
 *
 */

public class HumanView implements View{

	private Human human;
	
	
	
	public HumanView(Human human) {
		super();
		this.human = human;
	}



	@Override
	public void display() {
		System.out.println("------------------------------------------------------");
		System.out.println("Name: "+human.getName());
		System.out.println("Surname: "+human.getSurname());
		System.out.println("ID number: "+human.getID());
		System.out.println("State: "+human.getState());
		System.out.println("------------------------------------------------------");
	}

}
