package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기_2146 {
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int result = Integer.MAX_VALUE;
    static int landNum = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[N][N];
        // 섬마다 번호 부여
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] >= 2 || arr[i][j]==0) { // 이미 번호가 부여된 섬
                    continue;
                }
                bfsLand(i, j, N);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] >= 2) { // 육지인 경우
                    visit = new boolean[N][N];
                    bfsBridge(i, j, N);
                }
            }
        }

        System.out.println(result);
    }

    static void bfsLand(int x, int y, int N) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visit[x][y] = true;
        arr[x][y] = landNum;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=N || visit[nx][ny] || arr[nx][ny]==0) {
                    continue;
                }

                visit[nx][ny] = true;
                arr[nx][ny] = landNum;
                queue.add(new int[] {nx, ny});
            }
        }
        landNum++;
    }

    static void bfsBridge(int x, int y, int N) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0}); // {x좌표, y좌표, 총 거리}
        visit[x][y] = true;
        int currentLandNum = arr[x][y];

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            if(arr[pos[0]][pos[1]]!=0 && arr[pos[0]][pos[1]] != currentLandNum) { // 새로운 육지를 발견한 경우
                result = Math.min(result, pos[2]-1);
                break;
            }

            for(int i=0; i<4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=N) {
                    continue;
                }
                if(arr[nx][ny]==currentLandNum || visit[nx][ny]) { // 바다이면서 방문하지 않는 바다만 탐색
                    continue;
                }

                visit[nx][ny] = true;
                queue.add(new int[]{nx, ny, pos[2]+1});
            }
        }
    }

}
