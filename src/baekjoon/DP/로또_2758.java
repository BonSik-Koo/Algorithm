package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 로또_2758 {
    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            long answer = 0;

            // 행: 선택 개수
            long[][] dp = new long[n+1][m+1];
            Arrays.fill(dp[1], 1);

            for(int i=2; i<=n; i++) {
                for(int j=1; j<=m; j++) {
                    for(int k=1; k<=j/2; k++) {
                        dp[i][j] += dp[i-1][k];
                    }
                }
            }

            // 총 경우의 수
            for(int i=1; i<=m; i++) {
                answer += dp[n][i];
            }
            result.append(answer + "\n");
        }

        System.out.println(result.toString());
    }
}
