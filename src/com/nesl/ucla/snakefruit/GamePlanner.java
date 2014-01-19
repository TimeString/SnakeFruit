package com.nesl.ucla.snakefruit;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

public class GamePlanner {
	private FruitDeliver fruitDeliver;
	private Snake humanSnake;
	//private Score score;
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	public static int FIELD_WIDTH = 53;
	public static int FIELD_HEIGHT = 43;
	public static Hexagon[][] field = new Hexagon[FIELD_HEIGHT][FIELD_WIDTH]; 
	
	private int start_r = 0;
	private int start_c = 0;
	
	private final double swipeEffectiveDistance = 30.0;
	private final double swipeEffectiveDistance2 = swipeEffectiveDistance * swipeEffectiveDistance;
	
	private int width, height;
	
	public GamePlanner(int _width, int _height) {
		width = _width;
		height = _height;
		FIELD_WIDTH = width / (Hexagon.BITMAP_WIDTH + 2);
		FIELD_HEIGHT = height / (Hexagon.BITMAP_HEIGHT + 2);
		start_r = FIELD_HEIGHT / 2;
		start_c = FIELD_WIDTH / 2;
		Hexagon.OFFSET_X = (int)(width / 2.0 + Hexagon.rc2px(-FIELD_HEIGHT / 2, - FIELD_WIDTH / 2));
		Hexagon.OFFSET_Y = (int)(height / 2.0 + Hexagon.rc2py(-FIELD_HEIGHT / 2, - FIELD_WIDTH / 2));
		Log.i(TAG, "middle: " + (width / 2) + ", " + (height / 2));
		Log.i(TAG, "original: " + Hexagon.OFFSET_X + ", " + Hexagon.OFFSET_Y);
		
		for (int i = 0; i < FIELD_HEIGHT; i++)
			for (int j = 0; j < FIELD_WIDTH; j++)
				field[i][j] = new Hexagon(i, j);
		for (int i = 0; i < FIELD_HEIGHT; i++) {
			int left = 0 + (i - FIELD_HEIGHT / 2);
			if (left < 0)
				left = 0;
			int right = FIELD_WIDTH - 1 + (i - FIELD_HEIGHT / 2);
			if (right >= FIELD_WIDTH)
				right = FIELD_WIDTH - 1;
			field[i][left].updateType(HexagonType.WALL);
			field[i][right].updateType(HexagonType.WALL);
			for (int j = 0; j < FIELD_WIDTH; j++) {
				if (j < left || j > right)
					field[i][j].updateType(HexagonType.DEADZONE);
				else if (j == left || j == right)
					field[i][j].updateType(HexagonType.WALL);
				else if (i == 0 || i == FIELD_HEIGHT - 1)
					field[i][j].updateType(HexagonType.WALL);
				else
					field[i][j].updateType(HexagonType.EMPTY);
			}
		}
		//humanSnake = new Snake(start_r, start_c, Color.CYAN);
		//for (int i = 0; i < FIELD_HEIGHT; i++)
		//	for (int j = 0; j < FIELD_WIDTH; j++)
		//		field[i][j].updateType(HexagonType.EMPTY);
		//field[start_r][start_c].updateType(HexagonType.WALL);
	}
	
	// the frame starts!
	public void trigger(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		//humanSnake.move();
		for (int i = 0; i < FIELD_HEIGHT; i++)
			for (int j = 0; j < FIELD_WIDTH; j++)
				field[i][j].draw(canvas);
		//field[start_r][start_c].draw(canvas);
	}
	
	// the screen detect the user swipes
	public void passUserInputSwipe(int dx, int dy) {
		double r2 = (double)(dx*dx + dy*dy);
		if (r2 >= swipeEffectiveDistance2) {
			double angle = Math.atan2(-dy, dx) + Math.PI / 6.0;
			if (angle < 0.0)
				angle += Math.PI * 2.0;
			int newDir = (int)(angle / (Math.PI / 3.0));
			Log.i(TAG, "get direction: " + newDir);
			//snake.setDirection(newDir);
		}
	}
}
