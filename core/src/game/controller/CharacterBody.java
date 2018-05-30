package game.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

public abstract class CharacterBody extends EntityBody{

	public CharacterBody(World world, EntityModel model) {
		super(world, model);
		// TODO Auto-generated constructor stub
	}
	

	public abstract void rayCast(World world );

}
