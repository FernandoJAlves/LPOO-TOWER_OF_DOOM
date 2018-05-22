package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import game.main.TowerOfDoom;
import game.model.HeroModel;

public class GUI extends Stage{
	private HeroModel hero;
	private ImageButton upButton;
	private ImageButton fireButton;
	private ImageButton leftButton;
	private ImageButton rightButton;
	int screenWidth, screenHeight;
	
	
	public GUI(HeroModel h) {
		super();
		hero = h;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		switch(Gdx.app.getType()) {
		   case Android:
			   this.initButtons();
			   
		}
		Gdx.input.setInputProcessor(this);
	}
	
	private void initButtons() {
		this.setUpButton();
		this.setFireButton();
		this.setLeftButton();
		this.setRightButton();
	}
	
	private ImageButton createButton(String path) {
		Texture thrustTexture = TowerOfDoom.getInstance().getAssetManager().get(path);
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(thrustTexture));
		ImageButton button = new ImageButton(drawable);
		button.setScale(3);
		button.getImage().scaleBy(3);
		return button;
	}
	
	private void setUpButton() {
		upButton = createButton("ButtonUp.png");
		upButton.setPosition(12*this.screenWidth/16, this.getHeight()/16);
		this.upButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hero.move('w');
                return true;
            }
        });
		this.addActor(upButton);
	}
	
	private void setFireButton() {
		fireButton = createButton("ButtonFire.png");
		fireButton.setPosition(14*this.screenWidth/16, this.getHeight()/16);
		this.fireButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hero.move('w');
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                hero.move('n');
            }
        });
		this.addActor(fireButton);
	}
	
	private void setLeftButton() {
		leftButton = createButton("ButtonLeft.png");
		leftButton.setPosition(this.screenWidth/16, this.getHeight()/16);
		this.leftButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hero.move('a');
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                hero.move('n');
            }
        });
		this.addActor(leftButton);
	}
	
	private void setRightButton() {
		rightButton = createButton("ButtonRight.png");
		rightButton.setPosition(3*this.screenWidth/16, this.getHeight()/16);
		this.rightButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hero.move('d');
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                hero.move('n');
            }
        });
		this.addActor(rightButton);
	}
	
	public void update(float delta) {
        this.act(delta); //Perform ui logic
        this.draw(); //Draw the ui
	}

}
