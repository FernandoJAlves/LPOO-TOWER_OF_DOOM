package game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Pool;

import game.model.EntityModel.directionState;


public class GameModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5144259653074020691L;
	private static GameModel instance;
	private HeroModel hero;
	private HeroModel netHero;
	private ArrayList<CharacterModel> enemies;
	private List<PlasmaModel> plasmaballs;
    Pool<PlasmaModel> plasmaballPool;
	private LevelModel level;
	
	private GameModel() {
		this.setPool();
		level = initLevel(1);
		enemies = level.getChars();
		hero = new HeroModel(488,720);
		hero.setPosition(level.getHeroPosition());
		plasmaballs = new ArrayList<PlasmaModel>();
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
	
	public LevelModel initLevel(int levelNum) {
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
	
	public void setMultiplayer() {
		this.netHero = new HeroModel(500,720);
		netHero.setPosition(level.getHero2Position());
	}

    public List<PlasmaModel> getPlasmaballs() {
        return plasmaballs;
    }
    
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
    
    public void remove(EntityModel model) {
        if (model instanceof PlasmaModel) {
            plasmaballs.remove(model);
            ((PlasmaModel) model).setState(0);
            ((PlasmaModel) model).resetExplosion();
           	plasmaballPool.free((PlasmaModel) model);
        }
    }
    
    private void setPool() {
    	this.plasmaballPool = new Pool<PlasmaModel>() {
            @Override
            protected PlasmaModel newObject() {
                return new PlasmaModel(0,0);
            }
        };
    }
    
    public static void delete() {
    	instance = null;
    }
	
	
	
}
