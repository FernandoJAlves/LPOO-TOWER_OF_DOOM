package game.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.dataStream.Player2Socket;
import game.dataStream.PlayerSocket;
import game.main.TowerOfDoom;
import game.menu.MainMenu;
import game.model.HeroModel;

public class Player2View extends ScreenAdapter{
	private PlayerSocket socket;
	private HeroModel hero;
	private GUI gui;
	private TowerOfDoom game;
	private Texture background;
	
	
	public Player2View(){
		this.socket = new Player2Socket();
		game = TowerOfDoom.getInstance();
		this.hero = new HeroModel(0,0);
		gui = new GUI();
		gui.initButtons();
		//this.createCam();
		this.background = game.getAssetManager().get("menu_background.png");
		this.waitingForPlayer1();
	}
	
	@Override
    public void render(float delta) {
		this.handleNetInputs();
		this.sendHero();
		this.updateDraw();
		gui.update(delta);
	}
	
	private void updateDraw() {
		SpriteBatch batch = TowerOfDoom.getInstance().getBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//this.updateCam(batch);
		batch.begin();
		batch.draw(this.background, 0, 0);
		batch.end();
		
	}

	private void sendHero() {
		if(socket.isConnected()) {
			((Player2Socket)this.socket).sendHero(this.hero.createPacket());
		}
		else {
			if(gui.getAddr() != null) {
				this.socket.connect(gui.getAddr());
			}
		}
		
	}

	
	public void waitingForPlayer1() {
		this.gui.message2();
	}
	
	public void handleNetInputs() {
		if(gui.keyPressed('e')) {
			MainMenu.getInstance().returnToMenu();
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
}
