package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 월드컵_6987 {
    // 총 15번 경기가 고정
    static int[] home = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
    static int[] away = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
    static int[][] value;
    static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result = "";

        for(int i=0; i<4; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 0:win, 1:draw, 2:loss
            value = new int[6][3];
            int sum = 0;
            for(int j=0; j<6; j++){
                for(int k=0; k<3; k++){
                    value[j][k] = Integer.parseInt(st.nextToken());
                    sum += value[j][k];
                }
            }

            answer = false;
            if(sum!=30){
                result += "0 ";
                continue;
            }

            dfs(0);
            result += (answer) ? "1 ": "0 ";
        }

        System.out.println(result);
    }

    public static void dfs(int idx){
        if(answer){
            return;
        }
        if(idx>=15){
            answer = true;
            return;
        }

        // 홈 승리, 어웨이 패배
        if(value[home[idx]][0]>0 && value[away[idx]][2]>0) {
            value[home[idx]][0]--;
            value[away[idx]][2]--;
            dfs(idx + 1);
            value[home[idx]][0]++;
            value[away[idx]][2]++;
        }

        // 홈, 어웨이 무승부
        if(value[home[idx]][1]>0 && value[away[idx]][1]>0) {
            value[home[idx]][1]--;
            value[away[idx]][1]--;
            dfs(idx + 1);
            value[home[idx]][1]++;
            value[away[idx]][1]++;
        }

        // 홈 패배, 어웨이 승리
        if(value[home[idx]][2]>0 && value[away[idx]][0]>0) {
            value[home[idx]][2]--;
            value[away[idx]][0]--;
            dfs(idx + 1);
            value[home[idx]][2]++;
            value[away[idx]][0]++;
        }
    }

}
