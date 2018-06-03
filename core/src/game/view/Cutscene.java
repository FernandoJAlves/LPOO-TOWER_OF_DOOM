package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;

import game.main.TowerOfDoom;
import game.menu.MainMenu;


public class Cutscene extends ScreenAdapter {
	private Stage stage;
	public Cutscene(String path) {
		Texture img = TowerOfDoom.getInstance().getAssetManager().get(path);
		this.stage = this.setStage();
		Image back = new Image(img);
		back.setPosition(0, 0,Align.left);
		stage.addActor(back);
		Gdx.input.setInputProcessor(stage);
		
		
	}
	
	
	@Override
    public void render(float delta) {
        this.stage.act(delta); //Perform ui logic
        this.stage.draw(); //Draw the ui
	}
	
	public Stage setStage() {
		Stage st = new Stage(new FillViewport(640,480));
		switch(Gdx.app.getType()) {
		   case Desktop:
				st.addListener(new InputListener() {
					public boolean keyDown(InputEvent event, int keycode) {
					fadeOut();
					return true;
			        }
				});
		   default:
			   st.addListener(new ClickListener() {
		            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		            	fadeOut();
		                return true;
		            }
			   });
			   break;
			   
		}

		return st;
	}
	
	private void fadeOut() {
		this.stage.addAction(Actions.sequence(Actions.fadeOut(5f),Actions.hide()));
	}
}
