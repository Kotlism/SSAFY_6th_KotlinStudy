package com.ssafy.algorithm.boj_14567_선수과목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[][] map;
	public static int[] res;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		res = new int[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;
		}
		
		for (int i = 0; i < N; i++) {
			System.out.printf("%d ",process(i)+1);
		}
	}

	private static int process(int idx) {
		if(res[idx] != 0) return res[idx];
		for (int i = 0; i < idx; i++) {
			if(map[i][idx] == 1) {
				int value = process(i) + 1;
				res[idx] = res[idx] < value ? value : res[idx];
			}
		}
		return res[idx];
	}
}
