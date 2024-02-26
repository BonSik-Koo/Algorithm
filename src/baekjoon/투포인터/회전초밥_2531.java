package baekjoon.투포인터;

import java.util.*;
import java.io.*;

public class 회전초밥_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] belt = new int[N*2]; // 회전초밥 벨트
        int[] eating = new int[d+1]; // 스시별 먹은 개수
        for(int i=0; i<N; i++) {
            int value = Integer.parseInt(br.readLine());
            belt[i] = value;
            belt[i+N] = value;
        }

        int result = Integer.MIN_VALUE;
        int count = 0; // 먹은 스시 개수
        int eatingKind = 0; // 먹은 스시 종류 개수 + 보너스 스시 포함
        int toIdx = 0;
        int fromIdx = 0;
        while(toIdx < N) {
            // 처음 먹는 스시 종류
            if(eating[belt[fromIdx]] == 0) {
                eatingKind++;
            }
            eating[belt[fromIdx]]++;
            count++;
            fromIdx++;

            // 연속된 4개 스시를 먹은 경우
            if(count == k) {
                if(eating[c] > 0) {
                    result = Math.max(result, eatingKind);
                } else {
                    result = Math.max(result, eatingKind + 1);
                }

                // 첫 스시 제거
                eating[belt[toIdx]]--;
                if(eating[belt[toIdx]] == 0) {
                    eatingKind--;
                }
                count--;
                toIdx++;
            }
        }

        System.out.println(result);
    }
}
