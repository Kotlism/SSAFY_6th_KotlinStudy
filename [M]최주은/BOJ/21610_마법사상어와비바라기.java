package com.ssafy.algorithm.boj_21610_마법사상어와비바라기;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int map[][], visited[][];
	public static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static Queue<Point> q = new LinkedList<Point>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 비바라기 시전
		q.offer(new Point(0, N-1));
		q.offer(new Point(1, N-1));
		q.offer(new Point(0, N-2));
		q.offer(new Point(1, N-2));
		
		for (int i = 1; i <= M; i++) {			
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			int len = q.size();
			
			// 각 지점 물 증가
			for (int j = 0; j < len; j++) {
				Point p = q.poll();
				int nx = (p.x + dx[d-1] * s) % N >= 0 ? (p.x + dx[d-1] * s) % N : N + (p.x + dx[d-1] * s) % N;
				int ny = (p.y + dy[d-1] * s) % N >= 0 ? (p.y + dy[d-1] * s) % N : N + (p.y + dy[d-1] * s) % N;
				
				// 구름이 사라질 때 순간(i)를 저장
				visited[ny][nx] = i;
				map[ny][nx]++;
				q.offer(new Point(nx, ny));
			}
			
			// 물복사버그
			for (int j = 0; j < len; j++) {
				Point p = q.poll();
				int cnt = 0;
				
				for (int k = 1; k <= 4; k++) {
					int nx = p.x + dx[k*2-1];
					int ny = p.y + dy[k*2-1];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					
					if(map[ny][nx] > 0) cnt++;
				}
				
				map[p.y][p.x] += cnt; 
			}
			
			// 2 이상인 칸에 구름 생성
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(map[j][k] < 2 || visited[j][k] == i) continue;
					q.offer(new Point(k, j));
					map[j][k] -= 2;
				}
			}
		}
		
		// 물의 합
		int res = 0;
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < N; k++) {
				res += map[j][k];
			}
		}
		
		System.out.println(res);
	}
	
	public static void debug(int idx) {
		if(idx != -1) System.out.printf("=======%d=======\n", idx);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
