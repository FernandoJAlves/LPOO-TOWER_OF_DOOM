package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.model.EntityModel;
import game.model.SlugModel;

/**
 * 
 * SlugView.java - Class that handles the Slug view
 *
 */
public class SlugView extends CharacterView{

	private Animation<TextureRegion> walkingAnimation;
	private Animation<TextureRegion> atackingAnimation;
	private Animation<TextureRegion> damageAnimation;
	
	private float stateTime = 0;
	SlugModel.slugState state;
	
	/**
	 * Constructor for SlugView
	 */
	public SlugView() {
		super();
		FRAME_TIME = 0.18f;
		this.sprite = this.createSprite();
	}
	
	/**
	 * Overrides the update method
	 */
	@Override
	public void update(EntityModel model) {
    	super.update(model);
    	this.stateTime = 
        stateTime += Gdx.graphics.getDeltaTime();
    	((SlugModel)model).setStateTime(stateTime);
    	if(state != ((SlugModel)model).getState()) {
    		state= ((SlugModel)model).getState();
    		stateTime = 0;
    	}
		
	}

	/**
	 * Overrides the createSprite method
	 */
	@Override
	public Sprite createSprite() {
		walkingAnimation = this.createAnimation("SlugWalking.png", 5);
		atackingAnimation = this.createAnimation("SlugAttacking.png", 5);
		damageAnimation = this.createAnimation("SlugDying.png", 3);
		return new Sprite(this.walkingAnimation.getKeyFrame(0));
	}

	/**
	 * Overrides the draw method
	 */
    @Override
    public void draw(SpriteBatch batch) {
        switch(this.state) {
        case WALK:
        	sprite.setRegion(walkingAnimation.getKeyFrame(stateTime,true));
        	break;
        case ATTACK:
        	sprite.setRegion(atackingAnimation.getKeyFrame(stateTime,false));
        	break;
        case DAMAGE:
        	sprite.setRegion(damageAnimation.getKeyFrame(stateTime,false));
        	break;
		default:
			sprite.setRegion(walkingAnimation.getKeyFrame(stateTime, true));
			break;
        }
        sprite.draw(batch);
    }
	
	/**
	 * Overrides the flipAnimations method
	 */
	@Override
	protected void flipAnimations() {
		super.flipAnimation(walkingAnimation);
		super.flipAnimation(atackingAnimation);
		super.flipAnimation(damageAnimation);
	}

}
