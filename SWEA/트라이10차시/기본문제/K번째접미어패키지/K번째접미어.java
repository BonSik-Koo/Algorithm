package SWExpertAcademyProblem.트라이10차시.기본문제.K번째접미어패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class K번째접미어 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/트라이10차시/res/input1.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int K = Integer.parseInt(bf.readLine());
            List<String> list = new ArrayList<>();
            String str = bf.readLine();
            for(int i=0;i<str.length();i++){
                list.add(str.substring(i));
            }
            Collections.sort(list);

            System.out.println("#"+test_case+" "+list.get(K-1));
        }
    }
}
