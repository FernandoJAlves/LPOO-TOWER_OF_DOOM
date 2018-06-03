package game.model;

import java.io.Serializable;

/**
 * 
 * SlugModel.java - Class the includes all the logic for SlugModel
 *
 */
public class SlugModel extends CharacterModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8278827726645127363L;

	public enum slugState {WALK,ATTACK,DAMAGE};
	private slugState state;
	private boolean alert;
	private int attackRange;
	private int viewRange;
	private int xStart;
	private float attackTime;
	private float damageTime;

	/**
	 * Constructor for SlugModel
	 * 
	 * @param x - X value
	 * @param y - Y value
	 */
	public SlugModel(int x, int y) {
		super(x, y);
		state = slugState.WALK;
		this.speed = 30;
		alert = false;
		attackRange = 50;
		viewRange = 120;
		xStart = x;
		hitpoints = 5;
	}

	/**
	 * Override of the abstract function move
	 */
	@Override
	public void move(char c) {
		//System.out.println(this.state);
		switch(this.state) {
		case WALK:
			if(c == 'a') {
				this.speed = -30;
				this.dir = directionState.LEFT;
			}
			if(c == 'd') {
				this.speed = 30;
				this.dir = directionState.RIGHT;
			}
			if(c == 'f') {
				this.state = slugState.ATTACK;
			}
			if(c == 'o') {
				this.speed = 0;
				this.decrementHitpoints();
				this.state = slugState.DAMAGE;
			}
			break;
			
		case ATTACK:
			break;
			
		case DAMAGE:
			break;
			
		default:
			break;
		}
		
	}
	
	/**
	 * Override of the update abstract method
	 */
	@Override
	public void update(float delta) {
		switch(this.state) {
		case DAMAGE:
			damageTime += delta;
			if(damageTime > (3 * 0.18f)) {
				System.out.println(this.hitpoints);
				damageTime = 0;
				this.state = slugState.WALK;
				if(this.dir == directionState.LEFT) {
					this.speed = -30;
				}
				else {
					this.speed = 30;
				}
				if(this.hitpoints <= 0) {
					this.setFlaggedForRemoval(true);
				}
			}
			return;
		case ATTACK:
			attackTime += delta;
			//System.out.println(attackTime);
			if(attackTime > (5 * 0.18f)) {
				attackTime = 0;
				this.state = slugState.WALK;
			}
			else {
				return;
			}
			break;
		
		default:
			break;
		}
		
		if(!alert) {
			if(this.xStart - x > 72) {
				this.move('d');
			}
			else if(x - this.xStart > 72) {
				this.move('a');
			}
		}
		
	}

	/**
	 * Override of getModelType abstract method
	 */
	@Override
	public ModelType getModelType() {
		return EntityModel.ModelType.SLUG;
	}
	
	/**
	 * Sets the value of alert
	 * 
	 * @param a - The wanted boolean value
	 */
	public void setAlert(boolean a) {
		this.alert = a;
	}
	
	/**
	 * Returns alert
	 * 
	 * @return Alert value
	 */
	public boolean isAlert() {
		return this.alert;
	}
	
	/**
	 * Gets the state
	 * 
	 * @return The slugState
	 */
	public slugState getState() {
		return this.state;
	}
	
	/**
	 * Gets the viewRange
	 * 
	 * @return The viewRange
	 */
	public int getViewRange() {
		return this.viewRange;
	}
	
	/**
	 * Gets the attackRange
	 * 
	 * @return The attackRange
	 */
	public int getAttackRange() {
		return this.attackRange;
	}


	


}
