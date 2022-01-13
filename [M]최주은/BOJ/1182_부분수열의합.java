import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, S, res = 0;
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());			
		}
		
		// 부분수열의 개수 
		DFS(0, 0);
		
		// 공집합인 경우 예외 처리
		if(S == 0) res--;
		
		// 출력
		System.out.println(res);
	}

	private static void DFS(int idx, int sum) {
		if(idx == N){
			if(sum == S) res++;
			return;
		}
		
		DFS(idx+1, sum + arr[idx]);
		DFS(idx+1, sum + 0);
	}
}
