package com.ssafy.algorithm.boj_1074_Z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int res;
	public static void main(String[] args) throws IOException{
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		// 계산
		divideConquer(N, r, c);
		
		// 출력
		System.out.println(res);
	}
	
	public static void divideConquer(int n, int r, int c) {
		int b = (int) Math.pow(2, n-1); // Boundary
		
		if(n > 1) {
			if(r < b && c < b) {
				// 제1사분면
				divideConquer(n-1, r, c);
			}else if(r < b && c >= b) {
				// 제2사분면
				res += Math.pow(4, n-1);
				divideConquer(n-1, r, c-b);
			}else if(r >= b && c < b) {
				// 제3사분면
				res += (Math.pow(4, n-1) * 2);
				divideConquer(n, r-b, c);
			}else {
				// 제4사분면
				res += (Math.pow(4, n-1) * 3);
				divideConquer(n, r-b, c-b);
			}
		}else res += ( 2 * r + c);
	}
}
