package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 죽음의비_22944 {
    static int N, H, D;
    static char[][] map;
    static int [][] check;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Pos {
        int x, y;
        int h, d;
        int cnt;
        public Pos (int x, int y, int h, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.d = d;
            this.cnt = cnt;
        }

        public boolean hasUmbrella() {
            return d != 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        check = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(check[i], Integer.MIN_VALUE);
        }

        int startX = 0, startY = 0;
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<N; j++) {
                char ch = input.charAt(j);
                if(ch == 'S') {
                    startX = i;
                    startY = j;
                }
                map[i][j] = ch;
            }
        }

        int answer = bfs(startX, startY);
        System.out.println(answer);
    }

    static int bfs(int startX, int startY) {
        int result = -1;
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(startX, startY, H, 0, 0));

        while(!queue.isEmpty()) {
            Pos cur = queue.poll();

            // 안전지대에 도착한 경우
            if(map[cur.x][cur.y] == 'E') {
                result = cur.cnt;
                break;
            }

            // 방문 가능 검증 (최대 이동 가능 거리 기준)
            if(check[cur.x][cur.y] < cur.h + cur.d) {
                check[cur.x][cur.y] = cur.h + cur.d;
            } else {
                continue;
            }

            // 체력 검증
            if(cur.h == 0) continue;

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nD = cur.d;
                int nH = cur.h;
                if(0>nx || nx>=N || 0>ny || ny>=N) continue;

                // 현재 위치에 우산이 있는 경우
                if(map[nx][ny] == 'U') {
                    nD = D -1;
                }
                // 우산이 없는 지점
                else {
                    if(cur.hasUmbrella()) {
                        nD -= 1;
                    } else {
                        nH -= 1;
                    }
                }

                queue.add(new Pos(nx, ny, nH, nD, cur.cnt + 1));
            }

        }

        return result;
    }

}
