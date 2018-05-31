package game.dataStream;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Player2Socket extends PlayerSocket{
	public Player2Socket() {
		super();
		try {
			this.host = InetAddress.getByName("0.0.0.0");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setSocket();
	}
	
	public void sendHero(InputPacket hero) {
		this.sendObjects(hero);
	}
	

}
