package game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.model.HeroModel;

public class TowerOfDoom extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	public static OrthographicCamera cam;
	HeroModel hero;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("Stage2.png"));
		cam = new OrthographicCamera(256,256);
		cam.translate(128, 128);
		hero = new HeroModel("HeroSprite.png",64,64);
	}

	@Override
	public void render () {
		hero.move();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		hero.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
