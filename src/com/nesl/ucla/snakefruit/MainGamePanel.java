package com.nesl.ucla.snakefruit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author impaler
 * This is the main surface that handles the ontouch events and draws
 * the image to the screen.
 */
@SuppressLint("WrongCall")
public class MainGamePanel extends SurfaceView implements
		SurfaceHolder.Callback {

	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private MainThread thread;
	
	private GamePlanner gamePlanner;
	
	private int touchDownX, touchDownY;
	
	public MainGamePanel(Context context) {
		super(context);
		
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		// create the game loop thread
		thread = new MainThread(getHolder(), this);
		
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		//Log.i(TAG, "width=" + getWidth() + ", height=" + getHeight());
		gamePlanner = new GamePlanner(getWidth(), getHeight());
		
		
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			touchDownX = (int)event.getX();
			touchDownY = (int)event.getY();
		} 
		else if (event.getAction() == MotionEvent.ACTION_UP) {
			gamePlanner.passUserInputSwipe((int)event.getX() - touchDownX, (int)event.getY() - touchDownY);
		}
		return true;
	}

	
	
	@Override
	protected void onDraw(Canvas canvas) {
		//Log.i(TAG, "called onDraw()");
		gamePlanner.trigger(canvas);
	}
}
