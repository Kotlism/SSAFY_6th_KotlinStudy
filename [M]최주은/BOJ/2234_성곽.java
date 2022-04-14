package com.ssafy.algorithm.boj_2234_성곽;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, roomCnt, maxRoom, maxRoom2;
	public static int map[][];
	public static int dir[][] = { { 1, 0, -1 }, { 2, -1, 0 }, { 4, 0, 1 }, { 8, 1, 0 } };
	public static boolean visited[][];
	public static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 가장큰 방 찾기 및 룸 갯수 구하기
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					q.offer(new Point(j, i));
					roomCnt++;
					int cnt = bfs();
					maxRoom = maxRoom < cnt ? cnt : maxRoom;
				}
			}
		}

		// 가장큰 방 찾기 및 룸 갯수 구하기
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				visited = new boolean[M][N];
				for (int k = 0; k < 4; k++) {
					if((map[i][j] & dir[k][0]) == dir[k][0]) {
						visited[i][j] = true;
						map[i][j] = map[i][j] - dir[k][0];
						q.offer(new Point(j, i));
						int cnt = bfs();
						maxRoom2 = maxRoom2 < cnt ? cnt : maxRoom2;
						map[i][j] = map[i][j] + dir[k][0];
					}
				}
			}
		}

		System.out.println(roomCnt);
		System.out.println(maxRoom);
		System.out.println(maxRoom2);
	}

	private static int bfs() {
		int cnt = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				// 벽 유무 검사
				if ((map[p.y][p.x] & dir[i][0]) != dir[i][0]) {
					int yy = p.y + dir[i][1];
					int xx = p.x + dir[i][2];

					if (xx < 0 || xx >= N || yy < 0 || yy >= M || visited[yy][xx]) continue;

					cnt++;
					visited[yy][xx] = true;
					q.offer(new Point(xx, yy));
				}
			}
		}

		return cnt;
	}
}
