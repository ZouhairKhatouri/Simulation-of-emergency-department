package auxiliary;

import java.io.Serializable;

import human.*;

public class Message implements Serializable {
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6997357243177206450L;
	public String content;
	public Human sender;
	public Human receiver;
	public double timestamp;
	
	//Constructor
	
	public Message(String message,Human sender, Human receiver,double timestamp ) {
		this.content=message;
		this.sender=sender;
		this.receiver=receiver;
		this.timestamp=timestamp;
	}

	// this method shows the message
	
	public void show() {
		System.out.println("sent by: "+this.sender.getName()+" "+this.sender.getSurname());
		System.out.println("received at: "+timestamp+"min \n \n");
		System.out.println(this.content);
	}
	
	
	// Basic fonctionnalities that allow handling messages in a message-box
	
	public void store(MessageBox messageBox) {
		messageBox.addNewMessage(this);
		return;
	}
	
	public void remove() {};
	
	public void markUnread(MessageBox messageBox) {
		this.content="(Unread)"+this.content;
		messageBox.addNewMessage(this);
		return;
	}
}
