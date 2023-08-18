package SWExpertAcademyProblem.분할정복8차시.기본문제.염라대왕의이름정렬패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * 분할정복문제(정렬알고리즘 사용문제)
 *
 * - HashSet 으로 중복 제거!!!
 */
public class 염라대왕의이름정렬 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/분할정복8차시/res/input1.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            //초기화
            int N = Integer.parseInt(bf.readLine());
            HashSet<String> set = new HashSet<>();
            for(int i=0;i<N;i++){
                set.add(bf.readLine()); //중복 제거!!!!!!!!!!!!!!!!
            }

            //정렬
            List<String> list = new ArrayList<>(set);
            list.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if(o1.length()==o2.length())
                        return o1.compareTo(o2);
                    else
                        return o1.length()- o2.length();
                }
            });

            System.out.println("#"+test_case);
            for(String st : list)
                System.out.println(st);
        }
    }
}
