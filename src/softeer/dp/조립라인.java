package softeer.dp;

import java.io.*;
import java.util.*;


public class 조립라인 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 초기화
        int[][] workTime = new int[2][N];
        int[][] moveTime = new int[2][N-1];
        for(int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int moveToB = Integer.parseInt(st.nextToken());
            int moveToA = Integer.parseInt(st.nextToken());

            workTime[0][i] = A;
            workTime[1][i] = B;
            moveTime[0][i] = moveToB;
            moveTime[1][i] = moveToA;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int endA = Integer.parseInt(st.nextToken());
        int endB = Integer.parseInt(st.nextToken());
        workTime[0][N-1] = endA;
        workTime[1][N-1] = endB;

        // dp
        int[][] dp = new int[2][N];
        dp[0][0] = workTime[0][0];
        dp[1][0] = workTime[1][0];
        for(int i=1; i<N; i++) {
            dp[0][i] = Math.min(dp[0][i-1]+workTime[0][i], dp[1][i-1]+workTime[0][i]+moveTime[1][i-1]);
            dp[1][i] = Math.min(dp[1][i-1]+workTime[1][i], dp[0][i-1]+workTime[1][i]+moveTime[0][i-1]);
        }

        System.out.println(Math.min(dp[0][N-1], dp[1][N-1]));
    }
}
