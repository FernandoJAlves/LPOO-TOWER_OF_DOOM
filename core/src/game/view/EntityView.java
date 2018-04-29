package game.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.model.EntityModel;

public abstract class EntityView {
    protected Sprite sprite;


    public EntityView() {
    	
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }


    public abstract void update(EntityModel model);
}
