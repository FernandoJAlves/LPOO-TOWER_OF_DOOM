package game.dataStream;

import java.net.DatagramSocket;
import java.net.SocketException;

import game.model.HeroModel;

public class Player2Socket extends PlayerSocket{
	public Player2Socket() {
		super();
		this.receivePort = 3333;
		this.sendPort = 1234;
		try {
			this.socket = new DatagramSocket(this.receivePort,this.host);
			this.socket.setBroadcast(true);
			this.socket.setSoTimeout(1);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public GamePacket getGamePacket() {
		Object obj = this.readObject();
		if(obj != null ) {
			return ((GamePacket) obj);
		}
		return null;
	}
	
	public void sendHero(HeroModel hero) {
		this.sendObjects(hero);
	}
}
