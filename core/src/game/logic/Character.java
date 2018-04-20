package game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor{
	protected Texture[][] sprites;
	protected Sprite sp;
	protected Body body;
	protected int x,y;
	protected int speed;
	public enum charState {STARE,WALK,JUMP,ATTACK,DAMAGE};
	protected charState state;
	
	public Character(String path, int x, int y){
		this.sp = new Sprite();
		this.sp.setTexture(new Texture(Gdx.files.internal(path)));
		this.sp.setPosition(x, y);
		this.speed = 11;
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(this.sp.getTexture(), this.sp.getX(), this.sp.getY());
	}
	
	
}
