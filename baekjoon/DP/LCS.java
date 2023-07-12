package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st1 = br.readLine();
        String st2 = br.readLine();
        int [][]dp = new int[st1.length()][st2.length()];

        //초기화
        dp[0][0] = (st1.charAt(0)==st2.charAt(0))?1:0;
        for(int i=1;i<st2.length();i++){
            dp[0][i] = (st1.charAt(0)==st2.charAt(i))?1:dp[0][i-1];
        }
        for(int i=1;i<st1.length();i++){
            dp[i][0] = (st2.charAt(0)==st1.charAt(i))?1:dp[i-1][0];
        }

        //DP 시작
        for(int i=1;i<st1.length();i++){
            char c1 = st1.charAt(i);
            for(int j=1;j<st2.length();j++){
                char c2 = st2.charAt(j);
                if(c1==c2)
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        System.out.println(dp[st1.length()-1][st2.length()-1]);
    }
}
