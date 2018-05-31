package game.dataStream;

import java.io.Serializable;

public class HeroPacket implements Serializable{

	private transient static final long serialVersionUID = 2398579694887329741L;
	public float speed;
	public float yspeed;
	public HeroPacket(float s, float ys){
		
	}
}
