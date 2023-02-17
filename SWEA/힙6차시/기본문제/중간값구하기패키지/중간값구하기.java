package SWExpertAcademyProblem.힙6차시.기본문제.중간값구하기패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * - 최대힙,최소힙 사용문제
 * - 우선순위큐(최대,최소) 사용하면된다.
 * - 다시풀문제!!!!
 *
 * - 풀이
 * 1. 개수가 같으면 최대큐에 개수가 다르면 최소큐에 넣는다 -> 왜냐하면 최대큐에는 중앙값이 속해있기 때문에 항상 최대큐의 사이즈가 +1 더크다.
 * 2. 큐에 넣고난뒤 최대큐의 peek > 최소큐의 peek 이면 스왑
 *    (최대큐에는 "기준값>=" 의 값이 속하도록, 최소큐에는 "기준값<" 의 값이 속하도록 하기위해서)
 * 3. 최대큐의 pop < 최소큐의 pop 이면 그만두고 최대큐의 peek 이 중간값임
 */

public class 중간값구하기 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/힙6차시/res/input4.txt"));
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            long result =0;
            PriorityQueue<Integer> maxQ = new PriorityQueue<>(new Comparator<Integer>() { //오름차순
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            PriorityQueue<Integer> minQ = new PriorityQueue<>(); //내림차순

            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken()); int A = Integer.parseInt(st.nextToken());
            maxQ.add(A); //초기

            for(int i=0;i<N;i++){
                st = new StringTokenizer(bf.readLine());
                for(int j=0;j<2;j++){
                    int n = Integer.parseInt(st.nextToken());
                    if(minQ.size() != maxQ.size())  //사이즈가 다를때
                        minQ.add(n);
                    else
                        maxQ.add(n);
                }
                //실제 로직
                while (true) {
                    Integer maxQN = maxQ.peek();
                    Integer minQN = minQ.peek();
                    if(maxQN > minQN){ //서로 바꾸기
                        maxQ.poll(); minQ.poll();
                        maxQ.add(minQN); minQ.add(maxQN);
                    }else
                        break;
                }
                result = (result+maxQ.peek())%20171109;
            }
            System.out.println("#"+test_case+" "+result);
        }
    }
}
