package game.menu;

import game.main.TowerOfDoom;
import game.view.GameView;

public class PauseMenu extends MainMenu{
	private GameView game;
	public PauseMenu(GameView g) {
		super();
		game = g;
		this.ReturnToGame();
	}
	
	
	private void ReturnToGame() {
		TowerOfDoom.getInstance().setScreen(this.game);
	}
}
