package game.controller;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

/**
 * 
 * PlasmaBody.java - The plasma body class
 *
 */
public class PlasmaBody extends EntityBody{

	/**
	 * Constructor for PlasmaBody
	 * 
	 * @param world - The world we want to inser the body in
	 * @param model - The model we want to get the values from
	 */
	PlasmaBody(World world, EntityModel model) {
		super(world, model);
		float density = 50f;
		float height = 0.40f;
		float width = 0.40f;
		float restitution = 1f;
		short category = EntityBody.CATEGORY_PLASMA | EntityBody.CATEGORY_HERO;
		short mask = EntityBody.CATEGORY_SLUG | EntityBody.CATEGORY_FLOOR;
		
		float radius = 10;
		
		super.createFixture(body,radius, width, height, density, restitution,category,mask);
	}

	/**
	 * Override of the CreateShape function
	 */
	@Override
	public Shape createShape(float x, float y) {
        CircleShape polygon = new CircleShape();
        polygon.setRadius(x);
        return polygon;
	}

}
