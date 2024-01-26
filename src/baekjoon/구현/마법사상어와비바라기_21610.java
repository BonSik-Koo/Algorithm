package baekjoon.구현;

import java.io.*;
import java.util.*;

public class 마법사상어와비바라기_21610 {
    static int[] dx = {0, 0, -1 , -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Cloud> clouds = init();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            move(clouds, d, s);
            rain(clouds);
            spread(clouds);
            updateNewCloud(clouds);
        }

        System.out.println(getResult());
    }

    static List<Cloud> init() {
        List<Cloud> clouds = new ArrayList<>();
        clouds.add(new Cloud(N,1));
        clouds.add(new Cloud(N,2));
        clouds.add(new Cloud(N-1,1));
        clouds.add(new Cloud(N-1,2));
        return clouds;
    }

    static void move(List<Cloud> clouds, int dIdx, int s) {
        for(Cloud cloud : clouds) {
            for(int i=0; i<s; i++){
                cloud.x += dx[dIdx];
                cloud.y += dy[dIdx];

                cloud.x = (cloud.x==0) ? (N) : cloud.x;
                cloud.x = (cloud.x==N+1) ? 1 : cloud.x;
                cloud.y = (cloud.y==0) ? (N) : cloud.y;
                cloud.y = (cloud.y==N+1) ? 1 : cloud.y;
            }
        }
    }

    static void rain(List<Cloud> clouds) {
        for(Cloud cloud : clouds){
            arr[cloud.x][cloud.y] += 1;
        }
    }

    static void spread(List<Cloud> clouds){
        for(Cloud cloud: clouds){
            int plus = 0;
            for(int i=2; i<=8; i+=2){
                int x = cloud.x + dx[i];
                int y = cloud.y + dy[i];
                if(x<1 || x>N || y<1 || y>N || arr[x][y]<1) {
                    continue;
                }
                plus++;
            }
            arr[cloud.x][cloud.y] += plus;
        }
    }

    static void updateNewCloud(List<Cloud> originalClouds){
        List<Cloud> newCloud = new ArrayList<>();
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(arr[i][j] < 2){
                    continue;
                }

                Cloud cloud = new Cloud(i, j);
                if(originalClouds.contains(cloud)) {
                    continue;
                }

                newCloud.add(cloud);
                arr[i][j] -= 2;
            }
        }

        originalClouds.clear();
        originalClouds.addAll(newCloud);
    }

    static int getResult() {
        int sum = 0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                sum += arr[i][j];
            }
        }
        return sum;
    }

    static class Cloud{
        int x, y;
        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Cloud cloud = (Cloud) obj;
            return x==cloud.x && y==cloud.y;
        }

    }

}
