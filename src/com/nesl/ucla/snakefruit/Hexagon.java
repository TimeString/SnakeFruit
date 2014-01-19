package com.nesl.ucla.snakefruit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

public class Hexagon {
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	Bitmap bitmap;
	Canvas tmp;
	private int r, c;
	private Paint p;
	private HexagonType type;
	private double px, py;
	public static int BITMAP_HEIGHT = 28;
	public static int BITMAP_WIDTH = (int) (BITMAP_HEIGHT * Math.sqrt(3) / 2);
	
	private Path path;
	private Direction direction;
	
	private static final double separateFactor = 1.0;
	private static final double r2x = -BITMAP_HEIGHT * Math.sqrt(3.0) / 4.0 * separateFactor;
	private static final double r2y = BITMAP_HEIGHT / 4.0 * 3.0 * separateFactor;
	private static final double c2x = BITMAP_HEIGHT * Math.sqrt(3.0) / 2.0 * separateFactor;
	private static final double c2y = 0.0 * separateFactor;
	
	// calculate the vertex coordinate of the hexagon
	private static float[] X = new float[6];
	private static float[] Y = new float[6];
	
	public static double OFFSET_X;
	public static double OFFSET_Y;
	
	public Hexagon(int r, int c) {
		// do some initialize
		this.r = r;
		this.c = c;
		this.direction = Direction.RIGHT;
		bitmap = Bitmap.createBitmap(BITMAP_WIDTH + 1, BITMAP_HEIGHT + 1, Bitmap.Config.ARGB_8888);
		tmp = new Canvas(bitmap);
		
		path = new Path();
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

//	public void update
	private void generateCoor()
	{
		X[0] = (float) BITMAP_WIDTH;
		Y[0] = (float) (BITMAP_HEIGHT/4.0);
		X[1] = (float) (BITMAP_WIDTH/2.0);
		Y[1] = (float) 0;
		X[2] = (float) 0;
		Y[2] = (float) (BITMAP_HEIGHT/4.0);
		X[3] = 0;
		Y[3] = (float) (3 * BITMAP_HEIGHT / 4.0);
		X[4] = (float) (BITMAP_WIDTH/2.0);
		Y[4] = (float) BITMAP_HEIGHT;
		X[5] = (float) BITMAP_WIDTH;
		Y[5] = (float) (3 * BITMAP_HEIGHT / 4.0);
	}

	void drawWall(Canvas canvas)
	{
		p.setStyle(Paint.Style.FILL);
		p.setAntiAlias(true);
		generateCoor();
		path.moveTo(((float)px)+ X[0], ((float)py) + Y[0]);
		for (int i = 0; i < 6; i++) {
					path.lineTo(((float)px) + X[(i+1)%6],  ((float)py) + Y[(i+1)%6]);
			}
		path.close();
		canvas.drawPath(path, this.p);
	}
	
	public void updateDir(int dir)
	{
//		this.direction = dir;
	}
	
	private void drawHex(Canvas canvas)
	{
		bitmap.eraseColor(Color.TRANSPARENT);
		generateCoor();
		for (int i = 0; i < 6; i++)
			tmp.drawLine(X[(i)%6], Y[(i)%6], X[(i+1)%6], Y[(i+1)%6], this.p);
		canvas.drawBitmap(bitmap, (float)px, (float)py, this.p);
	}
	
	private void drawHead(Canvas canvas)
	{
		drawHex(canvas);
		tmp.drawOval(new RectF(4, 4, 9, 9), p);
		canvas.drawBitmap(bitmap, (float)px, (float)py, this.p);
	}
	private void updateBitmap(Canvas canvas)
	{
		switch(this.type)
		{
		case EMPTY:
			break;
		case HEAD:
			drawHead(canvas);
			break;
		case BODY:
			drawHex(canvas);
		case FRUIT:
			break;
		case WALL:
 			drawWall(canvas);
			break;
		case DEADZONE:
			break;
		}
		
	}
}
