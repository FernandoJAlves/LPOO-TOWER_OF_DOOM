package game.model;

import java.util.ArrayList;


public class GameModel {
	private static GameModel instance;
	private HeroModel hero;
	private HeroModel netHero;
	private ArrayList<CharacterModel> enemies;
	private LevelModel level;
	
	private GameModel() {
		level = initLevel(1);
		enemies = level.getChars();
		hero = new HeroModel(488,720);
		hero.setPosition(level.getHeroPosition());
	}
	
	public static GameModel getInstance() {
		if(instance == null) {
			instance = new GameModel();
		}
		return instance;
	}
	
	public static void setInstance(GameModel game) {
		GameModel aux = GameModel.getInstance();
		aux.hero = game.getHero();
		aux.netHero = game.getNetHero();
		aux.enemies = game.getEnemies();
		aux.level = game.getLevel();
	}
	
	public HeroModel getHero() {
		return this.hero;
	}
	
	public ArrayList<CharacterModel> getEnemies(){
		return this.enemies;
	}
	
	private LevelModel initLevel(int levelNum) {
		switch(levelNum) {
		case 1:
			return new LevelModel1();
		default:
			return new LevelModel1();
		}
		
	}
	
	public LevelModel getLevel() {
		return this.level;
	}

	public HeroModel getNetHero() {
		return netHero;
	}

	public void setNetHero(HeroModel netHero) {
		this.netHero = netHero;
	}
	

}
