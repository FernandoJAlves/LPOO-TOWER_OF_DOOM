package game.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

public abstract class EntityBody {


	final Body body;
	
	EntityBody(World world, EntityModel model){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(model.getX(), model.getY());
		bodyDef.fixedRotation = true;
		
		body = world.createBody(bodyDef);
		body.setUserData(model);
	}
	
	final void createFixture(Body body,float radius, float width, float height, float density, float restitution) {
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = createShape(radius,radius);

        fixtureDef.density = density;
        fixtureDef.friction = 0;
        fixtureDef.restitution = restitution;
        body.createFixture(fixtureDef);

        //polygon.dispose();
    }

	
	public float getX() {
		return body.getPosition().x;
	}
	
	public float getY() {
		return body.getPosition().y;	
	}
	
	public float getAngle() {
		return this.body.getAngle();
	}
	
	public void setTransform(float x, float y, float angle) {
		body.setTransform(x, y, angle);
	}

	public void applyForceToCenter(float forceX, float forceY, boolean awake) {
		body.applyForceToCenter(forceX, forceY, awake);
	}
	
	public void setLinearVelocity(float vX, float vY) {
		body.setLinearVelocity(vX, vY);
	}
	
	public Object getUserData() {
		return body.getUserData();
	}
	
	public abstract Shape createShape(float x, float y);
		
	
}
