package game.view;

import java.util.Map;
import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import game.main.TowerOfDoom;

public class GUI extends Stage{
	protected Map<Character, Boolean> keys;
	private ImageButton upButton;
	private ImageButton fireButton;
	private ImageButton leftButton;
	private ImageButton rightButton;
	int screenWidth, screenHeight;
	
	
	public GUI() {
		super();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		this.initKeys();
		switch(Gdx.app.getType()) {
		   case Android:
			   this.initButtons();
			   break;
		   default:
			   break;
			   
		}
		Gdx.input.setInputProcessor(this);
	}
	
	private void initKeys() {
		this.keys = new TreeMap<Character, Boolean>();
		this.keys.put('w', false);
		this.keys.put('a', false);
		this.keys.put('f', false);
		this.keys.put('d', false);
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
		//button.setScale(3);
		button.getImage().scaleBy(3);
		return button;
	}
	
	private void setUpButton() {
		upButton = createButton("ButtonUp.png");
		upButton.setPosition(12*this.screenWidth/16, this.getHeight()/16);
		this.upButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                keys.replace('w', true);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            	keys.replace('w', false);
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
            	keys.replace('f', true);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            	keys.replace('f', false);
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
            	keys.replace('a', true);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            	keys.replace('a', false);
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
            	keys.replace('d', true);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            	keys.replace('d', false);
            }
        });
		this.addActor(rightButton);
	}
	
	private void updateKeys() {
		   if(Gdx.input.isKeyPressed(Keys.LEFT)) 
			   this.keys.replace('a', true);
		   else this.keys.replace('a', false);
		   if(Gdx.input.isKeyPressed(Keys.RIGHT)) 
			   this.keys.replace('d', true);
		   else this.keys.replace('d', false);
		   if(Gdx.input.isKeyPressed(Keys.UP)) 
			   this.keys.replace('w', true);
		   else this.keys.replace('w', false);
		   if(Gdx.input.isKeyPressed(Keys.A)) 
			   this.keys.replace('f', true);
		   else this.keys.replace('f', false);
	}
	
	public boolean keyPressed(char key) {
		return this.keys.get(key);
	}
	
	public void update(float delta) {
		this.updateKeys();
        this.act(delta); //Perform ui logic
        this.draw(); //Draw the ui
	}

}
