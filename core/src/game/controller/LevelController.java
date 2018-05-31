package game.controller;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.menu.MainMenu;

public abstract class LevelController {
	private ArrayList<Body> bodies;
	private final World world;
	
	LevelController(){
		this.world = new World(new Vector2(0, -60), true);
		bodies = new ArrayList<Body>();
		this.generateBodies();
	}
	
	protected Body createBody(int x, int y, int width, int length, String data) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(x, y);
		Body body = world.createBody(bodyDef);
	    PolygonShape polygon = new PolygonShape();
	    polygon.setAsBox(width, length);
	    FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = polygon;
	    fixtureDef.density = 20;
	    fixtureDef.restitution = 0f;
	    body.createFixture(fixtureDef);
	    polygon.dispose();
	    body.setUserData(data);
		return body;
	}
	
	protected abstract void generateBodies();
	
	protected void addBody(int x, int y, int width, int height, String data) {
		int newWidth = width/2;
		int newHeight = height/2;
		this.bodies.add(createBody(x+newWidth,y+newHeight,newWidth,newHeight,data));
	}
	
	protected void addBody_m(int x, int y, int width, int height, String data) {
		x *= 24;
		y *= 24;
		width *= 24;
		height *= 24;
		
		int newWidth = width/2;
		int newHeight = height/2;
		this.bodies.add(createBody(x+newWidth,y+newHeight,newWidth,newHeight,data));
	}
	
	protected void addFloor(int x, int y, int width, int height) {
		this.addBody_m(x, y, width, height, "floor");
	}
	
	protected void addHole(int x, int y, int width, int height) {
		this.addBody_m(x, y, width, height, "hole");
	}
	
	protected void addDoor(int x, int y, int width, int height) {
		this.addBody_m(x, y, width, height, "door");
	}
	
	public void step(float timeStep, int velocityIterations, int positionIterations) {
		this.world.step(timeStep, velocityIterations, positionIterations);
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public void nextLevel() {
		MainMenu.getInstance().returnToMenu();
	}
}
