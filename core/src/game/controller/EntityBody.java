package game.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

public abstract class EntityBody {
	//TODO
	private static float PIXEL_TO_METER = 1f;

	final Body body;
	
	EntityBody(World world, EntityModel model){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(model.getX(), model.getY());
		
		body = world.createBody(bodyDef);
		body.setUserData(model);
	}
	
	final void createFixture(Body body, float[] vertexes, float width, float height, float density) {
        // Transform pixels into meters, center and invert the y-coordinate
        for (int i = 0; i < vertexes.length; i++) {
            if (i % 2 == 0) vertexes[i] -= width / 2;   // center the vertex x-coordinate
            if (i % 2 != 0) vertexes[i] -= height / 2;  // center the vertex y-coordinate

            if (i % 2 != 0) vertexes[i] *= -1;          // invert the y-coordinate

            vertexes[i] *= PIXEL_TO_METER;              // scale from pixel to meter
        }

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertexes);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.density = density;

        body.createFixture(fixtureDef);

        polygon.dispose();
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
	
	public Object getUserData() {
		return body.getUserData();
	}
	
	
}
