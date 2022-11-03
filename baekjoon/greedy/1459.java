package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다시 풀기!!!!!!!!!!!!!!!!!!
 *
 * -BFS 를 쓸수 없는 이유
 *  - 2차원 정해진 크기가 없다. 메모리는 한정되어있으니
 *  - 처리 속도가 너무 느리다.
 *
 * - 3가지 경우에 대한 수학적 규칙 찾기(최소로 되는)
 * - "result" 자료형 생각하기
 */
public class G_1459 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        long X = Integer.parseInt(st.nextToken());
        long Y = Integer.parseInt(st.nextToken());
        long W = Integer.parseInt(st.nextToken());
        long S = Integer.parseInt(st.nextToken());

        //평행 이동
        long result1 = (W*(X+Y));

        //대각선만 이동
        long result2;
        if ((X + Y) % 2 == 0) { //짝수인 경우
            result2 = (Math.max(X,Y) * S);
        }else
            result2 = ((Math.max(X,Y)-1)*S+1*W); //홀수인 경우에는 부득히 하게 평행이동 하나를 사용해야됌

        //평행 + 대각선 이동
        long result3= (Math.min(X,Y)*S + Math.abs(X-Y)*W);

        long result  = Math.min(result1, Math.min(result2,result3));
        System.out.println(result);
    }
}
