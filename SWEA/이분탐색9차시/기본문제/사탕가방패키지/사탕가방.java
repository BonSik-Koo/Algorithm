package SWExpertAcademyProblem.이분탐색9차시.기본문제.사탕가방패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - 이분 탐색 문제
 *
 * - M<=(A1/가방수 + A2/가방수 ...) => 어떤하 방식이든지 캔디를 조합해서 M개가 담기게 만들수 있다.!
 * - M>(A1/가방수 + A2/가방수 ...) => M개가 담기게 만들수 없다.!
 */
public class 사탕가방 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/이분탐색9차시/res/input3.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            long min =1, max =0;
            //초기화
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken()); long M = Long.parseLong(st.nextToken());
            Long[] arr = new Long[N];
            st = new StringTokenizer(bf.readLine());
            for(int i=0;i<N;i++) {
                arr[i] = Long.parseLong(st.nextToken());
                max = Math.max(max, arr[i]);
            }

            //이분탐색 로직
            long result =0;
            while (min<=max){
                long mid = min + (max-min)/2;
                long sum =0;
                for(int i=0;i<N;i++)
                    sum += arr[i]/mid; //핵심 로직!!

                if(sum>=M) {//가방의 수를 늘려야 되는 경우
                    result = mid;
                    min = mid + 1;
                }
                else //가방의 수를 줄여야 되는 경우
                    max = mid-1;
            }

            //출력
            System.out.println("#"+test_case+" "+result);
        }
    }

}
