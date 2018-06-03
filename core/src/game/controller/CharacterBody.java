package game.controller;

import com.badlogic.gdx.physics.box2d.World;

import game.model.EntityModel;

/**
 * 
 * CharacterBody - The abstract class of Characters
 *
 */
public abstract class CharacterBody extends EntityBody{

	/**
	 * Constructor of Character Body
	 * 
	 * @param world - The world you want insert the body in
	 * @param model -  The model you want to insert
	 */
	public CharacterBody(World world, EntityModel model) {
		super(world, model);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Absctract function for rayCast
	 * 
	 * @param world - The world you want to analize
	 */
	public abstract void rayCast(World world );

}
