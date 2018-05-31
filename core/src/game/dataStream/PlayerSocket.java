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
			host = InetAddress.getByName("localhost");
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
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
			oos.writeObject(arg0);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		byte[] data = baos.toByteArray();
		//System.out.println(data.length);
		DatagramPacket packet = new DatagramPacket(data, data.length,this.host,this.sendPort);
		try {
			this.socket.send(packet);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public String getAddress() {
		String str = this.socket.getLocalAddress().getHostAddress();
		if(str == null) {
			str = "null";
		}
		return str;
	}
	
	public boolean isConnected() {
		return this.socket.isConnected();
	}
	
	
	public void connect(String addr) {
		try {
			this.host = InetAddress.getByName(addr);
			this.socket.connect(this.host,this.sendPort);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
