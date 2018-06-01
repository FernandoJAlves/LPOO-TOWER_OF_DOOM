package game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;
import game.model.HeroModel;
import game.model.SlugModel;

public class SlugBody extends CharacterBody{
	RayCastCallback callback;
	private SlugModel slug;
	public SlugBody(World world, EntityModel model) {
		super(world, model);
		this.slug = (SlugModel) model;
		this.initRayCast();
	}
	
	
	private void initRayCast() {
		callback = new RayCastCallback() {

			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
				if(fixture.getUserData() instanceof HeroModel) {
					//TODO
					if(Math.abs(body.getPosition().y - point.y) < slug.getAttackRange()) {
						//slug.move('f')
					}
					if(slug.getDirection() == EntityModel.directionState.RIGHT) {
						//slug.move('a')
					}
					else {
						//slug.move('d')
					}
				}
				return 0;
			}

		};
	}
	
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


	@Override
	public Shape createShape(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}
}
