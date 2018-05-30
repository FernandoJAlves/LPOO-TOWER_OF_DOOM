package game.view;

import java.util.Map;
import java.util.TreeMap;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import game.main.TowerOfDoom;
import game.menu.MainMenu;

public class GUI extends Stage{
	protected Map<Character, Boolean> keys;
	private ImageButton upButton;
	private ImageButton fireButton;
	private ImageButton leftButton;
	private ImageButton rightButton;
	int screenWidth, screenHeight;
	private Dialog msg;
	
	
	public GUI() {
		super();
		this.setMsg();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		this.initKeys();
		switch(Gdx.app.getType()) {
		   case Android:
			   this.initButtons();
			   break;
		   case Desktop:
			   this.initKeyboard();
			   break;
		   default:
			   this.initButtons();
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
	
	public boolean keyPressed(char key) {
		return this.keys.get(key);
	}
	
	private void initKeyboard() {
		  this.addListener(new InputListener() 
		    {
		        @Override
		        public boolean keyDown(InputEvent event, int keycode) 
		        {
		           if(keycode == 21) 
					   keys.replace('a', true);
		 		   else if(keycode == 22) 
					   keys.replace('d', true);
		 		   else if(keycode == 19) 
					   keys.replace('w', true);
		 		   else if(keycode == 29) //'a' on keyboard
					   keys.replace('f', true);
		            return true;
		        }
		        
		        @Override
		        public boolean keyUp(InputEvent event, int keycode) 
		        {
		 		   if(keycode == 21) 
					   keys.replace('a', false);
		 		   else if(keycode == 22) 
					   keys.replace('d', false);
		 		   else if(keycode == 19) 
					   keys.replace('w', false);
		 		   else if(keycode == 29) //'a' on keyboard
					   keys.replace('f', false);
		            return true;
		        }
		    });
	}
	
	public void update(float delta) {
        this.act(delta); //Perform ui logic
        this.draw(); //Draw the ui
	}
	
	private void setMsg() {
		Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
		this.msg = new Dialog("Network", skin) {
		    public void result(Object obj) {
		    	if(obj instanceof Boolean) {
		        	TowerOfDoom.getInstance().setScreen(MainMenu.getInstance());
		    	}
		    }
		};
		
	}
	
	public void message1(String str) {

		msg.text("Your IP: " + str + "\nWaiting for Player 2");
		msg.button("Cancel",true);
		msg.show(this);
	}
	
	public void message2(String str) {

		msg.text("Player 1 IP:");
		msg.button("IP");
		msg.button("Cancel",true);
		msg.show(this);
	}
	
	public void disableMsg() {
		this.msg.setVisible(false);
	}

}
