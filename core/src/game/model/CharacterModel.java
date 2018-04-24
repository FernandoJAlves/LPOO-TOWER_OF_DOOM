package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class CharacterModel extends EntityModel{
	//protected Texture[][] sprites;
	protected Sprite sp;
	//protected Body body;
	public enum directionState{LEFT,RIGHT};
	protected directionState dir;
	
	public CharacterModel(String path, int x, int y){ //TODO clean path
		super(x, y);
		this.sp = new Sprite();
		this.sp.setTexture(new Texture(Gdx.files.internal(path)));
		this.sp.setPosition(x, y);
		
	}
	
	public directionState getDirection() {
		return this.dir;
	}
	
	abstract public void move();
	
	public void draw(SpriteBatch batch) {
		batch.draw(this.sp.getTexture(), this.sp.getX(), this.sp.getY());
	}
}
