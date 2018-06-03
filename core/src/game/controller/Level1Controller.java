package game.controller;

/**
 * 
 * Level1Controller.java - Controlls the logic expecific to level1
 *
 */
public class Level1Controller extends LevelController{
	/**
	 * Constructor of Level1 
	 *
	 */
	public Level1Controller(){
		super();
	}

	/**
	 * Override of the generateBodies function
	 * 
	 */
	@Override
	protected void generateBodies() {
		//TODO POVOATE with bodies
		
		//Level 1 ceiling
		this.addFloor(8,23,134,1);
			
		//Floor from start room to the first pit
		this.addFloor(8,14,18,1);
		
		//2x2 in the start room floor and ceiling
		this.addFloor(17,15,2,2);
		this.addFloor(17,21,2,2);
		
		//Wall in start room
		this.addFloor(7,15,1,8);
		
		//Platform between pit 1 and 2
		this.addFloor(30,7,3,8);
		
		//Sides of the pits
		this.addFloor(25,7,1,8);
		this.addFloor(37,7,1,8);
	
		//Floor in second area until pit 3
		this.addFloor(37,14,68,1);
		
		//Platform in second area
		this.addFloor(41,17,4,1);
		
		//Edge in second area
		this.addFloor(48,19,4,1);
		
		//Pillar in second area
		this.addFloor(50,15,2,4);
		
		//Ceiling Pillar
		this.addFloor(69,17,2,6);
		
		//Floor Pillar
		this.addFloor(80,15,2,6);
		
		//Platform 1
		this.addFloor(71,17,6,1);
		
		//Platform 2
		this.addFloor(74,20,6,1);
		
		//Ceiling Pillar
		this.addFloor(86,18,1,5);
		
		//5x3 Platform 1
		this.addFloor(100,15,5,3);
		
		//5x3 Platform 2
		this.addFloor(109,15,5,3);
		
		//Final floor
		this.addFloor(114,14,28,1);
		
		//Final wall
		this.addFloor(142,15,1,8);
		
		//First hole "cover"
		this.addFloor(25,6,13,1);
		
		//First hole
		this.addHole(26, 7, 4, 1);
		
		//Second hole
		this.addHole(33, 7, 4, 1);
		
		//Third hole
		this.addHole(105, 7, 4, 1);
		
		
		//Door
		this.addDoor(141, 14, 1, 8);
		
	}

}
