package com.ssafy.algorithm.boj_14891_톱니바퀴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static LinkedList<Integer>[] wheels = new LinkedList[4];
	public static int K, res;
	public static Queue<macro> queue = new LinkedList<>();
	
	public static class macro{
		int idx, dir;
		public macro(int i, int d) {
			this.idx = i;
			this.dir = d;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; wheels[i++] = new LinkedList<>());	
		
		// 입력
		for (int i = 0; i < 4; i++) {
			StringBuilder sb = new StringBuilder(br.readLine());
			for (int j = 0; j < 8; j++) {
				wheels[i].add(sb.charAt(j)-'0');
			}
		}
		
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int idx = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			
			process(idx, dir, -1);
			while(!queue.isEmpty()) {
				macro m = queue.poll();
				
				// 화전
				if(m.dir == 1) wheels[m.idx].addFirst(wheels[m.idx].removeLast());
				else wheels[m.idx].addLast(wheels[m.idx].removeFirst());
			}
		}
		
		for (int i = 0; i < 4; i++) {
			int mul = (int) Math.pow(2, i);
			if(wheels[i].peekFirst() == 1) res += mul;
		}
		
		System.out.println(res);
	}

	private static void process(int idx, int dir, int preIdx) {
		queue.add(new macro(idx, dir));
		
		// 오른쪽의 극이 무엇인지 확인
		int right = wheels[idx].get(2);
		int left = wheels[idx].get(6);
		
		// 왼쪽
		if((preIdx == -1 || preIdx > idx) && idx-1 >= 0) {
			int otherRight = wheels[idx-1].get(2);
			if(left != otherRight) process(idx-1, dir == 1 ? -1 : 1, idx);
		}
		
		// 오른쪽
		if((preIdx == -1 || preIdx < idx) && idx+1 < 4) {
			int otherLeft = wheels[idx+1].get(6);
			if(right != otherLeft) process(idx+1, dir == 1 ? -1 : 1, idx);
		}
	}
}
