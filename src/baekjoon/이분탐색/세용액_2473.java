package baekjoon.이분탐색;

import java.io.*;
import java.util.*;

public class 세용액_2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] res = new long[3];
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long diff = Long.MAX_VALUE;
        for(int i=0; i<N; i++) {
            int left = i+1;
            int right = N-1;
            while(left < right) {
                long sum = arr[left] + arr[right] + arr[i];

                long curDiff = Math.abs(sum);
                if(curDiff < diff) {
                    diff = curDiff;
                    res[0] = arr[i];
                    res[1] = arr[left];
                    res[2] = arr[right];
                }

                if(sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        Arrays.sort(res);
        System.out.println(res[0] + " " + res[1] + " " + res[2]);
    }

}
