package com.ssafy.algorithm.boj_1976_여행가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N];
		
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		// 그룹화
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int s = Integer.parseInt(st.nextToken());
				if(s == 1) union(i, j);
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		boolean init = false;
		int p = 0;
		
		while(st.hasMoreTokens()) {
			if(!init) {
				p = find(Integer.parseInt(st.nextToken()) - 1);
				init = true;
				continue;
			}
			
			int t = find(Integer.parseInt(st.nextToken()) - 1);
			if( t != p) {
				System.out.println("NO");
				System.exit(0);
			}
		}		
		System.out.println("YES");
	}

	
	private static void union(int i, int j) {
		int a = find(i);
		int b = find(j);
		if(a < b) parent[b] = a;
		else parent[a] = b;
	}


	private static int find(int i) {
		if(parent[i] == i) return i;
		return parent[i] = find(parent[i]);
	}
}
