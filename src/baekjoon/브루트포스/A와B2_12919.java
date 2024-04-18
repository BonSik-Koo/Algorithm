package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class A와B2_12919 {
    static boolean isSuccess = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        dfs(S, T);

        System.out.println(isSuccess ? 1 : 0);
    }

    static void dfs(String goal, String cur) {
        // 종료조건
        if(cur.isEmpty()) {
            return;
        }
        if(isSuccess) {
            return;
        }

        // 정답 검증
        if(goal.length() == cur.length()) {
            if(isSame(goal, cur)) {
                isSuccess = true;
            }
            return;
        }

        // 연산 1
        if(cur.charAt(cur.length()-1) == 'A') {
            String newStr = cur.substring(0, cur.length()-1);
            dfs(goal, newStr);
        }

        // 연산 2
        if(cur.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder();
            for(int i=cur.length()-1; i>=1; i--) {
                sb.append(cur.charAt(i));
            }
            dfs(goal, sb.toString());
        }
    }

    static boolean isSame(String goal, String cur) {
        for(int i=0; i<goal.length(); i++) {
            if(goal.charAt(i) != cur.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
