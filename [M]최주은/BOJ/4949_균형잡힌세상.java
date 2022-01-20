package boj4949_균형잡힌세상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static Stack<Character> stack = new Stack<Character>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			stack.clear();
			String s = br.readLine();
			boolean trigger = false;
			
			// 종료 조건
			if(s.equals(".")) break;
			
			// 탐색해서 짝이 맞는지 보기
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				
				
				switch(c) {
				case '(':
				case '[':
					stack.add(c);
					break;
				case ')':
					if(!stack.isEmpty() && stack.peek() == '(')
						stack.pop();
					else {
						System.out.println("no");
						trigger = true;
						break;
					}
					break;
				case ']':
					if(!stack.isEmpty() && stack.peek() == '[')
						stack.pop();
					else {
						System.out.println("no");
						trigger = true;
						break;
					}
					break;
				}
				
				if(trigger) break;
			}
			
			if(!trigger) {
				if(stack.size() > 0) System.out.println("no");
				else System.out.println("yes");
			}
		}
		
	}
}
