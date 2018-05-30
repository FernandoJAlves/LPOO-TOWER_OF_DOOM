package game.model;

import java.io.Serializable;

public abstract class CharacterModel extends EntityModel implements Serializable{

	
	/**
	 * 
	 */
	private transient static final long serialVersionUID = 4421535993963098352L;

	public CharacterModel(int x, int y){
		super(x, y);

		
	}
	
	abstract public void move(char c);
	
	abstract public void update(float delta);
	
}
