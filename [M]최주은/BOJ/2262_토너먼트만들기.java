import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static int N, res;
	public static ArrayList<Integer> arr = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");		
		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		
		int max = N;
		
		for (int i = 0; i < N-1; i++) {
			int idx = arr.indexOf(max);
			if(idx == 0) res += arr.get(idx) - arr.get(idx+1);
			else if(idx == arr.size() - 1) res += arr.get(idx) - arr.get(idx-1);
			else res += Math.min(arr.get(idx)-arr.get(idx+1), arr.get(idx) - arr.get(idx-1));
			
			arr.remove(idx);
			max--;
		}
		
		System.out.println(res);	
	}
}
