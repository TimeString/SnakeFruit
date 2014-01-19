package com.nesl.ucla.snakefruit;

import android.util.Log;

public class AIControlSnake extends Snake {
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private int id = 0;
	private int mark[][];
	private int dir[][];
	private int que[];
	private int qb, qe;
	
	public AIControlSnake(int initRow, int initCol, int initDir, int initColor,
			FruitDeliver _fruitDeliver) {
		super(initRow, initCol, initDir, initColor, _fruitDeliver);
		mark = new int[GamePlanner.FIELD_HEIGHT][GamePlanner.FIELD_WIDTH];
		dir = new int[GamePlanner.FIELD_HEIGHT][GamePlanner.FIELD_WIDTH];
		que = new int[GamePlanner.FIELD_HEIGHT * GamePlanner.FIELD_WIDTH];
		for (int i = 0; i < GamePlanner.FIELD_HEIGHT; i++)
			for (int j = 0; j < GamePlanner.FIELD_WIDTH; j++)
				mark[i][j] = id;
	}

	@Override
	protected void makeFinalDecision() {
		id++;
		int sr = snakeBody.get(0).getRow();
		int sc = snakeBody.get(0).getCol();
		int sdir = curDirection;
		//Log.i(TAG, "src: " + sr + ", " + sc + " -- " + sdir);
		que[0] = sr * 1000 + sc;
		qb = 0;
		qe = 1;
		mark[sr][sc] = id;
		dir[sr][sc] = sdir;
		int fr = sr, fc = sc;
		while (qb < qe) {
			int nr = que[qb] / 1000;
			int nc = que[qb] % 1000;
			int ndir = dir[nr][nc];
			for (int i = 5; i <= 7; i++) {
				int tdir = (ndir + i) % 6;
				int tr = nr + Utils.DIR_2_R[tdir];
				int tc = nc + Utils.DIR_2_C[tdir];
				if (mark[tr][tc] != id) {
					Hexagon thex = GamePlanner.field[tr][tc];
					if (thex.getType() == HexagonType.EMPTY || thex.getType() == HexagonType.FRUIT) {
						mark[tr][tc] = id;
						dir[tr][tc] = tdir;
						que[qe++] = tr * 1000 + tc;
						//Log.i(TAG, "to " + tr + ", " + tc + " -- " + tdir);
						if (thex.getType() == HexagonType.FRUIT) {
							fr = tr;
							fc = tc;
							//Log.i(TAG, "find fruit: " + fr + ", " + fc);
							qb = qe;
							break;
						}
					}
				}
			}
			qb++;
		}
		while (fr != sr || fc != sc) {
			targetDirection = dir[fr][fc];
			int tdir = dir[fr][fc];
			//Log.i(TAG, "frc: " + fr + ", " + fc + " -- " + tdir);
			fr -= Utils.DIR_2_R[tdir];
			fc -= Utils.DIR_2_C[tdir];
		}
		//Log.i(TAG, "decide: " + targetDirection);
	}

}
