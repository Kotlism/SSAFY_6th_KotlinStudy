package boj_2468_안전영역;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int N, mHeight, res;
	static boolean[][] flooding;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// 입력받기
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mHeight = mHeight > map[i][j] ? mHeight : map[i][j];
			}
		}
		
		// 실행
		run(0);
		
		// 출력
		System.out.println(res);
	}
	
	public static void run(int h) {
		if(h > mHeight) return;
		
		flooding = new boolean[N][N];
		
		// 침수 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] <= h) flooding[i][j] = true;
			}
		}
		
		// 안전한 영역 측정
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!flooding[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		
		// 비교
		res = res > cnt ? res : cnt;
		
		run(h+1);
	}
	
	public static void dfs(int y, int x) {
		flooding[y][x] = true;
		
		for (int i = 0; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];
			
			if(yy < 0 || yy >= N || xx < 0 || xx >= N || flooding[yy][xx]) continue;
			
			dfs(yy, xx);
		}
	}
}
