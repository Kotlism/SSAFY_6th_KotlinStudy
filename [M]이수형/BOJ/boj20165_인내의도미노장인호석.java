import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj20165_인내의도미노장인호석 {
    static int N, M, R,total;
    static int[][] board;
    static boolean[][] check;
    static attack[] attacklist;
    static int[][] defencelist;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class attack {
        int x;
        int y;
        char dir;

        public attack(int x, int y, char dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        check = new boolean[N][M];
        attacklist = new attack[R];
        defencelist = new int[R][2];
        total=0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            attacklist[i] = new attack(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, st.nextToken().charAt(0));
            st = new StringTokenizer(br.readLine());
            defencelist[i][0] = Integer.parseInt(st.nextToken()) - 1;
            defencelist[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
        start();
        System.out.println(total);
        printlist();
    }

    public static void start() {
        for (int i = 0; i < R; i++) {
            int attackX = attacklist[i].x;
            int attackY = attacklist[i].y;
            int attackdir = changeDir(attacklist[i].dir);
            int size = board[attackX][attackY];
            size--;
            total++;
            check[attackX][attackY] = true;
            while (size!=0){
                int newx = attackX + dx[attackdir];
                int newy = attackY + dy[attackdir];
                size--;
                if (isNotWall(newx, newy) && !check[newx][newy]) {
                    check[newx][newy] = true;
                    total++;
                    if (size < board[newx][newy]) {
                        size = board[newx][newy];
                        size--;
                    }
                }
                attackX = newx;
                attackY = newy;
            }
            check[defencelist[i][0]][defencelist[i][1]]=false;
        }
    }

    public static int changeDir(char dir) {
        switch (dir) {
            case 'E':
                return 0;
            case 'W':
                return 1;
            case 'S':
                return 2;
            case 'N':
                return 3;
        }
        return 0;
    }

    public static boolean isNotWall(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < M) {
            return true;
        }
        return false;
    }
    public static void printlist(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(check[i][j]){
                    System.out.print("F ");
                }else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }
}
