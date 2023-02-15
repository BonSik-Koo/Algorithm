package SWExpertAcademyProblem.트라이10차시.기본문제.K번째문자열패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class K번째문자열 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/트라이10차시/res/input2.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
             int K = Integer.parseInt(bf.readLine());
             String str = bf.readLine();

             HashSet<String> set = new HashSet<>();
             List<String> list = new ArrayList<>();
             String temp = "";
             for(int i=0;i<str.length();i++){
                 for(int j=i+1;j<=str.length();j++){
                     temp = str.substring(i,j);
                     if(!set.contains(temp)) {
                         list.add(temp);
                         set.add(temp);
                     }
                 }
             }

             //정렬
             Collections.sort(list, new Comparator<String>() {
                 @Override
                 public int compare(String o1, String o2) {

                     int index1=0,index2=0;
                     while (index1<o1.length() && index2<o2.length()){
                        if(o1.charAt(index1) == o2.charAt(index2)){
                            index1++; index2++;
                        }else
                            break;
                     }

                     if(index1 == o1.length()) { //o1 의 문자열이 더 짧은 경우
                         return -1;
                     }else if (index2 == o2.length()) { //o2 의 문자열이 더 짧은 경우
                         return 1;
                     }else { //문자가 다른 경우
                         return o1.charAt(index1)-o2.charAt(index2);
                     }
                 }
             });

            System.out.println("#"+test_case+" "+list.get(K-1));
        }
    }
}
