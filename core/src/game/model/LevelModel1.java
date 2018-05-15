package game.model;

import com.badlogic.gdx.math.Vector2;

public class LevelModel1 extends LevelModel{
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
	
	
}
