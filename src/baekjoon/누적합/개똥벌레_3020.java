package baekjoon.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 개똥벌레_3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] heightSum = new int[H+2];
        int[] countSum = new int[N+1];
        for(int i=0; i<N; i++){
            int height = Integer.parseInt(br.readLine());

            if(i%2==0) { // 석순
                heightSum[1] += 1;
                heightSum[height+1] += -1;
            } else{ // 종유석
                heightSum[H-height+1] += 1;
                heightSum[H+1] += -1;
            }
        }

        int minCount = Integer.MAX_VALUE;
        for(int i=1; i<=H; i++) {
            heightSum[i] += heightSum[i-1];
            minCount = Math.min(minCount, heightSum[i]);
            countSum[heightSum[i]] += 1;
        }

        System.out.println(minCount + " " + countSum[minCount]);
    }
}
