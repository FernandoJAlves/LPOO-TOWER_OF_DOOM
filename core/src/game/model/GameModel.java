package game.model;

import com.badlogic.gdx.scenes.scene2d.ui.List;

public class GameModel {
	private static GameModel instance;
	private HeroModel hero;
	private List<CharacterModel> enemies;
	
	private GameModel() {
		
	}
	
	public static GameModel getInstance() {
		if(instance == null) {
			instance = new GameModel();
		}
		return instance;
	}
	
	public HeroModel getHero() {
		return this.hero;
	}
	
	public List<CharacterModel> getEnemies(){
		return this.enemies;
	}

}
