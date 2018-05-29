package game.dataStream;

import java.io.Serializable;
import java.util.ArrayList;

import game.model.CharacterModel;
import game.model.HeroModel;

public class GamePacket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2376459776728070052L;
	public HeroModel hero;
	public HeroModel netHero;
	public ArrayList<CharacterModel> enemies;

	public GamePacket(HeroModel h,HeroModel n,ArrayList<CharacterModel> e){
		this.hero = h;
		this.netHero = n;
		this.enemies = e;
	}
}
