package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 다시 풀 문제
 */
public class 줄세우기_2631 {

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[]dp = new int[N+1];
        int[]arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        //DP 로직 시작
        //가장 긴 오름 차순 개수 구하기
        Arrays.fill(dp, 1);
        int maxL = 1;
        for(int i=2;i<=N;i++){
            for(int j=1; j<i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxL = Math.max(maxL, dp[i]);
        }

        System.out.println(N-maxL);
    }
}
