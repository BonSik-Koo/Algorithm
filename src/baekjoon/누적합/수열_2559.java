package baekjoon.누적합;

import java.io.*;
import java.util.StringTokenizer;

public class 수열_2559 {
    static final int MIN_VALUE = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        int[] sum = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합 계산
        for(int i=1; i<=N; i++) {
            sum[i] = sum[i-1] + arr[i];
        }

        // 연속 최대값 찾기
        int result = MIN_VALUE;
        for(int i=N; i>=K; i--) {
            int seqSum = sum[i] - sum[i-K];
            result = Math.max(result, seqSum);
        }

        System.out.println(result);
    }
}
