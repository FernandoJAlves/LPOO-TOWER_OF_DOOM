package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.model.EntityModel;
import game.model.PlasmaModel;

public class PlasmaView extends EntityView{
	private Animation<TextureRegion> bouncingAnimation;
	private Animation<TextureRegion> explodingAnimation;
	private int stateTime = 0;
	private int state = 0; //0 = jumping, 1 = exploding
	
	
	public PlasmaView() {
		super();
		FRAME_TIME = 0.15f;
		this.sprite = this.createSprite();
	}
	
	@Override
	public Sprite createSprite() {
		bouncingAnimation = this.createAnimation("Plasmaball.png", 2);
		explodingAnimation = this.createAnimation("Plasmaball_Explosion.png", 2);
		return new Sprite(this.bouncingAnimation.getKeyFrame(0));
	}
	
    @Override
    public void update(EntityModel model) {
    	super.update(model);
    	if(state != ((PlasmaModel)model).getState()) {
    		state = ((PlasmaModel)model).getState();
    		stateTime  = 0;
    	}
    	
    }
    
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
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
    
    
	

	@Override
	protected void flipAnimations() {
		// TODO Auto-generated method stub
		
	}

}
