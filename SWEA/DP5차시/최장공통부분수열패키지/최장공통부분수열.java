package SWExpertAcademyProblem.DP5차시.최장공통부분수열패키지;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - DP 문제
 * - 최장 공통 부분수열(Longest Common Subsequence) 길이 구하기 문제
 */
public class 최장공통부분수열 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/SWExpertAcademyProblem/DP5차시/res/input1.txt"));

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(bf.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            int[][] len = new int[s1.length() + 1][s2.length() + 1];

            //초기화
            for(int i=0;i<=s1.length();i++)
                len[i][0] = 0;
            for(int i=0;i<=s2.length();i++)
                len[0][i] = 0;

            //최장 공통 부분 수열 길이 DP 알고리즘
            for (int j = 1; j <= s1.length(); j++) {
                char c1 = s1.charAt(j-1);
                for (int i = 1; i <= s2.length(); i++) {
                    char c2 = s2.charAt(i-1);

                    if(c1!=c2){
                        len[j][i] = Math.max(len[j][i-1], len[j-1][i]);
                    }else{
                        len[j][i] = len[j-1][i-1]+1;
                    }
                }
            }
            System.out.println("#"+test_case+" "+len[s1.length()][s2.length()]);
        }
    }
}
