package game.model;


import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

public class HeroModel extends CharacterModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1120208627717480964L;


	public enum charState {STARE,WALK,JUMP,FALL, LAND,ATTACK,DAMAGE};
	protected charState state;

	public HeroModel(int x, int y) {
		super(x, y);
		this.speed = 0;
		this.yspeed = 0;
		this.state=charState.STARE;
	}
	
	public void move(char c) {
		
		if((c == 'w') && (this.yspeed == 0)) {
			this.yspeed = 85;
		}
		
		if(c == 'f') {
			
		}
		
		if((c == 'a')) {
			   speed = -50;
			   this.dir = directionState.LEFT;
		   }   
	   else if((c == 'd')) {
			   speed = 50;
			   this.dir = directionState.RIGHT;
		   }
	   else {
			   speed = 0;
		   }

	}
	
	public void update(float delta) {
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
	
	public void copy(HeroModel hero) {
		this.x = hero.x;
		this.y = hero.y;
		this.speed = hero.speed;
		this.state = hero.state;
		this.yspeed = hero.yspeed;
		this.dir = hero.dir;
	}
	
}
