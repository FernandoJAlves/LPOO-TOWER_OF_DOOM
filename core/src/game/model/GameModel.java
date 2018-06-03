package game.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Pool;

import game.model.EntityModel.directionState;

/**
 * 
 * GameModel.java - Class that handles the GameModel logic
 *
 */
public class GameModel {
	private static GameModel instance;
	private HeroModel hero;
	private HeroModel netHero;
	private ArrayList<CharacterModel> enemies;
	private List<PlasmaModel> plasmaballs;
    Pool<PlasmaModel> plasmaballPool;
	private LevelModel level;
	public enum gameState {PLAYING, WIN, LOSS};
	private gameState state;
	
	/**
	 * Constructor for the GameModel
	 */
	private GameModel() {
		this.setPool();
		level = initLevel(1);
		enemies = level.getChars();
		hero = new HeroModel(488,720);
		hero.setPosition(level.getHeroPosition());
		plasmaballs = new ArrayList<PlasmaModel>();
		setState(gameState.PLAYING);
	}
	
	/**
	 * Singleton - Gets the GameModel instance, creates one if null
	 * 
	 * @return The instance
	 */
	public static GameModel getInstance() {
		if(instance == null) {
			instance = new GameModel();
		}
		return instance;
	}
	
	/**
	 * Gets the hero model
	 * 
	 * @return The hero model
	 */
	public HeroModel getHero() {
		return this.hero;
	}
	
	/**
	 * Gets the arraylist of enemies
	 * 
	 * @return The arraylist 
	 */
	public ArrayList<CharacterModel> getEnemies(){
		return this.enemies;
	}
	
	/**
	 * Initiliazes the level
	 * 
	 * @param levelNum - wanted level
	 * @return The LevelModel
	 */
	public LevelModel initLevel(int levelNum) {
		this.state = gameState.PLAYING;
		switch(levelNum) {
		case 1:
			return new LevelModel1();
		default:
			return new LevelModel1();
		}
		
	}
	
	/**
	 * Gets the level model
	 * 
	 * @return The level model
	 */
	public LevelModel getLevel() {
		return this.level;
	}

	/**
	 * Gets the nethero model
	 * 
	 * @return The nethero model
	 */
	public HeroModel getNetHero() {
		return netHero;
	}

	/**
	 * Sets the nethero
	 * 
	 * @param netHero we want to get the values from
	 */
	public void setNetHero(HeroModel netHero) {
		this.netHero = netHero;
	}
	
	/**
	 * Update method for game model
	 * 
	 * @param delta - Time since last update
	 */
	public void update(float delta){
		this.hero.update(delta);
		if(this.netHero != null) {
			this.netHero.update(delta);
		}
		
        for (PlasmaModel plasmaBall : plasmaballs) {
            //System.out.println(plasmaBall.getJumpsLeft());
        	if (plasmaBall.getJumpsLeft() <= 0) {
        		plasmaBall.setState(1);
            	if(plasmaBall.decreaseExplosionTime(delta)) {
            		//System.out.println("hi juan");
            		plasmaBall.setFlaggedForRemoval(true);
            	}
            		
            }   	
        }
        
        for(CharacterModel enemy : enemies) {
        	enemy.update(delta);
        }

		
	}
	
	/**
	 * Sets game to multiplayer
	 */
	public void setMultiplayer() {
		this.netHero = new HeroModel(500,720);
		netHero.setPosition(level.getHero2Position());
	}

	/**
	 * Gets the list of plasmaBalls
	 * 
	 * @return List of plasmaBalls
	 */
    public List<PlasmaModel> getPlasmaballs() {
        return plasmaballs;
    }
    
    /**
     * Create a plasma ball
     * 
     * @param hero - used to know where to create the plasma ball
     * @return The created PlasmaModel 
     */
    public PlasmaModel createPlasmaBall(HeroModel hero) {
        PlasmaModel plasmaBall = plasmaballPool.obtain();
        
        plasmaBall.setFlaggedForRemoval(false);
        
        if(hero.getDirection() == directionState.LEFT) {
        	plasmaBall.setPosition(hero.getX() - 30, hero.getY());
        }
        else {
        	plasmaBall.setPosition(hero.getX() + 30, hero.getY());
        }
        plasmaBall.setJumpsLeft(3);
        plasmaballs.add(plasmaBall);
        return plasmaBall;
    }
    
    /**
     * Removes an entity from the world
     * @param model - Model to be removed
     */
    public void remove(EntityModel model) {
        if (model instanceof PlasmaModel) {
            plasmaballs.remove(model);
            ((PlasmaModel) model).setState(0);
            ((PlasmaModel) model).resetExplosion();
           	plasmaballPool.free((PlasmaModel) model);
        }
        if(model instanceof SlugModel) {
        	enemies.remove(model);
        	
        }
    }
    
    /**
     * Sets the pool of PlasmaModels
     */
    private void setPool() {
    	this.plasmaballPool = new Pool<PlasmaModel>() {
            @Override
            protected PlasmaModel newObject() {
                return new PlasmaModel(0,0);
            }
        };
    }
    
    /**
     * Deletes the instance of GameModel
     */
    public static void delete() {
    	instance = null;
    }
	
    /**
     * Sets gameState to LOSS
     */
	public void setGameOver() {
		setState(gameState.LOSS);
	}
	
    /**
     * Sets gameState to WIN
     */
	public void setGameWon() {
		setState(gameState.WIN);
	}

	/**
	 *  Gets the gameState
	 * @return the gameState
	 */
	public gameState getState() {
		return state;
	}

	/**
	 * Sets the gameState
	 * @param state - the new state
	 */
	public void setState(gameState state) {
		this.state = state;
	}
	
}
