package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 뱀_3190 {
    static final int EMPTY = -1, APPLE = 2, LEFT = 3, RIGHT = 4;
    static int N, L;
    static boolean[][] map;
    static int [][] status;
    static int[][] command;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        map = new boolean[N +1][N +1];
        status = new int[N +1][N +1];
        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            status[row][col] = APPLE; // 사과 표시
        }

        L = Integer.parseInt(br.readLine());
        command = new int[L][2];
        for(int i=0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int dir = (st.nextToken().equals("L")) ? LEFT : RIGHT;
            command[i][0] = time;
            command[i][1] = dir;
        }

        System.out.println(play());
    }

    private static int play() {
        int fDir = 3;
        int bDir = 3;
        int bRow = 1, bCol = 1;
        int fRow = 1, fCol = 1;
        int commandIdx = 0;
        int time = 0;
        map[1][1] = true; // 초기 시작

        while(true) {
            time++;
            fRow += dx[fDir];
            fCol += dy[fDir];

            if(isExit(fRow, fCol)) {
                break;
            }

            map[fRow][fCol] = true;
            if(status[fRow][fCol] == APPLE) { // 사과가 있는 경우
                status[fRow][fCol] = EMPTY; // 사과 지우기
            } else { // 사과가 없는 경우
                map[bRow][bCol] = false;
                bRow += dx[bDir];
                bCol += dy[bDir];
            }

            // 머리 방향 전환
            if(commandIdx < L && command[commandIdx][0] == time) {
                fDir = changeDir(fDir, command[commandIdx][1]); // 반환 전환
                status[fRow][fCol] = command[commandIdx][1]; // 반환 전환 표시
                commandIdx++;
            }

            // 꼬리 방향 전환
            if(status[bRow][bCol] == LEFT) {
                bDir = changeDir(bDir, LEFT);
                status[bRow][bCol] = EMPTY;
            } else if(status[bRow][bCol] == RIGHT) {
                bDir = changeDir(bDir, RIGHT);
                status[bRow][bCol] = EMPTY;
            }
        }

        return time;
    }

    private static boolean isExit(int row, int col) {
        return row < 1 || row > N || col < 1 || col > N || map[row][col];
    }

    private static int changeDir(int dir, int turn) {
        int nDir = 0;
        if(turn == LEFT) { // 왼쪽 회전
            nDir = (dir + 1) % 4;
        } else { // 오른쪽 회전
            if(dir == 0) {
                nDir = 3;
            } else {
                nDir = dir - 1;
            }
        }
        return nDir;
    }

}
