package baekjoon.구현;

import java.io.*;
import java.util.*;

public class 상어중학교_21609 {
    static int REMOVE = Integer.MIN_VALUE;
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int score = 0;
        while(true) {
            Block maxGroupInfo = findMaxBlockGroup();
            if(maxGroupInfo == null) { // 탈출 조건
                break;
            }

            // 스코어 업데이트
            score += (maxGroupInfo.totalCnt * maxGroupInfo.totalCnt);

            // 블록 그룹 제거
            remove(maxGroupInfo.standardX, maxGroupInfo.standardY);

            // 중력
            gravity();

            // 반시계 회전
            rotate();

            // 중력
            gravity();
        }

        System.out.println(score);
    }

    static Block findMaxBlockGroup() {
        visit = new boolean[N][N];
        Block maxBlock = new Block(0, 0, Integer.MIN_VALUE, Integer.MIN_VALUE);

        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                if(visit[x][y]) {
                    continue;
                }
                if(map[x][y] == -1) {
                    continue;
                }
                if(map[x][y] == REMOVE) {
                    continue;
                }
                if (map[x][y] == 0) {
                    continue;
                }

                Block cur = bfsBlock(x, y);
                if(cur == null) {
                    continue;
                }
                if(maxBlock.compareGroup(cur)) {
                    maxBlock = cur;
                };

                rainbowClear(visit);
            }
        }

        if(maxBlock.totalCnt == Integer.MIN_VALUE) { // 최대 블럭 그룹을 못찾은 경우
            return null;
        }
        return maxBlock;
    }

    static Block bfsBlock(int x, int y) {
        int color = map[x][y];
        int totalCnt = 1;
        int rainbowCnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visit[x][y] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(0>nx || nx>=N || 0>ny || ny>=N || visit[nx][ny]) {
                    continue;
                }
                if(map[nx][ny] == -1) {
                    continue;
                }
                if(map[nx][ny] == REMOVE) {
                    continue;
                }
                if(map[nx][ny] != color) {
                    if(map[nx][ny] == 0) {
                        rainbowCnt++;
                        totalCnt++;
                        visit[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                    continue;
                }

                totalCnt++;
                visit[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }

        if(totalCnt < 2) {
            return null;
        }
        return new Block(x, y, totalCnt, rainbowCnt);
    }

    static void rainbowClear(boolean[][] visit) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 0) {
                    visit[i][j] = false;
                }
            }
        }
    }

    static void remove(int x, int y) {
        visit = new boolean[N][N];
        visit[x][y] = true;
        int color = map[x][y];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        map[x][y] = REMOVE;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(0>nx || nx>=N || 0>ny || ny>=N) {
                    continue;
                }
                if(map[nx][ny] == -1) {
                    continue;
                }
                if(map[nx][ny] == REMOVE) {
                    continue;
                }
                if(visit[nx][ny]) {
                    continue;
                }
                if(map[nx][ny] != color) {
                    if(map[nx][ny] == 0) {
                        visit[nx][ny] = true;
                        map[nx][ny] = REMOVE;
                        queue.add(new int[]{nx, ny});
                    }
                    continue;
                }

                visit[nx][ny] = true;
                map[nx][ny] = REMOVE;
                queue.add(new int[]{nx, ny});
            }
        }
    }

    static void gravity() {
        for(int x=0; x<N; x++) {
            for(int y=N-2; y>=0; y--) {
                if(map[y][x]==REMOVE || map[y][x]==-1){
                    continue;
                }

                int cx = y;
                while(true) {
                    cx++;
                    if(cx >= N) {
                        break;
                    }
                    if(map[cx][x] == -1) {
                        break;
                    }
                    if(map[cx][x] != REMOVE) {
                        break;
                    }
                }
                cx--;

                if(cx == y) {
                    continue;
                }
                map[cx][x] = map[y][x];
                map[y][x] = REMOVE;
            }
        }
    }

    static void rotate() {
        int[][] nMap = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                nMap[i][j] = map[j][N-i-1];
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = nMap[i][j];
            }
        }
    }

    static class Block {
        int standardX, standardY;
        int totalCnt, rainbowCnt;
        public Block(int x, int y, int count, int rainbowCnt) {
            this.standardX = x;
            this.standardY = y;
            this.totalCnt = count;
            this.rainbowCnt = rainbowCnt;
        }

        public boolean compareGroup(Block other) {
            if(totalCnt != other.totalCnt) {
                return totalCnt < other.totalCnt;
            }
            if(rainbowCnt != other.rainbowCnt) {
                return rainbowCnt < other.rainbowCnt;
            }
            if(standardX != other.standardX) {
                return standardX < other.standardX;
            }
            return standardY < other.standardY;
        }
    }

}
