package game.model;

public abstract class EntityModel {
	protected float x,y;
	protected float speed;
	public enum ModelType{HERO, GUARD, OGRE,SLUG};
	public enum directionState{LEFT,RIGHT};
	protected directionState dir;
	
	public EntityModel(int x, int y){
		this.x = x;
		this.y = y;
		this.dir = directionState.RIGHT;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public directionState getDirection() {
		return this.dir;
	}
	
	public abstract ModelType getModelType();
}
