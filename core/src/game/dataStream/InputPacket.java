package game.dataStream;

import java.io.Serializable;

/**
 * 
 * Packet to send the input information from Player 2 to Player 1
 *
 */
public class InputPacket implements Serializable{

	private transient static final long serialVersionUID = 2398579694887329741L;
	public boolean w;
	public boolean a;
	public boolean f;
	public boolean d;
	/**
	 * Constructor of the packet
	 */
	public InputPacket(){
		this.w = false;
		this.a = false;
		this.f = false;
		this.d = false;
	}
}
