import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj19238_스타트택시 {
    static int N, M, K;
    static int[][] board;
    static taxi starttaxi;
    static ArrayList<person> personlist;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] check;

    public static class taxi {
        int x;
        int y;
        int weight;

        public taxi(int x, int y, int weight) {
            this.x = x - 1;
            this.y = y - 1;
            this.weight = weight;
        }
    }

    public static class person {
        int startx;
        int starty;
        int endx;
        int endy;

        public person(int startx, int starty, int endx, int endy) {
            this.startx = startx - 1;
            this.starty = starty - 1;
            this.endx = endx - 1;
            this.endy = endy - 1;
        }

        @Override
        public String toString() {
            return "person{" +
                    "startx=" + startx +
                    ", starty=" + starty +
                    ", endx=" + endx +
                    ", endy=" + endy +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        N = Integer.parseInt(st[0]);
        M = Integer.parseInt(st[1]);
        K = Integer.parseInt(st[2]);
        board = new int[N][N];
        check = new boolean[N][N];
        personlist = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st[j]);
            }
        }
        st = br.readLine().split(" ");
        starttaxi = new taxi(Integer.parseInt(st[0]), Integer.parseInt(st[1]), K);
        for (int i = 0; i < M; i++) {
            st = br.readLine().split(" ");
            personlist.add(new person(Integer.parseInt(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3])));
        }
        int size = personlist.size();
        for (int i = 0; i < size; i++) {
            start();
            if (starttaxi.weight < 0) {
                break;
            }
        }
        if (starttaxi.weight < 0 || personlist.size() > 0) {
            System.out.println(-1);
        } else {
            System.out.println(starttaxi.weight);
        }

    }

    public static void start() {
        int idx = 0;
        int row = Integer.MAX_VALUE;
        int col = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        int[][] findboard = findperson();
        for (int i = 0; i < personlist.size(); i++) {
            int startx = personlist.get(i).startx;
            int starty = personlist.get(i).starty;
            int time = findboard[startx][starty];
            if (min > time) {
                idx = i;
                min = time;
            }
        }
        for (int i = 0; i < personlist.size(); i++) {
            int startx = personlist.get(i).startx;
            int starty = personlist.get(i).starty;
            int time = findboard[startx][starty];
            if (min == time) {
                //System.out.println("dd" + personlist.get(i) + " " + row + " " + idx + " " + min);
                if (row > startx) {
                    row = startx;
                    col = starty;
                    idx = i;
                }
                if (row == startx) {
                    if (col > starty) {
                        row = startx;
                        col = starty;
                        idx = i;
                    }
                }
                //System.out.println("dd" + personlist.get(i) + " " + row + " " + idx + " " + min);
            }
        }
        if (min == Integer.MAX_VALUE) {
            return;
        }
        starttaxi.x = personlist.get(idx).startx;
        starttaxi.y = personlist.get(idx).starty;
        starttaxi.weight -= min;
        int consume = bfs(starttaxi.x, starttaxi.y, personlist.get(idx).endx, personlist.get(idx).endy);
        if (consume == Integer.MAX_VALUE) {
            return;
        }
        //System.out.println(personlist.get(idx) + " " + consume);
        if (starttaxi.weight - consume >= 0) {
            starttaxi.x = personlist.get(idx).endx;
            starttaxi.y = personlist.get(idx).endy;
            starttaxi.weight = starttaxi.weight + consume;
            personlist.remove(idx);
        } else {
            starttaxi.weight = starttaxi.weight - consume;
            return;
        }
    }

    public static int[][] findperson() {
        int[][] tempboard = new int[N][N];
        check = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{starttaxi.x, starttaxi.y});
        check[starttaxi.x][starttaxi.y] = true;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++) {
                int newx = temp[0] + dx[i];
                int newy = temp[1] + dy[i];
                if (isNotWall(newx, newy) && !check[newx][newy] && board[newx][newy] != 1) {
                    q.add(new int[]{newx, newy});
                    check[newx][newy] = true;
                    tempboard[newx][newy] = tempboard[temp[0]][temp[1]] + 1;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tempboard[i][j] == 0) {
                    tempboard[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        tempboard[starttaxi.x][starttaxi.y] = 0;
        return tempboard;
    }

    public static int bfs(int x, int y, int endx, int endy) {
        if (x == endx && y == endy) {
            return 0;
        }
        int[][] tempboard = new int[N][N];
        check = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        check[x][y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++) {
                int newx = temp[0] + dx[i];
                int newy = temp[1] + dy[i];
                if (isNotWall(newx, newy) && !check[newx][newy] && board[newx][newy] != 1) {
                    if (newx == endx && newy == endy) {
                        return tempboard[temp[0]][temp[1]] + 1;
                    }
                    q.add(new int[]{newx, newy});
                    check[newx][newy] = true;
                    tempboard[newx][newy] = tempboard[temp[0]][temp[1]] + 1;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public static boolean isNotWall(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }
        return false;
    }
}
