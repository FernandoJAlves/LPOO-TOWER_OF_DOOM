package game.model;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

/**
 * 
 * LevelModel1.java - Class with all the logic for LevelModel1
 *
 */
public class LevelModel1 extends LevelModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4305558602974902560L;

	/**
	 * Constructor for LevelModel1
	 * 
	 */
	public LevelModel1() {
		super();
	}

	/**
	 * Override of abstract method for generating Chars
	 */
	@Override
	public void generateChars() {
		this.addSlug(1440,380);
		this.addSlug(2256,380);
		this.addSlug(2904,380);
	}
	
	/**
	 * Override of abstract method to get the HeroPosition when a level starts
	 */
	public Vector2 getHeroPosition() {
		return new Vector2(288,380);
	}
	
	/**
	 * Override of abstract method to get the netHeroPosition when a level starts
	 */
	public Vector2 getHero2Position() {
		return new Vector2(308,380);
	}

	/**
	 * Override of abstract method that gets the xLimit for the camera
	 */
	@Override
	public int getXLimit() {
		return 3100;
	}

	/**
	 * Override of abstract method that gets the yLimit for the camera
	 */
	@Override
	public int getYLimit() {
		// TODO Auto-generated method stub
		return 400;
	}
	
}
