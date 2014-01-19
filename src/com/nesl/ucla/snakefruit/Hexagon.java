package com.nesl.ucla.snakefruit;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Hexagon {
	Bitmap bitmap;
	private int r, c;
	private int color;
	private HexagonType type;
	private double px, py;
	private int BITMAP_LENGTH = 28;
	private int BITMAP_WIDTH = 28;
	
	private final double separateFactor = 1.2;
	private final double r2x = -BITMAP_HEIGHT * Math.sqrt(3.0) / 4.0 * separateFactor;
	private final double r2y = BITMAP_HEIGHT * 4.0 / 3.0 * separateFactor;
	private final double c2x = BITMAP_HEIGHT * Math.sqrt(3.0) / 2.0 * separateFactor;
	private final double c2y = 0.0 * separateFactor;
	
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
		bitmap = Bitmap.createBitmap(BITMAP_LENGTH, BITMAP_WIDTH, Bitmap.Config.ARGB_8888);
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
	
	public void draw() {
//		createBitmap();
	}
	
	public static double rc2px(int r, int c) {
		return r * r2x + c * c2x;
	}
	
	public static double rc2py(int r, int c) {
		return r * r2y + c * c2y;
	}
	
	private void updateBitmap()
	{
		this.bitmap.eraseColor(Color.TRANSPARENT);
		switch(this.type)
		{
		case EMPTY:
			break;
		}
	}
	public void updateType(HexagonType type)
	{
		this.type = type;
	}
	
	public void updateColor(int color)
	{
		this.color = color;
	}
}
