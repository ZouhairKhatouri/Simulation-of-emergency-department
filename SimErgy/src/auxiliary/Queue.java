package auxiliary;

import java.io.Serializable;
import java.util.*;

/**
 * 
 *This generic represents a queue of elements of a given type T .
 *However, this class has a method that doesn't correspond to the theorical specification of a queue wich first().
 * @param <T>
 */

public class Queue<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6207015875432759686L;
	/**
	 * @param container : is the support container of this queue.
	 */
	
	// Attributes
	
	public ArrayList<T> container;
	
	// Constructor of an empty queue.
	
	public Queue() {
		this.container= new ArrayList<T>();
	}
	
	// Complete constructor
	
	public Queue(ArrayList<T> container) {
		this.container=container;
	}

	// Methods
	
	/**
	 * This method appends an element at the end of the queue.
	 * @param t the element to add.
	 */
	
	public void append(T t) {
		this.container.add(t);
	}
	
	/**
	 * This method returns the first element to get in the queue while deleting it.
	 * @return the first element to get in the queue.
	 */
	
	public T deq() {
		T t = this.container.get(0);
		this.container.remove(0);
		return t;
	}
	
	/**
	 * This method returns the first element to get in the queue, i.e the last element of the container.
	 * @return the first element to get in the queue.
	 */
	
	public T last() {
		return container.get(0);
	}
	
	/**
	 * This method return the last element to get in the queue, i.e the first element of the container.
	 * @return the last element to get in the queue.
	 */
	
	public T first() {
		return container.get(container.size()-1);
	}
	
	/**
	 * This method overrides the java.lang.Object.equals .
	 */
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Queue<?>) {
			 if(this.container.size()==((Queue<?>)obj).container.size()) {
			    	int i=0;
			    	while(i<this.container.size()) {
			    		if(this.container.get(i)!=((Queue<?>)obj).container.get(i)) {
			    			return false;
			    		}
			    		i+=1;
			    	}
			    	return true;
			    }
			 else {
				 return false;
			 }
		}
	    else {
	    	return false;
	    }
	}
}
