package game.controller;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class LevelController {
	private ArrayList<Body> bodies;
	private World world;
	
	LevelController(World world){
		this.world = world;
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
		return body;
	}
	
	private void generateBodies() {
		this.addBody(300,336,216,48);
	}
	
	private void addBody(int x, int y, int width, int height) {
		this.bodies.add(createBody(x,y,width/2,height/2));
	}
	
	public void step(float timeStep, int velocityIterations, int positionIterations) {
		this.world.step(timeStep, velocityIterations, positionIterations);
	}
}
