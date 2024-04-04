package baekjoon.다익스트라;

import java.util.*;
import java.io.*;

public class 레이저통신_6087 {
    static char[][] map;
    static int w, h;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        visit = new boolean[4][h][w];
        int[][] c = new int[2][2];
        int cnt = 0;
        for(int i=0; i<h; i++) {
            String str = br.readLine();
            for(int j=0; j<w; j++) {
                char ch = str.charAt(j);
                map[i][j] = ch;
                if(ch == 'C') {
                    c[cnt][0] = i;
                    c[cnt][1] = j;
                    cnt++;
                }
            }
        }

        int answer = dij(c[0][0], c[0][1], c[1][0], c[1][1]);
        System.out.println(answer);
    }

    static int dij(int startX, int startY, int endX, int endY) {
        int result = Integer.MAX_VALUE;
        int[][] turnCnt = new int[h][w];
        for(int i=0; i<h; i++) {
            Arrays.fill(turnCnt[i], Integer.MAX_VALUE);
        }
        Queue<Pos> queue = new PriorityQueue<>();

        // 초기값
        turnCnt[startX][startY] = 0;
        for(int i=0; i<4; i++) {
            int nx = startX + dx[i];
            int ny = startY + dy[i];
            if(isCheck(nx, ny, i)) {
                queue.add(new Pos(nx, ny, i, 0));
                visit[i][nx][ny] = true;
            }
        }

        while(!queue.isEmpty()) {
            Pos cur = queue.poll();

            // 종료 조건 검증
            if(cur.x == endX && cur.y == endY) {
                result = cur.turn;
                break;
            }
            if(result <= cur.turn) {
                continue;
            }
            if(turnCnt[cur.x][cur.y] >= cur.turn) {
                turnCnt[cur.x][cur.y] = cur.turn;
            } else {
                continue;
            }

            visit[cur.dir][cur.x][cur.y] = true;

            // 직진
            int nx = cur.x + dx[cur.dir];
            int ny = cur.y + dy[cur.dir];
            if(isCheck(nx, ny, cur.dir)) {
                queue.add(new Pos(nx, ny, cur.dir, cur.turn));
            }

            // 왼쪽 90도 전환 거울 설치
            int nDir = (cur.dir+1)%4;
            nx = cur.x + dx[nDir];
            ny = cur.y + dy[nDir];
            if(isCheck(nx, ny, nDir)) {
                queue.add(new Pos(nx, ny, nDir, cur.turn+1));
            }

            // 오른쪽 90도 회전 거울 설치
            nDir = (cur.dir + 3) %4;
            nx = cur.x + dx[nDir];
            ny = cur.y + dy[nDir];
            if(isCheck(nx, ny, nDir)) {
                queue.add(new Pos(nx, ny, nDir, cur.turn + 1));
            }
        }

        return result;
    }

    static boolean isCheck(int x , int y, int dir) {
        if(x<0 || x>=h || y<0 || y>=w || map[x][y] == '*') {
            return false;
        }
        if(visit[dir][x][y]) {
            return false;
        }
        return true;
    }

    static class Pos implements Comparable<Pos>{
        int x, y;
        int dir; // 0: 위, 1: 왼, 2: 아래, 3: 오
        int turn; // 방향 전환 횟수 = 거울 설치
        public Pos(int x, int y, int dir, int turn) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.turn = turn;
        }
        @Override
        public int compareTo(Pos p) {
            return turn - p.turn;
        }
    }

}
