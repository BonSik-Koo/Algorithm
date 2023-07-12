package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 격자상의경로_10164 {

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][]dp = new int[N+1][M+1];
        //초기화
        for(int i=0;i<N;i++){
            Arrays.fill(dp[i],0);
        }

        //K번호 좌표 구하기
        int x = N, y = M;
        if(K!=0){
            if(K%M==0){
                x=K/M;
                y=M;
            }else{
                y=K%M;
                x=K/M+1;
            }
        }

        //시작
        dp[1][1] = 1;
        for(int i=1; i<=N;i++){
            for(int j=1;j<=M;j++){
                if(i==1 && j==1){
                    continue;
                }

                if((i>x && j<y) || (i<x && j>y)){
                    dp[i][j] = 0; //지나가지 않아도 되는길
                }else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[N][M]);
    }

}
