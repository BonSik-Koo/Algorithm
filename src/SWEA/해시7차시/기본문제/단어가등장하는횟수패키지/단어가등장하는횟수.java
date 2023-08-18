package SWEA.해시7차시.기본문제.단어가등장하는횟수패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * - 문자열 검색 알고리즘:KMP
 * - 다시풀문제
 *
 * - 참고 자료 : https://hanyeop.tistory.com/355
 */
public class 단어가등장하는횟수 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/해시7차시/res/input2.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String B = bf.readLine(); //기본 문자열
            String S = bf.readLine(); //찾을 단어

            //KMP-prefix 배열 초기화
            int []prefix = new int[S.length()];
            int j=0; prefix[0]= 0;
            for(int i=1;i<S.length();i++){

                if(S.charAt(j) == S.charAt(i)){
                    j++;
                    prefix[i] = j;
                }else {
                    j=0;
                    if(S.charAt(j) == S.charAt(i)){
                        prefix[i] = 1;
                        j++;
                    }else {
                        prefix[i] = 0;
                    }
                }
            }

            //KMP 알고리즘 로직
            int result =0;
            int index =0;
            for(int i=0;i<B.length();i++){

                while (index>0 && B.charAt(i)!= S.charAt(index)){
                    index = prefix[index-1];
                }

                if(B.charAt(i)==S.charAt(index)){ //문자가 같은 경우

                    if(index == S.length()-1){ //일치하는 단어가 같은경우
                        index = prefix[index];
                        result++;
                    }else
                        index++;
                }
            }

            //출력
            System.out.println("#"+test_case+" "+result);
        }
    }

}
