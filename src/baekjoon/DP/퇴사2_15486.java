package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 다시 풀 문제
 */
public class 퇴사2_15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[]dp = new int[N+1];
        int[][] info = new int[N][2]; //T, P
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, 0);
        int max = 0;
        for(int i=0;i<=N;i++){
            //i 날까지의 최대 가치
            max = Math.max(max, dp[i]);
            if(i==N){
                break;
            }

            int T = info[i][0];
            int P = info[i][1];
            //T기간 종료후 한번만 얻을 수 있는 가치 값을 표시
            //i날의 까지 max가치와 비교
            if(i+T <= N){
                dp[i+T] = Math.max(dp[i+T], max + P);
            }
        }

        System.out.println(max);
    }
}
