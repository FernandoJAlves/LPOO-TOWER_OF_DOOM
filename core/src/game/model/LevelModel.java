package game.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

/**
 * 
 * LevelModel.java - Abstract class with all the logic for LevelModels
 *
 */
public abstract class LevelModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4316961198719715398L;
	protected ArrayList<CharacterModel> chars;
	
	/**
	 * Constructor for LevelModel
	 * 
	 */
	public LevelModel() {
		chars = new ArrayList<CharacterModel>();
		this.generateChars();
	}
	
	/**
	 * Abstract method for generating Chars
	 */
	protected abstract void generateChars();
	
	/**
	 * Adds a slug to chars
	 * 
	 * @param x - X value
	 * @param y - Y value
	 */
	protected void addSlug(int x, int y) {
		this.chars.add(new SlugModel(x,y));
	}
	
	/**
	 * Abstract method to get the HeroPosition when a level starts
	 * 
	 * @return Vector2 with the position
	 */
	public abstract Vector2 getHeroPosition();
	
	/**
	 * Abstract method to get the netHero Position when a level starts
	 * 
	 * @return Vector2 with the position
	 */
	public abstract Vector2 getHero2Position();
	
	/**
	 * Gets the arraylist chars
	 * 
	 * @return The arraylist of CharacterModels
	 */
	public ArrayList<CharacterModel> getChars() {
		return this.chars;
	}
	
	/**
	 * Abstract method that gets the xLimit for the camera
	 * 
	 * @return X limit value
	 */
	public abstract int getXLimit();
	
	/**
	 * Abstract method that gets the yLimit for the camera
	 * 
	 * @return Y limit value
	 */
	public abstract int getYLimit();
}
