package game.model;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

public class LevelModel1 extends LevelModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4305558602974902560L;

	public LevelModel1() {
		super();
	}

	@Override
	public void generateChars() {
		this.addSlug(0, 0);
		
	}
	
	public Vector2 getHeroPosition() {
		return new Vector2(288,380);
	}
	
	public Vector2 getHero2Position() {
		return new Vector2(308,380);
	}
	
}
