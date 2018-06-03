package game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;
import game.model.HeroModel;
import game.model.SlugModel;

/**
 * 
 * SlugBody.java - The Slug body class
 *
 */
public class SlugBody extends CharacterBody{
	RayCastCallback callback;
	private SlugModel slug;
	
	/**
	 * Constructor for SlugBody
	 * 
	 * @param world - The world we want to insert the body into
	 * @param model - The model we want to get the values from
	 */
	public SlugBody(World world, EntityModel model) {
		super(world, model);
		this.slug = (SlugModel) model;
		this.initRayCast();
		
		float density = 50f;
		float height = 1.20f;
		float width = 2.0f;
		float restitution = 0f;
		short category = EntityBody.CATEGORY_SLUG;
		short mask = EntityBody.CATEGORY_HERO | EntityBody.CATEGORY_FLOOR | EntityBody.CATEGORY_PLASMA;
		
		super.createFixture(body, 0, width, height, density, restitution,category,mask);
	}
	
	/**
	 * Override of the CreateShape function
	 * 
	 */
	@Override
	public Shape createShape(float x, float y) {
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(30, 23);
        return polygon;
	}
	
	/**
	 * Initializes the rayCast
	 * 
	 */
	private void initRayCast() {
		callback = new RayCastCallback() {
			
			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
				
				if(fixture.getBody().getUserData() instanceof HeroModel) {
					((SlugModel)body.getUserData()).setAlert(true);
					//TODO
					if(Math.abs(body.getPosition().x - point.x) < slug.getAttackRange()) {
						//System.out.println("hi nandinho");
						slug.move('f');
					}
					if(slug.getDirection() == EntityModel.directionState.RIGHT) {
						slug.move('d');
					}
					else {
						slug.move('a');
					}
				}
				else {
					((SlugModel)body.getUserData()).setAlert(false);
				}
				return 0;
			}

		};
	}
	
	/**
	 * Raycast logic
	 * 
	 */
	public void rayCast(World world ) {
		float x = body.getPosition().x;
		float y = body.getPosition().y;
		if(slug.getDirection() == EntityModel.directionState.RIGHT) {
			x += slug.getViewRange();
		}
		else {
			x -= slug.getViewRange();
		}
		Vector2 point2 = new Vector2(x,y);
		world.rayCast(callback, body.getPosition(), point2);
	}


}
