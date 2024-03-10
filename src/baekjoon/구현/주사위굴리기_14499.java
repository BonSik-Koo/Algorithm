package baekjoon.구현;

import java.io.*;
import java.util.*;

public class 주사위굴리기_14499 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M, K;
    static int[][] map;
    static int x, y;
    static int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            int command = Integer.parseInt(st.nextToken());
            move(command-1);
        }
    }

    static void move(int command) {
        int nx = x + dx[command];
        int ny = y + dy[command];
        if(0>nx || nx>=N || 0>ny || ny>=M) {
            return;
        }

        roll(command, nx, ny);
        x = nx;
        y = ny;
    }

    static void roll(int command, int nx, int ny) {
        // 주사위 굴리기
        if(command == 3) { // 아래로 이동
            int temp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[4];
            dice[4] = dice[5];
            dice[5] = temp;
        } else if(command == 2) { // 위로 이동
            int temp = dice[5];
            dice[5] = dice[4];
            dice[4] = dice[2];
            dice[2] = dice[0];
            dice[0] = temp;
        } else if(command == 0) { // 오른쪽 이동
            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = dice[5];
            dice[5] = temp;
        } else {
            int temp = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[5];
            dice[5] = temp;
        }

        // 주사위 아랫면 값 업데이트
        if(map[nx][ny] == 0) {
            map[nx][ny] = dice[2];
        } else {
            dice[2] = map[nx][ny];
            map[nx][ny] = 0;
        }

        // 주샤위 윗면 출력
        System.out.println(dice[5]);

    }

}
