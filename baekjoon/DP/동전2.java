package baekjoon.DP.동전2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int []dp = new int[k+1];
        int []coins = new int[n];
        for(int i=0;i<n;i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        //초기화
        Arrays.fill(dp, Integer.MAX_VALUE); //초기값으로 만들수 없다고 표시
        dp[0] = 0;

        //dp 시작
        for(int i=0;i<n;i++){
            int coin = coins[i];
            for(int j=coin;j<=k;j++){
                if(dp[j-coin] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j], dp[j-coin]+1);
                }
            }
        }

        //출력
        System.out.println((dp[k]!=Integer.MAX_VALUE)?dp[k]:-1);
    }
}
