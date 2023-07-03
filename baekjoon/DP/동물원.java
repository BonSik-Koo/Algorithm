package baekjoon.DP;

import java.util.Scanner;

public class 동물원 {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        final int mod = 9901;

        long[][] dp = new long[N+1][3];
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;
        for(int i=2;i<=N;i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%mod; //0번째 열에 아무 사자도 배치하지 않는 경우(이전 행의 모든 경우의 수 합)
            dp[i][1] = (dp[i-1][0] + dp[i-1][2])%mod; //1번째 열에 사자를 배치하는 경우
            dp[i][2] = (dp[i-1][0] + dp[i-1][1])%mod; //2번째 열에 사자를 배치하는 경우
        }

        System.out.println((dp[N][0]+dp[N][1]+dp[N][2])%mod);
    }
}
