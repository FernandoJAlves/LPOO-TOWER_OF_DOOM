package game.model;


/**
 * 
 * CharacterModel.java - Abstract Character Model class
 *
 */
public abstract class CharacterModel extends EntityModel{

	
	protected int hitpoints;
	protected float stamina;

	/**
	 * Constructor for CharacterModel
	 * @param x - The X value
	 * @param y - The y value
	 */
	public CharacterModel(int x, int y){
		super(x, y);

		
	}
	
	/**
	 * Abstract method for moving characters
	 * @param c - The input
	 */
	abstract public void move(char c);
	
	/**
	 * Abstract method for updating characters
	 * @param delta - The time since last update
	 */
	abstract public void update(float delta);
	
	/**
	 * Gets the hitpoints
	 * 
	 * @return The hitpoints
	 */
	public int getHitPoints() {
		return hitpoints;
	}
	
	/**
	 * Gets the stamina
	 * 
	 * @return The stamina
	 */
	public float getStamina() {
		return stamina;
	}
	
	/**
	 * Decrements the stamina
	 */
	public void decrementStamina() {
		stamina--;
	}
	
	/**
	 * Decrements the hitpoints
	 */
	public void decrementHitpoints() {
		hitpoints--;
	}
	
}
