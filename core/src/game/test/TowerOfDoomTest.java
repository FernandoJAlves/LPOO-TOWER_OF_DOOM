package game.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;

import game.*;
import game.controller.GameController;
import game.controller.HeroBody;
import game.main.TowerOfDoom;
import game.model.EntityModel.ModelType;
import game.model.EntityModel.directionState;
import game.model.GameModel;
import game.model.HeroModel;
import game.model.HeroModel.charState;
import game.model.SlugModel.slugState;
import game.model.LevelModel;
import game.model.LevelModel1;
import game.model.PlasmaModel;
import game.model.SlugModel;

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
	
	@Test
	public void testHeroModelDamage1() {
		HeroModel hero = new HeroModel(0, 0);
		
		hero.update(10);
		assertEquals(hero.getHitPoints(),10,0.01);
		hero.move('o');
		assertEquals(hero.getState(), charState.DAMAGE);
		assertEquals(hero.getHitPoints(), 9,0.1);
		hero.update(2);
		hero.setYSpeed(0);
		hero.move('d');
		hero.update(1);
		assertEquals(hero.getState(), charState.WALK);
		hero.move('p');
		assertEquals(hero.getState(), charState.DAMAGE);
		assertEquals(hero.getHitPoints(), 8,0.1);
	}
	
	@Test
	public void testHeroModelDamage2() {
		HeroModel hero = new HeroModel(0, 0);
		
		hero.update(10);
		assertEquals(hero.getHitPoints(),10,0.01);
		hero.move('w');
		hero.update(1);
		assertEquals(hero.getState(), charState.JUMP);
		hero.move('p');
		assertEquals(hero.getState(), charState.DAMAGE);
		assertEquals(hero.getHitPoints(), 9,0.1);
		hero.update(2);
		hero.move('o');
		assertEquals(hero.getState(), charState.DAMAGE);
		assertEquals(hero.getHitPoints(), 8,0.1);
	}
	
	@Test
	public void testHeroModelDamage3() {
		HeroModel hero = new HeroModel(0, 0);
		
		for(int i = 0; i < 9; i++) {
			hero.decrementHitpoints();
		}
		assertEquals(hero.getHitPoints(),1,0.01);
		hero.update(1);
		hero.move('p');
		assertEquals(hero.getState(), charState.DAMAGE);
		assertEquals(hero.getHitPoints(), 0,0.1);
		hero.update(6 * 0.15f);
		assertEquals(GameModel.getInstance().getState(), GameModel.gameState.PLAYING);
		hero.update(1);
		assertEquals(GameModel.getInstance().getState(), GameModel.gameState.LOSS);
		
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
		assertEquals(game.getEnemies().size(),3);
		game.delete();
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
	public void testGameModelUpdateHeros() {
		GameModel game = GameModel.getInstance();
		game.setMultiplayer();
		HeroModel hero = game.getHero();
		HeroModel nethero = game.getNetHero();
		game.getHero().move('d');
		game.getNetHero().move('d');
		game.update(0);
		assertEquals(hero.getState(), charState.WALK);
		assertEquals(nethero.getState(), charState.WALK);
		game.delete();
	}
	
	@Test
	public void testGameModelUpdatePlasma1() {
		GameModel game = GameModel.getInstance();
		
		List<PlasmaModel> plasmaBalls = game.getPlasmaballs();
		plasmaBalls.add(new PlasmaModel(0,0));
		assertFalse(plasmaBalls.get(0).isFlaggedToBeRemoved());
		assertEquals(plasmaBalls.get(0).getJumpsLeft(), 0);
		game.update(10);
		assertTrue(plasmaBalls.get(0).isFlaggedToBeRemoved());
		
		game.delete();
	}
	
	@Test
	public void testGameModelUpdatePlasma2() {
		GameModel game = GameModel.getInstance();
		HeroModel hero = game.getHero();
		
		List<PlasmaModel> plasmaBalls = game.getPlasmaballs();
		PlasmaModel pBall = game.createPlasmaBall(game.getHero());
		assertFalse(plasmaBalls.get(0).isFlaggedToBeRemoved());
		assertEquals(plasmaBalls.get(0).getJumpsLeft(), 3);
		assertEquals(pBall.getX(), hero.getX() + 30, 0.1);
		assertEquals(pBall.getY(), hero.getY(), 0.1);
		
		pBall.setJumpsLeft(0);
		game.update(10);
		assertTrue(plasmaBalls.get(0).isFlaggedToBeRemoved());
		
		game.delete();
	}
	
	@Test
	public void testGameModelUpdatePlasma3() {
		GameModel game = GameModel.getInstance();
		HeroModel hero = game.getHero();
		hero.move('a');
		
		List<PlasmaModel> plasmaBalls = game.getPlasmaballs();
		PlasmaModel pBall = game.createPlasmaBall(game.getHero());
		assertFalse(plasmaBalls.get(0).isFlaggedToBeRemoved());
		assertEquals(plasmaBalls.get(0).getJumpsLeft(), 3);
		assertEquals(pBall.getX(), hero.getX() - 30, 0.1);
		assertEquals(pBall.getY(), hero.getY(), 0.1);
		
		pBall.setJumpsLeft(0);
		game.update(10);
		assertTrue(plasmaBalls.get(0).isFlaggedToBeRemoved());
		game.remove(pBall);
		
		game.delete();
	}
	
	//SLUG MODEL
	
	@Test
	public void testSlugModelInit() {
		SlugModel slug = new SlugModel(0,0);
		
		assertEquals(slug.getAttackRange(),50);
		assertEquals(slug.getModelType(), ModelType.SLUG);
		assertEquals(slug.getState(), slugState.WALK);
		assertEquals(slug.getViewRange(), 120);
		assertFalse(slug.isAlert());

	}
	
	@Test
	public void testSlugModelMove() {
		SlugModel slug = new SlugModel(0,0);
		slug.move('d');
		assertEquals(slug.getSpeed(),30,0.01);
		assertEquals(slug.getDirection(), directionState.RIGHT);
		
		slug.move('a');
		assertEquals(slug.getSpeed(),-30,0.01);
		assertEquals(slug.getDirection(), directionState.LEFT);
		
		slug.move('f');
		assertEquals(slug.getState(), slugState.ATTACK);

	}
	
	@Test
	public void testSlugModelAlert() {
		SlugModel slug = new SlugModel(0,0);
		slug.setPosition(-73, 0);
		slug.update(1);
		assertEquals(slug.getSpeed(),30,0.01);
		assertEquals(slug.getDirection(), directionState.RIGHT);
		slug.setPosition(73, 0);
		slug.update(1);
		assertEquals(slug.getSpeed(),-30,0.01);
		assertEquals(slug.getDirection(), directionState.LEFT);
		slug.setAlert(true);
		slug.setPosition(-73, 0);
		slug.update(1);
		assertEquals(slug.getSpeed(),-30,0.01);
		assertEquals(slug.getDirection(), directionState.LEFT);


	}

	//CONTROLLER
	
	@Test
	public void testGameController() {
		GameModel game = GameModel.getInstance();
		
		/*
		HeroBody heroBody = GameController.getInstance().getHero();
		assertEquals(heroBody.getAngle(), 0, 0.1);
		assertEquals(heroBody.getX(),0,0.1);
		assertEquals(heroBody.getY(), 0, 0.1);
		
		*/
		

	}
	
}
