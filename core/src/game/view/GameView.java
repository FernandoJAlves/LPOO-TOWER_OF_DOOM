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
import game.dataStream.GamePacket;
import game.dataStream.Player1Socket;
import game.dataStream.Player2Socket;
import game.dataStream.PlayerSocket;
import game.main.TowerOfDoom;
import game.model.GameModel;
import game.model.HeroModel;

public class GameView extends ScreenAdapter{
	
	private boolean multiplayer;
	private boolean player2;
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
	
	public GameView(boolean mult, boolean p2){
		this.multiplayer = mult;
		this.player2 = p2;
		setSockets();
		
		this.setPlayerTwo();

		game = TowerOfDoom.getInstance();
		this.loadAssets();
		level = new LevelView1();

		this.setHero();
		gui = new GUI();
		
		hv = new HeroView();
		nv = new HeroView();
		this.createCam();
		
		this.waitingForPlayer();
	}
	
	
	@Override
    public void render(float delta) {
		
		this.handleInput();
		this.updateNetworkModels();
		this.updateLogic(delta);

		this.updateNetworkController();
		this.updateDraw();
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
		this.game.getAssetManager().load( "Plasmaball.png" , Texture.class);
		this.game.getAssetManager().load( "Plasmaball_Explosion.png" , Texture.class);
		this.game.getAssetManager().finishLoading();
		
	}
	
	private void updateCam(SpriteBatch batch) {
        cam.position.set(GameModel.getInstance().getHero().getX(), GameModel.getInstance().getHero().getY()+50, 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
	}
	
	private void createCam() {
		cam = new OrthographicCamera(SCREEN_WIDTH,SCREEN_WIDTH * ((float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getBackBufferWidth()));
		
        if (DEBUG_PHYSICS && !this.player2) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = cam.combined.cpy();
            debugCamera.scl(1);
        }
	}
	
	private void debugPhysics() {
        if (DEBUG_PHYSICS && !this.player2) {
            debugCamera = cam.combined.cpy();
            debugCamera.scl(1);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }
	}
	
	public void handleInput() {
		   
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
	
	public void setSockets() {
		if(this.multiplayer) {
			GameModel.getInstance().setMultiplayer();
			if(this.player2) {
				this.socket = new Player2Socket();
			}
			else {
				this.socket = new Player1Socket();
			}
		}
	}
	
	public void setHero() {
		if(this.player2) {
			hero = GameModel.getInstance().getNetHero();
			netHero = GameModel.getInstance().getHero();
		}
		else {
			hero = GameModel.getInstance().getHero();
			netHero = GameModel.getInstance().getNetHero();
		}
		
	}
	
	public void updateNetworkModels() {
		if(this.multiplayer) {
			if(this.player2) {
				System.out.println("Speed: " + GameModel.getInstance().getNetHero().getYSpeed());
				((Player2Socket)socket).sendHero(GameModel.getInstance().getNetHero());
			}
			else {
				HeroModel h = ((Player1Socket)socket).getHeroModel();
				if(h != null) {
					this.gui.disableMsg();
					GameModel.getInstance().getNetHero().copyToNet(h);
					
				}
			}
		}
	}
	
	public void updateNetworkController() {
		if(this.multiplayer) {
			if(this.player2) {
				GamePacket game = ((Player2Socket)socket).getGamePacket();
				if(game != null) {
					GameModel.getInstance().setInstance(game);
				}
			}
			else {
				((Player1Socket)socket).sendGamePacket(GameModel.getInstance().getPacket());
			}
		}
	}
	
	private void updateDraw() {
		SpriteBatch batch = TowerOfDoom.getInstance().getBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.updateCam(batch);
		batch.begin();
		level.draw(batch);
		hv.update(hero);
		hv.draw(batch);
		if(this.multiplayer) {
			nv.update(netHero);
			nv.draw(batch);
		}
		batch.end();
	}
	
	private void setPlayerTwo() {
		if(this.player2) {
			GamePacket game = ((Player2Socket)socket).getGamePacket();
			if(game != null) {
				GameModel.getInstance().setInstance(game);
			}
		}
	}
	
	private void updateLogic(float delta) {
		if(!this.player2) {
		GameModel.getInstance().update(delta);
		GameController.getInstance().update(delta);
		}
	}
	
	public void waitingForPlayer() {
		if(this.multiplayer) {
			if(this.player2) {
				this.gui.message2(null);
			}
			else {
				this.gui.message1(this.socket.getAddress());
				
			}
		}
	}
	

	
}
