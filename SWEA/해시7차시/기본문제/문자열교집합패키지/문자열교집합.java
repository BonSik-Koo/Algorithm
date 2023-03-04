package SWExpertAcademyProblem.해시7차시.기본문제.문자열교집합패키지;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * - 해시 자료구조 사용법
 */
public class 문자열교집합 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/해시7차시/res/input1.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            Map<String,Boolean> map = new HashMap<>();

            int result = 0;
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(bf.readLine());
            for(int i=0;i<N;i++){
                map.put(st.nextToken(), true);
            }

            st = new StringTokenizer(bf.readLine());
            for(int i=0;i<M;i++){
                if(map.containsKey(st.nextToken()))
                    result++;
            }

            System.out.println("#"+test_case+" "+result);
        }
    }

}
