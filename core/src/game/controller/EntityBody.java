package game.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

/**
 * 
 * EntityBody.java - An abstract class for the bodies of entities
 *
 */
public abstract class EntityBody {


	final Body body;
	
	final static short CATEGORY_HERO = 0x0001; 
	final static short CATEGORY_SLUG = 0x0002; 
	final static short CATEGORY_FLOOR = 0x0004; 
	final static short CATEGORY_PLASMA = 0x0008; 
	
	/**
	 * Constructs the EntityBody
	 * 
	 * @param world - The world we want to insert a body into
	 * @param model - The model we want to insert
	 */
	EntityBody(World world, EntityModel model){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(model.getX(), model.getY());
		bodyDef.fixedRotation = true;
		body = world.createBody(bodyDef);
		body.setUserData(model);
	}
	
	/**
	 * Creates the body fixture
	 * 
	 * @param body - The body we want to add the fixture to
	 * @param radius - The radius of the body (in case it is a sphere)
	 * @param width - The width of the body
	 * @param height - The height of the body
	 * @param density - The density of the body
	 * @param restitution - The restitution of the body
	 * @param category - The category of the body
	 * @param mask - The mask of the body
	 */
	final void createFixture(Body body,float radius, float width, float height, float density, float restitution, short category, short mask) {
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = createShape(radius,radius);

        fixtureDef.density = density;
        fixtureDef.friction = 0;
        fixtureDef.restitution = restitution;
        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;
        body.createFixture(fixtureDef);

        //polygon.dispose();
    }

	/**
	 * Gets the X value of the body
	 * 
	 * @return The X value
	 */
	public float getX() {
		return body.getPosition().x;
	}
	
	/**
	 * Gets the Y value of the body
	 * 
	 * @return The Y value
	 */
	public float getY() {
		return body.getPosition().y;	
	}

	/**
	 * Gets the Angle value of the body
	 * 
	 * @return The Angle value
	 */
	public float getAngle() {
		return this.body.getAngle();
	}
	
	/**
	 * Applies a tranformation to the body
	 * 
	 * @param x - The new X value
	 * @param y - The new Y value
	 * @param angle - The new Angle value
	 */
	public void setTransform(float x, float y, float angle) {
		body.setTransform(x, y, angle);
	}

	/**
	 * Apllies a force to the centre of the body
	 * 
	 * @param forceX - Force intensity in X
	 * @param forceY - Force intensity in Y
	 * @param awake - Boolean to determine if the body is alive
	 */
	public void applyForceToCenter(float forceX, float forceY, boolean awake) {
		body.applyForceToCenter(forceX, forceY, awake);
	}
	
	/**
	 * Sets the body linear Velocity
	 * 
	 * @param vX - Velocity in X
	 * @param vY - Velocity in Y
	 */
	public void setLinearVelocity(float vX, float vY) {
		body.setLinearVelocity(vX, vY);
	}
	
	/**
	 * Gets the user data
	 * 
	 * @return The Object
	 */
	public Object getUserData() {
		return body.getUserData();
	}
	
	/**
	 * Abstract method to create shapes
	 * 
	 * @param x - X value
	 * @param y - Y value
	 * @return The Shape created
	 */
	public abstract Shape createShape(float x, float y);
	
	/**
	 * Gets the body
	 * 
	 * @return The body
	 */
	public Body getBody() {
		return this.body;
	}
		
	
}
