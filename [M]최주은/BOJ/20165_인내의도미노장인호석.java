package com.ssafy.algorithm.boj_20165_인내의도미노장인호석;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, R, score;
	public static int[][] map;
	public static boolean[][] board;
	public static int[] dy = { -1, 1, 0, 0};
	public static int[] dx = { 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		board = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			String d = st.nextToken();
			domino(x, y, d);
			
			st = new StringTokenizer(br.readLine(), " ");
			y = Integer.parseInt(st.nextToken())-1;
			x = Integer.parseInt(st.nextToken())-1;
			board[y][x] = false;
		}
		
		System.out.println(score);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j]) System.out.printf("F ");
				else System.out.printf("S ");
			}
			System.out.println();
		}
	}

	private static void domino(int x, int y, String d) {
		if(board[y][x]) return;
		int dd = 0;
		
		switch(d) {
		case "E" :
			dd = 3;
			break;
		case "W" :
			dd = 2;
			break;
		case "S" :
			dd = 1;
			break;
		case "N" :
			dd = 0;
			break;
		}
		
		process(x, y, map[y][x], dd);
	}

	private static void process(int x, int y, int h, int d) {
		if(x < 0 || x >= M || y < 0 || y >= N) return;
		
		int xx = x + dx[d];
		int yy = y + dy[d];
		
		if(h >= 0) {
			board[y][x] = true;
			score++;
			process(xx, yy, h-1, d);
		}else {
			if(board[y][x]) return;
			board[y][x] = true;
			score++;
			process(xx, yy, h, d);
		}
	}
}
