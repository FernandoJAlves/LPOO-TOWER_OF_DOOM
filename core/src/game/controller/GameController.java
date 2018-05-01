package game.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class GameController implements ContactListener{
	private HeroBody hero;
	private List<CharacterBody> enemies;
	private static GameController instance;
	
	private GameController() {
		
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

}
