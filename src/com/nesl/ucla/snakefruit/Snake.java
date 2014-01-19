package com.nesl.ucla.snakefruit;

import java.util.ArrayList;
import com.nesl.ucla.snakefruit.Utils;

public class Snake {
	private final int INITIAL_GROWTH = 5;
	
	private ArrayList<CoorI> body;
	
	private int nrToGrow;
	private int nrToCut;
	private Direction curDirection;
	
	public Snake(int initRow, int initCol) {
		//initialize
		//snake grows RIGHT for initial 5 stages
		nrToGrow = INITIAL_GROWTH;
		curDirection = Direction.RIGHT;
		
		//make initial head
		CoorI newHeadCoorI = new CoorI();
		newHeadCoorI.row = initRow;
		newHeadCoorI.col = initCol;
		body.add(newHeadCoorI);
	}
	
	// call from GamePlanner, to move or do something in it
	public void trigger() {
		// hello
		move();
			
	}

	//This function takes the number to grow and number to cut into consideration
	public void move() {
		//add head in direction in all cases
		addHead();
		
		//if in initial growth stage, do not remove tail
		if(nrToGrow > 0){
			nrToGrow--;
		}
		//else remove the tail
		else{
			removeTail();
		}
		
		if(nrToCut > 0){
			for(int i=0; i<nrToCut; i++){
				removeTail();
			}
		}
	}
	
	private void addHead(){
		CoorI newHeadCoorI = new CoorI();
		
		if(body.size() > 0){
			newHeadCoorI.row = body.get(0).row + Utils.getCoorIFromDir(curDirection).row;
			newHeadCoorI.col = body.get(0).col + Utils.getCoorIFromDir(curDirection).col;
			
			body.add(newHeadCoorI);	
		}
	}
	
	private void removeTail(){
		if(body.size() > 0){
			body.remove(body.size()-1);
		}
	}
	
	private int checkCollision(){
		return 0;
	}
	
	public void setDirection(Direction newDirection) {
		curDirection = newDirection;
	}
	
	public void setNrToGrow(int num){
		nrToGrow = num;
	}
	
	public void setNrToCut(int num){
		nrToCut = num;
	}
}
