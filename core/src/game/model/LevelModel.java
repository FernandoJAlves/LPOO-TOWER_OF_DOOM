package game.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public abstract class LevelModel {
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
	
	public ArrayList<CharacterModel> getChars() {
		return this.chars;
	}
}
