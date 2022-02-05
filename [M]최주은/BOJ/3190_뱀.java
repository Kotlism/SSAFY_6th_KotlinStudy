package com.ssafy.algorithm.boj_3190_뱀;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static int N,K,L;
	public static int[][] map;
	public static Deque<Point> deque = new ArrayDeque<Point>();
	
	public static int[] dy = {0, 1, 0, -1};
	public static int[] dx = {1, 0, -1, 0};
	
	public static char[] r = new char[10001];
			
	public static void main(String[] args) throws IOException{
		int res = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		deque.add(new Point(0,0));
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			
			map[col-1][row-1] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		

		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			r[t] = st.nextToken().charAt(0);
		}
		
		int dir = 0;
		while(true) {
			res++;
			int y = deque.getLast().y + dy[dir];
			int x = deque.getLast().x + dx[dir];
			Point p = new Point(x, y);
			
			if(y < 0 || y >= N || x < 0 || x >= N || deque.contains(p)) {
				break;
			}
			
			deque.add(p);
			if(map[y][x] != 1) deque.removeFirst();
			else map[y][x] = 0;
			
			if(r[res] == 'D') dir = (dir+1)%4;
			if(r[res] == 'L') dir = (dir+3)%4;
		}
		
		System.out.println(res);
	}
}
