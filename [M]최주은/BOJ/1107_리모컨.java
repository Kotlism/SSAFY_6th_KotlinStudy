package com.ssafy.algorithm.boj_1107_리모콘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int target, broken, res;
	public static boolean[] able = new boolean[1000001];
	public static boolean[] visited = new boolean[10];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		target = Integer.parseInt(br.readLine());
		
		// 100번 채널이면 움직일 필요가 없음.
		if(target == 100) {
			System.out.println(0);
			System.exit(0);
		}
		
		broken = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		if(broken != 0) st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 10; i++) {
			able[i] = true;
		}
		
		for (int i = 0; i < broken; i++) {
			int channel = Integer.parseInt(st.nextToken());
			able[channel] = false;
		}
		
		// 계산
		res = Math.abs(target - 100); // 100번 채널에서 이동했을때
		
		// 조합을 통해 이동 가능한 채널을 모두 적는다.
		comb(0, 0);
		
		for (int i = 0; i < able.length; i++) {
			if(able[i]) {
				int val = Math.abs(target-i) + Integer.toString(i).length();
				res = res < val ? res : val;
			}
		}
		
		System.out.println(res);
	}

	private static void comb(int dept, int value) {
		if(value > 1000000 || dept > 7) return;
		if(dept!=0) able[value] = true;
		
		for (int i = 0; i < 10; i++) {
			if(able[i]) {
				comb(dept+1, (value*10+i));
			}
		}
	}
}
