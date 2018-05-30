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
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class PlayerSocket {
	private int size = 1024;
	protected DatagramSocket socket;
	protected InetAddress host;
	protected int sendPort;
	protected int receivePort;
	
	public PlayerSocket() {
		try {
			host = InetAddress.getByName("172.30.1.250");
			

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
		GZIPInputStream gzipIn = null;
		ObjectInputStream is = null;
		try {
			gzipIn = new GZIPInputStream(in);
			is = new ObjectInputStream(gzipIn);
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
		GZIPOutputStream compressed = null;
		ObjectOutputStream oos = null;
		try {
			
			compressed = new GZIPOutputStream(baos);
			oos = new ObjectOutputStream(compressed);
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
			e.printStackTrace();
		}
	}
	
}
