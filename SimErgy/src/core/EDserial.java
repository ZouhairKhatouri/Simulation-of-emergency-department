package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * This class is used to serialize the ED described in the initiale configuration.
 *
 */

public class EDserial {

	public static void main(String[] args) {
		
		ED initED = new ED("Hospital Saint Louis", 4, 4, 4, 1, 1, 1, 5, 5, 5, 10);
		
		try
		{
		FileOutputStream fileOut =new FileOutputStream("my_simergy.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(initED);
		out.close();
		fileOut.close();
		}
		catch(IOException i)
		{
		i.printStackTrace();
		}
		
		try {
			
			FileInputStream fileIn = new FileInputStream("my_simergy.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			initED = (ED) in.readObject();
			in.close();
			fileIn.close();
			
		}
			
		catch(IOException i) {
				i.printStackTrace();
				return;
		}
			
		catch(ClassNotFoundException c) {
				c.printStackTrace();
				return;
		}
			
		System.out.println(initED.physicians.get(0).state);
		}
}
