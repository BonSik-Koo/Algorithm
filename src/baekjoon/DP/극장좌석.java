package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 극장좌석 {

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int []arr = new int[M+1];
        for(int i=0;i<M;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr[M] = N+1; //마지막 좌석 번호 표시를 위해

        int[] diff = new int[M+1];//vip 좌석 간 간격(간격으로 dp 점화식 구성)
        int init = 0;
        int maxDiff =0;
        for(int i=0;i<=M;i++){
            diff[i] = (arr[i]-1) - init;

            maxDiff = Math.max(maxDiff, diff[i]);
            init = arr[i];
        }

        //초기값
        int[]dp = new int[maxDiff+1];
        dp[0] = 1;
        if(maxDiff>=1) {
            dp[1] = 1;
        }
        for(int i=2;i<=maxDiff;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        //결과값
        long result = 1;
        for(int i=0;i<=M;i++){
            result *= dp[diff[i]];
        }
        System.out.println(result);
    }
}
