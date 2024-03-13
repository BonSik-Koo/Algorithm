package baekjoon.브루트포스.종이조각;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이조각_14391_비트마스킹 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][]map = new int[N][M];
        int answer = Integer.MIN_VALUE;

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        // 1 2 3
        // 3 4 5
        // 비트 표현 -> 101 111 (5 4 3 3 2 1)
        // 0: 가로, 1: 세로
        for(int s=0; s<(1<<(N*M)); s++) {
            int sum = 0;
            // 가로 값 찾기
            for(int i=0; i<N; i++) {
                int cur = 0;
                for(int j=0; j<M; j++) {
                    int k = i*M+j;
                    if( (s&(1<<k)) == 0) {
                        cur *= 10;
                        cur+=map[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            // 세로 값 착기
            for(int j=0; j<M; j++) {
                int cur = 0;
                for(int i=0; i<N; i++) {
                    int k = i*M+j;
                    if( (s&(1<<k)) != 0) {
                        cur *= 10;
                        cur+=map[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }

}
