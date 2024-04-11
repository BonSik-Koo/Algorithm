package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 홀수홀릭호석_20164 {
    static int oldSum = 0;
    static int minResult = Integer.MAX_VALUE;
    static int maxResult = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        process(N, getOldCnt(N));
        System.out.println(minResult + " " + maxResult);
    }

    private static void process(int value, int total) {
        // 한자리 일 경우
        if(value < 10) {
            minResult = Math.min(minResult, total);
            maxResult = Math.max(maxResult, total);
        }

        // 두자리일 경우
        else if(value < 100) {
            int nValue = (value % 10) + (value / 10);
            process(nValue, total + getOldCnt(nValue));
        }

        // 세자리 일 경우
        else {
            String str = Integer.toString(value);
            int len = str.length();
            for(int i=0; i<=len-3; i++) {
                for(int j=i+1; j<=len-2; j++) {
                    int value1 = Integer.parseInt(str.substring(0, i+1));
                    int value2 = Integer.parseInt(str.substring(i+1, j+1));
                    int value3 = Integer.parseInt(str.substring(j+1, len));
                    int sum = value1 + value2 + value3;
                    process(sum, total + getOldCnt(sum));
                }
            }
        }
    }

    private static int getOldCnt(int value) {
        int cnt = 0;
        while(value > 0) {
            int temp = value % 10;
            if(temp % 2 != 0) {
                cnt++;
            }
            value /= 10;
        }
        return cnt;
    }

}
