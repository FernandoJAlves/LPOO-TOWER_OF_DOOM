package game.dataStream;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Player2Socket extends PlayerSocket{
	public Player2Socket() {
		super();
		try {
			host = InetAddress.getByName("0.0.0.0");
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		this.receivePort = 3333;
		this.sendPort = 1234;
		try {
			this.socket = new DatagramSocket(this.receivePort);
			this.socket.connect(this.host, this.sendPort);
			this.socket.setBroadcast(true);
			this.socket.setSoTimeout(1);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void sendHero(InputPacket hero) {
		this.sendObjects(hero);
	}
	

}
