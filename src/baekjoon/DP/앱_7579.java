package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 앱_7579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N+1][10001];
		int[] memory = new int[N+1];
		int[] cost = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++){
			memory[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++){
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = Integer.MAX_VALUE;
		for(int i=1;i<=N;i++){
			int m = memory[i];
			int c = cost[i];
			
			for(int j=0;j<=10000;j++){
				// 초기값
				// 한개 일 때, 경우 생각해야 함.
				if(i==1){
					if(j>=c)
						dp[i][j] = m;
				}else {
					if(j >= c) {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - c] + m);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
				
				if(dp[i][j]>=M){
					result = Math.min(result, j);
				}
			}
		}
		
		System.out.println(result);
	}
}
