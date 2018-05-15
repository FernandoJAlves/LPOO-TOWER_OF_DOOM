package game.controller;

import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

public class HeroBody extends CharacterBody{

	public HeroBody(World world, EntityModel model) {
		super(world, model);
		float density = 50f;
		float height = 1.60f;
		float width = 0.75f;
		
		float radius = 24;
		
		super.createFixture(body,radius, width, height, density);
	}

}
