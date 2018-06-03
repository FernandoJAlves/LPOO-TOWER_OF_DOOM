package game.controller;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import game.main.TowerOfDoom;
import game.model.CharacterModel;
import game.model.EntityModel;
import game.model.EntityModel.ModelType;
import game.model.EntityModel.directionState;
import game.model.GameModel;
import game.model.HeroModel;
import game.model.PlasmaModel;
import game.model.SlugModel;

/**
 * 
 * GameController.java - The class responsible for manage the game logic
 *
 */
public class GameController implements ContactListener{
	private boolean multiplayer = false;
	private HeroBody hero;
	private HeroBody netHero;
	private ArrayList<CharacterBody> enemies;
	private static GameController instance;
	private World world;
	private LevelController level;
	private ArrayList<PlasmaBody> activePlasmaBalls;
	
	private static final float PLASMA_X_SPEED = 100f;
	private static final float PLASMA_Y_SPEED = 10f;
	
	/**
	 * Constructor for GameController
	 */
	private GameController() {
		level = this.levelSelection(1);
		this.world = level.getWorld();
		hero = new HeroBody(world,GameModel.getInstance().getHero());
		if(GameModel.getInstance().getNetHero() != null) {
			setNetHero(new HeroBody(world,GameModel.getInstance().getNetHero()));
			this.multiplayer = true;
		}

		
		this.enemies = new ArrayList<CharacterBody>();
		for(CharacterModel enemy: GameModel.getInstance().getEnemies()) {
			if(enemy.getModelType() == ModelType.SLUG)
				this.enemies.add( new SlugBody(world,enemy));		
		}
		activePlasmaBalls = new ArrayList<PlasmaBody>();
		
		world.setContactListener(this);
	}

