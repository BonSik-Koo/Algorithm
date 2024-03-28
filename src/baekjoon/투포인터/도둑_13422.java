package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도둑_13422 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] money = new int[N*2+1]; // 배열 확장
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) {
                money[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=N+1; i<=N*2; i++) {
                money[i] = money[i-N];
            }

            // 누적합 + 투포인터 탐색
            int start = 1, end = 1;
            int sum = 0;
            int count = 0;
            while(start<=N) {
                sum += money[end];

                if(end - start == M-1) {
                    if(sum < K) {
                        count++;
                    }
                    sum -= money[start++];
                }

                // 예외 케이스
                if(end == N && N == M) {
                    break;
                }

                end++;
            }

            sb.append(count + "\n");
        }

        System.out.println(sb.toString());
    }
}
