package com.nesl.ucla.snakefruit;

public class Hexagon {
	private int x, y;
	private double px, py;
	
	// calculate the vertex coordinate of the hexagon
	private static double[] vertexCoorX = new double[6];
	private static double[] vertexCoorY = new double[6];
	
	public Hexagon(int _x, int _y) {
		// do some initialize
		px = x2px();
		py = y2py();
	}
	
	public void draw() {
		// TODO
	}
	
	private double x2px() {
		return 0; //TODO
	}
	
	private double y2py() {
		return 0;  //TODO
	}
}
