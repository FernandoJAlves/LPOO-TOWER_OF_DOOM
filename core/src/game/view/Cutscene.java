package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;

import game.main.TowerOfDoom;
import game.menu.MainMenu;


/**
 * @brief Cutscene - class responsible for cutscenes
 * @param path- the path to the texture used in the cutscene
 */
public class Cutscene extends ScreenAdapter {
	private Stage stage;
	private Image back;
	public Cutscene(String path) {
		Texture img = TowerOfDoom.getInstance().getAssetManager().get(path);
		this.stage = this.setStage();
		this.back = new Image(img);
		back.setSize(stage.getWidth(),stage.getHeight());
	    back.setOrigin(stage.getWidth()/2,stage.getHeight()/2);
	    back.setColor(Color.BLACK);
		stage.addActor(back);
		back.addAction(Actions.color(Color.WHITE,2)); 
		Gdx.input.setInputProcessor(stage);
		
		
	}
	
	
	@Override
	/**
	 * @brief Renders the game custcene
	 * 
	 * @param delta the elapsed time
	 */
    public void render(float delta) {
		Batch batch = this.stage.getBatch();
		batch.setColor(batch.getColor().r,batch.getColor().g,batch.getColor().b,0);
        this.stage.act(delta); //Perform ui logic
        this.stage.draw(); //Draw the ui
	}
	/**
	 * @brief Sets the stage
	 * @return the new stage
	 */
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
	/**
	 * @brief [Activates fadeout action
	 */
	private void fadeOut() {
		  back.addAction(Actions.sequence(Actions.color(Color.BLACK,2),Actions.run(new Runnable() {
              @Override
              public void run() {
            	  MainMenu.getInstance().returnToMenu();
              }
          })));

	}
}
