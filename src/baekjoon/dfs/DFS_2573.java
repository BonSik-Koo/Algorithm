package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * dfs 풀이
 */

public class DFS_2573 {
    static int row, col;
    static int[][]map;
    static boolean[][] visit;
    static int[]x ={1,-1,0,0};
    static int[]y ={0,0,1,-1};

    public static void main(String[] args) throws IOException {

        int result =0;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        visit = new boolean[row][col];

        //초기화
        for(int i=0;i<row;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0;j<col;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                visit[i][j] = false;
            }
        }

        while(true) {
            int count =0; //빙산의 분리된 개수
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (map[i][j] != 0 && visit[i][j] != true) {
                        visit[i][j] = true;
                        dfs(i, j);
                        count++;
                    }
                }
            }

            if(count >=2)
                break;

            int maxNum = year();//빙산이 녹는 과정
            if (maxNum ==0) { //빙산이 다녹았는지 확인 하는 경우
                result =0;
                break;
            }
            result++;
            for(int i=0;i<row;i++) //초기화
                Arrays.fill(visit[i], false);
        }

        System.out.println(result);
    }

    public static void dfs(int row1, int col1) {

        for(int i=0;i<4;i++) {
            int newRow = row1 + x[i];
            int newCol = col1 + y[i];
            if( (0>newRow||newRow>=row) || (0>newCol || newCol>=col))
                continue;

            if(map[newRow][newCol] != 0 && visit[newRow][newCol] !=true) {
                visit[newRow][newCol] = true;
                dfs(newRow, newCol);
            }
        }
    }

    public static int year() {
        int maxNum = 0;
        int [][]copy = new int[row][col];

        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                int space = 0;
                maxNum = Math.max(maxNum, map[i][j]);
                if(map[i][j] != 0 ) {

                    for(int k=0;k<4;k++) {
                        if( (0<=i+x[k] && i+x[k]<row) && (0<=j+y[k] && j+y[k]<col) && (map[i+x[k]][j+y[k]] == 0))
                            space++;
                    }
                    copy[i][j] = Math.max(map[i][j] - space, map[i][j] - map[i][j]);

                }else
                    copy[i][j] = map[i][j];
            }
        }

        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                map[i][j] = copy[i][j];
            }
        }
        return  maxNum;
    }

}
