package game.dataStream;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Player1Socket extends PlayerSocket{
	public Player1Socket() {
		super();
		try {
			host = InetAddress.getByName("255.255.255.255");
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		this.receivePort = 1234;
		this.sendPort = 3333;
		try {
			this.socket = new DatagramSocket(this.receivePort);
			//this.socket.connect(this.host, this.sendPort);
			this.socket.setBroadcast(true);
			this.socket.setSoTimeout(1);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public InputPacket getHeroPacket() {
		Object obj = this.readObject();
		if(obj != null) {
			return ((InputPacket) obj);
		}
		return null;
	}
}
