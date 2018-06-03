package game.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import game.controller.GameController;
import game.dataStream.InputPacket;
import game.dataStream.Player1Socket;
import game.dataStream.PlayerSocket;
import game.main.TowerOfDoom;
import game.menu.MainMenu;
import game.menu.PauseMenu;
import game.model.CharacterModel;
import game.model.EntityModel;
import game.model.GameModel;
import game.model.HeroModel;
import game.model.PlasmaModel;

/**
 * 
 * GameView.java - Class that handles the GameView
 *
 */
public class GameView extends ScreenAdapter{
	
	private boolean multiplayer;
	private PlayerSocket socket;
	
    private static final boolean DEBUG_PHYSICS = true;
    private static final int SCREEN_WIDTH = 400;
    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugCamera;
	
	private OrthographicCamera cam;
	private TowerOfDoom game;
	private LevelView level;
	private HeroModel hero;
	private HeroModel netHero;
	private HeroView hv;
	private HeroView nv;
	
	private GUI gui;
	private HUD hud;
	
	/**
	 * Constructor for GameView
	 * @param mult - True if multiplayer, false otherwise
	 */
	public GameView(boolean mult){
		this.multiplayer = mult;
		setSockets();
		game = TowerOfDoom.getInstance();
		this.loadAssets();
		level = new LevelView1();

		this.setHero();
		gui = new GUI();
		hud = new HUD(hero, netHero);
		
		hv = new HeroView();
		nv = new HeroView();
		this.createCam();
		
		this.waitingForPlayer();
		this.playMusic();
	}
	
	/**
	 * Override of the render method
	 */
	@Override
    public void render(float delta) {
		GameController.getInstance().removeFlagged();
		this.handleInput();
		this.updateNetworkModels();
		this.updateLogic(delta);
		this.updateDraw();
		this.hud.update(delta,this.hero,this.netHero);
		gui.update(delta);
		this.debugPhysics();
	}
	
	/**
	 * Override of the dispose method
	 */
	@Override
	public void dispose() {
		this.level.dispose();
	}
	
	/**
	 * Loads the assets
	 */
	public void loadAssets() {
		this.game.getAssetManager().load("HeroStaring.png", Texture.class);
		this.game.getAssetManager().load( "HeroSprite.png" , Texture.class);
		this.game.getAssetManager().load( "Stage2.png" , Texture.class);
		this.game.getAssetManager().load( "HeroWalking.png" , Texture.class);
		this.game.getAssetManager().load( "HeroJumping.png" , Texture.class);
		this.game.getAssetManager().load( "HeroLanding.png" , Texture.class);
		this.game.getAssetManager().load( "HeroFiring.png" , Texture.class);
		this.game.getAssetManager().load( "level1.png" , Texture.class);
		this.game.getAssetManager().load( "Plasmaball.png" , Texture.class);
		this.game.getAssetManager().load( "Plasmaball_Explosion.png" , Texture.class);
		this.game.getAssetManager().load( "Sound/attackSound.mp3" , Sound.class);
		this.game.getAssetManager().load("Sound/stage.mp3",Sound.class);
		this.game.getAssetManager().load( "SlugWalking.png" , Texture.class);
		this.game.getAssetManager().load( "SlugAttacking.png" , Texture.class);
		this.game.getAssetManager().load( "SlugDying.png" , Texture.class);
		this.game.getAssetManager().finishLoading();
		
	}
	
