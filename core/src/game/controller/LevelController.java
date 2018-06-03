package game.controller;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.menu.MainMenu;

/**
 * 
 * LevelController.java - Absctract class for controlling a levels logic
 *
 */
public abstract class LevelController {
	private ArrayList<Body> bodies;
	private final World world;
	
	/**
	 * Constructor for LevelController
	 * 
	 */
	LevelController(){
		this.world = new World(new Vector2(0, -60), true);
		bodies = new ArrayList<Body>();
		this.generateBodies();
	}
	
	/**
	 * Creates a body with the given parameters
	 * 
	 * @param x - The X Value
	 * @param y - The Y value
	 * @param width - The width
	 * @param length - The lenght
	 * @param data - The data
	 * @return The body created
	 */
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
	    fixtureDef.filter.categoryBits = EntityBody.CATEGORY_FLOOR;
	    fixtureDef.filter.maskBits = -1;
	    body.createFixture(fixtureDef);
	    polygon.dispose();
	    body.setUserData(data);
		return body;
	}
	
	/**
	 * Abstract function for generating Bodies
	 * 
	 */
	protected abstract void generateBodies();
	
	/**
	 * Adds a body in the specified coordinates/size
	 * @param x - X coord
	 * @param y - Y coord
	 * @param width - Width of the box
	 * @param height - Height of the box
	 * @param data - Addtional data
	 */
	protected void addBody(int x, int y, int width, int height, String data) {
		int newWidth = width/2;
		int newHeight = height/2;
		this.bodies.add(createBody(x+newWidth,y+newHeight,newWidth,newHeight,data));
	}
	
	/**
	 * Adds a body in the specified coordinates/size scaled by 24
	 * @param x - X coord/24
	 * @param y - Y coord/24
	 * @param width - Width of the box/24
	 * @param height - Height of the box/24
	 * @param data - Addtional data
	 */
	protected void addBody_m(int x, int y, int width, int height, String data) {
		x *= 24;
		y *= 24;
		width *= 24;
		height *= 24;
		
		int newWidth = width/2;
		int newHeight = height/2;
		this.bodies.add(createBody(x+newWidth,y+newHeight,newWidth,newHeight,data));
	}
	
	/**
	 * Adds a floor to the level
	 * @param x - X coord/24
	 * @param y - Y coord/24
	 * @param width - Width of the box/24
	 * @param height - Height of the box/24
	 */
	protected void addFloor(int x, int y, int width, int height) {
		this.addBody_m(x, y, width, height, "floor");
	}
	
	/**
	 * Adds a hole to the level
	 * @param x - X coord/24
	 * @param y - Y coord/24
	 * @param width - Width of the box/24
	 * @param height - Height of the box/24
	 */
	protected void addHole(int x, int y, int width, int height) {
		this.addBody_m(x, y, width, height, "hole");
	}
	
	/**
	 * Adds a door to the level
	 * @param x - X coord/24
	 * @param y - Y coord/24
	 * @param width - Width of the box/24
	 * @param height - Height of the box/24
	 */
	protected void addDoor(int x, int y, int width, int height) {
		this.addBody_m(x, y, width, height, "door");
	}
	
	/**
	 * Run a step in the world
	 * 
	 * @param timeStep - Time of the step
	 * @param velocityIterations - N of velocity iterations
	 * @param positionIterations - N of position iterations
	 */
	public void step(float timeStep, int velocityIterations, int positionIterations) {
		this.world.step(timeStep, velocityIterations, positionIterations);
	}
	
	/**
	 * Gets the world
	 * 
	 * @return The world
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Returns to menu
	 */
	public void nextLevel() {
		MainMenu.getInstance().returnToMenu();
	}
}
