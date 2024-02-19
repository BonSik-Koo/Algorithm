package baekjoon.bfs;

import java.io.*;
import java.util.*;

public class 두동전_16197 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[][] map;
    static boolean[][][][] visit;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M][N][M];
        Coin[] coins = new Coin[2];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = str.charAt(j);
                if (ch == 'o') {
                    Coin coin = new Coin(i, j);
                    coins[idx++] = coin;
                }
                map[i][j] = ch;
            }
        }

        int result = bfs(coins);
        System.out.println(result);
    }

    static int bfs(Coin[] coins) {
        Queue<Play> queue = new LinkedList<>();
        queue.add(new Play(coins[0], coins[1], 0));
        visit[coins[0].row][coins[0].col][coins[1].row][coins[1].col] = true;

        while (!queue.isEmpty()) {
            Play play = queue.poll();

            if (play.count >= 10) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nCoin1Row = play.coin1.row + dx[i];
                int nCoin1Col = play.coin1.col + dy[i];
                int nCoin2Row = play.coin2.row + dx[i];
                int nCoin2Col = play.coin2.col + dy[i];

                // 이동 가능 조건 체크
                if(!canMove(nCoin1Row, nCoin1Col)) {
                    nCoin1Row = play.coin1.row;
                    nCoin1Col = play.coin1.col;
                }
                if(!canMove(nCoin2Row, nCoin2Col)) {
                    nCoin2Row = play.coin2.row;
                    nCoin2Col = play.coin2.col;
                }

                // 종료 조건 체크
                int notOutCount = 0; // 나가지 않은 동전의 개수
                if (nCoin1Row >= 0 && nCoin1Row < N && nCoin1Col >= 0 && nCoin1Col < M) {
                    notOutCount++;
                }
                if (nCoin2Row >= 0 && nCoin2Row < N && nCoin2Col >= 0 && nCoin2Col < M) {
                    notOutCount++;
                }

                if (notOutCount == 1) {
                    return play.count + 1;
                } else if (notOutCount == 2 && !visit[nCoin1Row][nCoin1Col][nCoin2Row][nCoin2Col]) {
                    visit[nCoin1Row][nCoin1Col][nCoin2Row][nCoin2Col] = true;
                    queue.add(new Play(new Coin(nCoin1Row, nCoin1Col), new Coin(nCoin2Row, nCoin2Col), play.count + 1));
                }
            }
        }

        return -1;
    }

    static boolean canMove(int row, int col) {
        // 벽일 경우에만 이동할 수 없다.
        if(row>=0 && row<N && col>=0 && col<M && map[row][col]=='#') {
            return false;
        }
        return true;
    }

    static class Play {
        Coin coin1, coin2;
        int count;

        public Play(Coin coin1, Coin coin2, int count) {
            this.coin1 = coin1;
            this.coin2 = coin2;
            this.count = count;
        }
    }

    static class Coin {
        int row, col;

        public Coin(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
