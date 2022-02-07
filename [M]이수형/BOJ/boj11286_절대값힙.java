package today5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj11286_절대값힙 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<myint> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num==0) {
				if(pq.isEmpty()) {
					System.out.println(0);
				}else {
					System.out.println(pq.poll().x);
				}
			}else {
				pq.add(new myint(num,Math.abs(num)));
			}
		}
	}

	public static class myint implements Comparable<myint> {
		int x;
		int absx;

		public myint(int x, int absx) {
			this.x = x;
			this.absx = absx;
		}

		@Override
		public int compareTo(myint o) {
			// TODO Auto-generated method stub
			if (this.absx == o.absx) {
				return this.x - o.x;
			}
			return this.absx - o.absx;
		}
	}

}
