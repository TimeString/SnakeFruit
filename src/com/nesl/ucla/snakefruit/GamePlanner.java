package com.nesl.ucla.snakefruit;

import android.graphics.Canvas;
import android.graphics.Color;

public class GamePlanner {
	//private FruitDeliver fruitDeliver;
	private Snake humanSnake;
	//private Score score;
	
	public static final int field_width = 53;
	public static final int field_height = 43;
	public static Hexagon[][] field = new Hexagon[field_width][field_height]; 
	
	private final int start_x = field_width / 2;
	private final int start_y = field_height / 2;
	
	public GamePlanner() {
		for (int i = 0; i < field_width; i++)
			for (int j = 0; j < field_height; j++) {
				
			}
		humanSnake = new Snake(start_x, start_y);
	}
	
	// the frame starts!
	public void trigger(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
	}
	
	// the screen detect the user swipes
	public void passUserInputSwipe(int dx, int dy) {
		// snake.setDirection(??);
	}
}
