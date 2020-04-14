package auxiliary;

import java.io.Serializable;

import human.Human;

/**
 * 
 * This class reprensents the a message_box, i.e a container owned by a human containing messages.
 *
 */

public class MessageBox implements Serializable {
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1142611878508179357L;
	public Human owner;
	public Queue<Message> box = new Queue<Message>();
	
	// Complete Constructor
	
	public MessageBox(Human owner, Queue<Message> box) {
		this.owner=owner;
		this.box= box;
	}
	
	//Constructor of an empty message-box
	
	public MessageBox(Human owner) {
		this.owner=owner;
		this.box= new Queue<Message>();
	}

	//Showing the content of a message-box
	
	public void show() {
		System.out.println("hello "+owner.getName()+" "+owner.getSurname()+" here is the messages that you have received: \n");
		for(Message m: this.box.container) {
			m.show();
			System.out.println("\n\n");
		}
	}
	
	//Adding a new message to the message-box
	
	public void addNewMessage(Message newMessage) {
		this.box.append(newMessage);
	}
}
