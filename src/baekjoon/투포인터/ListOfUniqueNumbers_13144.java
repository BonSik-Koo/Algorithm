package baekjoon.투포인터;

import java.util.*;
import java.io.*;

public class ListOfUniqueNumbers_13144 {
    static int N;
    static int[] arr, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        cnt = new int[100_001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(toPoint());
    }

    static long toPoint() {
        int left = 0, right = 0;
        long result = 0;

        while(left < N) {
            while(right < N && cnt[arr[right]] == 0) {
                cnt[arr[right++]]++;
            }

            result += right - left;
            cnt[arr[left++]]--;
        }

        return result;
    }

}
