package baekjoon.DP.게단오르기p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DP + 재귀 함수(Top Down) 방식
 * 다시 풀 문제
 */
public class 계단오르기 {
    static int[]arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1];

        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(br.readLine());
            dp[i] = -1; //초기화
        }

        //초기화
        dp[0] = 0; //빈 값(
        dp[1] = arr[1];
        if(N>=2){
            dp[2] = arr[2] + arr[1]; //2번째 칸을 밟았을 때 그전까지 올 수 있는 최대 값(1,2칸 모두 밟음)
        }

        System.out.println(findDF(N));
    }
    //재귀함수(Top Down) + DP
    public static int findDF(int n){
        if(dp[n]==-1){ //구하지 않은 지점
            dp[n] = Math.max(findDF(n-2), findDF(n-3)+arr[n-1]) + arr[n];
        }
        return dp[n];
    }
}
