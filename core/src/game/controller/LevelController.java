package game.controller;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class LevelController {
	private ArrayList<Body> bodies;
	private final World world;
	
	LevelController(){
		this.world = new World(new Vector2(0, -60), true);
		bodies = new ArrayList<Body>();
		this.generateBodies();
	}
	
	protected Body createBody(int x, int y, int width, int length) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(x, y);
		Body body = world.createBody(bodyDef);
	    PolygonShape polygon = new PolygonShape();
	    polygon.setAsBox(width, length);
	    FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = polygon;
	    fixtureDef.density = 20;
	    body.createFixture(fixtureDef);
	    polygon.dispose();
	    body.setUserData("Floor");
		return body;
	}
	
	protected abstract void generateBodies();
	
	protected void addBody(int x, int y, int width, int height) {
		int newWidth = width/2;
		int newHeight = height/2;
		this.bodies.add(createBody(x+newWidth,y+newHeight,newWidth,newHeight));
	}
	
	public void step(float timeStep, int velocityIterations, int positionIterations) {
		this.world.step(timeStep, velocityIterations, positionIterations);
	}
	
	public World getWorld() {
		return this.world;
	}
}
