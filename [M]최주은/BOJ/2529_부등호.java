package com.ssafy.algorithm.boj_2529_부등호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static boolean[] visited = new boolean[10];
	public static char[] arr;
	public static int[] nums;
	public static int K;
	public static Long max = 0L, min = Long.MAX_VALUE;
	public static String sMax, sMin;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		arr = new char[K];
		nums = new int[K+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		for (int i = 0; i < 10; i++) {
			nums[0] = i;
			visited[i] = true; 
			dfs(1);
			visited[i] = false;
		}
		System.out.println(sMax);
		System.out.println(sMin);
	}
	
	public static void dfs(int cnt) {
		if(cnt == nums.length) {
			String tmp = "";
			for (int i = 0; i < nums.length; i++) {
				tmp += nums[i];
			}
			
			Long value = Long.parseLong(tmp);
//			max = max > value ? max : value;
			if(max < value) {
				max = value;
				sMax = tmp;
			}
//			min = min < value ? min : value;
			if(min > value) {
				min = value;
				sMin = tmp;
			}
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			boolean tri = false;
			
			switch(arr[cnt-1]) {
			case '<':
				if (nums[cnt-1] < i) tri = true; 
				break;
			case '>':
				if (nums[cnt-1] > i) tri = true;
				break;
			}
			
			if(!visited[i] && tri) {
				visited[i] = true;
				nums[cnt] = i;
				dfs(cnt+1);
				visited[i] = false;
			}
		}
	}
}
