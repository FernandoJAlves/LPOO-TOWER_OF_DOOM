package game.controller;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

/**
 * 
 * HeroBody.java - The class for the Hero Body
 *
 */
public class HeroBody extends CharacterBody{

	/**
	 * Constructor for the Hero body
	 * 
	 * @param world - The world you want to insert the Hero in
	 * @param model - The model you want to obtain the values from
	 */
	public HeroBody(World world, EntityModel model) {
		super(world, model);
		float density = 50f;
		float height = 1.60f;
		float width = 0.75f;
		float restitution = 0f;
		short category = EntityBody.CATEGORY_HERO | EntityBody.CATEGORY_PLASMA;
		short mask = EntityBody.CATEGORY_SLUG | EntityBody.CATEGORY_FLOOR;
		
		float radius = 24;
		
		super.createFixture(body,radius, width, height, density, restitution,category,mask);
	}

	@Override
	public void rayCast(World world) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Override of the CreateShape function
	 * 
	 */
	@Override
	public Shape createShape(float x, float y) {
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(18, 23);
        return polygon;
	}

}
