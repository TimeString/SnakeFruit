package com.nesl.ucla.snakefruit;

public class Utils {
	public static final int DIR_2_R[] = { 0, -1 ,-1,  0,  1,  1};
	public static final int DIR_2_C[] = { 1,  0, -1, -1,  0,  1};
	
	public static int rc_2_dir(int r, int c) {
		for (int i = 0; i < 6; i++)
			if (r == DIR_2_R[i] && c == DIR_2_C[i])
				return i;
		return -1;
	}
}
