package game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;

import game.model.CharacterModel;
import game.model.EntityModel;
import game.model.EntityModel.ModelType;
import game.model.GameModel;

public class GameController implements ContactListener{
	private HeroBody hero;
	private List<CharacterBody> enemies;
	private static GameController instance;
	private final World world;
	private LevelController level;
	
	private GameController() {
		world = new World(new Vector2(0, -30), true);
		level = new LevelController(world);
		hero = new HeroBody(world,GameModel.getInstance().getHero());
		for(CharacterModel enemy: GameModel.getInstance().getEnemies()) {
			if(enemy.getModelType() == ModelType.GUARD)
				new GuardBody(world,enemy);
			else if(enemy.getModelType() == ModelType.OGRE)
				new OgreBody(world,enemy);
			else if(enemy.getModelType() == ModelType.SLUG)
				new SlugBody(world,enemy);
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
	
	public List<CharacterBody> getEnemies(){
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
		world.step(delta, 6, 2);
		
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
        	EntityModel model = ((EntityModel) body.getUserData());
        	if(model instanceof EntityModel) {
            model.setPosition(body.getPosition().x, body.getPosition().y);
            model.setYSpeed(body.getLinearVelocity().y);
        	}
        }
	}
	

}
