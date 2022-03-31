package com.ssafy.algorithm.boj_1240_노드사이의거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, res;
	public static int arr[][];
	public static int distance[][];
	public static boolean visited[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		distance = new int[N+1][N+1];
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			arr[a][b] = d;
			arr[b][a] = d;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			visited = new boolean[N+1];
			res = 0;
			visited[a] = true;
			find(a, b, 0);
			System.out.println(res);
		}
	}

	private static void find(int a, int b, int d) {
		if(a == b) {
			res = d;
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if(!visited[i] && arr[a][i] > 0) {
				visited[i] = true;
				find(i, b, d + arr[a][i]);
			}
		}
	}
}
