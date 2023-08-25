package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다시 풀 문제
 */
public class 자두나무_2240 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] arr = new int[T+1];
        for(int i=1;i<=T;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[3][T+1][W+1]; //[위치][시간][이동 횟수]
        for(int t=1; t<=T; t++){
            if(arr[t] == 1){ //1위치에 사과가 떨어진 경우
                //0번 이동 했을 때(이전 시간의 위치와 같은 자리)
                dp[1][t][0] = dp[1][t-1][0] + 1;
                dp[2][t][0] = dp[2][t-1][0];

                for(int w=1; w<=W; w++){ //1~W 번 이동했을 때 최대 사과 개수
                    dp[1][t][w] = Math.max(dp[1][t-1][w], dp[2][t-1][w-1]) + 1;
                    dp[2][t][w] = Math.max(dp[2][t-1][w], dp[1][t-1][w-1]);
                }
            }else{ //2위치에 사과가 떨어진 경우
                dp[2][t][0] = dp[2][t-1][0];
                dp[1][t][0] = dp[1][t-1][0];

                for(int w=1; w<=W; w++){
                    dp[2][t][w] = Math.max(dp[2][t-1][w], dp[1][t-1][w-1]) + 1;
                    dp[1][t][w] = Math.max(dp[1][t-1][w], dp[2][t-1][w-1]);
                }
            }
        }

        int result = 0;
        for(int i=0;i<=W;i++){
            result =  Math.max(result, Math.max(dp[1][T][i], dp[2][T][i]));
        }
        System.out.println(result);
    }
}
