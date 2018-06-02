package game.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;

import game.*;
import game.main.TowerOfDoom;
import game.model.EntityModel.ModelType;
import game.model.EntityModel.directionState;
import game.model.GameModel;
import game.model.HeroModel;
import game.model.HeroModel.charState;
import game.model.LevelModel;
import game.model.LevelModel1;
import game.model.PlasmaModel;

public class TowerOfDoomTest {

	//HERO MODEL
	
	@Test
	public void testHeroModelInit() {
		HeroModel hero = new HeroModel(0, 0);
		assertEquals(hero.getDirection(), directionState.RIGHT);
		assertEquals(hero.getHitPoints(), 10);
		assertEquals(hero.getModelType(), ModelType.HERO);
		assertEquals(hero.getSpeed(),0,0.01);
		assertEquals(hero.getStamina(),10,0.01);
		assertEquals(hero.getState(),charState.STARE);
		assertEquals(hero.getX(),0,0.01);
		assertEquals(hero.getXSpeed(),0,0.01);
		assertEquals(hero.getY(),0,0.01);
		assertEquals(hero.getYSpeed(),0,0.01);
	}

	@Test
	public void testHeroModelSet() {
		HeroModel hero = new HeroModel(0, 0);
		hero.setPosition(1, 1);
		assertEquals(hero.getX(),1,0.01);
		assertEquals(hero.getY(),1,0.01);
		hero.setXSpeed(100);
		hero.setY(10);
		hero.setYSpeed(100);
		assertEquals(hero.getDirection(), directionState.RIGHT);
		assertEquals(hero.getHitPoints(), 10);
		assertEquals(hero.getModelType(), ModelType.HERO);
		assertEquals(hero.getSpeed(),100,0.01);
		assertEquals(hero.getStamina(),10,0.01);
		assertEquals(hero.getState(),charState.STARE);
		assertEquals(hero.getX(),1,0.01);
		assertEquals(hero.getXSpeed(),100,0.01);
		assertEquals(hero.getY(),10,0.01);
		assertEquals(hero.getYSpeed(),100,0.01);
		Vector2 v = new Vector2(3,3);
		hero.setPosition(v);
		assertEquals(hero.getX(),3,0.01);
		assertEquals(hero.getY(),3,0.01);
		hero.setYSpeed(1);
		hero.update(1);
		assertNotEquals(hero.getState(),charState.JUMP);
		hero.setYSpeed(-1);
		hero.update(1);
		assertNotEquals(hero.getState(),charState.FALL);
	}
	
	@Test
	public void testHeroModelStareMoveRight() {
		HeroModel hero = new HeroModel(0, 0);
		hero.move('d');
		assertEquals(hero.getSpeed(), 50,0.01);
		assertEquals(hero.getDirection(), directionState.RIGHT);
	}
	
	@Test
	public void testHeroModelStareMoveLeft() {
		HeroModel hero = new HeroModel(0, 0);
		hero.move('a');
		assertEquals(hero.getSpeed(), -50,0.01);
		assertEquals(hero.getDirection(), directionState.LEFT);
	}
	
	@Test
	public void testHeroModelStareMoveJump() {
		HeroModel hero = new HeroModel(0, 0);
		hero.move('w');
		assertEquals(hero.getYSpeed(), 100,0.01);
	}
	
	@Test
	public void testHeroModelStareMoveFire() {
		HeroModel hero = new HeroModel(0, 0);
		hero.move('f');
		assertEquals(hero.getState(), charState.ATTACK);
	}
	
	@Test
	public void testHeroModelMoveUpdateJump() {
		HeroModel hero = new HeroModel(0, 0);
		hero.move('w');
		assertEquals(hero.getYSpeed(), 100,0.01);
		hero.update(1);
		assertEquals(hero.getState(),charState.JUMP);
		hero.move('d');
		assertEquals(hero.getSpeed(), 50,0.01);
		assertEquals(hero.getDirection(), directionState.RIGHT);
		hero.move('a');
		assertEquals(hero.getSpeed(), -50,0.01);
		assertEquals(hero.getDirection(), directionState.LEFT);
		hero.setYSpeed(50);
		hero.move('w');
		assertNotEquals(hero.getYSpeed(), 100,0.01);
		hero.move('f');
		assertEquals(hero.getState(), charState.ATTACK);
		
	}
	
	@Test
	public void testHeroModelMoveUpdateFall() {
		HeroModel hero = new HeroModel(0, 0);
		
		hero.setYSpeed(-100);
		hero.update(1);
		assertEquals(hero.getState(),charState.FALL);
		hero.move('d');
		assertEquals(hero.getSpeed(), 50,0.01);
		assertEquals(hero.getDirection(), directionState.RIGHT);
		hero.move('a');
		assertEquals(hero.getSpeed(), -50,0.01);
		assertEquals(hero.getDirection(), directionState.LEFT);
		hero.move('w');
		assertNotEquals(hero.getYSpeed(), 100,0.01);
		hero.move('f');
		assertEquals(hero.getState(), charState.ATTACK);
	}
	
