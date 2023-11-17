package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class 미세먼지안녕_17114 {
    static int[][] map;
    static List<Integer> machineRow;
    static int R, C;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        machineRow = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
                if (value == -1) {
                    machineRow.add(i);
                }
            }
        }

        for (int i = 0; i < T; i++) {
            spread();
            operateUp();
            operateDown();
        }
        int result = getResult();
        System.out.println(result);
    }

    public static int getResult() {
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    result += map[i][j];
                }
            }
        }
        return result;
    }

    public static void spread() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] <= 0) {
                    continue;
                }
                //미세 먼지가 있는 경우
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) {
                        continue;
                    }
                    temp[nx][ny] += map[i][j] / 5;
                    count++;
                }
                temp[i][j] += (map[i][j] - (map[i][j] / 5) * count);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    public static void operateUp() {
        int mR = machineRow.get(0);
        int[][] temp = new int[R][C];
        temp[mR][0] = -1;

        //오른쪽
        for (int i = 1; i < C - 1; i++) {
            temp[mR][i + 1] = map[mR][i];
        }
        //위쪽
        for (int i = mR; i >= 1; i--) {
            temp[i - 1][C - 1] = map[i][C - 1];
        }
        //왼쪽
        for (int i = C - 1; i > 0; i--) {
            temp[0][i - 1] = map[0][i];
        }
        //아래쪽(먼지 제거 고려)
        for (int i = 0; i < mR - 1; i++) {
            temp[i + 1][0] = map[i][0];
        }

        for (int i = 0; i <= mR; i++) {
            for (int j = 0; j < C; j++) {
                if (i != 0 && i != mR && j != 0 && j != C - 1) {
                    continue;
                }
                map[i][j] = temp[i][j];
            }
        }
    }

    public static void operateDown() {
        int mR = machineRow.get(1);
        int[][] temp = new int[R][C];
        temp[mR][0] = -1;

        //오른쪽
        for (int i = 1; i < C - 1; i++) {
            temp[mR][i + 1] = map[mR][i];
        }
        //아래쪽
        for (int i = mR; i < R - 1; i++) {
            temp[i + 1][C - 1] = map[i][C - 1];
        }
        //왼쪽
        for (int i = C - 1; i > 0; i--) {
            temp[R - 1][i - 1] = map[R - 1][i];
        }
        //위쪽(먼지 제거 고려)
        for (int i = R - 1; i > mR + 1; i--) {
            temp[i - 1][0] = map[i][0];
        }

        for (int i = mR; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i != mR && i != R - 1 && j != 0 && j != C - 1) {
                    continue;
                }
                map[i][j] = temp[i][j];
            }
        }
    }

}
