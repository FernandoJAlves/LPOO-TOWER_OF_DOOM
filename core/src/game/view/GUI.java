package game.view;

import java.util.Map;
import java.util.TreeMap;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
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
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import game.main.TowerOfDoom;
import game.menu.MainMenu;

/**
 * 
 * GUI.java - Class with code to generate the GUI
 *
 */
public class GUI extends Stage{
	private Map<Character, Boolean> keys;
	private String addr;
	private ImageButton upButton;
	private ImageButton fireButton;
	private ImageButton leftButton;
	private ImageButton rightButton;
	private float screenWidth;
	private float screenHeight;
	private Dialog msg;
	
	/**
	 * Constructor for GUI
	 */
	public GUI() {
		super(new ExtendViewport(320,240));
		this.setMsg();
		screenWidth = (float)this.getWidth();
		screenHeight = (float)this.getHeight();
		this.initKeys();
		switch(Gdx.app.getType()) {
		   case Android:
			   this.initButtons();
			   Gdx.input.setCatchBackKey(true);
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
	
	/**
	 * Initializes the keys
	 */
	private void initKeys() {
		this.keys = new TreeMap<Character, Boolean>();
		this.keys.put('w', false);
		this.keys.put('a', false);
		this.keys.put('f', false);
		this.keys.put('d', false);
		this.keys.put('e', false);
	}
	
	/**
	 * Initializes the buttons
	 */
	public void initButtons() {
		this.setUpButton();
		this.setFireButton();
		this.setLeftButton();
		this.setRightButton();
		this.setBackButton();
	}
	
	/**
	 * Create a button
	 * @param path - path to image
	 * @return The created button
	 */
	private ImageButton createButton(String path) {
		Texture thrustTexture = TowerOfDoom.getInstance().getAssetManager().get(path);
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(thrustTexture));
		ImageButton button = new ImageButton(drawable);
		return button;
	}
	
	/**
	 * Sets the UpButton
	 */
	private void setUpButton() {
		upButton = createButton("ButtonUp.png");
		upButton.setPosition(12*this.screenWidth/16, screenHeight/16);
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
	
	/**
	 * Sets the FireButton
	 */
	private void setFireButton() {
		fireButton = createButton("ButtonFire.png");
		fireButton.setPosition(14*this.screenWidth/16, screenHeight/16);
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
	
	/**
	 * Sets the LeftButton
	 */
	private void setLeftButton() {
		leftButton = createButton("ButtonLeft.png");
		leftButton.setPosition(this.screenWidth/16, screenHeight/16);
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
	
	/**
	 * Sets the RightButton
	 */
	private void setRightButton() {
		rightButton = createButton("ButtonRight.png");
		rightButton.setPosition(3*this.screenWidth/16, screenHeight/16);
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
	
	/**
	 * Sets the BackButton
	 */
	private void setBackButton() {
		this.addListener(new InputListener() {
			 @Override
		        public boolean keyDown(InputEvent event, int keycode) 
		        {
				 if(keycode == Keys.BACK) {
					 MainMenu.getInstance().returnToMenu();
				 }
				return true;
		        }}
		);	
		}
	
	/**
	 * Returns boolean if keyPressed
	 * @param key - key you want to test
	 * @return True if key is pressed, false otherwise
	 */
	public boolean keyPressed(char key) {
		return this.keys.get(key);
	}
	
	/**
	 * Initializes the keyboard
	 */
	private void initKeyboard() {
		  this.addListener(new InputListener() 
		    {
		        @Override
		        public boolean keyDown(InputEvent event, int keycode) 
		        {
		           if(keycode == Keys.LEFT) 
					   keys.replace('a', true);
		 		   else if(keycode == Keys.RIGHT) 
					   keys.replace('d', true);
		 		   else if(keycode == Keys.UP) 
					   keys.replace('w', true);
		 		   else if(keycode == Keys.A) //'a' on keyboard
					   keys.replace('f', true);
		 		   else if(keycode == Keys.ESCAPE) //ESC on keyboard
					   keys.replace('e', true);
		            return true;
		        }
		        
		        @Override
		        public boolean keyUp(InputEvent event, int keycode) 
		        {
		 		   if(keycode == Keys.LEFT) 
					   keys.replace('a', false);
		 		   else if(keycode == Keys.RIGHT) 
					   keys.replace('d', false);
		 		   else if(keycode == Keys.UP) 
					   keys.replace('w', false);
		 		   else if(keycode == Keys.A) //'a' on keyboard
					   keys.replace('f', false);
		 		   else if(keycode == Keys.ESCAPE) //ESC on keyboard
					   keys.replace('e', false);
		            return true;
		        }
		    });
	}
	
	/**
	 * Update method for the GUI
	 * @param delta - Time since last update
	 */
	public void update(float delta) {
        this.act(delta); //Perform ui logic
        this.draw(); //Draw the ui
	}
	
	/**
	 * Sets the message
	 */
	private void setMsg() {
		Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
		this.msg = new Dialog("Network", skin) {
		    public void result(Object obj) {
		    	if(obj instanceof Boolean) {
		        	MainMenu.getInstance().returnToMenu();
		    	}
		    }
		};
		
	}
	
	/**
	 * Creates message1
	 * @param str - The IP in string format
	 */
	public void message1(String str) {

		msg.text("Your IP: " + str + "\nWaiting for Player 2");
		msg.button("Cancel",true);
		msg.show(this);
	}
	
	/**
	 * Creates message2
	 */
	public void message2() {

		 TextInputListener textListener = new TextInputListener()
		    {
		        @Override
		        public void input(String input)
		        {
		            addr = input;
		        }

		        @Override
		        public void canceled() 
		        {
		            MainMenu.getInstance().returnToMenu();
		        }
		    };

		    Gdx.input.getTextInput(textListener, "Player 1 IP: ", "", null);
	}
	
	/**
	 * Disables the message
	 */
	public void disableMsg() {
		this.msg.setVisible(false);
	}
	
	/**
	 * Gets the address
	 * @return The address
	 */
	public String getAddr() {
		return this.addr;
	}
	
	/**
	 * Resets the Esc button
	 */
	public void resetEscapeButton() {
		this.keys.replace('e', false);
	}
	

}
