package SWExpertAcademyProblem.이분탐색9차시.기본문제.촛불이벤트패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * - 이분탐색 문제
 */
public class 촛불이벤트 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/이분탐색9차시/res/input2.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            long N = Long.parseLong(bf.readLine()); //입력에 적당한 자료형 사용

            long left =0;
            long right = Integer.MAX_VALUE;
            long result = -1;
            while (left<=right) {
                long mid = left + (right-left)/2;

                long t = ((mid+1)*mid)/2;
                if(t==N){
                    result = mid;
                    break;
                }else if(t>N){ //줄여야되는 경우
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }
            System.out.println("#"+test_case+ " "+ result);
        }
    }

}
