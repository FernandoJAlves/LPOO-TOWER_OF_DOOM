package game.main;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMode extends ScreenAdapter{
	protected SpriteBatch batch;
	
	public GameMode(SpriteBatch b){
		this.batch = b;
	}

}
