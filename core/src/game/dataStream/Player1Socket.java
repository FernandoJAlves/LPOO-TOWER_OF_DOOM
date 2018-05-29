package game.dataStream;


import java.net.DatagramSocket;
import java.net.SocketException;

import game.model.HeroModel;

public class Player1Socket extends PlayerSocket{
	public Player1Socket() {
		super();
		this.receivePort = 1234;
		this.sendPort = 3333;
		try {
			this.socket = new DatagramSocket(this.receivePort,this.host);
			this.socket.setBroadcast(true);
			this.socket.setSoTimeout(1);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public HeroModel getHeroModel() {
		Object obj = this.readObject();
		if(obj != null) {
			return ((HeroModel) obj);
		}
		return null;
	}
	
	public void sendGamePacket(GamePacket game) {
		this.sendObjects(game);
	}
}
