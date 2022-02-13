import java.util.Scanner;

public class boj10164_격자상의경로 {
    static int N, M, K,result;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        result=0;
        if(K==0){
            K=N*M;
        }
        int[] list = new int[N*M];
        list[0]=1;
        find(1,list,1);
        System.out.println(result);
    }
    public static void find(int num,int[] list,int depth){
        if(num==M*N){
            for (int i = 0; i < depth; i++) {
                if(list[i]==K){
                    result++;
                    return;
                }
            }
        }


        if(num+1<=M*N&&num%M!=0){
            list[depth]=num+1;
            find(num+1,list,depth+1);
        }
        if(num+M<=M*N){
            list[depth]=num+M;
            find(num+M,list,depth+1);
        }

    }
}
