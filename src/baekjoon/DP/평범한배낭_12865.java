package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.*;

public class 평범한배낭_12865 {
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][K+1];
        int[][] products = new int[N+1][2];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            products[i][0] = Integer.parseInt(st.nextToken());
            products[i][1] = Integer.parseInt(st.nextToken());
        }

        //dp 시작
        dp(dp, products, N, K);

        System.out.println(dp[N][K]);
    }
    private static void dp(int [][]dp, int[][] products, int N, int K) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if(j-products[i][0] >= 0){
                    dp[i][j] = Math.max(dp[i-1][j], products[i][1] + dp[i - 1][j - products[i][0]]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
    }
}
