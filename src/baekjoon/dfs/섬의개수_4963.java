package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 섬의개수_4963 {
    static int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if(w==0 && h==0){
                break;
            }

            int[][] map = new int[h][w];
            boolean[][] visit = new boolean[h][w];
            int cnt = 0;
            for(int x=0; x<h; x++){
                st = new StringTokenizer(br.readLine());
                for(int y=0; y<w; y++){
                    map[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            for(int x=0; x<h; x++){
                for(int y=0; y<w; y++){
                    if(map[x][y] == 0 || visit[x][y]) {
                        continue;
                    }
                    findIsland(map, visit, x, y);
                    cnt++;
                }
            }

            sb.append(cnt + "\n");
        }

        System.out.println(sb.toString());
    }

    public static void findIsland(int[][] map, boolean[][] visit, int x, int y){
        visit[x][y] = true;

        for(int i=0; i<8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || nx>=map.length || ny<0 || ny>=map[0].length || map[nx][ny]==0 || visit[nx][ny]){
                continue;
            }
            findIsland(map, visit, nx, ny);
        }
    }

}
