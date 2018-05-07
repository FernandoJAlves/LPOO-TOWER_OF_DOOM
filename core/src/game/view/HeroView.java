package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.main.TowerOfDoom;
import game.model.EntityModel;
import game.model.HeroModel;

public class HeroView extends CharacterView{
	private Animation<TextureRegion> staringAnimation;
	private Animation<TextureRegion> walkingAnimation;
	private Animation<TextureRegion> jumpingAnimation;
	private Animation<TextureRegion> landingAnimation;
	private float FRAME_TIME = 0.15f;
	private float stateTime = 0;
	HeroModel.charState state;
	
	public HeroView() {
		super();
		this.sprite = this.createSprite();
	}

	@Override
	public Sprite createSprite() {
		staringAnimation = this.createAnimation("HeroStaring.png", 6);
		walkingAnimation = this.createAnimation("HeroWalking.png", 6);
		jumpingAnimation = this.createAnimation("HeroJumping.png", 7);
		landingAnimation = this.createAnimation("HeroLanding.png", 3);
		return new Sprite(this.staringAnimation.getKeyFrame(0));
	}
	
    
    private Animation<TextureRegion>createAnimation(String name, int divisions) {
        Texture thrustTexture = TowerOfDoom.getInstance().getAssetManager().get(name);
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / divisions, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[divisions];
        System.arraycopy(thrustRegion[0], 0, frames, 0, divisions);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }
    
    @Override
    public void update(EntityModel model) {
    	super.update(model);
    	if(state != ((HeroModel)model).getState()) {
    		state= ((HeroModel)model).getState();
    		stateTime = 0;
    	}
    	
    }
    
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
		default:
			sprite.setRegion(staringAnimation.getKeyFrame(stateTime, true));
			break;
        }
        sprite.draw(batch);
    }

	@Override
	protected void flipAnimations() {
		super.flipAnimation(walkingAnimation);
		super.flipAnimation(staringAnimation);
		super.flipAnimation(jumpingAnimation);
		super.flipAnimation(landingAnimation);
	}

}
