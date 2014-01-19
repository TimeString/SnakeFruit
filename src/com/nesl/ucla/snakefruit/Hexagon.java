package com.nesl.ucla.snakefruit;

import android.graphics.Bitmap;

public class Hexagon {
	Bitmap bitmap;
	private int x, y;
	private int color;
	private HexagonType type;
	private double px, py;
	
	// calculate the vertex coordinate of the hexagon
	private static double[] vertexCoorX = new double[6];
	private static double[] vertexCoorY = new double[6];
	
	public Hexagon(int r, int c) {
		// do some initialize
		px = x2px();
		py = y2py();
	}
	
	public void draw() {
		createBitmap();
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
