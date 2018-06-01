package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.model.EntityModel;
import game.model.HeroModel;
import game.model.SlugModel;

public class SlugView extends CharacterView{

	private Animation<TextureRegion> walkingAnimation;
	private Animation<TextureRegion> atackingAnimation;
	private Animation<TextureRegion> damageAnimation;
	
	private float stateTime = 0;
	SlugModel.slugState state;
	
	public SlugView() {
		super();
		FRAME_TIME = 0.18f;
		this.sprite = this.createSprite();
	}
	
	@Override
	public void update(EntityModel model) {
    	super.update(model);
    	if(state != ((SlugModel)model).getState()) {
    		state= ((SlugModel)model).getState();
    		stateTime = 0;
    	}
		
	}

	@Override
	public Sprite createSprite() {
		walkingAnimation = this.createAnimation("SlugWalking.png", 5);
		atackingAnimation = this.createAnimation("SlugAttacking.png", 5);
		damageAnimation = this.createAnimation("SlugDying.png", 3);
		return new Sprite(this.walkingAnimation.getKeyFrame(0));
	}

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        switch(this.state) {
        case WALK:
        	sprite.setRegion(walkingAnimation.getKeyFrame(stateTime,true));
        	break;
        case ATTACK:
        	sprite.setRegion(atackingAnimation.getKeyFrame(stateTime,false));
        	break;
        case DAMAGE:
        	sprite.setRegion(damageAnimation.getKeyFrame(stateTime,false));
		default:
			sprite.setRegion(walkingAnimation.getKeyFrame(stateTime, true));
			break;
        }
        sprite.draw(batch);
    }
	
	@Override
	protected void flipAnimations() {
		super.flipAnimation(walkingAnimation);
		super.flipAnimation(atackingAnimation);
		super.flipAnimation(damageAnimation);
	}

}
