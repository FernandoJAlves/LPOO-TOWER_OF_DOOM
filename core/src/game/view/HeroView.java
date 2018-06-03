package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.model.EntityModel;
import game.model.HeroModel;

/**
 * 
 * HeroView.java - Class that handles the hero's View
 *
 */
public class HeroView extends CharacterView{
	private Animation<TextureRegion> staringAnimation;
	private Animation<TextureRegion> walkingAnimation;
	private Animation<TextureRegion> jumpingAnimation;
	private Animation<TextureRegion> landingAnimation;
	private Animation<TextureRegion> firingAnimation;
	
	private float stateTime = 0;
	HeroModel.charState state;
	
	/**
	 * Constructor for HeroView
	 */
	public HeroView() {
		super();
		FRAME_TIME = 0.15f;
		this.sprite = this.createSprite();
	}

	/**
	 * Override for createSprite method
	 */
	@Override
	public Sprite createSprite() {
		staringAnimation = this.createAnimation("HeroStaring.png", 6);
		walkingAnimation = this.createAnimation("HeroWalking.png", 6);
		jumpingAnimation = this.createAnimation("HeroJumping.png", 7);
		landingAnimation = this.createAnimation("HeroLanding.png", 3);
		firingAnimation = this.createAnimation("HeroFiring.png", 8);
		return new Sprite(this.staringAnimation.getKeyFrame(0));
	}
	
    

    /**
     * Override for update method
     */
    @Override
    public void update(EntityModel model) {
    	super.update(model);
    	if(state != ((HeroModel)model).getState()) {
    		state= ((HeroModel)model).getState();
    		stateTime = 0;
    	}
    	
    }
    
    /**
     * Override for draw method
     */
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        switch(this.state) {
        case WALK:
        	sprite.setRegion(walkingAnimation.getKeyFrame(stateTime,true));
        	break;
        case JUMP:
        	sprite.setRegion(jumpingAnimation.getKeyFrame(stateTime,false));
        	break;
        case FALL:
        	sprite.setRegion(jumpingAnimation.getKeyFrames()[jumpingAnimation.getKeyFrames().length-1]);
        	break;
        case ATTACK:
        	sprite.setRegion(firingAnimation.getKeyFrame(stateTime,false));
        	break;
		default:
			sprite.setRegion(staringAnimation.getKeyFrame(stateTime, true));
			break;
        }
        sprite.draw(batch);
    }

    /**
     * Flips the animations
     */
	@Override
	protected void flipAnimations() {
		super.flipAnimation(walkingAnimation);
		super.flipAnimation(staringAnimation);
		super.flipAnimation(jumpingAnimation);
		super.flipAnimation(landingAnimation);
		super.flipAnimation(firingAnimation);
	}

}
