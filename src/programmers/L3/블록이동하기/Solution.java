package programmers.L3.블록이동하기;

import java.util.*;

class Solution {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private int[][] map;
    private int n;

    public int solution(int[][] board) {
        n = board.length;
        map = board;
        return bfs(board);
    }

    private int bfs(int[][] board) {
        boolean[][][] visit = new boolean[n][n][2];
        Queue<Robot> robots = new LinkedList<>();
        robots.add(new Robot(0, 0, 0, 0, 1, 0));

        while(!robots.isEmpty()) {
            Robot cur = robots.poll();
            int status = cur.status;
            int cBx = cur.br;
            int cBy = cur.bc;
            int cFx = cur.fr;
            int cFy = cur.fc;
            int count = cur.count;

            if(visit[cBx][cBy][status] && visit[cFx][cFy][status]) continue;
            visit[cBx][cBy][status] = true;
            visit[cFx][cFy][status] = true;

            if((cBx==n-1 && cBy==n-1) || (cFx==n-1 && cFy==n-1)) {
                return count;
            }

            // 기본 상하좌우 이동
            for(int i=0; i<4; i++) {
                int nBx = cBx + dx[i];
                int nBy = cBy + dy[i];
                int nFx = cFx + dx[i];
                int nFy = cFy + dy[i];

                if(isCondition(nBx, nBy, nFx, nFy)) {
                    robots.add(new Robot(status, nBx, nBy, nFx, nFy, count+1));
                }
            }

            // 90도 회전
            // 가로 상태
            if(status == 0) {
                // 위로 회전
                if(isCondition(cBx-1, cBy, cFx-1, cFy)) {
                    // 뒤축 고정
                    robots.add(new Robot(1, cBx, cBy, cBx-1, cBy, count+1));
                    // 앞축 고정
                    robots.add(new Robot(1, cFx-1, cFy, cFx, cFy, count+1));
                }

                // 아래로 회전
                if(isCondition(cBx+1, cBy, cFx+1, cFy)) {
                    // 뒤축 고정
                    robots.add(new Robot(1, cBx, cBy, cBx+1, cBy, count+1));
                    // 앞축 고정
                    robots.add(new Robot(1, cFx+1, cFy, cFx, cFy, count+1));
                }
            }
            // 세로 상태
            else if(status == 1){
                // 왼쪽으로 회전
                if(isCondition(cBx, cBy-1, cFx, cFy-1)) {
                    // 뒤축 고정
                    robots.add(new Robot(0, cBx, cBy, cBx, cBy-1, count+1));
                    // 앞축 고정
                    robots.add(new Robot(0, cFx, cFy-1, cFx, cFy, count+1));
                }

                // 오른쪽으로 회전
                if(isCondition(cBx, cBy+1, cFx, cFy+1)) {
                    // 뒤축 고정
                    robots.add(new Robot(0, cBx, cBy, cBx, cBy+1, count+1));
                    // 앞축 고정
                    robots.add(new Robot(0, cFx, cFy+1, cFx, cFy, count+1));
                }
            }
        }

        return 0;
    }

    private boolean isCondition(int x1, int y1, int x2, int y2) {
        if(x1<0 || x1>=n || x2<0 || x2>=n || y1<0 || y1>=n || y2<0 || y2>=n || map[x1][y1]==1 || map[x2][y2]==1) {
            return false;
        }
        return true;
    }

    private class Robot {
        int status; // 0:가로, 1:세로
        int br, bc;
        int fr, fc;
        int count;
        public Robot(int status, int br, int bc, int fr, int fc, int count) {
            this.status = status;
            this.br = br;
            this.bc = bc;
            this.fr = fr;
            this.fc = fc;
            this.count = count;
        }
    }
}