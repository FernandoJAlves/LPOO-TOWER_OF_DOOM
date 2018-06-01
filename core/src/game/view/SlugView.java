package game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.model.EntityModel;
import game.model.SlugModel;

public class SlugView extends CharacterView{

	private Animation<TextureRegion> walkingAnimation;
	private Animation<TextureRegion> atackingAnimation;
	private Animation<TextureRegion> damageAnimation;
	
	private float stateTime = 0;
	SlugModel.slugState state;
	
	public SlugView() {
		super();
		FRAME_TIME = 0.15f;
		this.sprite = this.createSprite();
	}
	
	@Override
	public void update(EntityModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite createSprite() {
		walkingAnimation = this.createAnimation("SlugWalking.png", 5);
		atackingAnimation = this.createAnimation("SlugAttacking.png", 5);
		damageAnimation = this.createAnimation("SlugDying.png", 3);
		return new Sprite(this.walkingAnimation.getKeyFrame(0));
	}

	@Override
	protected void flipAnimations() {
		// TODO Auto-generated method stub
		
	}

}
