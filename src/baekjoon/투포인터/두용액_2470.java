package baekjoon.투포인터;

import java.util.*;
import java.io.*;
public class 두용액_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] value = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            value[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(value);

        int result = Integer.MAX_VALUE;
        String answer = "";
        int start = 0;
        int end = N-1;
        while(start<end){
            int diff = value[start] + value[end];
            if(Math.abs(diff) < Math.abs(result)) {
                answer = value[start] + " " + value[end];
                result = diff;
            }

            if(diff < 0){
                start++;
            } else if(diff > 0){
                end--;
            } else {
                result = 0;
                break;
            }
        }

        System.out.println(answer);
    }
}
