package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputHandler {
	public char getInput() {
		   if(Gdx.input.isKeyPressed(Keys.LEFT)) 
			   return 'a';
		   if(Gdx.input.isKeyPressed(Keys.RIGHT)) 
			   return 'd';
		   if(Gdx.input.isKeyPressed(Keys.UP)) 
			   return 'w';
		   if(Gdx.input.isKeyPressed(Keys.DOWN)) 
			   return 's';
		   else return 'n';
	}
}