	/**
	 * Updates the camera
	 * @param batch - Batch used to set the projection matrix
	 */
	private void updateCam(SpriteBatch batch) {
		float x = GameModel.getInstance().getHero().getX();
		float y = GameModel.getInstance().getHero().getY();
		
		if(x > GameModel.getInstance().getLevel().getXLimit()) {
			x = GameModel.getInstance().getLevel().getXLimit();
		}
		
		if(y > GameModel.getInstance().getLevel().getYLimit()) {
			y = GameModel.getInstance().getLevel().getYLimit();
		}
        cam.position.set(x, y+50, 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
	}
	
	/**
	 * Creates a camera
	 */
	private void createCam() {
		cam = new OrthographicCamera(SCREEN_WIDTH,SCREEN_WIDTH * ((float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getBackBufferWidth()));
		
        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = cam.combined.cpy();
            debugCamera.scl(1);
        }
	}
	
	/**
	 * Activate debug physics
	 */
	private void debugPhysics() {
        if (DEBUG_PHYSICS) {
            debugCamera = cam.combined.cpy();
            debugCamera.scl(1);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }
	}
	
	/**
	 * Handles the input
	 */
	public void handleInput() {
		
		if(gui.keyPressed('e')) {
			this.pause();
		}
		   
		if(gui.keyPressed('w')) {
			hero.move('w');
		}
		
		if(gui.keyPressed('f')) {
			hero.move('f');
		}
		
		if(gui.keyPressed('a')) {
			hero.move('a');
		}
		
		else if(gui.keyPressed('d')) {
			hero.move('d');
		}
		else {
			hero.move('n');
		}
		
	}
	
	/**
	 * Handles the net hero input
	 * @param h - InputPacket from net hero
	 */
	public void handleNetInput(InputPacket h) {
		if(h.w) {
			netHero.move('w');
		}
		
		if(h.f) {
			netHero.move('f');
		}
		
		if(h.a) {
			netHero.move('a');
		}
		
		else if(h.d) {
			netHero.move('d');
		}
		else {
			netHero.move('n');
		}
	}
	
	/**
	 * Sets the sockets
	 */
	public void setSockets() {
		if(this.multiplayer) {
			GameModel.getInstance().setMultiplayer();
			this.socket = new Player1Socket();
		}
	}
	
	/**
	 * Sets the Heros
	 */
	public void setHero() {
		hero = GameModel.getInstance().getHero();
		netHero = GameModel.getInstance().getNetHero();
	}
	
	/**
	 * Updates the newwork models
	 */
	public void updateNetworkModels() {
		if(this.multiplayer) {
			InputPacket h = ((Player1Socket)socket).getHeroPacket();
			if(h != null) {
				this.gui.disableMsg();
				this.handleNetInput(h);
				
			}
		}
	}
	
	/**
	 * Updates the draw
	 */
	private void updateDraw() {
		SpriteBatch batch = TowerOfDoom.getInstance().getBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.updateCam(batch);
		batch.begin();
		level.draw(batch);
		drawEntities(batch);
		batch.end();
	}

	/**
	 * Draw entities
	 * @param batch - The sprite batch
	 */
	private void drawEntities(SpriteBatch batch) {
		hv.update(hero);
		hv.draw(batch);
		if(this.multiplayer) {
			nv.update(netHero);
			nv.draw(batch);
		}
		
		ArrayList<CharacterModel> enemies = GameModel.getInstance().getEnemies();
		for(CharacterModel enemy : enemies) {
			if(enemy.getModelType() == EntityModel.ModelType.SLUG) {
				EntityView view = ViewFactory.makeView(enemy);
				view.update(enemy);
				view.draw(batch);
			}
		}
		
		List<PlasmaModel> plasmaBalls = GameModel.getInstance().getPlasmaballs();
		for(PlasmaModel plasmaB : plasmaBalls) {
			EntityView view = ViewFactory.makeView(plasmaB);
			view.update(plasmaB);
			view.draw(batch);
		}
		
	}
	
	/**
	 * Updates the game logic
	 * @param delta - Time since last update
	 */
	private void updateLogic(float delta) {
		GameModel.getInstance().update(delta);
		GameController.getInstance().update(delta);
	}
	
	/**
	 * Waits for player 2;
	 */
	public void waitingForPlayer() {
		if(this.multiplayer) {
			this.gui.message1(this.socket.getAddress());
		}
	}
	
	/**
	 * Pauses the game
	 */
	public void pause() {
		if(!this.multiplayer) {
		this.gui.resetEscapeButton();
		this.stopMusic();
		TowerOfDoom.getInstance().setScreen(new PauseMenu(this));
		}
	}
	
	/**
	 * Resumes the game
	 */
	public void resume() {
		TowerOfDoom.getInstance().setScreen(this);
		this.playMusic();
		Gdx.input.setInputProcessor(gui);
	}
	
	/**
	 * Terminates the games and returns to menu
	 */
	public void terminate() {
		if(this.multiplayer) {
		this.socket.close();
		}
		this.stopMusic();
		MainMenu.getInstance().returnToMenu();
		
	}
	
	/**
	 * Plays music
	 */
	private void playMusic() {
		Sound music = game.getAssetManager().get("Sound/stage.mp3");
		music.play();
	}
	
	/**
	 * Stops music
	 */
	private void stopMusic() {
		Sound music = game.getAssetManager().get("Sound/stage.mp3");
		music.stop();
	}
	
	/**
	 * Determines if in multiplayer
	 * @return True if multiplayer, false otherwise
	 */
	public boolean isMultiplayer() {
		return this.multiplayer;
	}
	
}
