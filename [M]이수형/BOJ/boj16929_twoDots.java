import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class boj16929_twoDots {
    static int N,M,result;
    static char[][] board;
    static boolean[][] check;
    static int[] startxy;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] num = br.readLine().split(" ");
        N = Integer.parseInt(num[0]);
        M = Integer.parseInt(num[1]);
        board = new char[N][M];
        check= new boolean[N][M];
        startxy = new int[2];
        result =0;
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j]=temp[j].charAt(0);
            }
        }
        boolean[] alpha = new boolean[26];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!check[i][j]){
                    dfs(i,j,-1,-1,1,board[i][j]);
                    if(result>0){
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }
        System.out.println("No");

    }

    public static void dfs(int x, int y,int prex,int prey,int depth,char alpha){
        if(check[x][y]&&depth>=4){
            result++;
            return;
        }
        check[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int newx = x + dx[i];
            int newy = y + dy[i];
            if(isNotWall(newx,newy)&&alpha==board[newx][newy]){
                if(prex==newx&&prey==newy) continue;
//                System.out.println(newx+" "+newy);
                dfs(newx,newy,x,y,depth+1,alpha);
            }
        }
    }
    public static boolean isNotWall(int x , int y){
        if(x>=0&&x<N&&y>=0&&y<M){
            return true;
        }else {
            return false;
        }
    }
}
