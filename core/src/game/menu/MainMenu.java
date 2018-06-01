package game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import game.controller.GameController;
import game.main.TowerOfDoom;
import game.model.GameModel;
import game.view.GameView;
import game.view.Player2View;

public class MainMenu extends ScreenAdapter{
	private OrthographicCamera cam;
	private Texture back;
	private float width;
	private float height;
	private static MainMenu instance;
	private final Stage menuStage;
	private TowerOfDoom game;
	private ImageButton singleButton;
	private ImageButton multiButton;
	private ImageButton findButton;
	private ImageButton hostButton;
	private ImageButton returnButton;
	private ImageButton closeButton;
	
	
	protected MainMenu() {
		game = TowerOfDoom.getInstance();
		
		
		menuStage = new Stage(new ExtendViewport(640,480));
		this.cam = new OrthographicCamera(640,480);
		
		this.width = this.menuStage.getWidth();
		this.height = this.menuStage.getHeight();
		
		Gdx.input.setInputProcessor(menuStage);
		
	}
	
	
	public static MainMenu getInstance() {
		if(instance == null) {
			instance = new MainMenu();
			instance.loadAssets();
			instance.createBackground();
			instance.setMenuButtons();
			instance.playMusic();
		}
		return instance;
	}
	
	
	@Override
    public void render(float delta) {
		SpriteBatch batch = TowerOfDoom.getInstance().getBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.updateCam(batch);
		batch.begin();
		batch.draw(this.back, 0, 0);
		batch.end();
        this.menuStage.act(delta); //Perform ui logic
        this.menuStage.draw(); //Draw the ui
	}
	
	
	

	public void loadAssets() {
		this.game.getAssetManager().load("Sound/stage.mp3", Sound.class);
		this.game.getAssetManager().load("Sound/menu.mp3", Sound.class);
		this.game.getAssetManager().load("Host_button.png", Texture.class);
		this.game.getAssetManager().load( "LeaveGame_button.png" , Texture.class);
		this.game.getAssetManager().load( "Multiplayer_button.png" , Texture.class);
		this.game.getAssetManager().load( "ReturnToGame_button.png" , Texture.class);
		this.game.getAssetManager().load( "Singleplayer_button.png" , Texture.class);
		this.game.getAssetManager().load( "CloseApp_button.png" , Texture.class);
		this.game.getAssetManager().load( "Find_button.png" , Texture.class);
		this.game.getAssetManager().load( "menu_background.png" , Texture.class);
		this.game.getAssetManager().load( "ButtonLeft.png" , Texture.class);
		this.game.getAssetManager().load( "ButtonRight.png" , Texture.class);
		this.game.getAssetManager().load( "ButtonUp.png" , Texture.class);
		this.game.getAssetManager().load( "ButtonFire.png" , Texture.class);
		this.game.getAssetManager().finishLoading();
		
	}
	
	private void setMenuButtons() {
		this.setSingleButton();
		this.setMultiButton();
		this.setFindButton();
		this.setHostButton();
		this.setCloseButton();
		this.setReturnButton();
		this.setMainButtons(true);
	}
	
	private void setMainButtons(boolean vis) {
		this.singleButton.setVisible(vis);
		this.multiButton.setVisible(vis);
		this.closeButton.setVisible(vis);
	}
	
	private void setMultiButtons(boolean vis) {
		this.hostButton.setVisible(vis);
		this.findButton.setVisible(vis);
		this.returnButton.setVisible(vis);
	}
	
	private void setSingleButton() {
		this.singleButton = this.createButton("Singleplayer_button.png");
		singleButton.setPosition(this.width/2-this.singleButton.getWidth()/2, 3*this.height/4);
		this.singleButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	stopMusic();
            	game.setScreen(new GameView(false));
                return true;
                }
		});
		this.menuStage.addActor(this.singleButton);
	}
	
	private void setMultiButton() {
		this.multiButton = this.createButton("Multiplayer_button.png");
		multiButton.setPosition(this.width/2-this.multiButton.getWidth()/2, 2*this.height/4);
		this.multiButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	setMainButtons(false);
            	setMultiButtons(true);
            	
                return true;
                }
		});
		this.menuStage.addActor(this.multiButton);
	}
	
	private void setFindButton() {
		this.findButton = this.createButton("Find_button.png");
		findButton.setPosition(this.width/2-this.singleButton.getWidth()/2, 3*this.height/4);
		this.findButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	stopMusic();
            	game.setScreen(new Player2View());
                return true;
                }
		});
		this.menuStage.addActor(this.findButton);
	}
	
	
	private void setHostButton() {
		this.hostButton = this.createButton("Host_button.png");
		hostButton.setPosition(this.width/2-this.hostButton.getWidth()/2, 2*this.height/4);
		this.hostButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	stopMusic();
            	game.setScreen(new GameView(true));
                return true;
                }
		});
		this.menuStage.addActor(this.hostButton);
		
		
	}
	
	private void setCloseButton() {
		this.closeButton = this.createButton("CloseApp_button.png");
		closeButton.setPosition(this.width/2-this.closeButton.getWidth()/2, this.height/4);
		this.closeButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	Gdx.app.exit();
                return true;
                }
		});
		this.menuStage.addActor(this.closeButton);
	}
	
	private void setReturnButton() {
		this.returnButton = this.createButton("CloseApp_button.png");
		returnButton.setPosition(this.width/2-this.returnButton.getWidth()/2, this.height/4);
		this.returnButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	setMultiButtons(false);
            	setMainButtons(true);
                return true;
                }
		});
		this.menuStage.addActor(this.returnButton);
	}
	
	private ImageButton createButton(String path) {
		Texture thrustTexture = TowerOfDoom.getInstance().getAssetManager().get(path);
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(thrustTexture));
		ImageButton button = new ImageButton(drawable);
		button.setVisible(false);
		return button;
	}
	
	public void createBackground() {
		this.back = TowerOfDoom.getInstance().getAssetManager().get("menu_background.png");
	}
	
	@Override
	public void dispose() {
		this.back.dispose();
		//this.menuStage.dispose();
	}
	
	
	public void returnToMenu() {
		GameModel.delete();
		GameController.delete();
		TowerOfDoom.getInstance().setScreen(MainMenu.getInstance());
		this.setMainButtons(true);
		this.setMultiButtons(false);
		Gdx.input.setInputProcessor(menuStage);
		this.playMusic();
	}
	
	private void playMusic() {
		Sound music = game.getAssetManager().get("Sound/menu.mp3");
		music.play();
	}
	
	private void stopMusic() {
		Sound music = game.getAssetManager().get("Sound/menu.mp3");
		music.stop();
	}
	
	private void updateCam(SpriteBatch batch) {
        cam.position.set(this.cam.viewportWidth,this.cam.viewportHeight/2, 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
	}
	
	
	
	

}
