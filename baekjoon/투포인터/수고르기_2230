package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 수고르기_2230 {
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] list = new int[N];
        for(int i=0;i<N;i++){
            list[i] = Integer.parseInt(br.readLine());
        }

        //투포인트 사용을 위해 정렬
        Arrays.sort(list);

        //투포인트
        int min = Integer.MAX_VALUE;
        int i =0,j =0;
        while (i<=j && j<N){
            if(list[j] - list[i] < M){
                j++;
            }else{
                min = Math.min(min, list[j]-list[i]);
                i++;
            }
        }

        System.out.println(min);
    }
}
