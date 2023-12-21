package baekjoon.DP;

import java.util.*;
import java.io.*;
public class 행렬곱셉순서_11049 {
    static int n;
    static int[][] dp;
    static int[] value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        value = new int[n+1];
        dp = new int[n][n];

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // ex) 5*2, 2*1 -> value[0]:5, value[1]:2, value[2]:1
            value[i] = r;
            value[i+1] = c;
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        System.out.println(topDown(0, n-1));
    }

    public static int topDown(int pos, int cur){
        if(pos == cur){
            return 0;
        }
        if(dp[pos][cur] != Integer.MAX_VALUE){ // 이미 한번 게산 된 경우
            return dp[pos][cur];
        }

        for(int i=pos; i<cur; i++){
            // ex) (A)(BCD) -> (A) + (BCD) + (value[A] * value[B] * value[D+1])
            int val = topDown(pos, i) + topDown(i+1, cur) + (value[pos]*value[i+1]*value[cur+1]);
            dp[pos][cur] = Math.min(dp[pos][cur], val);
        }

        return dp[pos][cur];
    }

}

