package com.ssafy.algorithm.boj_3079_입국심사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static long max;
	public static long[] time;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		time = new long[N];
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(br.readLine());
			if(time[i] > max) max = time[i];
		}
		
		long left = 0;
		long right = max*M; // 60
		
		while(left <= right) {
			long mid = (left + right)/2; // 30
			long ppl = 0L;
			for (int i = 0; i < N; i++) {
				ppl += mid/time[i];
			}
			
			if(ppl < M) left = mid + 1;
			else right = mid - 1;
		}
		
		System.out.println(left);
	}
}
