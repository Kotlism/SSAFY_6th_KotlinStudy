package today5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class boj2841_외계인의기타연주 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int P = sc.nextInt();
		int cnt = 0;
		Stack<Integer>[] list = new Stack[6];
		for (int i = 0; i < list.length; i++) {
			list[i]=new Stack<>();
		}
		for (int i = 0; i < N; i++) {
			int line = sc.nextInt()-1;
			int flat = sc.nextInt();
			
			if(list[line].isEmpty()||list[line].peek()<flat) {
				list[line].add(flat);
				cnt++;
				continue;
			}
			while(!list[line].isEmpty()&&list[line].peek()>flat) {
				list[line].pop();
				cnt++;
			}
			if(list[line].isEmpty()||list[line].peek()<flat) {
				list[line].add(flat);
				cnt++;
				continue;
			}
		}
		System.out.println(cnt);
		
	}



}
