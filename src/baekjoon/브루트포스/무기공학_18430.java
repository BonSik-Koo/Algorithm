package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 무기공학_18430 {
    static int N, M; // 열, 행
    static int[][] map;
    static int[][] check; // 0: 빈칸, 1: 부메랑 외각, 2: 부메랑 중심
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {-1, 0, 1, 0, -1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        check = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(result);
    }

    static void dfs(int idx) {
        if(idx == N * M) {
            int totalDegree = getTotalDegree();
            result = Math.max(result, totalDegree);
            return;
        }

        int i = idx / M;
        int j = idx % M;

        if(check[i][j] == 0) {
            for (int k = 0; k < 4; k++) {
                int firstX = i + dx[k];
                int firstY = j + dy[k];
                int secondX = i + dx[k + 1];
                int secondY = j + dy[k + 1];

                // 범위 검색
                if (isOutRange(firstX, firstY) || isOutRange(secondX, secondY))
                    continue;

                // 방문 검즘
                if (check[firstX][firstY] > 0 || check[secondX][secondY] > 0)
                    continue;

                check[firstX][firstY] = 1;
                check[secondX][secondY] = 1;
                check[i][j] = 2; // 중심
                dfs(idx + 1);
                check[firstX][firstY] = 0;
                check[secondX][secondY] = 0;
                check[i][j] = 0; // 중심
            }
        }

        dfs(idx+1);
    }

    private static int getTotalDegree() {
        int total = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(check[i][j] == 2) {
                    total += (map[i][j] * 2);
                } else if (check[i][j] == 1) {
                    total += map[i][j];
                }
            }
        }
        return total;
    }

    static boolean isOutRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

}
