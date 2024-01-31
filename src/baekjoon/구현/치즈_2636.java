package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈_2636 {
    static int[][] arr;
    static boolean[][] visit;
    static int N, M;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int deletedCount = 0;
        while (true) {
            visit = new boolean[N][M];

            // 1. 사라질 치즈 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if((i==0 || i==N-1) && (j==0 || j==M-1) && !visit[i][j]) {
                        bfs(i, j);
                    }
                }
            }

            // 2. 치즈 없애기
            deletedCount = remove();
            time++;

            // 3. 체크
            if(isCompleted()) {
                break;
            }
        }

        System.out.println(time);
        System.out.println(deletedCount);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visit[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny]) {
                    continue;
                }

                if (arr[nx][ny] == 1) {
                    arr[nx][ny] += 1; // 제거 대상 표시
                } else if (arr[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }

        }
    }

    static int remove() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 2) {
                    count++;
                    arr[i][j] = 0;
                }
            }
        }
        return count;
    }

    static boolean isCompleted() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
