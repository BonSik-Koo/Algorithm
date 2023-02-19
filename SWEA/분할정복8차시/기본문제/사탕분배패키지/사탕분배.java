package SWExpertAcademyProblem.분할정복8차시.기본문제.사탕분배패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * - 분할정복 문제
 */
public class 사탕분배 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/분할정복8차시/res/input2.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int result =0;
            Map<Integer, int[]> list = new HashMap<>();
            HashSet<Integer> valueCheck = new HashSet<>();

            StringTokenizer st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            //로직 수행
            Boolean in = false;
            for(int i=1;i<=K;i++){
                int p1 = Math.min(A,B); int p2 =Math.max(A,B);
                A = p1*2; B = p2-p1;

                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
                if(valueCheck.contains(A)){ //순환이 되는경우를 발견
                    int index = K%(i-1);
                    if(index==0){
                        result = Math.min(list.get(i-1)[0], list.get(i-1)[1]);
                    }else {
                        result = Math.min(list.get(index)[0], list.get(index)[1]);
                    }
                    in = true;
                    break;
                }
                list.put(i, new int[]{A,B});
                valueCheck.add(A);
            }
            if(!in)
                result = Math.min(A,B);

            System.out.println("#"+test_case+ " "+result);
        }
    }


}
