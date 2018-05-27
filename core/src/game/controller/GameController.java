package game.controller;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import game.model.CharacterModel;
import game.model.EntityModel;
import game.model.EntityModel.ModelType;
import game.model.GameModel;

public class GameController implements ContactListener{
	private HeroBody hero;
	private ArrayList<CharacterBody> enemies;
	private static GameController instance;
	private World world;
	private LevelController level;
	
	private GameController() {
		level = this.levelSelection(1);
		this.world = level.getWorld();
		hero = new HeroBody(world,GameModel.getInstance().getHero());
		this.enemies = new ArrayList<CharacterBody>();
		for(CharacterModel enemy: GameModel.getInstance().getEnemies()) {
			if(enemy.getModelType() == ModelType.SLUG)
				this.enemies.add( new SlugBody(world,enemy));
			/*
			else if(enemy.getModelType() == ModelType.GUARD)
				//add Guard
			else if(enemy.getModelType() == ModelType.OGRE)
				//add Ogre
			*/
				
		}
		world.setContactListener(this);
	}

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
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
	
	public HeroBody getHero() {
		return this.hero;
	}
	
	public ArrayList<CharacterBody> getEnemies(){
		return this.enemies;
	}
	
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	public void update(float delta) {
		hero.setLinearVelocity(((EntityModel)hero.getUserData()).getSpeed(), ((EntityModel)hero.getUserData()).getYSpeed());
		this.rayCastController();
		world.step(delta, 6, 2);
		
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
        	
        	if(body.getUserData() instanceof EntityModel) {
        	EntityModel model = ((EntityModel) body.getUserData());
            model.setPosition(body.getPosition().x, body.getPosition().y);
            model.setYSpeed(body.getLinearVelocity().y);
        	}
        }
	}
	
	public World getWorld() {
		return this.level.getWorld();
	}
	
	private LevelController levelSelection(int num) {
		switch(num) {
		case 1:
			return new Level1Controller();
		default:
			return new Level1Controller();
		}
	}
	
	private void rayCastController() {
		for(CharacterBody enemy: this.enemies) {
			enemy.rayCast(world);
		}
	}

}
