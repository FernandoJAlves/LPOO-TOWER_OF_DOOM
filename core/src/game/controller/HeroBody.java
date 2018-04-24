package game.controller;

import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

public class HeroBody extends CharacterBody{

	public HeroBody(World world, EntityModel model) {
		super(world, model);
		float density = 1f;
		float height = 1.60f;
		float width = 0.75f;
		
		super.createFixture(body, new float[] {0,0, 0,0, 0,0, 0,0},width, height, density);
	}

}
