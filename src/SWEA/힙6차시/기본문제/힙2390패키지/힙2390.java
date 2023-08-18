package SWEA.힙6차시.기본문제.힙2390패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * - 힙 문제
 * - 우선순위 큐가 이미 힙을 구현하고 있어서 우선순위큐 사용
 */
public class 힙2390 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/힙6차시/res/input2.txt"));

        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
            int N = Integer.parseInt(bf.readLine());
            List<Integer> result = new ArrayList<>();
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int op = Integer.parseInt(st.nextToken());
                if(op == 1) { //추가 연산
                    queue.add(Integer.parseInt(st.nextToken()));
                }else { //삭제 연산
                    if(queue.isEmpty())
                        result.add(-1);
                    else
                        result.add(queue.poll());
                }
            }
            //출력
            System.out.print("#"+test_case);
            for(int i=0;i<result.size();i++){
                System.out.print(" "+ result.get(i));
            }
            System.out.println();
        }
    }
}
