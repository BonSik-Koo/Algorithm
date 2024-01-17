package baekjoon.dfs;


import java.util.*;
import java.io.*;
public class 게임_1103 {
    static int N, M;
    static char[][] arr;
    static int[][] dp;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        dp = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = str.charAt(j);
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        int result = dfs(0, 0);
        System.out.println(result);
    }

    static int dfs(int row, int col){
        // 이미 방문한 노드이만 저장된 횟수 반환
        if(dp[row][col] != Integer.MIN_VALUE) {
            return dp[row][col];
        }

        int maxCnt = 0;
        boolean flag = false;
        for(int i=0; i<4; i++){
            int nx = row + dx[i] * Character.getNumericValue(arr[row][col]);
            int ny = col + dy[i] * Character.getNumericValue(arr[row][col]);
            if(nx<0 || nx>=N || ny<0 || ny>=M || arr[nx][ny]=='H') {
                continue;
            }

            if(visit[nx][ny]){
                flag = true;
                break;
            }

            visit[nx][ny] = true;
            int cnt = dfs(nx, ny);
            if(cnt==-1){
                flag = true;
                break;
            }
            maxCnt = Math.max(maxCnt, cnt);
            visit[nx][ny] = false;
        }

        if(flag) {
            return -1;
        }

        dp[row][col] = maxCnt + 1;
        return maxCnt + 1;
    }

}
