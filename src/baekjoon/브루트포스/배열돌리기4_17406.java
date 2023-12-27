package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기4_17406 {
    static int N, M, K;
    static int[][] arr;
    static int[][] round;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1];
        round = new int[K][3];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            // 0:r, 1:c, 2:s
            round[i][0] = Integer.parseInt(st.nextToken());
            round[i][1] = Integer.parseInt(st.nextToken());
            round[i][2] = Integer.parseInt(st.nextToken());
        }

        bf(0, new int[K], new boolean[K]);
        System.out.println(result);
    }

    public static void bf(int count, int[] sequence, boolean[] visit){
        if(count == K) {
            doCycle(sequence);
            return;
        }

        for(int i=0; i<K; i++){
            if(visit[i]) continue;

            visit[i] = true;
            sequence[count] = i;
            bf(count+1, sequence, visit);
            visit[i] = false;
        }
    }
    public static void doCycle(int[] sequence){
        int[][] copy = copy();

        for(int i=0; i<sequence.length; i++){
            int r = round[sequence[i]][0];
            int c = round[sequence[i]][1];
            int S = round[sequence[i]][2];

            for(int s=1; s<=S; s++){
                // 오른쪽
                int rightTmp = copy[r-s][c+s];
                for(int y=c+s-1; y>=c-s; y--){
                    copy[r-s][y+1] = copy[r-s][y];
                }

                // 아래쪽
                int downTmp = copy[r+s][c+s];
                for(int x=r+s-1; x>=r-s; x--){
                    copy[x+1][c+s] = copy[x][c+s];
                }
                copy[r-s+1][c+s] = rightTmp;

                // 왼쪽
                int leftTmp = copy[r+s][c-s];
                for(int y=c-s+1; y<=c+s; y++){
                    copy[r+s][y-1] = copy[r+s][y];
                }
                copy[r+s][c+s-1] = downTmp;

                // 위쪽
                for(int x=r-s+1; x<=r+s; x++){
                    copy[x-1][c-s] = copy[x][c-s];
                }
                copy[r+s-1][c-s] = leftTmp;
            }
        }

        updateResult(copy);
    }

    public static int[][] copy() {
        int[][] copy = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }
    public static void updateResult(int[][] arr){
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++){
            int sum = 0;
            for(int j=1; j<=M; j++){
                sum += arr[i][j];
            }
            min = Math.min(min, sum);
        }
        result = Math.min(result, min);
    }

}
