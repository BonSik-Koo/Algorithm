package baekjoon.구현;

import java.util.*;
import java.io.*;

public class 마법사상어와파이어볼_20056 {
    static int N, K;
    static List<FireBall>[][] map;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        init();
        command();
        System.out.println(getSumM());
    }

    private static void command() {
        int count = 0;
        while(count++ < K) {
            // 파이어볼 이동
            move();
            // 파이어볼 합치기
            integrate();
        }
    }

    private static void move() {
        // 복사본
        List<FireBall>[][] copy = new List[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                copy[i][j] = new ArrayList<>();
            }
        }

        // 파이어볼 이동
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(FireBall ball : map[i][j]) {
                    int nx = (i + N + dx[ball.d] * (ball.s % N)) % N;
                    int ny = (j + N + dy[ball.d] * (ball.s % N)) % N;
                    copy[nx][ny].add(new FireBall(ball.m, ball.s, ball.d));
                }
            }
        }

        // 복사본 복사
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j].clear();
                for(FireBall temp : copy[i][j]) {
                    map[i][j].add(temp);
                }
            }
        }
    }

    private static void integrate() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j].size() < 2) continue;

                int size = map[i][j].size();
                int sumM = 0;
                int sumS = 0;
                int count1 = 0; // 짝수 방향 개수
                int count2 = 0; // 홀수 방향 개수
                for(FireBall ball : map[i][j]) {
                    sumM += ball.m;
                    sumS += ball.s;

                    if(ball.d % 2 == 0) {
                        count1++;
                    } else {
                        count2++;
                    }
                }

                map[i][j].clear();
                int nD = (count1!=0 && count2!=0) ? 1 : 0;
                for(int k=0; k<4; k++) {
                    int nM = sumM / 5;
                    int nS = sumS / size;

                    if(nM == 0) break;

                    map[i][j].add(new FireBall(nM, nS, nD));
                    nD += 2;
                }
            }
        }
    }

    private static int getSumM() {
        int sum = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j].isEmpty()) continue;

                for(FireBall ball : map[i][j]) {
                    sum += ball.m;
                }
            }
        }

        return sum;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new List[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(new FireBall(m, s, d));
        }
    }

    private static class FireBall {
        int m, s, d;
        public FireBall(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

}
