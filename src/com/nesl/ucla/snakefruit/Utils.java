package com.nesl.ucla.snakefruit;

public class Utils {
	public CoorI getCoorIFromDir(Direction myDirection){
		CoorI myCoorI = new CoorI();
		
		switch(myDirection) {
			case LEFT:
					myCoorI.col = -1;
					myCoorI.row = 0;
				break;
				
			case RIGHT:
					myCoorI.col = 1;
					myCoorI.row = 0;
				break;
				
			case UPPER_LEFT:
					myCoorI.col = -1;
					myCoorI.row = -1;
				break;
				
			case UPPER_RIGHT:
					myCoorI.col = 1;
					myCoorI.row = -1;
				break;
				
			case LOWER_LEFT:
					myCoorI.col = -1;
					myCoorI.row = 1;
				break;
				
			case LOWER_RIGHT:
					myCoorI.col = 1;
					myCoorI.row = -1;
				break;
		}
		
		return myCoorI;
	}

}
