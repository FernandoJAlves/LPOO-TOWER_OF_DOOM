package game.model;

import java.io.Serializable;

public abstract class CharacterModel extends EntityModel implements Serializable{

	
	/**
	 * 
	 */
	private transient static final long serialVersionUID = 4421535993963098352L;
	
	protected int hitpoints;
	protected float stamina;

	public CharacterModel(int x, int y){
		super(x, y);

		
	}
	
	abstract public void move(char c);
	
	abstract public void update(float delta);
	
	public int getHitPoints() {
		return hitpoints;
	}
	
	public float getStamina() {
		return stamina;
	}
	
	public void decrementStamina() {
		stamina--;
	}
	
	public void decrementHitpoints() {
		hitpoints--;
	}
	
}
