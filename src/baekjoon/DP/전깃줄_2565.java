package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 전깃줄_2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] wire = new int[n+1][2];
        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            wire[i][0] = A;
            wire[i][1] = B;
        }

        int maxWireCount = dp(n, wire);
        System.out.println(n - maxWireCount);
    }

    public static int dp(int n, int[][] wire){
        int[]dp = new int[n+1];
        int maxWireCount = Integer.MIN_VALUE;
        Arrays.sort(wire, (w1, w2) -> w1[0] - w2[0]);

        for(int i=1; i<=n; i++){
            dp[i] = 1; // 자기 자신

            for(int j=1; j<i; j++){
                if(wire[i][1] <= wire[j][1]){
                    continue;
                }

                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            maxWireCount = Math.max(dp[i], maxWireCount);
        }

        return maxWireCount;
    }

}
