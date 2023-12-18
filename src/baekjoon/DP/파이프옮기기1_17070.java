package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기1_17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dp(map, N);
        System.out.println(result);
    }

    public static int dp(int[][] map, int N) {
        // 0: 가로, 1: 세로, 2:대각선
        int[][][] dp = new int[3][N][N];
        dp[0][0][1] = 1; // 초기값

        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                // 가로 경우의 수 계산
                if (map[i][j] == 0) {
                    dp[0][i][j] = (dp[0][i][j - 1] + dp[2][i][j - 1]);
                }
                // 세로 경우의 수 계산
                if (i - 1 >= 0 && map[i][j] == 0) {
                    dp[1][i][j] = (dp[1][i - 1][j] + dp[2][i - 1][j]);
                }

                // 대각선 경우의 수 계산
                if (i - 1 >= 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0 && map[i][j] == 0) {
                    dp[2][i][j] = (dp[2][i - 1][j - 1] + dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1]);
                }
            }
        }

        return dp[0][N - 1][N - 1] + dp[1][N - 1][N - 1] + dp[2][N - 1][N - 1];
    }

}
