package com.nesl.ucla.snakefruit;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

public class GamePlanner {
	private FruitDeliver fruitDeliver;
	private int nSnakes = 3;
	private Snake[] snakes = new Snake[nSnakes];
	//private Score score;
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	public static int FIELD_WIDTH = 53;
	public static int FIELD_HEIGHT = 43;
	public static Hexagon[][] field; 
	
	private int start_r = 0;
	private int start_c = 0;
	
	private final double swipeEffectiveDistance = 30.0;
	private final double swipeEffectiveDistance2 = swipeEffectiveDistance * swipeEffectiveDistance;
	
	private int width, height;
	
	private long lastUpdateTime = 0;
	private final long updateFrameMilliScond = 200;
	
	public GamePlanner(int _width, int _height) {
		width = _width;
		height = _height;
		FIELD_WIDTH = width / (Hexagon.BITMAP_WIDTH + 2);
		FIELD_HEIGHT = height / (Hexagon.BITMAP_HEIGHT + 2);
		start_r = FIELD_HEIGHT / 2;
		start_c = FIELD_WIDTH / 2;
		field = new Hexagon[FIELD_HEIGHT][FIELD_WIDTH];
		Hexagon.OFFSET_X = (int)(width / 2.0 + Hexagon.rc2px(-FIELD_HEIGHT / 2, - FIELD_WIDTH / 2));
		Hexagon.OFFSET_Y = (int)(height / 2.0 + Hexagon.rc2py(-FIELD_HEIGHT / 2, - FIELD_WIDTH / 2));
		Hexagon.generateCoor();
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
		fruitDeliver = new FruitDeliver(this);
		fruitDeliver.generateNewOne();
		snakes[0] = new HumanControlSnake(start_r - 5, start_c - 10, 0, Color.CYAN, fruitDeliver);
		snakes[1] = new AIControlSnake(start_r + 5, start_c + 10, 3, Color.YELLOW, fruitDeliver);
		snakes[2] = new AIControlSnake(start_r + 5, start_c - 10, 2, Color.RED, fruitDeliver);
		
		//for (int i = 0; i < FIELD_HEIGHT; i++)
		//	for (int j = 0; j < FIELD_WIDTH; j++)
		//		field[i][j].updateType(HexagonType.EMPTY);
		//field[start_r][start_c].updateType(HexagonType.WALL);
	}
	
	// the frame starts!
	public void trigger(Canvas canvas) {
		long now = System.currentTimeMillis();
		long delta = now - lastUpdateTime;
		if (delta >= updateFrameMilliScond) {
			// update time
			long ahead = delta - updateFrameMilliScond;
			if (ahead > updateFrameMilliScond)
				ahead = updateFrameMilliScond;
			lastUpdateTime = now - ahead;
			for (int i = 0; i < nSnakes; i++) {
				if (snakes[i] != null)
					snakes[i].move();
			}
			fruitDeliver.generateNewOne();
		}
		
		//Log.i(TAG, "trigger");
		canvas.drawColor(Color.BLACK);
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
			//Log.i(TAG, "get direction: " + newDir);
			((HumanControlSnake)snakes[0]).setDirection(newDir);
		}
	}
}
