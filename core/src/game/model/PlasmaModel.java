package game.model;

import java.io.Serializable;

public class PlasmaModel extends EntityModel implements Serializable{
	
	private int jumpsLeft;
	private int state;
	private boolean flaggedForRemoval = false;
	private float explosionTime = 8 * 0.15f;
	
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
	
	public boolean decreaseExplosionTime(float delta) {
		explosionTime -= delta;
		return explosionTime < 0;
	}
	
	public int getState() {
		return state;
	}
	
    public boolean isFlaggedToBeRemoved() {
        return flaggedForRemoval;
    }
    
    public void setFlaggedForRemoval(boolean value) {
        this.flaggedForRemoval = value;
    }
	
	@Override
	public ModelType getModelType() {
		return ModelType.PLASMA;
	}

}
