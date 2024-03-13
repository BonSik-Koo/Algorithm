package baekjoon.브루트포스.종이조각;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이조각_14391_재귀 {
    static int N, M;
    static int[][] map;
    static int[][] dir;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dir = new int[N][M];

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int row, int col) {
        if(row >= N) {
            calculate();
            return;
        }

        for(int i=0; i<2; i++) { // 0: 세로, 1: 가로
            dir[row][col] = i;

            int nRow = row;
            int nCol = col;
            if(col == M-1) {
                nRow += 1;
                nCol = 0;
            } else {
                nCol += 1;
            }
            dfs(nRow, nCol);
        }
    }

    private static void calculate() {
        boolean[][] visit = new boolean[N][M];
        int total = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++){
                int sum = 0;
                if(visit[i][j]) {
                    continue;
                }

                if(dir[i][j] == 1) { // 가로
                    int idx = j;
                    while(idx<M && dir[i][idx] == 1) { // 같은 가로일 때까지 반복
                        sum *= 10;
                        sum += map[i][idx];

                        visit[i][idx] = true;
                        idx++;
                    }
                } else { // 세로
                    int idx = i;
                    while(idx<N && dir[idx][j] == 0) {
                        sum *= 10;
                        sum += map[idx][j];

                        visit[idx][j] = true;
                        idx++;
                    }
                }
                total += sum;
            }
        }

        answer = Math.max(answer, total);
    }

}
