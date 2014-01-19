package com.nesl.ucla.snakefruit;

import android.graphics.Bitmap;

public class Hexagon {
	Bitmap bitmap;
	private int r, c;
	private int color;
	private HexagonType type;
	private double px, py;
	private int BITMAP_LENGTH = 28;
	private int BITMAP_WIDTH = 28;
	
	// calculate the vertex coordinate of the hexagon
	private static double[] vertexCoorX = new double[6];
	private static double[] vertexCoorY = new double[6];
	
	public Hexagon(int r, int c) {
		// do some initialize
		this.r = r;
		this.c = c;
		px = x2px();
		py = y2py();
		bitmap = Bitmap.createBitmap(BITMAP_LENGTH, BITMAP_WIDTH, Bitmap.Config.ARGB_8888);
	}
	
	public int getrow()
	{
		return this.r;
	}
	
	public int getcolumn()
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
		this.color = color;
	}
}
