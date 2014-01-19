package com.nesl.ucla.snakefruit;

import java.util.ArrayList;
import com.nesl.ucla.snakefruit.Utils;

public class Snake {
	private final int INITIAL_GROWTH = 5;
	private final int FIRST = 0;
	
	private ArrayList<Hexagon> snakeBody;
	
	private int nrToGrow;
	private int nrToCut;
	private Direction curDirection;
	private int color;
	
	public Snake(int initRow, int initCol, int initColor) {
		//initialize
		//snake grows RIGHT for initial 5 stages
		nrToGrow = INITIAL_GROWTH;
		curDirection = Direction.RIGHT;
		color = initColor;
		
		//make initial head
		snakeBody.add(FIRST, GamePlanner.field[initRow][initCol]);
	}
	
	// call from GamePlanner, to move or do something in it
	public void trigger() {
		// hello
		move();
			
	}

	//This function takes the number to grow and number to cut into consideration
	public void move() {
		//check for collision in next move
		/*if(checkCollision()){
			//chop off by half!
		}
		//else add a head
		else{
			if(checkForFruit()){
				
			}
			addHead();
		}
			*/
		
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
		int prevHeadRow;
		int prevHeadCol;
		int newHeadRow;
		int newHeadCol;
		
		
		if(snakeBody.size() > 0){
			//Get the coordinate of the previous head and calculate the next head coordinate, add Hexagon to list
			prevHeadRow = snakeBody.get(FIRST).getRow();
			prevHeadCol = snakeBody.get(FIRST).getCol();
			
			newHeadRow = prevHeadRow + Utils.getCoorIFromDir(curDirection).row;
			newHeadCol = prevHeadCol + Utils.getCoorIFromDir(curDirection).col;
			
			//Before we add the head we need to check for collision
			//checkHeadCollision!!!
			
			snakeBody.add(FIRST, GamePlanner.field[newHeadRow][newHeadCol]);	
			
			//Update the HexagonColor in field for new head
			GamePlanner.field[prevHeadRow][prevHeadCol].updateColor(color);
			
			//Update the HexagonType in field for new head
			switch(curDirection) {
			case LEFT:
				GamePlanner.field[prevHeadRow][prevHeadCol].updateType(HexagonType.HEAD_LEFT);
				break;
				
			case RIGHT:
				GamePlanner.field[prevHeadRow][prevHeadCol].updateType(HexagonType.HEAD_RIGHT);
				break;
				
			case UPPER_LEFT:
				GamePlanner.field[prevHeadRow][prevHeadCol].updateType(HexagonType.HEAD_UPPER_LEFT);	
				break;
				
			case UPPER_RIGHT:
				GamePlanner.field[prevHeadRow][prevHeadCol].updateType(HexagonType.HEAD_UPPER_RIGHT);	
				break;
				
			case LOWER_LEFT:
				GamePlanner.field[prevHeadRow][prevHeadCol].updateType(HexagonType.HEAD_LOWER_LEFT);
				break;
				
			case LOWER_RIGHT:
				GamePlanner.field[prevHeadRow][prevHeadCol].updateType(HexagonType.HEAD_LOWER_RIGHT);	
				break;
			}
			
			//Cancel the HexagonType in field for previous head to BODY. If it's supposed to be EMPTY, removeTail will update the cell again to EMPTY
			GamePlanner.field[prevHeadRow][prevHeadCol].updateType(HexagonType.BODY);
			
		}
	}
	
	private void removeTail(){
		int row;
		int col;
		
		//remove the tail from the snakeBody
		if(snakeBody.size() > 0){
			//get the coordinates of the tail
			row = snakeBody.get(snakeBody.size()-1).getRow();
			col = snakeBody.get(snakeBody.size()-1).getCol();	
			
			//update the snakeBody
			snakeBody.remove(snakeBody.size()-1);
			
			//update the field HexagonType to EMPTY accordingly
			GamePlanner.field[row][col].updateType(HexagonType.EMPTY);
		}
		
		
	}
	
	private int checkCollisionWall(){
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
