package com.ssafy.algorithm.boj_20924_트리의기둥과가지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static int N, R, resBody, resLeaf;
	public static ArrayList<Node>[] tree;
	public static boolean[] visited;
	
	public static class Node{
		int num, edge;
		public Node(int n, int e){
			this.num = n;
			this.edge = e;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		tree = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i = 0; i <= N; i++) tree[i] = new ArrayList<Node>();
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			tree[a].add(new Node(b, d));
			tree[b].add(new Node(a, d));
		}
		
		if(tree[R].size() > 1) dfs(R, 0, false);
		else dfs(R, 0, true);
		
		System.out.printf("%d %d", resBody, resLeaf);
	}

	private static void dfs(int n, int d, boolean isBody) {
		visited[n] = true;
		
		if(tree[n].size() == 1 && n != R) {
			if(isBody) resBody = resBody > d ? resBody : d;
			else resLeaf = resLeaf > d ? resLeaf : d;
			return;
		}
		
		if(isBody) {
			if(tree[n].size() > 2) {
				resBody = d;
				dfs(n, 0, false);
			}
			else {
				for(Node node : tree[n]) {
					if(visited[node.num]) continue;
					dfs(node.num, d+node.edge, isBody);
				}
				
			}
			
		}else {
			for(Node node : tree[n]) {
				if(visited[node.num]) continue;
				dfs(node.num, d+node.edge, isBody);
			}
		}
	}
}
