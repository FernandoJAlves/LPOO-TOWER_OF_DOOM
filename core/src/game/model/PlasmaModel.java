package game.model;

import java.io.Serializable;

public class PlasmaModel extends EntityModel implements Serializable{
	
	private int jumpsLeft;
	private int state;
	private float explosionTime = 8 * 0.15f;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -56992925497861864L;

	public PlasmaModel(int x, int y) {
		super(x, y);
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
		return explosionTime < 0;
	}
	
	public int getState() {
		return state;
	}
	
	@Override
	public ModelType getModelType() {
		return ModelType.PLASMA;
	}

}
