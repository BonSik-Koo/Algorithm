package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 합분해 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][]dp = new int[N+1][K+1];
        final int mod =  1000000000;

        for(int i=1;i<=N;i++){
            for(int j=1;j<=K;j++){
                //초기화
                if(i==1){
                    dp[i][j] = j;
                    continue;
                }
                if(j==1){
                    dp[i][j] = 1;
                    continue;
                }

                //실제 dp 로직
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])%mod;
            }
        }

        System.out.println(dp[N][K]);
    }
}
