package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.model.EntityModel;
import game.model.PlasmaModel;

/**
 * 
 * PlasmaView.java - Class that handles the Plasmas' View
 *
 */
public class PlasmaView extends EntityView{
	private Animation<TextureRegion> bouncingAnimation;
	private Animation<TextureRegion> explodingAnimation;
	private float stateTime = 0;
	private int state = 0; //0 = jumping, 1 = exploding
	
	/**
	 * Constructor for PlasmaView
	 */
	public PlasmaView() {
		super();
		FRAME_TIME = 0.15f;
		this.sprite = this.createSprite();
	}
	
	/**
	 * Override for createSprite method
	 */
	@Override
	public Sprite createSprite() {
		bouncingAnimation = this.createAnimation("Plasmaball.png", 2);
		explodingAnimation = this.createAnimation("Plasmaball_Explosion.png", 2);
		return new Sprite(this.bouncingAnimation.getKeyFrame(0));
	}
	
	/**
	 * Override for update method
	 */
    @Override
    public void update(EntityModel model) {
    	super.update(model);
    	this.stateTime = ((PlasmaModel)model).getStateTime();
        stateTime += Gdx.graphics.getDeltaTime();
        ((PlasmaModel)model).setStateTime(this.stateTime);
    	if(state != ((PlasmaModel)model).getState()) {
    		state = ((PlasmaModel)model).getState();
    		stateTime  = 0;
    	}
    	
    }
    
	/**
	 * Override for draw method
	 */
    @Override
    public void draw(SpriteBatch batch) {
        switch(this.state) {
        case 0:
        	sprite.setRegion(bouncingAnimation.getKeyFrame(stateTime,true));
        	break;
        case 1:
        	sprite.setRegion(explodingAnimation.getKeyFrame(stateTime,true));
        	break;

		default:
			sprite.setRegion(bouncingAnimation.getKeyFrame(stateTime, true));
			break;
        }
        sprite.draw(batch);
    }
    
    
	
	/**
	 * Override for flipAnimations method
	 */
	@Override
	protected void flipAnimations() {
		// TODO Auto-generated method stub
		
	}

}
