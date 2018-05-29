package game.dataStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PlayerSocket {
	private int size = 1024;
	protected DatagramSocket socket;
	protected InetAddress host;
	protected int sendPort;
	protected int receivePort;
	
	public PlayerSocket() {
		try {
			host =  InetAddress.getLocalHost();
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object readObject() {
		byte[] receivedData = new byte[this.size];
		DatagramPacket incomingPacket = new DatagramPacket(receivedData, receivedData.length);
		try {
			socket.receive(incomingPacket);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			return null;
		}
		byte[] data = incomingPacket.getData();
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		try {
			return  is.readObject();
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendObjects(Object arg0) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(this.size);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		try {
			oos.writeObject(arg0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		byte[] data = baos.toByteArray();

		DatagramPacket packet = new DatagramPacket(data, data.length,host,this.sendPort);
		try {
			this.socket.send(packet);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
