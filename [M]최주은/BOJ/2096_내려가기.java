package com.ssafy.algorithm.boj_2096_내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[][] map;
	public static int[] dx = {-1, 0, 1}, min, max;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		min = new int[3];
		max = new int[3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min[0] = max[0] = map[0][0];
		min[1] = max[1] = map[0][1];
		min[2] = max[2] = map[0][2];
		
		for (int i = 1; i < N; i++) {
			int[] mMax = new int[3];
			int[] mMin = new int[3];
			
			mMin[0] = Math.min(min[0], min[1]) + map[i][0];
			mMin[1] = Math.min(Math.min(min[0], min[1]), min[2]) + map[i][1];
			mMin[2] = Math.min(min[1], min[2]) + map[i][2];
			min = mMin;
			
			mMax[0] = Math.max(max[0], max[1]) + map[i][0];
			mMax[1] = Math.max(Math.max(max[0], max[1]), max[2]) + map[i][1];
			mMax[2] = Math.max(max[1], max[2]) + map[i][2];
			max = mMax;
		}
		
		int dmax = Math.max(Math.max(max[0], max[1]), max[2]);
		int dmin = Math.min(Math.min(min[0], min[1]), min[2]);
		System.out.printf("%d %d", dmax, dmin);
	}
}
