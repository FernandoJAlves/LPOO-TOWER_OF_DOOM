package game.dataStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import game.model.GameModel;
import game.model.HeroModel;

public class Player2Socket extends PlayerSocket{
	public Player2Socket() {
		super();
		try {
			this.socket = new Socket("localhost", 4445);
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());
			this.inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public GameModel getGameModel() {
		if(this.available() > 0) {
			return ((GameModel) this.readObject());
		}
		return null;
	}
	
	public void sendHero(HeroModel hero) {
		this.sendObjects(hero);
	}
}
