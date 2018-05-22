package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import game.controller.GameController;
import game.main.TowerOfDoom;
import game.model.GameModel;
import game.model.HeroModel;

public class GameView extends ScreenAdapter{
	
    private static final boolean DEBUG_PHYSICS = true;
    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugCamera;
	
	private OrthographicCamera cam;
	TowerOfDoom game;
	LevelView level;
	HeroModel hero;
	HeroView hv;
	GUI gui;
	
	public GameView(){
		game = TowerOfDoom.getInstance();
		this.loadAssets();
		level = new LevelView1();
		hero = GameModel.getInstance().getHero();
		gui = new GUI(hero);
		hv = new HeroView();
		this.createCam();
	}
	
	public void drawEnteties() {
		
	}
	
	@Override
    public void render(float delta) {
		SpriteBatch batch = TowerOfDoom.getInstance().getBatch();
		hero.move();
		hero.update();
		GameController.getInstance().update(delta);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.updateCam(batch);
		batch.begin();
		level.draw(batch);
		hv.update(hero);
		hv.draw(batch);
		batch.end();
		gui.update(delta);
		this.debugPhysics();
	}
	
	@Override
	public void dispose() {
		this.level.dispose();
	}
	
	public void loadAssets() {
		this.game.getAssetManager().load("HeroStaring.png", Texture.class);
		this.game.getAssetManager().load( "HeroSprite.png" , Texture.class);
		this.game.getAssetManager().load( "Stage2.png" , Texture.class);
		this.game.getAssetManager().load( "HeroWalking.png" , Texture.class);
		this.game.getAssetManager().load( "HeroJumping.png" , Texture.class);
		this.game.getAssetManager().load( "HeroLanding.png" , Texture.class);
		this.game.getAssetManager().load( "HeroFiring.png" , Texture.class);
		this.game.getAssetManager().load( "level1.png" , Texture.class);
		this.game.getAssetManager().load( "ButtonLeft.png" , Texture.class);
		this.game.getAssetManager().load( "ButtonRight.png" , Texture.class);
		this.game.getAssetManager().load( "ButtonUp.png" , Texture.class);
		this.game.getAssetManager().load( "ButtonFire.png" , Texture.class);
		this.game.getAssetManager().finishLoading();
		
	}
	
	private void updateCam(SpriteBatch batch) {
        cam.position.set(GameModel.getInstance().getHero().getX(), GameModel.getInstance().getHero().getY()+20, 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
	}
	
	private void createCam() {
		cam = new OrthographicCamera(512,512);
		
        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = cam.combined.cpy();
            debugCamera.scl(1);
        }
	}
	
	private void debugPhysics() {
        if (DEBUG_PHYSICS) {
            debugCamera = cam.combined.cpy();
            debugCamera.scl(1);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }
	}
		
}
