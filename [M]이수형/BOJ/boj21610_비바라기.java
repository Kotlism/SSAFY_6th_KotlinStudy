import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj21610_비바라기 {
    static int N, M,total;
    static int[][] board;
    static ArrayList<command> commandlist;
    static ArrayList<cloud> cloudlist;
    //0:좌 1:좌상 2:상 3:우상 4:우 5:우하 6:하 7:좌하
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static class cloud {
        int x;
        int y;

        public cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class command {
        int dir;
        int move;

        public command(int dir, int move) {
            this.dir = dir;
            this.move = move;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        commandlist = new ArrayList<>();
        cloudlist = new ArrayList<>();
        total=0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            commandlist.add(new command(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }
        cloudlist.add(new cloud(N - 1, 0));
        cloudlist.add(new cloud(N - 1, 1));
        cloudlist.add(new cloud(N - 2, 0));
        cloudlist.add(new cloud(N - 2, 1));
        for (int i = 0; i < M; i++) {
            movecloud(i);
            rain();
            nextcloudlist();
        }
        printboard();
    }

    public static void movecloud(int num) {
        int dir = commandlist.get(num).dir;
        int move = commandlist.get(num).move;

        for (int i = 0; i < cloudlist.size(); i++) {
            int x = cloudlist.get(i).x;
            int y = cloudlist.get(i).y;
            //System.out.println("before"+cloudlist.get(i).x+" "+cloudlist.get(i).y);
            //구름이동
            for (int j = 0; j < move; j++) {
                x += dx[dir];
                y += dy[dir];
                if (x == -1) {
                    x = N - 1;
                }
                if (x == N) {
                    x = 0;
                }
                if (y == -1) {
                    y = N - 1;
                }
                if (y == N) {
                    y = 0;
                }
            }
            cloudlist.get(i).x = x;
            cloudlist.get(i).y = y;
            board[x][y]++;
            //System.out.println("after"+cloudlist.get(i).x+" "+cloudlist.get(i).y);
        }
    }

    public static void rain() {
        for (int i = 0; i < cloudlist.size(); i++) {
            int x = cloudlist.get(i).x;
            int y = cloudlist.get(i).y;
            for (int j = 0; j < 4; j++) {
                int newx = x + dx[j * 2 + 1];
                int newy = y + dy[j * 2 + 1];
                if (isNotWall(newx, newy) && board[newx][newy] > 0) {
                    board[x][y]++;
                }
            }
        }
    }

    public static void nextcloudlist() {
        boolean[][] check = new boolean[N][N];
        for (cloud c : cloudlist) {
            check[c.x][c.y] = true;
        }
        cloudlist.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j] && board[i][j] >= 2) {
                    cloudlist.add(new cloud(i, j));
                    board[i][j] -= 2;
                }
            }
        }
    }


//    5 4
//        0 0 1 0 2
//        2 3 2 1 0
//        4 3 2 9 0
//        1 0 2 9 0
//        8 8 2 1 0
//        1 3
//        3 4
//        8 1
//        4 8
    public static boolean isNotWall(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }
        return false;
    }

    public static void printboard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                total+=board[i][j];
            }
        }
        System.out.println(total);
    }

}
