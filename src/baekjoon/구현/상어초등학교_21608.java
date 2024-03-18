package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 상어초등학교_21608 {
    static int[][] map;
    static int N;
    static List[] likeInfo;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};
    static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        likeInfo = new List[N*N+1];

        for(int i=0; i<N*N; i++) {
            List<Integer> like = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for(int j=0; j<4; j++) {
                like.add(Integer.parseInt(st.nextToken()));
            }
            choice(student, like);
            likeInfo[student] = like;
        }

        System.out.println(getResult());
    }

    static void choice(int student, List<Integer> like) {
        Pos priorityPos = new Pos(-1, -1, -1, -1);

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                // 이미 앉은 자리
                if(map[i][j] != EMPTY) {
                    continue;
                }

                int likeCount = 0;
                int emptyCount = 0;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx<0 || nx>=N || ny<0 || ny>=N) {
                        continue;
                    }

                    if(map[nx][ny] == EMPTY) {
                        emptyCount++;
                        continue;
                    }
                    if(like.contains(map[nx][ny])) {
                        likeCount++;
                    }
                }

                Pos pos = new Pos(likeCount, emptyCount, i, j);
                if(pos.isHighPriorityTo(priorityPos)) {
                    priorityPos = pos;
                }
            }
        }

        map[priorityPos.row][priorityPos.col] = student;
    }

    static int getResult() {
        int answer = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                List<Integer> like = likeInfo[map[i][j]];
                int cur = 0;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx<0 || nx>=N || ny<0 || ny>=N) {
                        continue;
                    }

                    if(like.contains(map[nx][ny])) {
                        cur++;
                    }
                }

                if(cur == 1) {
                    answer += 1;
                } else if(cur == 2) {
                    answer += 10;
                } else if(cur == 3) {
                    answer += 100;
                } else if(cur == 4) {
                    answer += 1000;
                }
            }
        }
        return answer;
    }

    static class Pos {
        int likeCount, emptyCount;
        int row, col;
        public Pos(int likeCount, int emptyCount, int row, int col) {
            this.likeCount = likeCount;
            this.emptyCount = emptyCount;
            this.row = row;
            this.col = col;
        }
        public boolean isHighPriorityTo(Pos target) {
            if(likeCount > target.likeCount) {
                return true;
            } else if(likeCount < target.likeCount) {
                return false;
            }

            if(emptyCount > target.emptyCount) {
                return true;
            } else if(emptyCount < target.emptyCount) {
                return false;
            }

            if(row < target.row) {
                return true;
            } else if(row > target.row) {
                return false;
            }

            return col < target.col;
        }
    }

}
