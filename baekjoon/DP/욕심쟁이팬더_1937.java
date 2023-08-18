package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욕심쟁이판다_1937 {
    static int n;
    static int[][] dp;
    static int[][] forest;
    static int[]dx ={1,-1,0,0};
    static int[]dy = {0,0,1,-1};
    static int result = 0;
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][n+1];
        forest = new int[n+1][n+1];

        //초기화
        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                forest[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = 0;
            }
        }

        //dp + dfs
        //memory(dp)를 통해 한번 탐색한 dfs 경로는 재방문하지 않게하기.
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                int count = dfs(i, j);
                result = Math.max(result, count);
            }
        }

        System.out.println(result);
    }

    private static int dfs(int x, int y){
        //memory에 존재하는 경우(dp)
        if(dp[x][y] != 0){
            return dp[x][y];
        }

        dp[x][y] = 1;
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<=0 || nx>n || ny<=0 || ny>n){
                continue;
            }
            if(forest[x][y] < forest[nx][ny]) {
                dp[x][y] = Math.max(dfs(nx, ny) + 1, dp[x][y]);
            }
        }
        return dp[x][y];
    }
}

