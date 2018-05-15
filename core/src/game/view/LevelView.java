package game.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.main.TowerOfDoom;

public class LevelView {
	Texture background;
	ArrayList<CharacterView> chars;
	
	public LevelView(String back) {
		this.background = TowerOfDoom.getInstance().getAssetManager().get(back);
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(this.background, 0, 0);
	}
	
	public void dispose() {
		background.dispose();
	}
}
