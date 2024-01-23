package baekjoon.DP;

import java.util.*;
import java.io.*;

public class 로봇조정하기_2169 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] temp = new int[2][M];
        int[][] dp = new int[N][M];
        dp[0][0] = arr[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] = (dp[0][i - 1] + arr[0][i]);
        }

        for (int i = 1; i < N; i++) {
            // 왼쪽, 위쪽에서 오는 경우
            temp[0][0] = (dp[i - 1][0] + arr[i][0]);
            for (int j = 1; j < M; j++) {
                temp[0][j] = Math.max(temp[0][j-1], dp[i-1][j]) + arr[i][j];
            }

            // 오른쪽, 위쪽에서 오는 경우
            temp[1][M-1] = (dp[i-1][M-1] + arr[i][M-1]);
            for(int j=M-2; j>=0; j--){
                temp[1][j] = Math.max(temp[1][j+1], dp[i-1][j]) + arr[i][j];
            }

            // 위 두경우에서의 최대값 구하기
            for(int j=0; j<M; j++){
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }

        System.out.println(dp[N-1][M-1]);
    }

}
