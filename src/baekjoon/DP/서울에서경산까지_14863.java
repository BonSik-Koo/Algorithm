package baekjoon.DP;

import java.io.*;
import java.util.*;

public class 서울에서경산까지_14863 {
    static int N, K;
    static int[][] infos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        infos = new int[N][4];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                infos[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dp();
        System.out.println(result);
    }

    static int dp() {
        int result = 0;
        int[][] dp = new int[N][K+1];
        dp[0][infos[0][0]] = infos[0][1];
        dp[0][infos[0][2]] = Math.max(dp[0][infos[0][2]], infos[0][3]); // 값이 같을 수 있음.

        for(int i=1; i<N; i++) {
            int[] info = infos[i];
            for(int k=0; k<=K; k++) { // O(N*K)는 가능
                if(dp[i-1][k] == 0) {
                    continue;
                }
                if(k + info[0] <= K) {
                    dp[i][k+info[0]] = Math.max(dp[i][k+info[0]], dp[i-1][k] + info[1]);
                }
                if(k + info[2] <= K) {
                    dp[i][k+info[2]] = Math.max(dp[i][k+info[2]], dp[i-1][k] + info[3]);
                }
            }
        }

        for(int i=0; i<=K; i++) {
            result = Math.max(result, dp[N-1][i]);
        }
        return result;
    }

}
