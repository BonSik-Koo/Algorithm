package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 다시 풀 문제
 */
public class 공통부분문자열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][]dp = new int[str1.length()+1][str2.length()+1];
        int result = 0;

        for(int i=1;i<=str1.length();i++){
            for(int j=1;j<=str2.length();j++){
                //증가할 때만 계산 - point!!
                //약간의 편법?
                //만약 현재 커서의 두 문자가 같은 경우에 i-1,j-1 좌표까지 문자가 연속해서 +1해주면 된다.
                //하지만 i-1,j-1 좌표까지 문자가 연속하지 않고 그냥 같은 경우가 있을 수 있다.!!!
                //하지만 이 때도 동일하게 해주는 이유는 어차피 정상적인 경우에 연속 개수의 값이 크기 때문이다.
                //또한, 나머지 값을 채우지 않아도 되는 이유는, 답을 구할 때 해당 좌표의 두 문자가 같은 경우에 i-1,j-1도 같기 때문에 이전 과정에서 값이 채워졌음!!
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        System.out.println(result);
    }
}
