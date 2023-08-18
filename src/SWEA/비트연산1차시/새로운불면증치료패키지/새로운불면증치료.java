package SWEA.비트연산1차시.새로운불면증치료패키지;

import java.io.FileInputStream;
import java.util.Scanner;

public class 새로운불면증치료 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new FileInputStream("SWExpertAcademyProblem/비트연산1차시/res/input1.txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        int n=0; //마지막에 체크할때 사용될 값
        for(int k=0;k<10;k++)
            n = n | (1 << k);

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String r_N = sc.next();
            String N = null;
            int check =0; //0~9자리 비트로 표현
            int i=1;
            while(true) {
                N = Integer.toString(Integer.parseInt(r_N)*i);
                for(int j=0;j<N.length();j++){
                    int shift = Character.getNumericValue(N.charAt(j));
                    check = check | (1 << shift );
                }
                if((n & check) == n)
                    break;
                i++;
            }
            System.out.println("#"+test_case+ " " + N);
        }
    }

}
