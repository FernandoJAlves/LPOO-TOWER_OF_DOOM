package game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.main.TowerOfDoom;

public abstract class CharacterView extends EntityView{
	
	protected float FRAME_TIME;
	
	public CharacterView(){
		super();
	}

    protected Animation<TextureRegion>createAnimation(String name, int divisions) {
        Texture thrustTexture = TowerOfDoom.getInstance().getAssetManager().get(name);
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / divisions, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[divisions];
        System.arraycopy(thrustRegion[0], 0, frames, 0, divisions);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }
	
}
