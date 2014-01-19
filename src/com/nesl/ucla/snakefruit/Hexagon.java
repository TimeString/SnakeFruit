package com.nesl.ucla.snakefruit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Hexagon {
	Bitmap bitmap;
	private int r, c;
	private Paint p;
	private HexagonType type;
	private double px, py;
	public static int BITMAP_HEIGHT = 28;
	public static int BITMAP_WIDTH = (int) (BITMAP_HEIGHT * Math.sqrt(3) / 2);
	
	// calculate the vertex coordinate of the hexagon
	private static double[] vertexCoorX = new double[6];
	private static double[] vertexCoorY = new double[6];
	
	public Hexagon(int r, int c) {
		// do some initialize
		this.r = r;
		this.c = c;
		px = x2px();
		py = y2py();
		p = new Paint();
		p.setColor(0);
		bitmap = Bitmap.createBitmap(BITMAP_HEIGHT, BITMAP_WIDTH, Bitmap.Config.ARGB_8888);
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
	
	private double x2px() {
		return 0; //TODO
	}
	
	private double y2py() {
		return 0;  //TODO
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
//		canvas.drawLine(px+width, py + h/4.0, px+width/2.0, py, this.paith);
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
			break;
		case DEADZONE:
			break;
		}
		drawHex(canvas);
	}
}
