package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로탐색 {
    static class Pos {
        int x, y;
        int count;
        public Pos(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    static int[]dx = {1,-1,0,0};
    static int[]dy = {0,0,1,-1};

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][] map = new int[N+1][M+1];
        for(int i=1; i<=N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j+1] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        int result = bfs(N, M, map);
        System.out.println(result);
    }
    private static int bfs(int N, int M, int [][]map){
        Boolean[][]visit = new Boolean[N+1][M+1];
        for(int i=0;i<=N;i++){
            Arrays.fill(visit[i], false);
        }
        Queue<Pos> queue = new LinkedList<>();
        int result = 0;

        queue.add(new Pos(1,1,1));
        visit[1][1] = true;

        while (!queue.isEmpty()){
            Pos popPos = queue.poll();
            if(popPos.x==N && popPos.y==M){
                result = popPos.count;
                break;
            }

            for(int i=0;i<4;i++){
                int nx = popPos.x + dx[i];
                int ny = popPos.y + dy[i];
                if(nx<1 || ny<1 || nx>N || ny>M){
                    continue;
                }
                if(visit[nx][ny]==true || map[nx][ny]==0){
                    continue;
                }

                queue.add(new Pos(nx,ny,popPos.count+1));
                visit[nx][ny] = true;
            }
        }
        return result;
    }
}
