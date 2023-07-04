package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 암호코드 {

    public static void main(String[]args) throws IOException {
        final int mod = 1000000;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String en_str = br.readLine();

        int[]dp = new int[en_str.length()+1];
        dp[0]=1;
        dp[1]=1;

        //조기 답없음 멈춤 조건(처음이 0인 경우)
        int earlyStopValue = Character.getNumericValue(en_str.charAt(0));
        if(earlyStopValue==0){
            System.out.println(0);
            return;
        }

        for(int i=2;i<=en_str.length();i++){
            int curNum = Character.getNumericValue(en_str.charAt(i-1));
            int preAndCurNum = Integer.parseInt(en_str.substring(i-2, i)); //그전 번호와 현재번호를 이은

            if(curNum != 0){
                if(10<=preAndCurNum && preAndCurNum<= 26){
                    dp[i] = (dp[i-1] + dp[i-2])%mod;
                }else {
                    dp[i] = dp[i-1];
                }
            }else{
                if(10<=preAndCurNum && preAndCurNum<= 26){
                    dp[i] = dp[i-2];
                }else { //답이 없는 경우
                    System.out.println(0);
                    return;
                }
            }
        }

        System.out.println(dp[en_str.length()]);
    }
}
