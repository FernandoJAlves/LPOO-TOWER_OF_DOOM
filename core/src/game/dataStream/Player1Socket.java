package game.dataStream;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Player1Socket extends PlayerSocket{
	public Player1Socket() {
		super();
		try {
			this.host = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
