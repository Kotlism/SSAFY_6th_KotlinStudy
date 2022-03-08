import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj16236_아기상어 {
    static int N, M;
    static int[][] board;
    static boolean[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static shark baby;
    static int time;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        check = new boolean[N][N];
        time = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    baby = new shark(i, j, 2, 0);
                    board[i][j]=0;
                }
            }
        }
        while(true){
            bfs();
        }
    }

    public static class shark {
        int x;
        int y;
        int size;
        int cnt;

        public void setCnt(int cnt) {
            this.cnt = cnt;
            if (cnt == this.size) {
                this.size++;
                this.cnt = 0;
            }
        }

        public shark(int x, int y, int size, int cnt) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", size=" + size +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void bfs() {
        ArrayList<int[]> fish = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        int[][] timeboard = new int[N][N];
        check = new boolean[N][N];
        q.add(new int[]{baby.x, baby.y});
        check[baby.x][baby.y] = true;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++) {
                int newx = temp[0] + dx[i];
                int newy = temp[1] + dy[i];
                if (isNotWall(newx, newy) && !check[newx][newy] && board[newx][newy] <= baby.size) {
                    q.add(new int[]{newx, newy});
                    check[newx][newy] = true;
                    timeboard[newx][newy]=timeboard[temp[0]][temp[1]]+1;
                    if (board[newx][newy] != 0 && board[newx][newy]< baby.size) {
                        fish.add(new int[]{newx, newy});
                    }
                }
            }
        }
        if (fish.size() == 0) {
            System.out.println(time);
            System.exit(0);
        }
        if (fish.size() > 1) {
            Collections.sort(fish, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(timeboard[o1[0]][o1[1]]==timeboard[o2[0]][o2[1]]){
                        if (o1[0] == o2[0]) {
                            return o1[1] - o2[1];
                        }
                        return o1[0] - o2[0];
                    }
                    return timeboard[o1[0]][o1[1]]-timeboard[o2[0]][o2[1]];
                }
            });
        }
        int eatX = fish.get(0)[0];
        int eatY = fish.get(0)[1];
        baby = new shark(eatX, eatY, baby.size, baby.cnt);
        baby.setCnt(baby.cnt + 1);
        board[eatX][eatY]=0;
        time+=timeboard[eatX][eatY];

    }

    public static boolean isNotWall(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }
        return false;
    }
}
