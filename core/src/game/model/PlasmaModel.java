package game.model;

import java.io.Serializable;

public class PlasmaModel extends EntityModel implements Serializable{
	
	private int jumpsLeft;
	private int state;
	private float explosionTime = 1;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -56992925497861864L;

	public PlasmaModel(int x, int y) {
		super(x, y);
		state = 0;
		// TODO Auto-generated constructor stub
	}

	public int getJumpsLeft() {
		return jumpsLeft;
	}
	
	public void setJumpsLeft(int n) {
		jumpsLeft = n;
	}
	
	public boolean decreaseJumpsLeft() {
		jumpsLeft--;
		return jumpsLeft < 0;
	}
	
	public boolean decreaseExplosionTime(float delta) {
		explosionTime -= delta;
		//System.out.println(explosionTime);
		return explosionTime < 0;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int n) {
		state = n;
	}
	
	public void resetExplosion() {
		explosionTime = 1;
	}
	
	@Override
	public ModelType getModelType() {
		return ModelType.PLASMA;
	}

}
