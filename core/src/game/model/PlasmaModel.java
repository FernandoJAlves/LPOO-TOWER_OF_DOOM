package game.model;

import java.io.Serializable;

public class PlasmaModel extends EntityModel implements Serializable{
	
	private int jumpsLeft;
	private int state;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -56992925497861864L;

	public PlasmaModel(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void setJumpsLeft(int n) {
		jumpsLeft = n;
	}
	
	public boolean decreaseJumpsLeft() {
		jumpsLeft--;
		return jumpsLeft < 0;
	}
	
	public int getState() {
		return state;
	}
	
	@Override
	public ModelType getModelType() {
		return ModelType.PLASMA;
	}

}
