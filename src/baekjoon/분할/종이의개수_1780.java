package baekjoon.분할;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이의개수_1780 {
    static int[][] paper;
    static int value1 = 0 ; // -1
    static int value2 =0 ; // 0
    static int value3 =0; // 1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        paper = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(1, N, 1, N);
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
    }

    static void divide(int startX, int endX, int startY, int endY) {
        int num = paper[startX][startY];
        boolean success = true;
        for(int i=startX; i<=endX; i++){
            for(int j=startY; j<=endY; j++){
                if(num != paper[i][j]){
                    success = false;
                    break;
                }
            }
        }

        if(success) {
            if(num==-1){
                value1++;
            }else if(num==0){
                value2++;
            }else{
                value3++;
            }
            return;
        }

        int length = (endX - startX + 1);
        if(length <= 1){
            return;
        }

       for(int i=startX; i<=endX; i+=length/3) {
           for(int j=startY; j<=endY; j+=length/3) {
               divide(i, i+length/3-1, j, j+length/3-1);
           }
       }
    }

}