	/**
	 * Override of the beginContact function in libGDX
	 */
	@Override
	public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        
        if(pairSlugPlasma(bodyA, bodyB)) {
        	if(bodyA.getUserData() instanceof PlasmaModel) {
        		((PlasmaModel)bodyA.getUserData()).setJumpsLeft(0);
        		((SlugModel)bodyB.getUserData()).move('o');
        	}
        	else {
        		((PlasmaModel)bodyB.getUserData()).setJumpsLeft(0);
        		((SlugModel)bodyA.getUserData()).move('o');
        	}
        }
        else if(pairHeroHole(bodyA, bodyB)) {
        	GameModel.getInstance().setGameOver();
        }
        else if(pairHeroDoor(bodyA, bodyB)) {
        	GameModel.getInstance().setGameWon();
        }
        else {
            if (bodyA.getUserData() instanceof PlasmaModel)
                plasmaCollision(bodyA);
            if (bodyB.getUserData() instanceof PlasmaModel)
            	plasmaCollision(bodyB);
        }
        
        
		
	}
	
	/**
	 * Handles the collision of a plasma ball with a non-enemy body
	 * 
	 * @param bodyA - The body of the plasma ball
	 */
	private void plasmaCollision(Body bodyA) {
		((PlasmaModel)bodyA.getUserData()).decreaseJumpsLeft();
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Gets the Hero
	 * 
	 * @return The Hero's body
	 */
	public HeroBody getHero() {
		return this.hero;
	}
	
	/**
	 * Gets the array of enemies
	 * 
	 * @return The array of enemies
	 */
	public ArrayList<CharacterBody> getEnemies(){
		return this.enemies;
	}
	
	/**
	 * Singleton - Gets the GameController, creates one if one does not exists
	 * 
	 * @return The GameController instance
	 */
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	/**
	 * Update function of the game controller
	 * 
	 * @param delta - Time since last update
	 */
	public void update(float delta) {
		hero.setLinearVelocity(((EntityModel)hero.getUserData()).getSpeed(), ((EntityModel)hero.getUserData()).getYSpeed());
		if(this.multiplayer)
			netHero.setLinearVelocity(((EntityModel)netHero.getUserData()).getSpeed(), ((EntityModel)netHero.getUserData()).getYSpeed());
		
		for(CharacterBody enemy : enemies) {
			enemy.setLinearVelocity(((EntityModel)enemy.getUserData()).getSpeed(), ((EntityModel)enemy.getUserData()).getYSpeed());	
			if(enemy instanceof SlugBody) {
				((SlugBody)enemy).rayCast(this.world);
			}
		}
		
		this.rayCastController();
		world.step(delta, 6, 2);
		
		this.updateModel(this.hero.getBody());
		if(this.multiplayer) {
			this.updateModel(this.netHero.getBody());
		}
		
		for(CharacterBody enemy : enemies) {
			this.updateModel(enemy.getBody());
		}
		
		for(PlasmaBody plasmaBall : activePlasmaBalls) {
			if(((PlasmaModel)plasmaBall.getUserData()).getState() == 0)
				this.updateModel(plasmaBall.getBody());
			else {
				plasmaBall.setLinearVelocity(0, 0);
				plasmaBall.applyForceToCenter(0, 0, false);
			}
		}
	}
	
	/**
	 * Gets the World
	 * 
	 * @return The world
	 */
	public World getWorld() {
		return this.level.getWorld();
	}
	
	/**
	 * Selects the level
	 * 
	 * @param num - The wanted level
	 * @return The level controller
	 */
	private LevelController levelSelection(int num) {
		switch(num) {
		case 1:
			return new Level1Controller();
		default:
			return new Level1Controller();
		}
	}
	
	/**
	 * Controls the rayCast for enemies
	 * 
	 */
	private void rayCastController() {
		for(CharacterBody enemy: this.enemies) {
			enemy.rayCast(world);
		}
	}

	/**
	 * Gets the net Hero
	 * 
	 * @return The netHero's body
	 */
	public HeroBody getNetHero() {
		return netHero;
	}

	/**
	 * Sets the netHero to the HeroBody given
	 * 
	 * @param netHero - The body you to set for the netHero
	 */
	public void setNetHero(HeroBody netHero) {
		this.netHero = netHero;
	}
	
	/**
	 * Sets the game to multiplayer
	 * 
	 * @param option - The option you want to set
	 */
	public void setMultiplayer(boolean option) {
		this.multiplayer = option;
	}
	
	/**
	 * Fire logic function
	 * 
	 * @param hero - The hero that is firing
	 */
    public void fire(HeroModel hero) {
    	this.playAttackSound();
        if (hero.getStamina() >= 1) {
            PlasmaModel plasmaBall = GameModel.getInstance().createPlasmaBall(hero);
            PlasmaBody body = new PlasmaBody(world, plasmaBall);
            if(hero.getDirection() == directionState.LEFT) {
            	body.setLinearVelocity(-PLASMA_X_SPEED,PLASMA_Y_SPEED);
            }
            else{
            	body.setLinearVelocity(PLASMA_X_SPEED,PLASMA_Y_SPEED);
            }
            activePlasmaBalls.add(body);
           
            hero.decrementStamina();
        }
    }

    /**
     * Checks if a pair of 2 bodies is a Slug/Plasma Pair
     * 
     * @param bodyA - BodyA to test
     * @param bodyB - BodyB to test
     * @return The value of the comparation
     */
    public boolean pairSlugPlasma(Body bodyA, Body bodyB) {
    	if(bodyA.getUserData() instanceof PlasmaModel && bodyB.getUserData() instanceof SlugModel) {
    		return true;
    	}
    	
    	if(bodyB.getUserData() instanceof PlasmaModel && bodyA.getUserData() instanceof SlugModel) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Checks if a pair of 2 bodies is a Hero/Hole Pair
     * 
     * @param bodyA - BodyA to test
     * @param bodyB - BodyB to test
     * @return The value of the comparation
     */
    public boolean pairHeroHole(Body bodyA, Body bodyB) {
    	if(bodyA.getUserData() instanceof HeroModel && bodyB.getUserData() instanceof String && bodyB.getUserData().equals("hole")) {
    		return true;
    	}
    	
    	if(bodyB.getUserData() instanceof HeroModel && bodyA.getUserData() instanceof String && bodyA.getUserData().equals("hole")) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Checks if a pair of 2 bodies is a Hero/Door Pair
     * 
     * @param bodyA - BodyA to test
     * @param bodyB - BodyB to test
     * @return The value of the comparation
     */
    public boolean pairHeroDoor(Body bodyA, Body bodyB) {
    	if(bodyA.getUserData() instanceof HeroModel && bodyB.getUserData() instanceof String && bodyB.getUserData().equals("door")) {
    		return true;
    	}
    	
    	if(bodyB.getUserData() instanceof HeroModel && bodyA.getUserData() instanceof String && bodyA.getUserData().equals("door")) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Removes flagged bodies from the world
     * 
     */
    public void removeFlagged() {
        for (Iterator<PlasmaBody> body = this.activePlasmaBalls.iterator(); body.hasNext(); ) {
        	PlasmaBody aux = body.next();
                if (((EntityModel)aux.getUserData()).isFlaggedToBeRemoved()) {
                    GameModel.getInstance().remove((EntityModel) aux.getUserData());
                    world.destroyBody(aux.getBody());
                    body.remove();
                    
                }
        }
        
        for (Iterator<CharacterBody> body = this.enemies.iterator(); body.hasNext(); ) {
        	CharacterBody aux = body.next();
        	if(aux instanceof SlugBody) {
                if (((EntityModel)aux.getUserData()).isFlaggedToBeRemoved()) {
                    GameModel.getInstance().remove((EntityModel) aux.getUserData());
                    world.destroyBody(aux.getBody());
                    body.remove();
                }
        	}
        }
        
    }
    
    /**
     * Deletes the instance of the GameController
     * 
     */
    public static void delete() {
    	instance = null;
    }
    
    /**
     * Updates the model
     * 
     * @param body - Body you want to get the values from
     */
    private void updateModel(Body body) {
    	EntityModel model = ((EntityModel) body.getUserData());
        model.setPosition(body.getPosition().x, body.getPosition().y);
        model.setYSpeed(body.getLinearVelocity().y);
    }
    
    /**
     * Plays the attack sound
     * 
     */
    private void playAttackSound() {
    	Sound attack = TowerOfDoom.getInstance().getAssetManager().get( "Sound/attackSound.mp3" , Sound.class);
    	attack.play();
    }
}
