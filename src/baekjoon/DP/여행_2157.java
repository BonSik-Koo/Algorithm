package baekjoon.DP;

import java.util.*;
import java.io.*;

public class 여행_2157 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] scores = new int[N+1][N+1];
        int[][] dp = new int[M+1][N+1];
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            if(n1 > n2) continue;
            scores[n1][n2] = Math.max(scores[n1][n2], score);
        }

        // 초기값
        for(int i=1; i<=N; i++) {
            dp[2][i] = scores[1][i];
        }

        // dp 탐색
        for(int i=2; i<=N; i++) {
            for(int j=i+1; j<=N; j++) {
                for(int k=3; k<=M; k++) {
                    if(dp[k-1][i] == 0 || scores[i][j] == 0) continue;

                    dp[k][j] = Math.max(dp[k][j], dp[k-1][i] + scores[i][j]);
                }
            }
        }

        // 결과 탐색
        int result = 0;
        for(int i=2; i<=M; i++) {
            result = Math.max(result, dp[i][N]);
        }
        System.out.println(result);
    }

}
