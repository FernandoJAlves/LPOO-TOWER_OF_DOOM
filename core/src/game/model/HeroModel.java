package game.model;


import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

import game.controller.GameController;

public class HeroModel extends CharacterModel implements Serializable{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = -1120208627717480964L;


	public enum charState {STARE,WALK,JUMP,FALL, LAND,ATTACK,DAMAGE};
	protected charState state;
	protected float attackTime = 0;
	private int hitpoints = 10;
	private int stamina = 10;

	public HeroModel(int x, int y) {
		super(x, y);
		this.speed = 0;
		this.yspeed = 0;
		this.state=charState.STARE;
	}
	
	public void move(char c) {
		
		switch(this.state) {
		
		case STARE:
			verticalMovement(c);
			horizontalMovement(c);
			fireMechanic(c);
			break;
		case WALK:
			verticalMovement(c);
			horizontalMovement(c);
			fireMechanic(c);
			break;
		case JUMP:
			horizontalMovement(c);
			fireMechanic(c);
			break;
		case FALL:
			horizontalMovement(c);
			fireMechanic(c);
			break;
		case LAND:
			break;
		case ATTACK:
			horizontalMovement(c);
			break;
		case DAMAGE:
			break;
		
		
		default:
			break;
		}
	
	}

	private void fireMechanic(char c) {
		if(c == 'f' && this.stamina > 0) {
			this.state = charState.ATTACK;
		}
	}

	private void verticalMovement(char c) {
		if (c == 'w') {
			this.yspeed = 85;
		}
	}

	private void horizontalMovement(char c) {
		if (c == 'a') {
			speed = -50;
			this.dir = directionState.LEFT;
		} else if (c == 'd') {
			speed = 50;
			this.dir = directionState.RIGHT;
		} else {
			speed = 0;
		}
	}
	
	public void update(float delta) {
		//TODO NANDINHO FAZ O SWITCH
		if(this.state == charState.ATTACK) {
			attackTime += delta;
			if(attackTime > (8 * 0.15f)) {
				GameController.getInstance().fire();
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
	
	public int getHitPoints() {
		return hitpoints;
	}
	
	public int getStamina() {
		return stamina;
	}
	
	public void decrementStamina() {
		stamina--;
	}

	
}
