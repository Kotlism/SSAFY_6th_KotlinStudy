import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj1976_여행가자 {
    static int N,M;
    static int[] parent;
    static int[][] board;
    static int[] list;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        board=new int[N][N];
        parent = new int[N];
        list = new int[M];
        for (int i = 0; i < N; i++) {
            parent[i]=i;
        }
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j]=Integer.parseInt(temp[j]);
                if(board[i][j]==1){
                    union(i,j);
                }
            }
        }

        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            list[i] = Integer.parseInt(temp[i])-1;
        }
        int start = find(list[0]);
        for (int i = 1; i < M; i++) {
            if(start != find(list[i])){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");

    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y){
                parent[y]=x;
            }else{
                parent[x]=y;
            }
        }
    }
    public static int find(int x){
        if(x==parent[x]){
            return x;
        }
        return parent[x]=find(parent[x]);
    }
}
