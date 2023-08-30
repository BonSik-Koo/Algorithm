package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연속합2_13398 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[][] dp = new int[2][n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //dp 로직
        dp[0][1] = arr[1];
        dp[1][1] = arr[1];
        int result = arr[1];
        for(int i=2;i<=n;i++){
            //삭제하지 않는 경우
            dp[0][i] = Math.max(dp[0][i-1]+arr[i], arr[i]);
            result = Math.max(dp[0][i], result);

            //한개 삭제하는 경우
            dp[1][i] = Math.max(dp[1][i-1]+arr[i] ,Math.max(dp[0][i-1], dp[0][i]));
            result = Math.max(dp[1][i], result);
        }

        System.out.println(result);
    }
}
