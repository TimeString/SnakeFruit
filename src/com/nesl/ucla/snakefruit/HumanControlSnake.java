package com.nesl.ucla.snakefruit;

public class HumanControlSnake extends Snake {

	public HumanControlSnake(int initRow, int initCol, int initDir,
			int initColor, FruitDeliver _fruitDeliver) {
		super(initRow, initCol, initDir, initColor, _fruitDeliver);
		
	}
	
	public void setDirection(int newDirection) {
		if (snakeBody.size() > 0) {
			targetDirection = newDirection;
			preferTargetDirection();
			Hexagon head = snakeBody.get(0);
			head.updateType(HexagonType.HEAD);
			head.updateColor(color);
			head.updateDir(curDirection);
		}
	}

	@Override
	protected void makeFinalDecision() {
		
	}
}
