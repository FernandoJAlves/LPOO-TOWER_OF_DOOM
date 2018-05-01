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
	private TextureRegion HeroRegion;
	private float FRAME_TIME = 0.15f;
	private float stateTime = 0;
	HeroModel.charState state;
	
	public HeroView() {
		super();
		this.sprite = this.createSprite();
	}

	@Override
	public Sprite createSprite() {
		staringAnimation = this.createStaringAnimation();
		HeroRegion= this.createHeroRegion();
		return new Sprite(HeroRegion);
	}
	
    private TextureRegion createHeroRegion() {
        Texture noThrustTexture = TowerOfDoom.getInstance().getAssetManager().get("HeroSprite.png");
        return new TextureRegion(noThrustTexture, noThrustTexture.getWidth(), noThrustTexture.getHeight());
    }
	
    private Animation<TextureRegion> createStaringAnimation() {
        Texture thrustTexture = TowerOfDoom.getInstance().getAssetManager().get("HeroStaring.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / 6, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[6];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 6);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }
    
    @Override
    public void update(EntityModel model) {
    	super.update(model);
    	state= ((HeroModel)model).getState();
    }
    
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (this.state==HeroModel.charState.STARE)
            sprite.setRegion(staringAnimation.getKeyFrame(stateTime, true));
        else
            sprite.setRegion(HeroRegion);

        sprite.draw(batch);
    }

	@Override
	protected void flipAnimations() {
		for(TextureRegion tex: staringAnimation.getKeyFrames()) {
			tex.flip(true, false);
		}
		
	}

}
