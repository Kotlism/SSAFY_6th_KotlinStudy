package today5;

import java.util.ArrayList;
import java.util.Scanner;

public class boj2262_토너먼트만들기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(sc.nextInt());
		}
		int max = n;
		int result = 0;
		for (int i = 0; i < n - 1; i++) {
			int index = list.indexOf(max--);
			if (index == 0) {
				result += list.get(index) - list.get(index + 1);
			} else if (index == list.size() - 1) {
				result += list.get(index) - list.get(index - 1);
			} else {
				result += Integer.min(list.get(index) - list.get(index + 1), list.get(index) - list.get(index - 1));
			}
			list.remove(index);
		}
		System.out.println(result);
	}

}