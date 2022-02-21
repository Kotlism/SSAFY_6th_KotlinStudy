import java.util.Scanner;

public class boj1107_리모컨 {
    static int N, M, min, result;
    static boolean[] list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        min = Math.abs(N - 100);
        list = new boolean[10];
        if (M != 0) {
            for (int i = 0; i < M; i++) {
                list[sc.nextInt()] = true;
            }
        }
        dfs(0, "");
        if (min == 0 && result == 0) {
            System.out.println(0);
        } else {

            System.out.println(Math.min(Math.abs(N - 100), min));
        }
        //System.out.println(min+" "+result+" "+(result+"").length());

    }

    public static void dfs(int depth, String num) {
        if (depth == (N + "").length() + 1) {
            int temp = Math.abs(N - Integer.parseInt(num))+(num + "").length();
            if (min > temp) {
                min = temp;
                result = Integer.parseInt(num);
            }
            return;
        }
        if(!num.equals("")){
            int temp = Math.abs(N - Integer.parseInt(num))+(num + "").length();
            if (min > temp) {
                min = temp;
                result = Integer.parseInt(num);

            }
        }

        for (int i = 0; i < 10; i++) {
            if (!list[i]) {
                dfs(depth + 1, num + i);
            }
        }
    }
}
