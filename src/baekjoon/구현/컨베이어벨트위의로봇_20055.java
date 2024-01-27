package baekjoon.구현;

import java.util.*;
import java.io.*;
public class 컨베이어벨트위의로봇_20055 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] rail = new int[2*N];
        boolean[] robot = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N*2; i++){
            rail[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<N; i++){
            robot[i] = false;
        }

        int count = 1;
        while(true) {
            // 1단계
            int lastVal = rail[2*N-1];
            for(int i=2*N-1; i>0; i--){
                rail[i] = rail[i-1];
            }
            rail[0] = lastVal;

            for(int i=N-1; i>0; i--){
                robot[i] = robot[i-1];
            }
            robot[0] = false;
            robot[N-1] = false; // 로봇이 있다면 내려야되니

            // 2단계
            for(int i=N-2; i>=0; i--){
                if(robot[i] && !robot[i+1] && rail[i+1]>0) {
                    robot[i] = false;
                    robot[i+1] = true;
                    rail[i+1]-=1;
                }
                robot[N-1] = false;
            }

            // 3단계
            if(rail[0] > 0) {
                robot[0] = true;
                rail[0] -= 1;
            }

            // 4단계
            int zeroCnt = 0;
            for(int i=0; i<N*2; i++){
                if(rail[i]==0){
                    zeroCnt ++;
                }
            }
            if(zeroCnt >= K) {
                break;
            }

            count++;
        }

        System.out.println(count);
    }

}
