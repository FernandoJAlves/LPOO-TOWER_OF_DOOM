package game.dataStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import game.model.GameModel;
import game.model.HeroModel;

public class Player1Socket extends PlayerSocket{
	private ServerSocket ServerSocket;
	public Player1Socket() {
		super();
		try {
			this.ServerSocket = new ServerSocket(1234);
			this.ServerSocket.setSoTimeout(20*1000);
			this.socket = this.ServerSocket.accept();
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());
			this.inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public HeroModel getHeroModel() {
		if(this.available() > 0) {
			return ((HeroModel) this.readObject());
		}
		return null;
	}
	
	public void sendGameModel(GameModel game) {
		this.sendObjects(game);
	}
}
