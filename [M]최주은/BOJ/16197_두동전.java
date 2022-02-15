package com.ssafy.algorithm.boj_16197_두동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static char[][] map;
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	
	public static class Point{
		int x1, y1, x2, y2, d;
		public Point(int x, int y, int d) {
			this.x1 = x;
			this.y1 = y;
			this.d = d;
		}
		
		public void set(int x, int y) {
			this.x2 = x;
			this.y2 = y;
		}
	}
	
	public static Queue<Point> queue = new LinkedList<Point>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		Point init = null;
		
		for (int i = 0; i < N; i++) {
			StringBuilder sb = new StringBuilder(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = sb.charAt(j);
				if(map[i][j] == 'o') {
					if(init == null) {
						init = new Point(j, i, 0);
					}else {
						init.x2 = j;
						init.y2 = i;
					}
				}
			}
		}
		
		queue.add(init);
		
		while(!queue.isEmpty()) {			
			Point p = queue.poll();
			
			if(p.d >= 10) {
				System.out.println(-1);
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				boolean first = false, second = false;
				
				int yy1 = p.y1 + dy[i];
				int xx1 = p.x1 + dx[i];
				
				if(xx1 < 0 || xx1 >= M || yy1 < 0 || yy1 >= N) first = true;
				
				int yy2 = p.y2 + dy[i];
				int xx2 = p.x2 + dx[i];
				
				if(xx2 < 0 || xx2 >= M || yy2 < 0 || yy2 >= N) second = true;
				
				if(first ^ second) {
					System.out.println(p.d+1);
					System.exit(0);
				}
				
				if(!first && !second) {
					Point coin = null;
					
					if(map[yy1][xx1] == '#') coin = new Point(p.x1, p.y1, p.d+1);
					else coin = new Point(xx1, yy1, p.d+1);
					
					if(map[yy2][xx2] == '#') coin.set(p.x2, p.y2);
					else coin.set(xx2, yy2);
					
					queue.add(coin);
				}
			}
		}
	}
}
