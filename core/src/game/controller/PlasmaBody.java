package game.controller;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

public class PlasmaBody extends EntityBody{

	PlasmaBody(World world, EntityModel model) {
		super(world, model);
		float density = 50f;
		float height = 0.40f;
		float width = 0.40f;
		float restitution = 1f;
		
		float radius = 10;
		
		super.createFixture(body,radius, width, height, density, restitution);
	}

	@Override
	public Shape createShape(float x, float y) {
        CircleShape polygon = new CircleShape();
        polygon.setRadius(x);
        return polygon;
	}

}
