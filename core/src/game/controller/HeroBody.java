package game.controller;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

public class HeroBody extends CharacterBody{

	public HeroBody(World world, EntityModel model) {
		super(world, model);
		float density = 50f;
		float height = 1.60f;
		float width = 0.75f;
		float restitution = 0f;
		
		float radius = 24;
		
		super.createFixture(body,radius, width, height, density, restitution);
	}

	@Override
	public void rayCast(World world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape createShape(float x, float y) {
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(x, y);
        return polygon;
	}

}
