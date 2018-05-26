package game.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class SlugModel extends CharacterModel{

	public SlugModel(int x, int y) {
		super(x, y);
	}

	@Override
	public void move() {
		Rectangle viewRange;
		Rectangle attackRange;
		Circle circ = new Circle(GameModel.getInstance().getHero().getX(),GameModel.getInstance().getHero().getY(),24);
		int viewWidth = 3;
		int attackWidth = 3;
		if(this.dir == EntityModel.directionState.RIGHT) {
			viewRange = new Rectangle(this.x,this.y,viewWidth,3);
			attackRange = new Rectangle(this.x,this.y,attackWidth,3);
		}
		else {
			viewRange = new Rectangle(this.x-viewWidth,this.y,viewWidth,3);
			attackRange = new Rectangle(this.x-attackWidth,this.y,attackWidth,3);
		}
		if(Intersector.overlaps(circ, viewRange)) {
			if(Intersector.overlaps(circ, attackRange)) {
				
			}
		}
		
	}

	@Override
	public ModelType getModelType() {
		// TODO Auto-generated method stub
		return null;
	}

}
