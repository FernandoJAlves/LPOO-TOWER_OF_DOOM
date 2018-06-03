package game.model;


/**
 * 
 * PlasmaModel.java - Class with all the logic for PlasmaModels
 *
 */
public class PlasmaModel extends EntityModel{
	
	private int jumpsLeft;
	private int state;
	private float explosionTime = 1;
	private float stateTime = 0;

	/**
	 * Constructor for PlasmaModel
	 * 
	 * @param x - X value
	 * @param y - Y value
	 */
	public PlasmaModel(int x, int y) {
		super(x, y);
		state = 0;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the jumpsLeft
	 * 
	 * @return Number of jumpsLeft
	 */
	public int getJumpsLeft() {
		return jumpsLeft;
	}
	
	/**
	 * Sets the number of jumpsLeft
	 * 
	 * @param n - Number of jumpsLeft wanted
	 */
	public void setJumpsLeft(int n) {
		jumpsLeft = n;
	}
	
	/**
	 * Decreases number of jumps left
	 * 
	 * @return False if higher than 0, otherwise returns True
	 */
	public boolean decreaseJumpsLeft() {
		jumpsLeft--;
		return jumpsLeft < 0;
	}
	
	/**
	 * Decreases the explosion time
	 * @param delta - Time of explosion to decrease
	 * @return False if higher than 0, otherwise returns True
	 */
	public boolean decreaseExplosionTime(float delta) {
		explosionTime -= delta;
		//System.out.println(explosionTime);
		return explosionTime < 0;
	}
	
	/**
	 * Gets the state
	 * 
	 * @return The state
	 */
	public int getState() {
		return state;
	}
	
	/**
	 * Sets the state
	 * 
	 * @param n - Wanted state
	 */
	public void setState(int n) {
		state = n;
		this.stateTime = 0;
	}
	
	/**
	 * Resets the explostion time
	 */
	public void resetExplosion() {
		explosionTime = 1;
	}
	
	/**
	 * Override of abstract method to get ModelType
	 */
	@Override
	public ModelType getModelType() {
		return ModelType.PLASMA;
	}

	/**
	 * Gets the stateTime
	 * @return stateTime
	 */
	public float getStateTime() {
		return stateTime;
	}

	/**
	 * Sets the stateTime
	 * @param stateTime
	 */
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

}
