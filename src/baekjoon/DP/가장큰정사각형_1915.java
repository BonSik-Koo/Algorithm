package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰정사각형_1915 {

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());

        int maxSize = 0;
        int [][]arr = new int[n+1][m+1];
        int [][]dp = new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            String str = br.readLine();
            for(int j=1;j<=m;j++){
                arr[i][j] = Character.getNumericValue(str.charAt(j-1));
            }
        }

        //DP 시작
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                dp[i][j] = 0;
                if((i==0 && j==0) | arr[i][j]==0){
                    continue;
                }

                dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1],dp[i-1][j-1])) + 1;
                maxSize = Math.max(dp[i][j], maxSize);
            }
        }

        System.out.println(maxSize * maxSize);
    }
}
