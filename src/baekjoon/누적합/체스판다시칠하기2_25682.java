package baekjoon.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스판다시칠하기2_25682 {
    static int[][] map;
    static int N, M, K;
    static  int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            String str = br.readLine();
            for(int j=1; j<=M; j++){
                char ch = str.charAt(j-1);
                // 흰색 체스판 기준으로 체스판 초기화
                if((i%2==0 && j%2==0) || (i%2==1 && j%2==1)){
                    if(ch=='B'){
                        map[i][j] = 1;
                    }else{
                        map[i][j] = 0;
                    }
                }else{
                    if(ch=='W'){
                        map[i][j] = 1;
                    }else{
                        map[i][j] = 0;
                    }
                }
            }
        }

        accumulationSum();
        sectionSum();

        // 검은색 체스판에서의 최소값은, 총 크기에 흰색 체스판의 최대을 뺸 값이다.
        int result = Math.min(min, K*K-max);
        System.out.println(result);
    }

    //배열 누적합 구하기
    public static void accumulationSum(){
        //행 누적합 구하기
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                map[i][j] += map[i][j-1];
            }
        }

        //열 누적합 구하기
        for(int i=1; i<=M; i++){
            for(int j=1; j<=N; j++){
                map[j][i] += map[j-1][i];
            }
        }
    }

    // 배열 구간 합 구하기
    // 흰색 체스판 기준으로 k*k 크기에서 칠해야하는 칸의 최대, 최소 값 구하기
    public static void sectionSum(){
        for(int i=K; i<=N; i++){
            for(int j=K; j<=M; j++){
                // 구간 합 구하기
                int count = map[i][j] - (map[i-K][j] + map[i][j-K] - map[i-K][j-K]);
                min = Math.min(min, count);
                max = Math.max(max, count);
            }
        }
    }

}
