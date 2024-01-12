package baekjoon.백트래킹.숫자재배치_16943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {
    static int[] A;
    static int B;
    static boolean[] visit;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String AStr = st.nextToken();
        B = Integer.parseInt(st.nextToken());

        visit = new boolean[AStr.length()];
        A = new int[AStr.length()];
        for(int i=0; i<AStr.length(); i++){
            // 문자를 정수형으로 넣으니 아스키코드 변환이 필요
            A[i] = AStr.charAt(i) - '0';
        }

        dfs(0, 0);
        System.out.println(result);
    }

    static void dfs(int count, int value){
        if(count == A.length){
            result = Math.max(value, result);
            return;
        }

        for(int i = 0; i< A.length; i++){
            if(visit[i] || (count==0 && A[i]==0)) {
                continue;
            }
            // 조기 멈춤 조건
            if(value*10 + A[i] > B){
                continue;
            }

            visit[i] = true;
            dfs(count+1, value*10 + A[i]);
            visit[i] = false;
        }
    }

}
