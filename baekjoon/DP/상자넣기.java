package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상자넣기 {

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int []dp = new int[N+1];
        int []arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;
        int result = 0;
        for(int i=2;i<=N;i++){
            int maxLength = 0;
            for(int j=i-1;j>=1;j--){
                if(arr[i] > arr[j]) {
                    maxLength = Math.max(maxLength, dp[j]);
                }
            }
            dp[i] =  maxLength+1;
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
