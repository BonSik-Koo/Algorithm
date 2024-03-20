package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 오목_2615 {
    // 검은:1, 흰색: 2
    static int[][] map = new int[20][20];

    // 오른쪽, 아래, 왼대각 아래, 오대각 아래
    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, -1, 1};
    static boolean[][][] visit = new boolean[20][20][4];

    static int count;
    static int answerRow, answerCol;
    static int answerColor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=1; i<=19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        play();

        if(answerColor == 0) {
            System.out.println(0);
        } else {
            System.out.println(answerColor);
            System.out.println(answerRow + " " + answerCol);
        }
    }

    static void play() {
        for(int i=1; i<=19; i++) {
            for(int j=1; j<=19; j++) {
                // 빈칸
                if(map[i][j] == 0) continue;

                // 4방향 탐색
                for(int d=0; d<4; d++) {
                    if(visit[i][j][d]) continue;

                    // 연속된 개수 찾기
                    initAnswer();
                    find(map[i][j], i, j, d);

                    // 결과 검증 (종료 조건)
                    if(count == 5) {
                        answerColor = map[i][j];
                        return;
                    }
                }
            }
        }
    }

    static void find(int color, int row, int col, int dir) {
        visit[row][col][dir] = true;

        // 결과 정보 업데이트
        count++;
        if(answerCol > col) {
            answerCol = col;
            answerRow = row;
        } else if(answerCol == col) {
            if(answerRow > row) {
                answerCol = col;
                answerRow = row;
            }
        }

        // 다음 위치 탐색
        int nRow = row + dx[dir];
        int nCol = col + dy[dir];
        if(nRow<1 || nRow>19 || nCol<1 || nCol>19 || map[nRow][nCol]!= color || visit[nRow][nCol][dir]) {
            return;
        }
        find(color, nRow, nCol, dir);
    }

    static void initAnswer() {
        count = 0;
        answerRow = Integer.MAX_VALUE;
        answerCol = Integer.MAX_VALUE;
        answerColor = 0;
    }

}
