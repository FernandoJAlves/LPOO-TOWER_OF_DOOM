package game.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.model.EntityModel;

public abstract class EntityView {
    protected Sprite sprite;


    public EntityView() {
		this.sprite = new Sprite();
    }

    public void draw(SpriteBatch batch) {
        //sprite.draw(batch);
    	batch.draw(this.sprite.getTexture(), this.sprite.getX(), this.sprite.getY());
    }


    public void update(EntityModel model) {
    	sprite.setCenter(model.getX(), model.getY());
    	sprite.setRotation(0);
    }
}
