package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.Message;
import human.Patient;
import human.Physician;

public class MessageTest {

	@Test
	public void test() throws Exception {
		
		Physician receiver = new Physician("Phillipe","Poumailloux",13);
		receiver.setState("occuped");
		Patient sender = new Patient("Paul","Sarter",12,"Gold","L3",0);
		Message message = new Message("Hello i'm a new patient in the hospital",sender,receiver,10.1);
		message.show();
		
		receiver.handleMessage(message);
		Message mess = receiver.messageBox.box.deq();
		mess.show();
		
		assertTrue(mess.content.equals("(Unread)Hello i'm a new patient in the hospital"));
		
	}
}
