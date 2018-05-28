package game.controller;

public class Level1Controller extends LevelController{
	public Level1Controller(){
		super();
	}

	@Override
	protected void generateBodies() {
		//TODO POVOATE with bodies
		
		//Level 1 ceiling
		this.addBody_m(8,23,134,1);
			
		//Floor from start room to the first pit
		this.addBody_m(8,14,18,1);
		
		//2x2 in the start room floor and ceiling
		this.addBody_m(17,15,2,2);
		this.addBody_m(17,21,2,2);
		
		//Wall in start room
		this.addBody_m(7,15,1,8);
		
		//Platform between pit 1 and 2
		this.addBody_m(30,7,3,8);
		
		//Sides of the pits
		this.addBody_m(25,7,1,8);
		this.addBody_m(37,7,1,8);
	
		//Floor in second area until pit 3
		this.addBody_m(37,14,68,1);
		
		//Platform in second area
		this.addBody_m(41,17,4,1);
		
		//Edge in second area
		this.addBody_m(48,19,4,1);
		
		//Pillar in second area
		this.addBody_m(50,15,2,4);
		
		//Ceiling Pillar
		this.addBody_m(69,17,2,6);
		
		//Floor Pillar
		this.addBody_m(80,15,2,6);
		
		//Platform 1
		this.addBody_m(71,17,6,1);
		
		//Platform 2
		this.addBody_m(74,20,6,1);
		
		//Ceiling Pillar
		this.addBody_m(86,18,1,5);
		
		//5x3 Platform 1
		this.addBody_m(100,15,5,3);
		
		//5x3 Platform 2
		this.addBody_m(109,15,5,3);
		
		//Final floor
		this.addBody_m(114,14,28,1);
		
		//Final wall
		this.addBody_m(142,15,1,8);
		
		
		
		
	}

}
