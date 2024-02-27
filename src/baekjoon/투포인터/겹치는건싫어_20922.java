package baekjoon.투포인터;

import java.io.*;
import java.util.*;

public class 겹치는건싫어_20922 {
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(toPoint());
    }

    static int toPoint() {
        int result = Integer.MIN_VALUE;
        int left = 0, right = 0;
        int[] cnt = new int[100_001];

        while(left < N) {
            while(right < N && cnt[arr[right]] + 1 <= K) {
                cnt[arr[right++]]++;
            }

            result = Math.max(result, right - left);
            cnt[arr[left++]]--;
        }

        return result;
    }

}
