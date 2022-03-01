import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2096_내려가기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max1 = Integer.parseInt(st.nextToken());
        int max2 = Integer.parseInt(st.nextToken());
        int max3 = Integer.parseInt(st.nextToken());
        int min1 = max1;
        int min2 = max2;
        int min3 = max3;
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());
            int temp3 = Integer.parseInt(st.nextToken());
            int premax1 = max1;
            int premax2 = max2;
            int premax3 = max3;
            int premin1 = min1;
            int premin2= min2;
            int premin3 = min3;
            max1 = Integer.max(premax1,premax2)+temp1;
            max2 = Integer.max(Integer.max(premax1,premax2),premax3)+temp2;
            max3 = Integer.max(premax2,premax3)+temp3;

            min1 = Integer.min(premin1,premin2)+temp1;
            min2 = Integer.min(Integer.min(premin1,premin2),premin3)+temp2;
            min3 = Integer.min(premin2,premin3)+temp3;
        }
        System.out.println(Integer.max(Integer.max(max1,max2),max3)+ " "+Integer.min(Integer.min(min1,min2),min3));
    }
}
