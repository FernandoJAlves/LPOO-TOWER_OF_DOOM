package game.dataStream;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 
 * Player2Socket - The class responsible for the communication of Player 2
 * @see PlayerSocket
 *
 */
public class Player2Socket extends PlayerSocket{
	/**
	 * Constructs Player2Socket object
	 */
	public Player2Socket() {
		super();
		this.receivePort = 3333;
		this.sendPort = 1234;
		try {
			this.socket = new DatagramSocket(this.receivePort);
			
			this.socket.setBroadcast(true);
			this.socket.setSoTimeout(1);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Sends the InputPacket
	 * @param hero - the InputPacket
	 */
	public void sendHero(InputPacket hero) {
		this.sendObjects(hero);
	}
	

}