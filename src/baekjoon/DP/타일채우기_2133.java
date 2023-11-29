package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 타일채우기_2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        if(N==1){
            System.out.println(0);
            return;
        }

        dp[2] = 3;
        for(int i=4; i<=N; i+=2){ // 홀수 크기에는 답이 없음.
            dp[i] = dp[i-2] * 3 + 2; // 각 크기에 예외 개수가 2개
            for(int j=i-4; j>=2; j-=2){  // 크기가 6부터는 추가 예외 개수 발생
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);
    }
}
