package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.model.HeroModel;

/**
 * 
 * HUD.java - Class that controls the HUD
 *
 */
public class HUD implements Disposable{
	private Stage stage;
	private Viewport viewport;
	
	private float WIDTH = 640;
	private float HEIGHT = this.WIDTH*((float)Gdx.graphics.getHeight())/((float)Gdx.graphics.getWidth());
	
	private float maxHealth;
	private float maxStamina;
	
	private Label health;
	private Label netHealth;
	private Label stamina;
	private Label netStamina;
    
    private ProgressBar healthBar;
    private ProgressBar netHealthBar;
    
    private ProgressBar staminaBar;
    private ProgressBar netStaminaBar;
    
    /**
     * Constructor for the HUD
     * @param hero - The hero model
     * @param netHero - The netHero model
     */
    public HUD(HeroModel hero, HeroModel netHero) {
        viewport = new ExtendViewport(WIDTH,HEIGHT);
        stage = new Stage(viewport);
        this.maxHealth = hero.getHitPoints();
        this.maxStamina = hero.getStamina();
        this.setElements(netHero);
 
    }
    
    /**
     * Creates a bar
     * @param back - Color of the back
     * @param front - Color of the front
     * @return The progress bar
     */
    private ProgressBar createBar(Color back, Color front) {
        Pixmap pixmap = new Pixmap(100, 20, Format.RGBA8888);
        pixmap.setColor(back);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        ProgressBarStyle progressBarStyle = new ProgressBarStyle();
        progressBarStyle.background = drawable;
        
        pixmap = new Pixmap(0, 20, Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
         
        progressBarStyle.knob = drawable;
         
        Pixmap pixmap2 = new Pixmap(100, 20, Format.RGBA8888);
        pixmap2.setColor(front);
        pixmap2.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap2)));
        pixmap2.dispose();
        progressBarStyle.knobBefore = drawable;
        ProgressBar bar = new ProgressBar(0.0f, 1.0f, 0.01f, false, progressBarStyle);
    	return bar;
    }
    
    /**
     * Sets the elements
     * @param nh - The model of the nethero
     */
    private void setElements(HeroModel nh) {
    	this.setHealthLabel();
    	this.setHealthBar();
    	this.setStaminaLabel();
    	this.setStaminaBar();
    	if(nh != null) {
    		this.setNetHealthLabel();
    		this.setNetHealthBar();
    		this.setNetStaminaLabel();
        	this.setNetStaminaBar();
    	}
    }
    
    /**
     * Sets the health label
     */
    private void setHealthLabel() {
    	this.health = this.createLabel("HEALTH", Color.RED);
    	health.setPosition(health.getWidth()/2, this.HEIGHT-30);
    	this.stage.addActor(health);
    }
    
    /**
     * Sets the nethero health label
     */
    private void setNetHealthLabel() {
    	this.netHealth = this.createLabel("HEALTH", Color.RED);
    	this.netHealth.setPosition(this.WIDTH - 2*this.netHealth.getWidth()-5, this.HEIGHT-30);
    	this.stage.addActor(this.netHealth);
    }
   
    /**
     * Sets the health bar
     */
   private void setHealthBar() {
	   this.healthBar = this.createBar(Color.RED, Color.GREEN);
	   this.healthBar.setPosition(10, this.HEIGHT-40,Align.left);
	   this.stage.addActor(this.healthBar);
   }
   
   /**
    * Sets the nethero health bar
    */
   private void setNetHealthBar() {
	   this.netHealthBar = this.createBar(Color.RED, Color.GREEN);
	   this.netHealthBar.setPosition(this.WIDTH-this.netHealthBar.getWidth()-5, this.HEIGHT-40,Align.left);
	   this.netHealthBar.setValue(1);
	   this.stage.addActor(this.netHealthBar);
   }
   
   /**
    * Sets the stamina label
    */
    private void setStaminaLabel() {
    	this.stamina = this.createLabel("STAMINA", Color.BLUE);
    	this.stamina.setPosition(this.stamina.getWidth()/2-5, this.HEIGHT-80);
    	this.stage.addActor(this.stamina);
    }
    
    /**
     * Sets the nethero stamina label
     */
    private void setNetStaminaLabel() {
    	this.netStamina = this.createLabel("STAMINA", Color.BLUE);
    	this.netStamina.setPosition(this.WIDTH - 2*this.netStamina.getWidth(), this.HEIGHT-80);
    	this.stage.addActor(this.netStamina);
    }
    
    /**
     * Sets the stamina bar
     */
    private void setStaminaBar() {
 	   this.staminaBar = this.createBar(Color.GRAY, Color.BLUE);
 	   this.staminaBar.setPosition(10, this.HEIGHT-90,Align.left);
 	   this.stage.addActor(this.staminaBar);
    }
    
    /**
     * Sets the nethero stamina bar
     */
    private void setNetStaminaBar() {
 	   this.netStaminaBar = this.createBar(Color.GRAY, Color.BLUE);
 	   this.netStaminaBar.setPosition(this.WIDTH-this.netHealthBar.getWidth()-5, this.HEIGHT-90,Align.left);
 	   this.netStaminaBar.setValue(1);
 	   this.stage.addActor(this.netStaminaBar);
    }
    
    /**
     * Creates a label
     * @param text - Text to display
     * @param color - Color of text
     * @return The created Label
     */
    private Label createLabel(String text, Color color) {
    	Label label = new Label(text, new Label.LabelStyle(new BitmapFont(), color));
    	return label;
    }
    
	/**
	 * Updates the bars
	 * @param h - The hero model
	 * @param nh - The netHero model
	 */
    private void updateBars(HeroModel h, HeroModel nh) {
    	this.healthBar.setValue(h.getHitPoints()/this.maxHealth);
    	this.staminaBar.setValue(h.getStamina()/this.maxStamina);
    	if(nh != null) {
    	   	this.netHealthBar.setValue(nh.getHitPoints()/this.maxHealth);
        	this.netStaminaBar.setValue(nh.getStamina()/this.maxStamina);
    	}
    }
    
    /**
     * Update method
     * @param delta - Time since last update
     * @param h - The hero model
     * @param nh - The netHero model
     */
    public void update(float delta, HeroModel h, HeroModel nh) {
    	this.updateBars(h, nh);
        this.stage.act(delta); //Perform ui logic
        this.stage.draw(); //Draw the ui
    }
	
	/**
	 * Override of the dispose method
	 */
	@Override
	public void dispose() {
		this.stage.dispose();
		
	}
	
}
