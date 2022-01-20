package boj_1717_집합의표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] group;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		group = new int[N+1];
		for (int i = 0; i <= N; i++) {
			group[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int m = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(m == 0) union(a, b);
			else {
				int p1 = find(a);
				int p2 = find(b);
				
				if(p1 == p2) System.out.println("YES");
				else System.out.println("NO");
			}
		}
	}
	
	public static void union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);
		
		if(p1 < p2) group[p2] = p1;
		else group[p1] = p2;
	}
	
	public static int find(int p) {
		if(p == group[p]) return p;
		return group[p] = find(group[p]);
	}
}
