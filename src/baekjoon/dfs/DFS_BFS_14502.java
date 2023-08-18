package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <풀이 순서>
 * 1. dfs로 벽(1) 세우기
 * 2. bfs로 바이러스 터트리기(2로 만들기)
 * 3. 반복문으로 안전 구역(0) count
 */

public class DFS_BFS_14502 {
    static int row;
    static int col;
    static int result = Integer.MIN_VALUE;

    static int[]x = {1,-1,0,0};
    static int[]y = {0, 0, 1,-1};
    static class location {
        int x; int y;
        public location(int x, int y) {this.x=x; this.y=y;}
    }
    static List<location> bLocationList = new ArrayList<>();
    static int[][] map;

    public static void wallDfs(int wallCount, int ni, int nj) {
        int newX=0; int newY=0;
        int newWallCount = 0;

        if(wallCount == 3) { //3개의 벽이 세워짐
            virusSpreadAndSaveAreaCount();
            return;
        }

        for(int i=ni;i<row;i++) {
            for(int j=nj;j<col;j++) {
                if(map[i][j]==0){
                    map[i][j] = 1; //벽을 세움
                    wallDfs(wallCount+1, ni, nj);
                    map[i][j] = 0; //원상 복구
                }
            }
        }
    }

    public static void virusSpreadAndSaveAreaCount() {

        //copy
        int[][]copyMap = new int[row][col];
        for(int z=0;z<row; z++) {
            for(int t=0;t<col;t++) {
                copyMap[z][t] = map[z][t];
            }
        }

        Queue<location> queue = new LinkedList<>();
        int newX=0; int newY=0;
        int count=0;

        for(int i=0;i<bLocationList.size();i++) {
            location bLocation = bLocationList.get(i); //바이러스 위치
            queue.add(bLocation);

            while(!queue.isEmpty()) { //바이러스 퍼트리기

                location loc = queue.poll();
                for(int j=0; j<4; j++) {
                    newX = loc.x +x[j] ; newY = loc.y + y[j];

                    if((0<=newX && newX<row) && (0<=newY &&newY<col) && (copyMap[newX][newY] == 0)) {
                        copyMap[newX][newY] = 2;
                        queue.add(new location(newX, newY));
                    }
                }
            }

        }

        //안전구역 count
        for(int q=0;q<row;q++) {
            for(int w=0;w<col;w++) {
                if(copyMap[q][w]==0)
                    count++;
            }
        }
        result = Math.max(count, result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];

        for(int i=0; i<row; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp==2) {
                    location bLocation = new location(i,j); //단지 바이러스의 위치를 알기위해서(바이러스를 찾는 반복문 횟수가 감소하니)
                    bLocationList.add(bLocation);
                }
                map[i][j] = temp;
            }
        }

        wallDfs(0,0,0);
        System.out.println(result);
    }
}
