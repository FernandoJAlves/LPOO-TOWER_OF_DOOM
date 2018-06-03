package game.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.main.TowerOfDoom;

/**
 * 
 * LevelView.java - Class for controlling the Levels' view
 *
 */
public class LevelView {
	Texture background;
	ArrayList<CharacterView> chars;
	
	/**
	 * Constructor for LevelView
	 * @param back - Name of the image
	 */
	public LevelView(String back) {
		this.background = TowerOfDoom.getInstance().getAssetManager().get(back);
	}
	
	/**
	 * Draw method
	 * @param batch - The sprite batch
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(this.background, 0, 0);
	}
	
	/**
	 * Dispose method
	 */
	public void dispose() {
		background.dispose();
	}
}
