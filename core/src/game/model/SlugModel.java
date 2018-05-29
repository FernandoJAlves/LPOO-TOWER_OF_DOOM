package game.model;

import java.io.Serializable;

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

	public SlugModel(int x, int y) {
		super(x, y);
		state = slugState.WALK;
		alert = false;
		attackRange = 1;
		viewRange = 3;
	}

	@Override
	public void move(char c) {

		
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

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	public void copy(SlugModel slug) {
		this.x = slug.x;
		this.y = slug.y;
		this.speed = slug.speed;
		this.yspeed = slug.yspeed;
		this.dir = slug.dir;
		this.state = slug.state;
		this.viewRange = slug.viewRange;
		this.attackRange = slug.attackRange;
		this.alert = slug.alert;
	}

}
