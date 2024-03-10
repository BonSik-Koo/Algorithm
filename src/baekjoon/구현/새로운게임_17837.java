package baekjoon.구현;

import java.io.*;
import java.util.*;

public class 새로운게임_17837 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int WHITE = 0, RED = 1, BLUE = 2;
    static int N, K;
    static int[][] map;
    static List<Integer>[][] horses;
    static Map<Integer, Horse> horseInfo;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        horses = new ArrayList[N][N];
        horseInfo = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                horses[i][j] = new ArrayList<>();
            }
        }
        for (int number = 0; number < K; number++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            horseInfo.put(number, new Horse(x - 1, y - 1, dir - 1));
            horses[x - 1][y - 1].add(number);
        }

        int res = play();
        System.out.println(res > 1000 ? -1 : res);
    }

    static int play() {
        int turn = 0;
        while (++turn <= 1000) {
            for (int number = 0; number < K; number++) {
                Horse horse = horseInfo.get(number);

                // 시작 말부터 위에 존재하는 말 탐색
                List<Integer> upHorses = new ArrayList<>();
                int startIdx = 0;
                for (int p = 0; p < horses[horse.x][horse.y].size(); p++) {
                    if (horses[horse.x][horse.y].get(p) == number) {
                        startIdx = p;
                        break;
                    }
                }
                for (int p = startIdx; p < horses[horse.x][horse.y].size(); p++) {
                    upHorses.add(horses[horse.x][horse.y].get(p));
                }

                int nx = horse.x + dx[horse.dir];
                int ny = horse.y + dy[horse.dir];

                if (0 > nx || nx >= N || 0 > ny || ny >= N || map[nx][ny] == BLUE) {
                    nx -= dx[horse.dir];
                    ny -= dy[horse.dir];

                    int dir = (horse.dir % 2 == 0) ? horse.dir + 1 : horse.dir - 1;
                    nx += dx[dir];
                    ny += dy[dir];
                    horse.dir = dir; // 방향 전환

                    if (0 > nx || nx >= N || 0 > ny || ny >= N || map[nx][ny] == BLUE) { // 이동하지 않음
                        continue;
                    } else {
                        if (map[nx][ny] == RED) {
                            for (int p = upHorses.size() - 1; p >= 0; p--) {
                                horses[nx][ny].add(upHorses.get(p));
                                Horse h = horseInfo.get(upHorses.get(p));
                                horseInfo.put(upHorses.get(p), new Horse(nx, ny, h.dir));
                            }
                        } else {
                            for (Integer upHorse : upHorses) {
                                horses[nx][ny].add(upHorse);
                                Horse h = horseInfo.get(upHorse);
                                horseInfo.put(upHorse, new Horse(nx, ny, h.dir));
                            }
                        }
                    }
                } else if (map[nx][ny] == WHITE) {
                    for (Integer upHorse : upHorses) {
                        horses[nx][ny].add(upHorse);
                        Horse h = horseInfo.get(upHorse);
                        horseInfo.put(upHorse, new Horse(nx, ny, h.dir));
                    }
                } else if (map[nx][ny] == RED) {
                    for (int p = upHorses.size() - 1; p >= 0; p--) {
                        horses[nx][ny].add(upHorses.get(p));
                        Horse h = horseInfo.get(upHorses.get(p));
                        horseInfo.put(upHorses.get(p), new Horse(nx, ny, h.dir));
                    }
                }

                // 탈출 조건
                if (horses[nx][ny].size() >= 4) {
                    return turn;
                }

                // 이전 위치 말 제거
                for (int p = horses[horse.x][horse.y].size() - 1; p >= startIdx; p--) {
                    horses[horse.x][horse.y].remove(p);
                }
            }
        }

        return turn;
    }

    static class Horse {
        int x, y;
        int dir;

        public Horse(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
