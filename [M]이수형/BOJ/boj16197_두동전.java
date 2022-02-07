import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj16197_두동전 {
    static int N, M, result;
    static char[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static coin coinList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        board = new char[N][M];
        result = 99999;
        coinList = new coin(-1, -1, -1, -1);
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j] = temp[j].charAt(0);
                if (board[i][j] == 'o') {
                    if (coinList.x1 == -1) {
                        coinList.x1 = i;
                        coinList.y1 = j;
                    } else {
                        coinList.x2 = i;
                        coinList.y2 = j;
                    }
                }
            }
        }
        back(coinList, 0);
        if (result == 99999) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }

    public static void back(coin coinList, int depth) {
        if (depth >= 10 || depth >= result) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            int newcoin1x = coinList.x1 + dx[i];
            int newcoin1y = coinList.y1 + dy[i];
            int newcoin2x = coinList.x2 + dx[i];
            int newcoin2y = coinList.y2 + dy[i];

            if ((isNotWall(newcoin1x, newcoin1y) && isWall(newcoin2x, newcoin2y))
                    || (isWall(newcoin1x, newcoin1y) && isNotWall(newcoin2x, newcoin2y))) {
                result = Integer.min(depth + 1, result);
                return;
            }

            if (isNotWall(newcoin1x, newcoin1y) && isNotWall(newcoin2x, newcoin2y)) {
                if (board[newcoin1x][newcoin1y] == '#') {
                    newcoin1x = coinList.x1;
                    newcoin1y = coinList.y1;
                }
                if (board[newcoin2x][newcoin2y] == '#') {
                    newcoin2x = coinList.x2;
                    newcoin2y = coinList.y2;
                }

                back(new coin(newcoin1x, newcoin1y, newcoin2x, newcoin2y), depth + 1);
            }


        }
    }

    public static class coin {
        int x1;
        int y1;
        int x2;
        int y2;

        public coin(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public static boolean isNotWall(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < M) {
            return true;
        }
        return false;
    }

    public static boolean isWall(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return true;
        }
        return false;
    }
}
