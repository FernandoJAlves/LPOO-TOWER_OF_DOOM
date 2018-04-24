package game.model;

public abstract class EntityModel {
	protected float x,y;
	protected float speed;
	public enum ModelType{HERO, GUARD, OGRE,SLUG};
	
	public EntityModel(int x, int y){
		this.x = x;
		this.y = y;
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
	
	public abstract ModelType getModelType();
}
