package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class HeroModel extends CharacterModel{
	public enum charState {STARE,WALK,JUMP,ATTACK,DAMAGE};
	protected charState state;

	public HeroModel(int x, int y) {
		super(x, y);
		this.speed = 0;
		this.state=charState.STARE;
	}
	
	public void move() {
		   if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			   speed = -25;
			   this.dir = directionState.LEFT;
			   this.state=charState.WALK;
		   }   
		   else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			   speed = 25;
			   this.dir = directionState.RIGHT;
			   this.state=charState.WALK;
		   }
		   else {
			   speed = 0;
			   this.state=charState.STARE;
		   }

	}
	
	public void update() {
		x += Gdx.graphics.getDeltaTime() * speed;
	}
	
	public charState getState() {
		return this.state;
	}

	@Override
	public ModelType getModelType() {
		return ModelType.HERO;
	}
	
}
