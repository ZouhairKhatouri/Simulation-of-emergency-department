package gui;

import java.util.ArrayList;

public abstract class Observable {

	ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public ArrayList<Observer> getObservers() {
		return observers;
	}
	public void setObservers(ArrayList<Observer> observers) {
		this.observers = observers;
	}
	public synchronized void addObserver(Observer ob) {
		this.observers.add(ob);
	}
	public synchronized boolean removeObserver(Observer ob) {
		return observers.remove(ob);
	}
	
	public synchronized void notifyObservers(Object obj) {
		for(Observer ob : observers) {
			ob.update(obj);
		}
		return;
	}
}
