package game.model;

import java.io.Serializable;

import game.controller.GameController;
import game.model.HeroModel.charState;

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
	private int attackTime;

	public SlugModel(int x, int y) {
		super(x, y);
		state = slugState.WALK;
		alert = false;
		attackRange = 40;
		viewRange = 80;
		xStart = x;
	}

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
	
	@Override
	public void update(float delta) {
		if(this.state == slugState.DAMAGE) {
			return;
		}
		
		if(this.state == slugState.ATTACK) {
			attackTime += delta;
			if(attackTime > (5 * 0.15f)) {
				attackTime = 0;
				this.state = slugState.WALK;
			}
			else {
				return;
			}
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

	@Override
	public ModelType getModelType() {
		return EntityModel.ModelType.SLUG;
	}
	
	public void setAlert(boolean a) {
		this.alert = a;
	}
	
	public boolean isAlert() {
		return this.alert;
	}
	
	public slugState getState() {
		return this.state;
	}
	
	public int getViewRange() {
		return this.viewRange;
	}
	
	public int getAttackRange() {
		return this.attackRange;
	}


	


}
