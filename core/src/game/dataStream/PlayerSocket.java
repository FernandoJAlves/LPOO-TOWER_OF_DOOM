package game.dataStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayerSocket {
	protected Socket socket;
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	
	public PlayerSocket() {

		
		
	}
	
	public int available(){
		try {
			return this.inputStream.available();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Object readObject() {
		try {
			return this.inputStream.readObject();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendObjects(Object arg0) {
		try {
			this.outputStream.writeObject(arg0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
