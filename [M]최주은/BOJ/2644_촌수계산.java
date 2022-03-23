package boj_2644_촌수계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, res = -1;
	public static int t1, t2;
	public static ArrayList<Integer>[] list; 
	public static boolean[] visited;
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		t1 = Integer.parseInt(st.nextToken());
		t2 = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			list[p].add(s);
			list[s].add(p);
		}
		
		dfs(t1, 0);
		System.out.println(res);
	}

	private static void dfs(int n, int t) {
		if(visited[n]) return;
		visited[n] = true;
		
		for (int i = 0; i < list[n].size(); i++) {
			if(list[n].get(i) == t2) {
				System.out.println(t+1);
				System.exit(0);
			}
			
			dfs(list[n].get(i), t+1);
		}
	}
}
