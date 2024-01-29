package softeer.dp;

import java.io.*;
import java.util.*;
public class 징검다리 {
    static int N;
    static int[] height;
    static int[] dp;
    static int result = 1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N];
        height = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            height[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        dp();
        System.out.println(result);
    }

    static void dp() {
        for(int i=1; i<N; i++){
            int max = 0;
            for(int j=i-1; j>=0; j--) {
                if(height[i] > height[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max+1;
            result = Math.max(result, dp[i]);
        }
    }

}
