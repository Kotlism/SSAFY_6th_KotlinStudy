package com.ssafy.algorithm.boj_9935_문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static String origin, bomb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		origin = br.readLine();
		bomb = br.readLine();
		
		Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < origin.length(); i++) {
			stack.push(origin.charAt(i));
			if(stack.size() >= bomb.length()) {
				boolean tri = true;				
				for (int j = 0; j < bomb.length(); j++) {
					if(bomb.charAt(j) != stack.elementAt(stack.size()-bomb.length()+j)) {
						tri = false;
						break;
					}
				}
				
				if(tri) {
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		
		if(stack.size() == 0) System.out.println("FRULA");
		else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.elementAt(i));
			}
			System.out.println(sb);
		}
	}
}
