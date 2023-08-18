package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            //초기화
            int []u_arr = new int[n+1];
            int []d_arr = new int[n+1];
            int [][] max_arr = new int[2][n+1];
            for(int j=0;j<n;j++){
                int t1 = Integer.parseInt(st1.nextToken());
                int t2 = Integer.parseInt(st2.nextToken());
                u_arr[j] = t1;
                d_arr[j] = t2;
            }
            max_arr[0][0] = u_arr[0];
            max_arr[1][0] = d_arr[0];
            max_arr[0][1] = u_arr[1] + d_arr[0];
            max_arr[1][1] = d_arr[1] + u_arr[0];

            //dp 시작
            for(int k=2;k<n;k++){
                //up 배열 위치
                max_arr[0][k] = Math.max(u_arr[k]+max_arr[1][k-1],
                        u_arr[k]+Math.max(max_arr[0][k-2], max_arr[1][k-2]));
                //down 배열 위치
                max_arr[1][k] = Math.max(d_arr[k]+max_arr[0][k-1],
                        d_arr[k]+Math.max(max_arr[0][k-2], max_arr[1][k-2]));
            }

            //결과 출력
            System.out.println(Math.max(max_arr[0][n-1], max_arr[1][n-1]));
        }
    }
}
