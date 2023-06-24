package baekjoon.DP.동전1p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 다시 풀 문제
 */
public class 동전1 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int []dp = new int[k+1];
        Arrays.fill(dp,0);
        dp[0] = 1;
        for(int i=0;i<n;i++){
            int val = Integer.parseInt(br.readLine());
            for(int j=val;j<=k;j++){
                dp[j] = dp[j] + dp[j-val];
            }
        }

        System.out.println(dp[k]);
    }
}
