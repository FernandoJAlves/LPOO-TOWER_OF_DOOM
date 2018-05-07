package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class HeroModel extends CharacterModel{
	public enum charState {STARE,WALK,JUMP,FALL, LAND,ATTACK,DAMAGE};
	protected charState state;

	public HeroModel(int x, int y) {
		super(x, y);
		this.speed = 0;
		this.yspeed = 0;
		this.state=charState.STARE;
	}
	
	public void move() {
		
		if(Gdx.input.isKeyPressed(Keys.UP) && (this.yspeed == 0)) {
			this.yspeed = 25;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			   speed = -25;
			   this.dir = directionState.LEFT;
		   }   
	   else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			   speed = 25;
			   this.dir = directionState.RIGHT;
		   }
	   else {
			   speed = 0;
		   }

	}
	
	public void update() {
		x += Gdx.graphics.getDeltaTime() * speed;
		if(this.yspeed > 5) {
			this.state = charState.JUMP;
		}
		else if (this.yspeed < -5) {
			this.state = charState.FALL;
		}
		else if(this.speed != 0) {
			this.state = charState.WALK;
		}
		else this.state = charState.STARE;
	}
	
	public charState getState() {
		return this.state;
	}

	@Override
	public ModelType getModelType() {
		return ModelType.HERO;
	}
	
}
