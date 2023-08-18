package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class 팰린드롬_10942 {
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int []arr = new int[N+1];
        Boolean [][] dpArray = new Boolean[N+1][N+1];
        for(int i=0;i<=N;i++) {
            Arrays.fill(dpArray[i], false);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
             arr[i+1] = Integer.parseInt(st.nextToken());
        }

        //dp 로직 시작
        dp(dpArray, arr, N);

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(dpArray[start][end]){
                sb.append("1\n");
            }else{
                sb.append("0\n");
            }
        }
        System.out.println(sb);
    }
    private static void dp(Boolean[][]dpArray, int[]array, int N){
        for(int i=N;i>=1;i--){
            for(int j=i; j<=N; j++){
                //자기 자신인 경우
                if(i==j){
                    dpArray[i][j] = true;
                    continue;
                }
                //나란히 붙어있는 경우
                if(i+1==j && array[i]==array[j]){
                    dpArray[i][j] = true;
                    continue;
                }

                if(array[i]==array[j]){
                    dpArray[i][j] = (dpArray[i+1][j-1])?true:false;
                }
            }
        }
    }
}
