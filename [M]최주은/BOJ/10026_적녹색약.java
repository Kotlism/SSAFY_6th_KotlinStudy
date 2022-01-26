import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int N;
	public static char[][] map;
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		System.out.printf("%d %d", process(true), process(false));
	}

	private static int process(boolean isNormal) {
		boolean[][] visited = new boolean[N][N];
		int res = 0;
		
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				if(!visited[i][j]) {
					res++;
					run(i, j, map[i][j], visited, isNormal);
				}
			}
		}
		
		return res;
	}

	private static void run(int y, int x, char c, boolean[][] visited, boolean isNormal) {
		visited[y][x] = true;
		
		for (int i = 0; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];
			
			if(yy < 0 || yy >= N || xx < 0 || xx >= N || visited[yy][xx]) continue;
			
			if(isNormal) {if(map[yy][xx] == c) run(yy,xx,c,visited,isNormal);}
			else {
				if(c == 'B') {if(map[yy][xx] == c) run(yy, xx, c, visited, isNormal);}
				else if(map[yy][xx] != 'B') run(yy, xx, c, visited, isNormal);
			}
		}
	}
}
