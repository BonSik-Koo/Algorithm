package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp + dfs 문제
 */
public class 내리막길 {

    final static int[]dx = {1,-1,0,0};
    final static int[]dy = {0,0,1,-1};
    static int [][]dp;
    static int [][]field;
    static int M;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dp = new int[M+1][N+1];
        field = new int[M+1][N+1];
        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int val = Integer.parseInt(st.nextToken());

                field[i][j] = val;
                dp[i][j] = -1;
                /**
                 * -1: 아직 방문하지 않은 길
                 * 0 : 해당 좌표부터 도착지 좌표까지 경로가 없는길
                 * etc : 해당 죄표부터 도착지 좌표까지 존재하는 경로의 개수
                 */
            }
        }

        System.out.println(dfs(1,1));
    }

    public static int dfs(int x, int y){
        //목적지에 도착한 경우, 경로가 발견되었다고 리턴
        if(x==M && y==N){
            return 1;
        }

        //현재 좌표는 이미 발견된 지점, 즉 현재 좌표부터 도착지점 까지 경로가 있음을 보장
        //현재 좌표에서 도착지점까지 경로 수(dp)를 리턴
        //조기 멈춤 조건
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        /**
         * dp[x][y]를 -1를 초기화 하지 않으면 "시간 초과"가 뜬다.
         * 그 이유는, 특정 경로를 올때마다 default 로 0으로 초기화하는데, 만약 해당 좌표에서 다른 경로로 못가면
         * 해당 좌표에서 더 이상 갈곳이 없다는 것을 표시해주기 위해서!!
         * 해당 좌표에서 더 이상 갈곳이 없다고 미리 표시(0으로)를 했기 때문에 다른 경로에 특정 좌표에 도착했을 때,
         * dp[x][y]!=-1 이 아니면 즉 0 or etc 일 경우, 특정 좌표에서 이제 경로가 없다는 의미와 특정 좌표에서 도착지까지 존재하는 경로의 개수를
         * 리턴하므로써 2가지 경우 모두 조기 멈춤을 해주기 위해서!!!
         */
        dp[x][y] = 0;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<1 || nx>M || ny<1 || ny>N){
                continue;
            }

            if(field[x][y] > field[nx][ny]){
                dp[x][y] += dfs(nx,ny);
            }
        }

        return dp[x][y];
    }
}
