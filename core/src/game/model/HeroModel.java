package game.model;


import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

public class HeroModel extends CharacterModel implements Serializable{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = -1120208627717480964L;


	public enum charState {STARE,WALK,JUMP,FALL, LAND,ATTACK,DAMAGE};
	protected charState state;
	protected float attackTime = 0;

	public HeroModel(int x, int y) {
		super(x, y);
		this.speed = 0;
		this.yspeed = 0;
		this.state=charState.STARE;
	}
	
	public void move(char c) {
		
		switch(this.state) {
		
		case STARE:
			if (c == 'w') {
				this.yspeed = 85;
			}
			if (c == 'a') {
				speed = -50;
				this.dir = directionState.LEFT;
			} else if (c == 'd') {
				speed = 50;
				this.dir = directionState.RIGHT;
			} else {
				speed = 0;
			}
			if(c == 'f') {
				this.state = charState.ATTACK;
			}
			break;
		case WALK:
			if (c == 'w') {
				this.yspeed = 85;
			}
			if (c == 'a') {
				speed = -50;
				this.dir = directionState.LEFT;
			} else if (c == 'd') {
				speed = 50;
				this.dir = directionState.RIGHT;
			} else {
				speed = 0;
			}
			if(c == 'f') {
				this.state = charState.ATTACK;
			}
			break;
		case JUMP:
			if (c == 'a') {
				speed = -50;
				this.dir = directionState.LEFT;
			} else if (c == 'd') {
				speed = 50;
				this.dir = directionState.RIGHT;
			} else {
				speed = 0;
			}
			if(c == 'f') {
				this.state = charState.ATTACK;
			}
			break;
		case FALL:
			if (c == 'a') {
				speed = -50;
				this.dir = directionState.LEFT;
			} else if (c == 'd') {
				speed = 50;
				this.dir = directionState.RIGHT;
			} else {
				speed = 0;
			}
			if(c == 'f') {
				this.state = charState.ATTACK;
			}
			break;
		case LAND:
			break;
		case ATTACK:

			break;
		case DAMAGE:
			break;
		
		
		default:
			break;
		}
	
	}
	
	public void update(float delta) {
		//TODO NANDINHO FAZ O SWITCH
		if(this.state == charState.ATTACK) {
			System.out.println(this.state);
			attackTime += delta;
			if(attackTime > (8 * 0.15f)) {
				attackTime = 0;
				this.state = charState.STARE;
			}
			else {
				return;
			}

		}

		if(this.yspeed > 1) {
			this.state = charState.JUMP;
			return;
		}
		else if (this.yspeed < -1) {
			this.state = charState.FALL;
			return;
		}
		else if(this.state != charState.JUMP) {
			if(this.speed != 0) {
				this.state = charState.WALK;
			}
			else this.state = charState.STARE;
			return;
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
		if(this.attackTime == 0) {
			this.state = hero.state;
		}
		
		this.yspeed = hero.yspeed;
	}
	public void copyToNet(HeroModel hero) {
		this.copy(hero);
		this.dir = hero.dir;
	}
	
	public void copyFromNet(HeroModel hero) {
		this.copy(hero);
		this.attackTime = hero.attackTime;
	}
	
}
