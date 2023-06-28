package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 점프 {

    static int N;
    static long [][]dp;
    static int [][]game;
    static final int []dx = {0,1};
    static final int []dy = {1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][N+1];
        game = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                game[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; //초기화, Point!!!!
            }
        }

        System.out.println(dfs(1,1));
    }

    public static long dfs(int x, int y){
        if(x==N && y==N){
            return 1;
        }

        //현재 시점에서 도착지까지 경로가 없는 경우 or 현재 시점에서 도착지까지 존재하는 경로의 개수
        //dp[x][y]=0 을 통해서 경로가 없음을 조기 멈춤
        //dp[x][y]=-1 을 통해서 아직 지나오지 않은 길
        //dp[x][y]!=-1 을 통해서 도착지까지 존재하는 경로의 개수 암.(조기 멈춤)
        if(dp[x][y] != -1){
            return dp[x][y];
        }
        dp[x][y] = 0; //현재 시점에서 도착지까지 경로가 없다고 기본값으로 초기화

        for(int i=0;i<2;i++){
            int jumpSize = game[x][y];
            int nx = x + dx[i]*jumpSize;
            int ny = y + dy[i]*jumpSize;

            if(nx<1 || ny<1 || nx>N || ny>N){
                continue;
            }
            dp[x][y] += dfs(nx, ny);
        }
        return dp[x][y];
    }
}
