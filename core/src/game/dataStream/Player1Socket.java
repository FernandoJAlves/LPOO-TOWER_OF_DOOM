package game.dataStream;


import java.net.DatagramSocket;
import java.net.SocketException;


public class Player1Socket extends PlayerSocket{
	public Player1Socket() {
		super();
		this.receivePort = 1234;
		this.sendPort = 3333;
		try {
			this.socket = new DatagramSocket(this.receivePort);
			this.socket.connect(this.host,this.sendPort);
			this.socket.setBroadcast(true);
			this.socket.setSoTimeout(1);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public HeroPacket getHeroPacket() {
		Object obj = this.readObject();
		if(obj != null) {
			return ((HeroPacket) obj);
		}
		return null;
	}
}
