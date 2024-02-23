package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int toIdx = 0;
        int fromIdx = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while(toIdx <= fromIdx && fromIdx<=N) {
            if(sum >= S) {
                minLength = Math.min(minLength, fromIdx - toIdx);
                sum -= arr[toIdx];
                toIdx++;
            } else {
                if(fromIdx >= N) {
                    break;
                }
                sum += arr[fromIdx];
                fromIdx++;
            }
        }

        if(minLength == Integer.MAX_VALUE) {
            System.out.println(0);
        }else {
            System.out.println(minLength);
        }
    }
}
