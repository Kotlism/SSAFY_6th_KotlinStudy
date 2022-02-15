package com.ssafy.algorithm.boj_10164_격자상의경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N,M,K;
	public static int[][] map;
	public static int[][] dp;
	
	public static int[] dy = {0, 1};
	public static int[] dx = {1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		
		int dx = 0, dy = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = (i-1)*M + j;
				if(map[i][j] == K) {
					dy = i;
					dx = j;
				}
			}
		}
		
		if(K == 0) System.out.println(comb(1, 1, M, N));
		else System.out.println(comb(1, 1, dx, dy)*comb(dx, dy, M, N));
	}

	private static int comb(int x, int y, int destX, int destY) {
		if(x == destX && y == destY) return 1;		

		for (int i = 0; i < 2; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			
			if(xx < 0 || xx > destX || yy < 0 || yy > destY) continue;
			
			if(dp[yy][xx] != 0) dp[y][x] += dp[yy][xx];
			else dp[y][x] += comb(xx, yy, destX, destY);
		}
		
		return dp[y][x];
	}
}
