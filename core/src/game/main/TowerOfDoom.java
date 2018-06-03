package game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.menu.MainMenu;
import game.view.GameView;

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
	
	@Override
	public void pause() {
		if(this.getScreen() instanceof GameView) {
			((GameView)this.getScreen()).pause();
		
		}
	}

	@Override
	public void resume() {

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
