package game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.menu.MainMenu;
import game.view.GameView;

/**
 * 
 * TowerOfDoom.java - Main Game class
 *
 */
public class TowerOfDoom extends Game {
	static TowerOfDoom instance;
	SpriteBatch batch;
	AssetManager assetManager;
	
	
	/**
	 * Override of the create method
	 */
	@Override
	public void create () {	
		assetManager = new AssetManager();
		TowerOfDoom.instance = this;	
		this.setScreen(MainMenu.getInstance());
		batch = new SpriteBatch();

	}
	
	/**
	 * Override of the dispose method
	 */
	@Override
	public void dispose () {
		batch.dispose();
		this.getScreen().dispose();
	}
	
	/**
	 * Override of the pause method
	 */
	@Override
	public void pause() {
		if(this.getScreen() instanceof GameView) {
			((GameView)this.getScreen()).pause();
		
		}
	}

	/**
	 * Override of the resume method
	 */
	@Override
	public void resume() {

	}
	
	/**
	 * Gets the instance
	 * 
	 * @return The instance
	 */
	public static TowerOfDoom getInstance() {
		return instance;
	}
	
	/**
	 * Gets the batch
	 * 
	 * @return The batch
	 */
	public SpriteBatch getBatch() {
		return this.batch;
	}
	
	/**
	 * Gets the asset manager
	 * 
	 * @return The asset manager
	 */
	public AssetManager getAssetManager() {
		return this.assetManager;
	}
}
