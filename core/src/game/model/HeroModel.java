package game.model;


import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

import game.controller.GameController;
import game.model.EntityModel.directionState;
import game.model.SlugModel.slugState;

/**
 * 
 * HeroModel.java - Class the includes all the logic for HeroModel
 *
 */
public class HeroModel extends CharacterModel implements Serializable{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = -1120208627717480964L;


	public enum charState {STARE,WALK,JUMP,FALL, LAND,ATTACK,DAMAGE};
	protected charState state;
	protected float attackTime = 0;
	protected float damageTime = 0;

	/**
	 * Constructor for HeroModel
	 * 
	 * @param x - X value
	 * @param y - Y value
	 */
	public HeroModel(int x, int y) {
		super(x, y);
		this.speed = 0;
		this.yspeed = 0;
		this.state=charState.STARE;
		this.hitpoints = 10;
		this.stamina = 10;
	}
	
	/**
	 * Override of the abstract function move
	 */
	public void move(char c) {
		
		switch(this.state) {
		
		case STARE:
			verticalMovement(c);
			horizontalMovement(c);
			fireMechanic(c);
			damageMechanic(c);
			break;
		case WALK:
			verticalMovement(c);
			horizontalMovement(c);
			fireMechanic(c);
			damageMechanic(c);
			break;
		case JUMP:
			horizontalMovement(c);
			fireMechanic(c);
			damageMechanic(c);
			break;
		case FALL:
			horizontalMovement(c);
			fireMechanic(c);
			damageMechanic(c);
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

	/**
	 * Handles input for damage
	 * 
	 * @param c - input
	 */
	private void damageMechanic(char c) {
		if(c == 'o') {
			this.speed = -75;
			this.yspeed = 100;
			this.state = charState.DAMAGE;
		}
		else if(c == 'p') {
			this.speed = 75;
			this.yspeed = 100;
			this.state = charState.DAMAGE;
		}
	}
	
	
	/**
	 * Handles input for firing
	 * 
	 * @param c - input
	 */
	private void fireMechanic(char c) {
		if(c == 'f' && this.stamina >= 1) {
			this.state = charState.ATTACK;
		}
	}

	/**
	 * Handles input for vertical movement
	 * 
	 * @param c - input
	 */
	private void verticalMovement(char c) {
		if (c == 'w') {
			this.yspeed = 100;
		}
	}

	/**
	 * Handles input for horizontal movement
	 * 
	 * @param c - input
	 */
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
	
	/**
	 * Override of the update abstract method
	 */
	public void update(float delta) {
		
		if(this.stamina < 10) {
			this.stamina += (delta/10);
		}

		if(this.state == charState.ATTACK) {
			attackTime += delta;
			if(attackTime > 3*0.15f && attackTime < 3*0.15f+delta) {
				GameController.getInstance().fire(this);
			}
			if(attackTime > (8 * 0.15f)) {
				
				attackTime = 0;
				this.state = charState.STARE;
			}
			else {
				return;
			}

		}
		
		if(this.state == charState.DAMAGE) {
			damageTime += delta;
			if(damageTime > (6 * 0.15f)) {
				damageTime = 0;
				this.state = charState.FALL;
				if(this.hitpoints <= 0) {
					GameModel.getInstance().setGameOver();
				}
			}
			return;
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
	
	/**
	 * Gets the state
	 * 
	 * @return The charState
	 */
	public charState getState() {
		return this.state;
	}
	
	/**
	 * Sets the position
	 * 
	 * @param vec - Wanted position
	 */
	public void setPosition(Vector2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	/**
	 * Override of getModelType abstract method
	 */
	@Override
	public ModelType getModelType() {
		return ModelType.HERO;
	}
	


	
}
