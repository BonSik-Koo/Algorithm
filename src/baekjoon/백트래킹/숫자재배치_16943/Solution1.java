package baekjoon.백트래킹.숫자재배치_16943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1 {
    static String A, B;
    static boolean[] visit;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = st.nextToken();
        B = st.nextToken();
        visit = new boolean[A.length()];

        dfs(0, new StringBuilder());
        System.out.println(result);
    }

    static void dfs(int count, StringBuilder value){
        // 종료 조건
        if(count == A.length()){
            int c = Integer.parseInt(value.toString());
            int b = Integer.parseInt(B);
            if(c < b){
                result = Math.max(c, result);
            }
            return;
        }

        // 조기 멈춤 조건
        if(value.length() > B.length()){
            return;
        }

        for(int i=0; i<A.length(); i++){
            if(visit[i]){
                continue;
            }

            if(count==0 && A.charAt(i)=='0'){ //첫자리는 0으로 시작할 수 없음
                continue;
            }

            visit[i] = true;
            dfs(count+1, value.append(A.charAt(i)));
            value.deleteCharAt(value.length()-1);
            visit[i] = false;
        }
    }

}
