package game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import menu.MainMenu;

public class TowerOfDoom extends Game {
	static TowerOfDoom instance;
	SpriteBatch batch;
	AssetManager assetManager;
	
	
	@Override
	public void create () {	
		assetManager = new AssetManager();
		TowerOfDoom.instance = this;	
		this.setScreen(MainMenu.getInstance());
		batch = new SpriteBatch();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		this.getScreen().dispose();
	}
	
	public static TowerOfDoom getInstance() {
		return instance;
	}
	
	public SpriteBatch getBatch() {
		return this.batch;
	}
	
	public AssetManager getAssetManager() {
		return this.assetManager;
	}
}
