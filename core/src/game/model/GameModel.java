package game.model;

import java.io.Serializable;
import java.util.ArrayList;

import game.dataStream.GamePacket;


public class GameModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5144259653074020691L;
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
	
	public void setInstance(GamePacket game) {
		this.hero.copy(game.hero);
		this.netHero.copy(game.netHero);
		for(int i = 0; i < this.enemies.size();i++) {
			if(i >= game.enemies.size()) {
				this.enemies.remove(i);
				continue;
			}
			if(this.enemies.get(i) instanceof SlugModel) {
				((SlugModel)this.enemies.get(i)).copy((SlugModel)(game.enemies.get(i)));
			}
		}
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
	
	public void update(float delta){
		this.hero.update(delta);
		if(this.netHero != null) {
			this.netHero.update(delta);
		}
	}
	
	public void setMultiplayer() {
		this.netHero = new HeroModel(500,720);
		netHero.setPosition(level.getHero2Position());
	}
	
	public GamePacket getPacket() {
		return new GamePacket(this.hero,this.netHero,this.enemies);
	}

}
