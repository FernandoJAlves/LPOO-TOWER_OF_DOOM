package game.dataStream;

import game.model.GameModel;
import game.model.HeroModel;

public class Player1Socket extends PlayerSocket{
	public HeroModel getHeroModel() {
		if(this.available() != 0) {
			return ((HeroModel) this.readObject());
		}
		return null;
	}
	
	public void sendGameModel(GameModel game) {
		this.sendObjects(game);
	}
}
