package com.ssafy.algorithm.boj_16929_twodots;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static char[][] map;
	public static boolean[][] visited;
	public static int[] dy = { -1, 1, 0, 0 };
	public static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			StringBuilder sb = new StringBuilder(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = sb.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited = new boolean[N][M];
				bfs(i, j, i, j, 0);
			}
		}

		System.out.println("No");
	}

	private static void bfs(int sy, int sx, int ny, int nx, int d) {
		visited[ny][nx] = true;
		
		for (int i = 0; i < 4; i++) {
			int yy = ny + dy[i];
			int xx = nx + dx[i];

			if (xx < 0 || xx >= M || yy < 0 || yy >= N || map[yy][xx] != map[sy][sx]) continue;

			if(yy == sy && xx == sx && d >= 2) {
				System.out.println("Yes");
				System.exit(0);
			}
			
			if(!visited[yy][xx]) bfs(sy, sx, yy, xx, d+1);
		}
	}
}
