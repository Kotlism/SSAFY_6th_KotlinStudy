import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj3079_입국심사 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int max = 0;
        int[] list = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list[i] = Integer.parseInt(st.nextToken());
            max = Integer.max(max, list[i]);
        }

        long left = 0;
        long right = ((long) M * max) + 1;
        long total = 0;
        while (left <= right) {

            long mid = (left + right) / 2;

            long cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += mid / list[i];
            }
            if (cnt >= M) {
                right = mid - 1;
                total = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(total);
    }
}
