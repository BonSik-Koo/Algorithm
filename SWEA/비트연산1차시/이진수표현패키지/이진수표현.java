package SWExpertAcademyProblem.비트연산1차시.이진수표현패키지;

import java.io.FileInputStream;
import java.util.Scanner;

public class 이진수표현 {

    public static void main(String args[]) throws Exception
    {
        //System.setIn(new FileInputStream("src/SWExpertAcademyProblem/res/input2.txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            boolean check = true;
            int N = sc.nextInt();  int M = sc.nextInt();
            for(int i=0;i<N;i++){
                if( ((M & (1 << i)) >> i) != 1){
                    check = false;
                    break;
                }
            }
            if(check == true)
                System.out.println("#"+test_case + " ON" );
            else
                System.out.println("#"+test_case + " OFF" );
        }
    }
}
