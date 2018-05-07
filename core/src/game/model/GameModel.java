package game.model;

import java.util.ArrayList;

public class GameModel {
	private static GameModel instance;
	private HeroModel hero;
	private ArrayList<CharacterModel> enemies;
	
	private GameModel() {
		enemies = new ArrayList<CharacterModel>();
		hero = new HeroModel(64,140);
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
	
	public ArrayList<CharacterModel> getEnemies(){
		return this.enemies;
	}

}
