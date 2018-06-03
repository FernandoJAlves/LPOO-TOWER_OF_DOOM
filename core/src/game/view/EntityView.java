package game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.main.TowerOfDoom;
import game.model.EntityModel;
import game.model.EntityModel.directionState;

/**
 * 
 * EntityView.java - Abstract class for the view of Entities
 *
 */
public abstract class EntityView {
    protected Sprite sprite;
    protected EntityModel.directionState dir;
	protected float FRAME_TIME;

	/**
	 * Constructor for EntityView
	 */
    public EntityView() {
		this.sprite = new Sprite();
		sprite.setRotation(0);
		this.dir = directionState.RIGHT;
    }

    /**
     * Draw a sprite
     * @param batch - Batch you want to draw
     */
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
    
    /**
     * Abstract method for creating a sprite
     * @return The created sprite
     */
    public abstract Sprite createSprite();

    /**
     * Update method for entities
     * @param model - The model you want to update
     */
    public void update(EntityModel model) {
    	sprite.setCenter(model.getX(), model.getY());
    	if(turning(model)) {
    		flipAnimations();
    	}
    }
    
    /**
     * Informs if turning
     * @param model - The model you want to test
     * @return True if turning, false if not
     */
    protected boolean turning(EntityModel model) {
    	if(this.dir != model.getDirection()) {
    		this.dir = model.getDirection();
    		return  true;
    	}
    	return false;
    }
    
    /**
     * Absctract method to flip animations
     */
    protected abstract void flipAnimations();
    
    /**
     * Flips 1 animation
     * @param a - The Animation you want to flip
     */
    protected void flipAnimation(Animation<TextureRegion> a) {
		for(TextureRegion tex: a.getKeyFrames()) {
			tex.flip(true, false);
		}
    }

    /**
     * Creates an Animation
     * @param name - name of the animation
     * @param divisions - number of divisions
     * @return The created animation
     */
	protected Animation<TextureRegion> createAnimation(String name, int divisions) {
	    Texture thrustTexture = TowerOfDoom.getInstance().getAssetManager().get(name);
	    TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / divisions, thrustTexture.getHeight());
	
	    TextureRegion[] frames = new TextureRegion[divisions];
	    System.arraycopy(thrustRegion[0], 0, frames, 0, divisions);
	
	    return new Animation<TextureRegion>(FRAME_TIME, frames);
	}
}
