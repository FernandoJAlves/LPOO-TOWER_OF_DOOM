package game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.main.TowerOfDoom;
import game.model.EntityModel;
import game.model.EntityModel.directionState;

public abstract class EntityView {
    protected Sprite sprite;
    protected EntityModel.directionState dir;
	protected float FRAME_TIME;


    public EntityView() {
		this.sprite = new Sprite();
		sprite.setRotation(0);
		this.dir = directionState.RIGHT;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
    
    public abstract Sprite createSprite();


    public void update(EntityModel model) {
    	sprite.setCenter(model.getX(), model.getY());
    	if(turning(model)) {
    		flipAnimations();
    	}
    }
    
    protected boolean turning(EntityModel model) {
    	if(this.dir != model.getDirection()) {
    		this.dir = model.getDirection();
    		return  true;
    	}
    	return false;
    }
    
    protected abstract void flipAnimations();
    
    protected void flipAnimation(Animation<TextureRegion> a) {
		for(TextureRegion tex: a.getKeyFrames()) {
			tex.flip(true, false);
		}
    }

	protected Animation<TextureRegion> createAnimation(String name, int divisions) {
	    Texture thrustTexture = TowerOfDoom.getInstance().getAssetManager().get(name);
	    TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / divisions, thrustTexture.getHeight());
	
	    TextureRegion[] frames = new TextureRegion[divisions];
	    System.arraycopy(thrustRegion[0], 0, frames, 0, divisions);
	
	    return new Animation<TextureRegion>(FRAME_TIME, frames);
	}
}
