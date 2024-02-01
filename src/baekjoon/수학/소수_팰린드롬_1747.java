package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 소수_팰린드롬_1747 {
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        isPrime = new boolean[1000001*2+1];
        Arrays.fill(isPrime, true);

        findPrime(1000001*2);
        int result = 2;
        for(int i=N; i<=1000001*2; i++) {
            // 소수 판별
            if(!isPrime[i]) {
                continue;
            }

            // 팰린드롬 판별
            int originalNum = i;
            int reverseNum = 0;
            while(true) {
                if(originalNum == 0) {
                    break;
                }
                reverseNum = reverseNum * 10 + originalNum % 10;
                originalNum = originalNum / 10;
            }
            if(i == reverseNum) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }

    // 소수 찾기 알고리즘 -> 에라토스테네스의 체
    static void findPrime(int N) {
        isPrime[0] = isPrime[1] = false;

        for(int i=2; i<=Math.sqrt(N); i++) {
            if(isPrime[i]) { // 해당 숫자가 소수인 경우, 해당 수를 제외한 배수들은 모두 소수가 아님
                for(int j=i*i; j<=N; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
