import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static int N;
	public static PriorityQueue<Pair> heap = new PriorityQueue<>();
	
	public static class Pair implements Comparable<Pair>{
		int absValue, realValue;
		
		public Pair(int absValue, int realValue) {
			this.absValue = absValue;
			this.realValue = realValue;
		}

		@Override
		public int compareTo(Pair o) {
			if(this.absValue > o.absValue) return 1;
			else if (this.absValue < o.absValue) return -1;
			else {
				if(this.realValue > o.realValue) return 1;
				else return -1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if(value != 0) heap.add(new Pair(Math.abs(value), value));
			else System.out.println(heap.isEmpty() ? 0 : heap.poll().realValue);
		}
	}
}
