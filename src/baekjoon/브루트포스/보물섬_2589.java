package baekjoon.브루트포스;

import java.util.*;
import java.io.*;
public class 보물섬_2589 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] arr = new char[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = str.charAt(j);
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i=0; i<N; i++){ // 완탐
            for(int j=0; j<M; j++){
                if(arr[i][j] == 'W') {
                    continue;
                }

                boolean[][] visit = new boolean[N][M];
                int maxDis = Integer.MIN_VALUE;

                Queue<int[]> queue = new LinkedList<>(); // bfs 완탐
                queue.add(new int[]{i, j, 0});
                visit[i][j] = true;
                while(!queue.isEmpty()) {
                    int[] pos = queue.poll();
                    maxDis = Math.max(maxDis, pos[2]);

                    for(int k=0; k<4; k++) {
                        int nx = pos[0] + dx[k];
                        int ny = pos[1] + dy[k];
                        if(nx<0 || nx>=N || ny<0 || ny>=M || visit[nx][ny] || arr[nx][ny]=='W') {
                            continue;
                        }
                        visit[nx][ny] = true;
                        queue.add(new int[]{nx, ny, pos[2] + 1});
                    }
                }

                result = Math.max(result, maxDis);
                visit = new boolean[N][M];
            }
        }

        System.out.println(result);
    }
}
