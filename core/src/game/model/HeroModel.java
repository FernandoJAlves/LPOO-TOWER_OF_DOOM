package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

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
			this.yspeed = 85;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			   speed = -30;
			   this.dir = directionState.LEFT;
		   }   
	   else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			   speed = 30;
			   this.dir = directionState.RIGHT;
		   }
	   else {
			   speed = 0;
		   }

	}
	
	public void move(char c) {
		
		if((c == 'w') && (this.yspeed == 0)) {
			this.yspeed = 85;
		}
		
		if((c == 'a')) {
			   speed = -30;
			   this.dir = directionState.LEFT;
		   }   
	   else if((c == 'd')) {
			   speed = 30;
			   this.dir = directionState.RIGHT;
		   }
	   else {
			   speed = 0;
		   }

	}
	
	public void update() {
		if(this.yspeed > 1) {
			this.state = charState.JUMP;
		}
		else if (this.yspeed < -1) {
			this.state = charState.FALL;
		}
		else if(this.state != charState.JUMP) {
			if(this.speed != 0) {
				this.state = charState.WALK;
			}
			else this.state = charState.STARE;
		}
		
	}
	
	public charState getState() {
		return this.state;
	}
	
	public void setPosition(Vector2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	@Override
	public ModelType getModelType() {
		return ModelType.HERO;
	}
	
}
