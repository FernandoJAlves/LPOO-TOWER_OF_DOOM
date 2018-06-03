package game.dataStream;


import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 
 * Player1Socket - Class responsible for the communication of Player 1
 * @see PlayerSocket
 *
 */
public class Player1Socket extends PlayerSocket{
	/**
	 * Constructs the Player1Socket object
	 */
	public Player1Socket() {
		super();
		this.receivePort = 1234;
		this.sendPort = 3333;
		try {
			this.socket = new DatagramSocket(this.receivePort);
			this.socket.setBroadcast(true);
			this.socket.setSoTimeout(1);
		} catch (SocketException e1) {

			try {
				socket = new DatagramSocket(null); 
				socket.setReuseAddress(true);
				socket.bind(new InetSocketAddress("localhost", this.receivePort));
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	
	/**
	 * Reads the object received and cast into a InputPacket
	 * @return the received InputPacket
	 */
	public InputPacket getHeroPacket() {
		Object obj = this.readObject();
		if(obj != null) {
			return ((InputPacket) obj);
		}
		return null;
	}
}