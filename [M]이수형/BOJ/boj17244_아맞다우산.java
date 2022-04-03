package algo2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17244_아맞다우산 {
    static int N, M;
    static char[][] board;
    static boolean[][] check;
    static boolean[] check2;
    static LinkedList<node> list;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static node start;
    static node end;
    static class node {
        int x;
        int y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        check = new boolean[N][M];
        list = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j]);
                if (board[i][j] == 'X') {
                    list.add(new node(i, j));
                }
                else if(board[i][j]=='S'){
                    start = new node(i,j);
                }
                else if(board[i][j]=='E'){
                    board[i][j]='X';
                    end = new node(i,j);
                }
            }
            System.out.println();
        }
        check2=new boolean[list.size()];
        System.out.println(list.get(1).x+" "+list.get(1).y);
        System.out.println(bfs(start.x,start.y,1));
        run(0,list.size(),new int[list.size()]);
    }
    public static void run(int depth,int size, int[] make){
        if(depth==size){
            list.addLast(end);
            int total = 0;
            total+=bfs(start.x,start.y,make[0]);
            for (int i = 0; i < size-1; i++) {
                total+=bfs(list.get(make[i]).x,list.get(make[i]).y,make[i+1]);
            }
            System.out.println(total);
        }
        for (int i = 0; i < size; i++) {
            if(!check2[i]){
                check2[i]=true;
                make[depth]=i;
                run(depth+1,size,make);
                check2[i]=false;
            }

        }
    }
    public static int bfs(int x, int y, int time) {
        Queue<node> q = new LinkedList<>();
        check[x][y] = true;
        q.add(new node(x, y));
        int[][] dist = new int[N][M];
        while (!q.isEmpty()) {
            node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int newx = now.x + dx[i];
                int newy = now.y + dy[i];
                if (isNotWall(newx, newy) && !check[newx][newy]) {
                    if (board[newx][newy] == '.' || board[newx][newy] == 'X') {
                        q.add(new node(newx, newy));
                        check[newx][newy]=true;
                        dist[newx][newy]+=dist[now.x][now.y]+1;
                    }
                    if (board[newx][newy] == 'X' && newx == list.get(time).x && newy == list.get(time).y) {
                        return dist[newx][newy];
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isNotWall(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
