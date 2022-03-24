package boj_23040_누텔라트리Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static long res;
	public static char[] str;
	public static ArrayList<Integer>[] list;
	public static int[] visited;
	public static long[] dp;
	public static Stack<Integer> nutellaStack = new Stack<Integer>();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = new char[N+1];
		list = new ArrayList[N+1];
		visited = new int[N+1];
		dp = new long[N+1];
		
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			list[v1].add(v2);
			list[v2].add(v1);
		}
		
		StringBuilder sb = new StringBuilder(br.readLine());
		for (int i = 1; i <= sb.length(); i++) {
			str[i] = sb.charAt(i-1);
		}
		
		for (int i = 1; i <= N; i++) {
			if(str[i] == 'B') res += dfs(i, i);
		}
		
		System.out.println(res);
	}

	private static long dfs(int idx, int v) {
		if(dp[idx] > 0L && str[idx] != 'B') return dp[idx];
		if(str[idx] != 'B') nutellaStack.add(idx);
		
		visited[idx] = v;
		long ret = 0L;
		
		for (int i = 0; i < list[idx].size(); i++) {
			int nextIdx = list[idx].get(i);
			if(str[nextIdx] == 'B' || visited[nextIdx] == v) continue;
			long cnt = dfs(nextIdx, v);
			ret += cnt + 1L;
			if(str[idx] == 'B')
				while(!nutellaStack.isEmpty()) dp[nutellaStack.pop()] = cnt;
		}
		
		return ret;
	}
}
