package game.model;


/**
 * 
 * EntityModel.java - Abstract class for entities
 *
 */
public abstract class EntityModel{
	protected float x,y;
	protected float speed,yspeed;
	public enum ModelType{HERO, GUARD, PLASMA,SLUG};
	public enum directionState{LEFT,RIGHT};
	protected directionState dir;
	private boolean flaggedForRemoval = false;
	
	/**
	 * Constructor for entity models
	 * @param x - X value
	 * @param y - Y value
	 */
	public EntityModel(int x, int y){
		this.x = x;
		this.y = y;
		this.dir = directionState.RIGHT;
	}
	
	/**
	 * Gets the X
	 * 
	 * @return X value
	 */
	public float getX() {
		return this.x;
	}
	
	/**
	 * Gets the Y
	 * 
	 * @return Y value
	 */
	public float getY() {
		return this.y;
	}
	
	/**
	 * Gets the speed
	 * 
	 * @return speed value
	 */
	public float getSpeed() {
		return this.speed;
	}
	
	/**
	 * Gets the X speed
	 * 
	 * @return X speed value
	 */
	public float getXSpeed() {
		return this.speed;
	}
	
	/**
	 * Sets the X speed value
	 * 
	 * @param xs2 - Desired speed
	 */
	public void setXSpeed(float xs2) {
		this.speed = xs2;
	}
	
	/**
	 * Gets the Y speed
	 * 
	 * @return Y speed value
	 */
	public float getYSpeed() {
		return this.yspeed;
	}
	
	/**
	 * Sets the Y speed value
	 * 
	 * @param xs2 - Desired speed
	 */
	public void setYSpeed(float ys2) {
		this.yspeed = ys2;
	}
	
	/**
	 * Gets the direction
	 * 
	 * @return Direction state
	 */
	public directionState getDirection() {
		return this.dir;
	}
	
	/**
	 * Abstract method to get the ModelType
	 * 
	 * @return The model type
	 */
	public abstract ModelType getModelType();
	
	/**
	 * Sets the model's position
	 * @param x2 - X value
	 * @param y2 - Y value
	 */
	public void setPosition(float x2, float y2) {
		this.x = x2;
		this.y = y2;
	}
	
	/**
	 * Sets the model's y value
	 * 
	 * @param y2 - Y value
	 */
	public void setY(float y2) {
		this.y = y2;
	}
	
	/**
	 * Informs if the entity is flagged for removal
	 * 
	 * @return The flaggedForRemoval variable
	 */
    public boolean isFlaggedToBeRemoved() {
        return flaggedForRemoval;
    }
    
    /**
     * Sets the entity's flagged for removal value
     * 
     * @param value - Boolean value wanted
     */
    public void setFlaggedForRemoval(boolean value) {
        this.flaggedForRemoval = value;
    }
	
}
