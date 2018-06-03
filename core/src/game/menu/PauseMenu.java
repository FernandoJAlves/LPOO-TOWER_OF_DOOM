package game.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.main.TowerOfDoom;
import game.view.GameView;

public class PauseMenu extends MainMenu{
	private GameView game;
	private ImageButton backButton;
	/**
	 * Constructs The PauseMenu object
	 * @param The actual instance of the GameView
	 */
	public PauseMenu(GameView g) {
		super();
		game = g;
		this.createBackground();
		this.setInitButtons();
	}
	
	private void setInitButtons() {
		this.setBackButton();
		this.setReturnButton();
		this.setCloseButton();
		this.showButtons();
	}
	
	private void setBackButton() {
		this.backButton = this.createButton("ReturnToGame_button.png");
		backButton.setPosition(this.width/2-this.backButton.getWidth()/2, 3*this.height/4);
		this.backButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        		returnToGame();
                return true;
                }
		});
		this.menuStage.addActor(this.backButton);
	}
	
	private void setReturnButton() {
		this.returnButton = this.createButton("ReturnToMenu_button.png");
		returnButton.setPosition(this.width/2-this.returnButton.getWidth()/2, this.height/2);
		this.returnButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	MainMenu.getInstance().returnToMenu();
                return true;
                }
		});
		this.menuStage.addActor(this.returnButton);
	}
	
	private void showButtons() {
		this.backButton.setVisible(true);
		this.returnButton.setVisible(true);
		this.closeButton.setVisible(true);
	}
	
	
	private void returnToGame() {
		TowerOfDoom.getInstance().setScreen(game);
		game.resume();
	}
}
