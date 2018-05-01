package game.model;


public abstract class CharacterModel extends EntityModel{
	public enum directionState{LEFT,RIGHT};
	protected directionState dir;
	
	public CharacterModel(int x, int y){
		super(x, y);

		
	}
	
	public directionState getDirection() {
		return this.dir;
	}
	
	abstract public void move();
	
}
