package game.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public abstract class LevelModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4316961198719715398L;
	protected ArrayList<CharacterModel> chars;
	
	public LevelModel() {
		chars = new ArrayList<CharacterModel>();
		this.generateChars();
	}
	
	protected abstract void generateChars();
	
	protected void addSlug(int x, int y) {
		this.chars.add(new SlugModel(x,y));
	}
	
	public abstract Vector2 getHeroPosition();
	
	public abstract Vector2 getHero2Position();
	
	public ArrayList<CharacterModel> getChars() {
		return this.chars;
	}
	
	public abstract int getXLimit();
	
	public abstract int getYLimit();
}
