package game.model;


/**
 * 
 * SlugModel.java - Class the includes all the logic for SlugModel
 *
 */
public class SlugModel extends CharacterModel{

	public enum slugState {WALK,ATTACK,DAMAGE};
	private slugState state;
	private boolean alert;
	private int attackRange;
	private int viewRange;
	private int xStart;
	private float attackTime;
	private float damageTime;
	private float stateTime = 0;

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
				this.stateTime = 0;
			}
			if(c == 'o') {
				this.speed = 0;
				this.decrementHitpoints();
				this.state = slugState.DAMAGE;
				this.stateTime = 0;
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
				damageTime = 0;
				this.state = slugState.WALK;
				this.stateTime = 0;
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
			if(attackTime > (5 * 0.18f)) {
				attackTime = 0;
				this.stateTime = 0;
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

	/**
	 * Gets the stateTime
	 * 
	 * @return The stateTime
	 */
	public float getStateTime() {
		return stateTime;
	}


	/**
	 *  Sets the stateTime
	 * @param stateTime
	 */
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}


	


}
