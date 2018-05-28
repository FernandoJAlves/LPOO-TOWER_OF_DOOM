package game.model;


public abstract class CharacterModel extends EntityModel{

	
	public CharacterModel(int x, int y){
		super(x, y);

		
	}
	
	abstract public void move(char c);
	
	abstract public void update(float delta);
	
}
