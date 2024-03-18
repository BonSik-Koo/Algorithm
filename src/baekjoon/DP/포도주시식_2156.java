package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 포도주시식_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] map = new int[n+1];
        int[] dp = new int[n+1];
        for(int i=1; i<=n; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        // 초기값
        dp[0] = 0;
        dp[1] = map[1];
        if(n >= 2) {
            dp[2] = dp[1] + map[2];
        }
        for(int i=3; i<=n; i++) {
            // 현재 와인을 마시는 경우 중 최대값
            int value = Math.max(dp[i-3] + map[i-1] + map[i], dp[i-2] + map[i]);
            // 현재 완인을 마시지 않는 경우 최대값
            if(dp[i-1] > value) {
                dp[i] = dp[i-1];
                continue;
            }
            dp[i] = value;
        }

        System.out.println(dp[n]);
    }
}
