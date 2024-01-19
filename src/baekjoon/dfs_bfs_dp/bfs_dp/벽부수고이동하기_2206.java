package baekjoon.dfs_bfs_dp.bfs_dp;

import java.util.*;
import java.io.*;
public class 벽부수고이동하기_2206 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0 ,1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] arr = new char[N+1][M+1];
        boolean[][][] visit = new boolean[N+1][M+1][2];

        for(int i=1; i<=N; i++){
            String str = br.readLine();
            for(int j=1 ;j<=M; j++){
                arr[i][j] = str.charAt(j-1);
            }
        }

        int result = -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1,1, 1, false));
        visit[1][1][0] = true;
        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(node.x == N && node.y == M){
                result = node.count;
                break;
            }

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx<=0 || nx>N || ny<=0 || ny>M) {
                    continue;
                }

                if(arr[nx][ny] == '0') {
                    // 벽 뿌숨을 사용했을 때
                    if(node.isUsed && !visit[nx][ny][1]) {
                        visit[nx][ny][1] = true;
                        queue.add(new Node(nx, ny, node.count + 1, node.isUsed));
                    }
                    // 벽 뿌숨을 사용안했을 때
                    else if (!node.isUsed && !visit[nx][ny][0]){
                        visit[nx][ny][0] = true;
                        queue.add(new Node(nx, ny, node.count + 1, node.isUsed));
                    }
                    continue;
                }

                if(arr[nx][ny] == '1' && !node.isUsed && !visit[nx][ny][1]) {
                    visit[nx][ny][1] = true;
                    queue.add(new Node(nx, ny, node.count + 1, true));
                }
            }
        }

        System.out.println(result);
    }

    static class Node {
        int x, y, count;
        boolean isUsed;
        public Node(int x, int y, int count, boolean isUsed){
            this.x = x;
            this.y = y;
            this.count = count;
            this.isUsed = isUsed;
        }
    }
}
