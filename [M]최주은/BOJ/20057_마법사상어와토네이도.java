package boj_20057_마법사상어와토네이도;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int res;
	public static int[][] map;
	public static int[] ry = {0, 1, 0, -1};
	public static int[] rx = {-1, 0, 1, 0};
	
	public static int[][][] ratio = {
			{
				{-2, 0, 5},
				{-1, 1, 10},
				{-1, -1, 10},
				{0, -1, 7},
				{0, 1, 7},
				{0, -2, 2},
				{0, 2, 2},
				{1, -1, 1},
				{1, 1, 1}
			},
			{
				{0, 2, 5},
				{1, 1, 10},
				{-1, 1, 10},
				{-2, 0, 2},
				{2, 0, 2},
				{-1, 0, 7},
				{1, 0, 7},
				{-1, -1, 1},
				{1, -1, 1}
			},
			{
				{2, 0, 5},
				{1, 1, 10},
				{1, -1, 10},
				{0, -1, 7},
				{0, 1, 7},
				{0, -2, 2},
				{0, 2, 2},
				{-1, -1, 1},
				{-1, 1, 1}
			},
			{
				{0, -2, 5},
				{-1, -1, 10},
				{1, -1, 10},
				{2, 0, 2},
				{-2, 0, 2},
				{1, 0, 7},
				{-1, 0, 7},
				{1, 1, 1},
				{-1, 1, 1}
			}
	};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int y = N/2;
		int x = N/2;
		int loc = 0;
		
		for (int i = 1; i < N; i++) { // 토네이도
			int rep = 2;
			if(i == N-1) rep = 3;
			
			for (int j = 0; j < rep; j++) { // 2번인지 3번인지
				int ny = y + ry[loc] * i;
				int nx = x + rx[loc] * i;
				
				while(x != nx || y != ny) {
					x += rx[loc];
					y += ry[loc];
					
					double sand = map[y][x];
					
					for (int k = 0; k < 9; k++) { // 9곳 모래 옮기기
						int xx = x + ratio[loc][k][0];
						int yy = y + ratio[loc][k][1];
						double r = ratio[loc][k][2]/100.0;
						int rSand = (int) Math.floor(map[y][x] * r);
						
						sand -= rSand;
						
						if(yy < 0 || yy >= N || xx < 0 || xx >= N ) {
							res += rSand;
							continue;
						}
						
						map[yy][xx] += rSand;
					}
					
					// a구역으로 모래 옮기기
					int xx = x + rx[loc];
					int yy = y + ry[loc];
					
					if(xx < 0 || xx >= N || yy < 0 || yy >= N) res += Math.floor(sand);
					else map[yy][xx] += Math.floor(sand);
					
					map[y][x] = 0;
				}
				
				loc = (loc+1)%4;
			
			}
		}
		
		System.out.println(res);
	}
}
