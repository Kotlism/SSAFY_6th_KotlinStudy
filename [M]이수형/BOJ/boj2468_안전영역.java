package today5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj2468_안전영역 {

	static int N, max, result;
	static int[][] board;
	static boolean[][] check;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		check = new boolean[N][N];
		max = 0;
		result = 0;
		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(temp[j]);
				max = Integer.max(max, board[i][j]);
			}
		}
		back();
		System.out.println(result);
	}

	public static void back() {

		for (int i = 0; i <= max; i++) {
			check = new boolean[N][N];
			int cnt = 0;
			for (int j = 0; j < board.length; j++) {
				for (int k = 0; k < board.length; k++) {
					if (!check[j][k] && board[j][k] > i) {
						cnt += bfs(j, k, i);
					}
				}
			}
			result = Integer.max(cnt, result);
		}
	}

	public static int bfs(int x, int y, int depth) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		check[x][y] = true;
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int newx = temp[0] + dx[i];
				int newy = temp[1] + dy[i];
				if(isNotWall(newx, newy)&&!check[newx][newy]&&board[newx][newy]>depth) {
					check[newx][newy]=true;
					q.add(new int[] {newx,newy});
				}
			}
		}
		return 1;
	}

	public static boolean isNotWall(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}

}
