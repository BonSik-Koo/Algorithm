package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내려가기 {

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][]minDp = new int[N][3];
        int[][]maxDp = new int[N][3];
        int[][]arr = new int[N][3];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                int val = Integer.parseInt(st.nextToken());
                //초기값
                if(i==0){
                    minDp[i][j] = val;
                    maxDp[i][j] = val;
                }
                arr[i][j] = val;
            }
        }

        //Dp 시작
        int maxResult = Math.max(maxDp[0][0], Math.max(maxDp[0][1], maxDp[0][2]));
        int minResult = Math.min(minDp[0][0], Math.min(minDp[0][1], minDp[0][2]));
        for(int i=1;i<N;i++){
            //min DP
            minDp[i][0] = Math.min(arr[i][0]+minDp[i-1][0], arr[i][0]+minDp[i-1][1]);
            minDp[i][1] = Math.min(arr[i][1]+minDp[i-1][1], Math.min(arr[i][1]+minDp[i-1][0], arr[i][1]+minDp[i-1][2]));
            minDp[i][2] = Math.min(arr[i][2]+minDp[i-1][2], arr[i][2]+minDp[i-1][1]);
            minResult = Math.min(minDp[i][0], Math.min(minDp[i][1], minDp[i][2]));

            //max DP
            maxDp[i][0] = Math.max(arr[i][0]+maxDp[i-1][0], arr[i][0]+maxDp[i-1][1]);
            maxDp[i][1] = Math.max(arr[i][1]+maxDp[i-1][1], Math.max(arr[i][1]+maxDp[i-1][0], arr[i][1]+maxDp[i-1][2]));
            maxDp[i][2] = Math.max(arr[i][2]+maxDp[i-1][2], arr[i][2]+maxDp[i-1][1]);
            maxResult = Math.max(maxDp[i][0], Math.max(maxDp[i][1], maxDp[i][2]));
        }

        System.out.println(maxResult + " " + minResult);
    }
}
