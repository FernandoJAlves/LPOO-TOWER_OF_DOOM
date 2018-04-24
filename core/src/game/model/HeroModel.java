package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class HeroModel extends CharacterModel{
	public enum charState {STARE,WALK,JUMP,ATTACK,DAMAGE};
	protected charState state;

	public HeroModel(String path, int x, int y) {
		super(path, x, y);
		this.speed = 25;
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

	@Override
	public ModelType getModelType() {
		return ModelType.HERO;
	}
	
}
