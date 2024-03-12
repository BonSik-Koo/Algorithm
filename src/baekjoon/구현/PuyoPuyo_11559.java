package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PuyoPuyo_11559 {
    static int N = 12, M = 6;
    static char[][] map = new char[N][M];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        int result = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        while (boomGroup()) {
            result++;
            gravity();
        }

        System.out.println(result);
    }

    private static boolean boomGroup() {
        boolean existGroup = false;
        boolean[][] visit = new boolean[N][M];

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j] || map[i][j] == '.') {
                    continue;
                }
                if (boom(i, j, visit)) { // 터트릴 그룹이 하라도 존재할 경우
                    existGroup = true;
                }
            }
        }
        return existGroup;
    }

    private static boolean boom(int x, int y, boolean[][] visit) {
        List<int[]> result = findGroupCount(x, y, visit);
        if (result.size() < 4) {
            return false;
        }

        for (int[] cur : result) {
            map[cur[0]][cur[1]] = '.'; // 빈칸으로 만들기
        }
        return true;
    }

    private static List<int[]> findGroupCount(int x, int y, boolean[][] visit) {
        List<int[]> list = new ArrayList<>(); // 최대 72개
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        list.add(new int[]{x, y});
        visit[x][y] = true;
        char color = map[x][y];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (0 > nx || nx >= N || 0 > ny || ny >= M || visit[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] != color) { // '.' 도 포함
                    continue;
                }
                queue.add(new int[]{nx, ny});
                list.add(new int[]{nx, ny});
                visit[nx][ny] = true;
            }
        }
        return list;
    }

    private static void gravity() {
        for (int y = 0; y < M; y++) {
            for (int x = N - 2; x >= 0; x--) {
                if(map[x][y] == '.') {
                    continue;
                }

                int cur = x + 1;
                while (cur < N) {
                    if(map[cur][y] == '.') {
                        cur++;
                        continue;
                    }
                    break;
                }
                cur--;
                if(cur == x) { // 원래 위치인 경우
                    continue;
                }
                map[cur][y] = map[x][y];
                map[x][y] = '.';
            }
        }
    }
}
