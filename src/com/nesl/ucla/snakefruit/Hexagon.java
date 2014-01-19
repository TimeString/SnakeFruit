package com.nesl.ucla.snakefruit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Hexagon {
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private int r, c;
	private Paint p;
	private HexagonType type;
	private double px, py;
	public static int BITMAP_HEIGHT = 28;
	public static int BITMAP_WIDTH = (int) (BITMAP_HEIGHT * Math.sqrt(3) / 2);
	
	private static final double separateFactor = 1.0;
	private static final double r2x = -BITMAP_HEIGHT * Math.sqrt(3.0) / 4.0 * separateFactor;
	private static final double r2y = BITMAP_HEIGHT / 4.0 * 3.0 * separateFactor;
	private static final double c2x = BITMAP_HEIGHT * Math.sqrt(3.0) / 2.0 * separateFactor;
	private static final double c2y = 0.0 * separateFactor;
	
	// calculate the vertex coordinate of the hexagon
	private static double[] vertexCoorX = new double[6];
	private static double[] vertexCoorY = new double[6];
	
	public static double OFFSET_X;
	public static double OFFSET_Y;
	
	public Hexagon(int r, int c) {
		// do some initialize
		this.r = r;
		this.c = c;

		px = rc2px(r, c) + OFFSET_X;
		py = rc2py(r, c) + OFFSET_Y;
		p = new Paint();
		p.setColor(Color.WHITE);
	}
	
	public int getRow()
	{
		return this.r;
	}
	
	public int getCol()
	{
		return this.c;
	}
	
	public HexagonType getType()
	{
		return type;
	}
	
	public void draw(Canvas canvas) {
		updateBitmap(canvas);
		
	}
	
	public static double rc2px(int r, int c) {
		return r * r2x + c * c2x;
	}
	
	public static double rc2py(int r, int c) {
		return r * r2y + c * c2y;
	}
	
	public void updateType(HexagonType type)
	{
		this.type = type;
	}
	
	public void updateColor(int color)
	{
		p.setColor(color);
	}

	private void drawHex(Canvas canvas)
	{
		//Log.i(TAG, "drawHex");
		Bitmap bitmap = Bitmap.createBitmap(BITMAP_WIDTH + 1, BITMAP_HEIGHT + 1, Bitmap.Config.ARGB_8888);
		Canvas tmp = new Canvas(bitmap);
		tmp.drawLine((float) BITMAP_WIDTH, (float) (BITMAP_HEIGHT/4.0), (float) (BITMAP_WIDTH/2.0), (float) 0, this.p);
		tmp.drawLine((float) (BITMAP_WIDTH/2.0), (float) 0, (float) 0, (float) (BITMAP_HEIGHT/4.0), this.p);
		tmp.drawLine( (float) 0, (float) (BITMAP_HEIGHT/4.0), (float) 0, (float) (3 * BITMAP_HEIGHT / 4.0), this.p);
		tmp.drawLine( (float) 0, (float) (3 * BITMAP_HEIGHT / 4.0), (float) (BITMAP_WIDTH/2), (float) BITMAP_HEIGHT, this.p);
		tmp.drawLine((float) (BITMAP_WIDTH/2), (float) BITMAP_HEIGHT, (float) BITMAP_WIDTH, (float) (3 * BITMAP_HEIGHT / 4.0), this.p);
		tmp.drawLine((float) BITMAP_WIDTH, (float) (3 * BITMAP_HEIGHT / 4.0), (float) BITMAP_WIDTH, (float) (BITMAP_HEIGHT/4.0), this.p);
		//Log.i(TAG, "coor: " + px + ", " + py);
		//Log.i(TAG,  "r2x: " + r2x);
		//Log.i(TAG,  "r2y: " + r2y);
		//Log.i(TAG,  "c2x: " + c2x);
		//Log.i(TAG,  "c2y: " + c2y);
		canvas.drawBitmap(bitmap, (float)px, (float)py, this.p);
	}
	
	private void updateBitmap(Canvas canvas)
	{
		switch(this.type)
		{
		case EMPTY:
			break;
		case HEAD_LEFT:
			break;
		case HEAD_RIGHT:
			break;
		case HEAD_UPPER_LEFT:
			break;
		case HEAD_UPPER_RIGHT:
			break;
		case HEAD_LOWER_LEFT:
			break;
		case HEAD_LOWER_RIGHT:
			break;
		case FRUIT:
			break;
		case WALL:
			drawHex(canvas);
			break;
		case DEADZONE:
			break;
		}
		
	}
}
