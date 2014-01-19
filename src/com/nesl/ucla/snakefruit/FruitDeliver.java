package com.nesl.ucla.snakefruit;

import java.util.Random;

public class FruitDeliver {
	private GamePlanner gamePlanner;
	//private int fr, fc;
	private Random random = new Random();
	
	private int nrOfFruit = 0;
	
	public FruitDeliver(GamePlanner _gamePlanner) {
		gamePlanner = _gamePlanner;
	}
	
	public void eatFruit(int r, int c) {
		nrOfFruit--;
	}
	
	public void generateNewOne() {
		int trials = 30;
		while (trials > 0 && nrOfFruit < 1) {
			int tr = random.nextInt(GamePlanner.FIELD_HEIGHT);
			int tc = random.nextInt(GamePlanner.FIELD_WIDTH);
			if (GamePlanner.field[tr][tc].getType() == HexagonType.EMPTY) {
				GamePlanner.field[tr][tc].updateType(HexagonType.FRUIT);
				nrOfFruit++;
			}
			trials--;
		}
	}
}
