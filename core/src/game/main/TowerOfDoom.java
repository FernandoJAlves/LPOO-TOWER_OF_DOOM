package game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.view.GameView;

public class TowerOfDoom extends Game {
	static TowerOfDoom instance;
	SpriteBatch batch;
	AssetManager assetManager;
	
	
	@Override
	public void create () {
		TowerOfDoom.instance = this;
		this.setScreen(new GameView());
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