	@Test
	public void testHeroModelMoveUpdateAttack() {
		HeroModel hero = new HeroModel(0, 0);

		hero.move('f');
		assertEquals(hero.getState(), charState.ATTACK);
		hero.move('d');
		assertEquals(hero.getSpeed(), 50,0.01);
		assertEquals(hero.getDirection(), directionState.RIGHT);
		hero.move('a');
		assertEquals(hero.getSpeed(), -50,0.01);
		assertEquals(hero.getDirection(), directionState.LEFT);
		hero.move('w');
		assertNotEquals(hero.getYSpeed(), 100,0.01);
	}
	
	@Test
	public void testHeroModelMoveUpdateWalk() {
		HeroModel hero = new HeroModel(0, 0);

		hero.move('d');
		assertEquals(hero.getSpeed(), 50,0.01);
		hero.update(1);
		assertEquals(hero.getState(),charState.WALK);
		hero.move('d');
		assertEquals(hero.getSpeed(), 50,0.01);
		hero.update(1);
		assertEquals(hero.getState(),charState.WALK);
		hero.move('a');
		assertEquals(hero.getSpeed(), -50,0.01);
		hero.update(1);
		assertEquals(hero.getState(),charState.WALK);
		hero.setXSpeed(0);
		hero.update(1);
		assertEquals(hero.getState(),charState.STARE);
		
		hero.move('d');
		hero.update(1);
		assertEquals(hero.getState(),charState.WALK);
		hero.move('w');
		assertEquals(hero.getYSpeed(), 100,0.01);
		hero.update(1);
		assertEquals(hero.getState(),charState.JUMP);
		hero.move('f');
		assertEquals(hero.getState(), charState.ATTACK);
		
	}
	
	@Test
	public void testHeroModelMoveUpdateWalkFire() {
		HeroModel hero = new HeroModel(0, 0);

		assertEquals(hero.getState(), charState.STARE);
		hero.move('d');
		assertEquals(hero.getSpeed(), 50,0.01);
		hero.update(1);
		assertEquals(hero.getState(),charState.WALK);
		hero.move('f');
		assertEquals(hero.getState(), charState.ATTACK);
		
	}
	
	@Test
	public void testHeroModelStamina() {
		HeroModel hero = new HeroModel(0, 0);
		
		hero.update(10);
		assertEquals(hero.getStamina(),10,0.01);
		
		hero.decrementStamina();
		assertEquals(hero.getStamina(),9,0.01);
		while(hero.getStamina() >= 1) {
			hero.decrementStamina();
		}
		assertEquals(hero.getStamina(), 0,0.01);
		assertNotEquals(hero.getState(), charState.ATTACK);
		hero.move('f');
		assertNotEquals(hero.getState(), charState.ATTACK);
		hero.update(10);
		assertEquals(hero.getStamina(), 1,0.01);
		hero.move('f');
		assertEquals(hero.getState(), charState.ATTACK);
		
	}
	
	//GAME MODEL
	
	@Test
	public void testGameModelInit() {
		GameModel game = GameModel.getInstance();
		assertNotEquals(game, null);
		HeroModel hero = game.getHero();
		HeroModel test = new HeroModel(288,380);
		assertEquals(hero.getDirection(), test.getDirection());
		assertEquals(hero.getX(), test.getX(), 0.1);
		assertEquals(hero.getY(), test.getY(), 0.1);
		LevelModel level = new LevelModel1();
		assertEquals(game.getLevel().getChars().size(), level.getChars().size());
		assertEquals(game.getLevel().getXLimit(), level.getXLimit());
		assertEquals(game.getLevel().getYLimit(), level.getYLimit());
		assertEquals(game.getPlasmaballs().size(),0);
		assertEquals(game.getEnemies().size(),1);

	}
	
	@Test
	public void testGameModelSet() {
		GameModel game = GameModel.getInstance();
		game.setNetHero(new HeroModel(0,0));
		HeroModel test = new HeroModel(0,0);
		assertEquals(game.getNetHero().getX(), test.getX(),0.01);
		assertEquals(game.getNetHero().getY(), test.getY(),0.01);
		assertEquals(game.getNetHero().getDirection(), test.getDirection());
		game.setMultiplayer();
		test = new HeroModel(308,380);
		assertEquals(game.getNetHero().getX(), test.getX(),0.01);
		assertEquals(game.getNetHero().getY(), test.getY(),0.01);
		assertEquals(game.getNetHero().getDirection(), test.getDirection());
		game.delete();

	}

	@Test
	public void testGameModelUpdate() {
		GameModel game = GameModel.getInstance();
		game.update(0);
		

	}
	
	
	
}
