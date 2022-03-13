import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj20056_파이어볼 {
    static int N, M, K;
    static int[][] board;
    static ArrayList<fireball> fireballlist;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static class fireball {
        int x;
        int y;
        int m;
        int d;
        int s;

        public fireball(int x, int y, int m, int d, int s) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.d = d;
            this.s = s;
        }

        @Override
        public String toString() {
            return "fireball{" +
                    "x=" + x +
                    ", y=" + y +
                    ", m=" + m +
                    ", d=" + d +
                    ", s=" + s +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fireballlist = new ArrayList<>();
        board = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballlist.add(new fireball(r, c, m, d, s));
        }
        for (int i = 0; i < K; i++) {
            move();
            devide();
        }
        int total=0;
        for (int i = 0; i < fireballlist.size(); i++) {
            total+=fireballlist.get(i).m;
        }
        System.out.println(total);
    }

    public static void move() {
        for (int i = 0; i < fireballlist.size(); i++) {
            int x = fireballlist.get(i).x;
            int y = fireballlist.get(i).y;
            int dir = fireballlist.get(i).d;
            int move = fireballlist.get(i).s;

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
            fireballlist.get(i).x = x;
            fireballlist.get(i).y = y;
        }
        Collections.sort(fireballlist, new Comparator<fireball>() {
            @Override
            public int compare(fireball o1, fireball o2) {
                if(o1.x==o2.x){
                    return o1.y-o2.y;
                }
                return o1.x-o2.x;
            }
        });
    }
    public static void devide(){
        Collections.sort(fireballlist, new Comparator<fireball>() {
            @Override
            public int compare(fireball o1, fireball o2) {
                if(o1.x==o2.x){
                    return o1.y-o2.y;
                }
                return o1.x-o2.x;
            }
        });
        ArrayList<fireball> templist = new ArrayList<>();
        for (int i = 0; i < fireballlist.size(); i++) {
            int x = fireballlist.get(i).x;
            int y =fireballlist.get(i).y;
            int saveM=fireballlist.get(i).m;
            int savedir=0;
            int saveS=fireballlist.get(i).s;
            if(fireballlist.get(i).d%2==0){
                savedir--;
            }else {
                savedir++;
            }
            int cnt=1;
            int idx=i+1;
            for (int j = idx; j < fireballlist.size(); j++) {
                idx=j;
                if(fireballlist.get(i).x==fireballlist.get(j).x && fireballlist.get(i).y==fireballlist.get(j).y){
                    saveM+=fireballlist.get(j).m;
                    saveS+=fireballlist.get(j).s;
                    if(fireballlist.get(j).d%2==0){
                        savedir--;
                    }else {
                        savedir++;
                    }
                    cnt++;
                }else {
                    idx--;
                    break;
                }
            }
            if(cnt==1){
                templist.add(fireballlist.get(i));
            }else{
                saveM/=5;
                saveS/=cnt;
                if(saveM>0){
                    if(Math.abs(savedir)==cnt){
                        templist.add(new fireball(x,y,saveM,0,saveS));
                        templist.add(new fireball(x,y,saveM,2,saveS));
                        templist.add(new fireball(x,y,saveM,4,saveS));
                        templist.add(new fireball(x,y,saveM,6,saveS));

                    }else{
                        templist.add(new fireball(x,y,saveM,1,saveS));
                        templist.add(new fireball(x,y,saveM,3,saveS));
                        templist.add(new fireball(x,y,saveM,5,saveS));
                        templist.add(new fireball(x,y,saveM,7,saveS));
                    }
                }
                i=idx;
            }
        }
        fireballlist.clear();
        fireballlist=templist;
    }
}
