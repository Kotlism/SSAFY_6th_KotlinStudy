package algo2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2234_성곽 {
    static int N, M;
    static dir[][] board;
    //서 북 동 남
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] check;
    static int[][] tempboard;
    static class dir {
        boolean east;
        boolean west;
        boolean south;
        boolean north;

        public dir(boolean east, boolean west, boolean south, boolean north) {
            this.east = east;
            this.west = west;
            this.south = south;
            this.north = north;
        }

        @Override
        public String toString() {
            return "dir{" +
                    "east=" + east +
                    ", west=" + west +
                    ", south=" + south +
                    ", north=" + north +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new dir[M][N];
        check = new boolean[M][N];
        tempboard = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j]=new dir(false,false,false,false);
                int now = Integer.parseInt(st.nextToken());
                if (now >= 8) {
                    board[i][j].south = true;
                    now -= 8;
                }
                if (now >= 4) {
                    board[i][j].east = true;
                    now -= 4;
                }
                if (now >= 2) {
                    board[i][j].north = true;
                    now -= 2;
                }
                if (now >= 1) {
                    board[i][j].west = true;
                    now -= 1;
                }
            }
        }
        ArrayList<int[]> list = new ArrayList<>();
        int num = 0;
        int max = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(!check[i][j]){
                    int total = bfs(i,j,num);
                    list.add(new int[]{num,total});
                    num++;
                    max = Integer.max(max,total);
                }
            }
        }
        int wallmax =0 ;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int newx = i+dx[k];
                    int newy = j+dy[k];
                    if(isNotWall(newx,newy)&&tempboard[newx][newy]!=tempboard[i][j]){
                       wallmax  = Integer.max(wallmax,list.get(tempboard[newx][newy])[1]+list.get(tempboard[i][j])[1]);
                    }
                }
            }
        }
        System.out.println(list.size());
        System.out.println(max);
        System.out.println(wallmax);
    }
    public static int bfs(int x, int y, int num){
        check[x][y]=true;
        tempboard[x][y]=num;
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        while (!q.isEmpty()){
            int[] temp = q.poll();
            if(!board[temp[0]][temp[1]].west){
                int newx = temp[0]+dx[0];
                int newy = temp[1]+dy[0];
                if(isNotWall(newx,newy)&&!check[newx][newy]){
                    q.add(new int[]{newx,newy});
                    check[newx][newy]=true;
                    tempboard[newx][newy]= num;
                }
            }
            if(!board[temp[0]][temp[1]].north){
                int newx = temp[0]+dx[1];
                int newy = temp[1]+dy[1];
                if(isNotWall(newx,newy)&&!check[newx][newy]){
                    q.add(new int[]{newx,newy});
                    check[newx][newy]=true;
                    tempboard[newx][newy]= num;
                }
            }
            if(!board[temp[0]][temp[1]].east){
                int newx = temp[0]+dx[2];
                int newy = temp[1]+dy[2];
                if(isNotWall(newx,newy)&&!check[newx][newy]){
                    q.add(new int[]{newx,newy});
                    check[newx][newy]=true;
                    tempboard[newx][newy]= num;
                }
            }
            if(!board[temp[0]][temp[1]].south){
                int newx = temp[0]+dx[3];
                int newy = temp[1]+dy[3];
                if(isNotWall(newx,newy)&&!check[newx][newy]){
                    q.add(new int[]{newx,newy});
                    check[newx][newy]=true;
                    tempboard[newx][newy]= num;
                }
            }
            cnt++;
        }
        return cnt;
    }
    public static boolean isNotWall(int x, int y){
        return x>=0&&x<M&&y>=0&&y<N;
    }
}
