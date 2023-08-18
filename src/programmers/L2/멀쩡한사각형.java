package programmers.L2;

import java.math.*;

public class 멀쩡한사각형 {

    public long solution(int w, int h) {
        long answer = 1;

        //gcd 구하기
        int gcd = BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).intValue();

        //수식을 통한 계산
        answer = ((long)w * (long)h) - (((long)w/gcd + (long)h/gcd +1) * gcd);
        return answer;

    }
}
