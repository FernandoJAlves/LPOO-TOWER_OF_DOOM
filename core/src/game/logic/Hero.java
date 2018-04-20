package game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Hero extends Character{

	public Hero(String path, int x, int y) {
		super(path, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		   if(Gdx.input.isKeyPressed(Keys.LEFT)) 
			      sp.setX(sp.getX() - Gdx.graphics.getDeltaTime() * speed);
			   if(Gdx.input.isKeyPressed(Keys.RIGHT)) 
			      sp.setX(sp.getX() + Gdx.graphics.getDeltaTime() * speed);
			   if(Gdx.input.isKeyPressed(Keys.UP)) 
			      sp.setY(sp.getY() + Gdx.graphics.getDeltaTime() * speed);
			   if(Gdx.input.isKeyPressed(Keys.DOWN)) 
			      sp.setY(sp.getY() - Gdx.graphics.getDeltaTime() * speed);
	}
	
}
