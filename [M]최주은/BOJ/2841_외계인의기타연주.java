package boj_2841_외계인의기타연주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, P, res;
	static Stack<Integer>[] stack;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		stack = new Stack[N+1];
		for (int i = 0; i <= N; i++) {
			stack[i] = new Stack<Integer>();
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int m = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			while(!stack[m].isEmpty()) {
				if(stack[m].peek() > p) {
					stack[m].pop();
					res++;
				}else break;
			}
			
			if(!stack[m].contains(p)) {
				stack[m].add(p);
				res++;
			}
		}
		
		System.out.println(res);
	}
}
