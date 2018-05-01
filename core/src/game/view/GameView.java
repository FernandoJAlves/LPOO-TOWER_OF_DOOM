package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.main.TowerOfDoom;
import game.model.HeroModel;

public class GameView extends ScreenAdapter{
	private OrthographicCamera cam;
	TowerOfDoom game;
	Texture img;
	HeroModel hero;
	HeroView hv;
	
	public GameView(){
		img = new Texture(Gdx.files.internal("Stage2.png"));
		cam = new OrthographicCamera(256,256);
		cam.translate(128, 128);
		hero = new HeroModel(64,108);
		hv = new HeroView();
		game = TowerOfDoom.getInstance();
	}
	
	public void drawEnteties() {
		
	}
	
	@Override
    public void render(float delta) {
		SpriteBatch batch = TowerOfDoom.getInstance().getBatch();
		hero.move();
		hero.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		hv.update(hero);
		hv.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose() {
		this.img.dispose();
	}
	
}
