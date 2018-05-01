package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class HeroView extends CharacterView{
	
	public HeroView() {
		super();
		this.sprite.setTexture(new Texture(Gdx.files.internal("HeroSprite.png")));
	}

}
