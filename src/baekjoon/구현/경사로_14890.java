package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로_14890 {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int i=0; i<N; i++) {
            if(isGo(i, 0, true)) {
                ans++;
            }
            if(isGo(0, i, false)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean isGo(int x, int y, boolean isRow) {
        int[] arr = new int[N];
        boolean[] isInstalled = new boolean[N];
        for(int i=0; i<N; i++) {
            if(isRow) {
                arr[i] = map[x][y+i];
            } else {
                arr[i] = map[x+i][y];
            }


        }

        for(int i=0; i<N-1; i++) {
            int diff = arr[i] - arr[i+1]; // 다음 칸과 높이차

            if(Math.abs(diff) >= 2) { // 차이가 2 이상인 경우
                return false;
            }
            if(diff == 0) { // 같은 높이인 경우
                continue;
            }
            if(diff == 1) { // 내리막인 경우
                for(int j=i+1; j<=i+L; j++) {
                    if(0>j || j>=N || arr[i+1]!=arr[j] || isInstalled[j]) {
                        return false;
                    }
                    isInstalled[j] = true;
                }
                i+=(L-1); // 다음 연산이 i++ 이니 -1 만큼
            }
            else if(diff == -1) { // 오르막인 경우
                for(int j=i; j>i-L; j--) {
                    if(0>j || j>=N || arr[i]!=arr[j] || isInstalled[j]) {
                        return false;
                    }
                    isInstalled[j] = true;
                }
            }
        }

        return true;
    }

}
