package game.dataStream;

import game.model.GameModel;
import game.model.HeroModel;

public class Player2Socket extends PlayerSocket{
	
	public GameModel getGameModel() {
		if(this.available() != 0) {
			return ((GameModel) this.readObject());
		}
		return null;
	}
	
	public void sendHero(HeroModel hero) {
		this.sendObjects(hero);
	}
}
