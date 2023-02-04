package SWExpertAcademyProblem.그래프4차시.응용문제.하나로패키지;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * - MST(최소 신장 트리) 문제 : 다익스트라 풀이법이랑 같음
 * - 약간 보고푼 문제
 * 
 * - 자료형 크기 신경쓰기
 * - Comparable 커스텀할때 비교 자료형 신경쓰기 하..
 */

public class 하나로 {

    public static class Node implements  Comparable<Node>{
        int index; long dis;
        public Node(int index, long dis) {
            this.index = index;
            this.dis = dis;
        }
        @Override
        public int compareTo(Node o) { //!!!!!!!!
            return Long.compare(this.dis, o.dis);
        }
    }
    public static void main(String args[]) throws Exception
    {
        System.setIn(new FileInputStream("src/SWExpertAcademyProblem/그래프4차시/res/input4.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            //초기화
            int N = Integer.parseInt(bf.readLine());
            long [][] weight = new long[N][N];
            Boolean[] visit = new Boolean[N];
            int []ix = new int[N];
            int []iy = new int[N];
            long []dis = new long[N];
            double fee =0;
            double result =0;
            Arrays.fill(visit, false);
            Arrays.fill(dis, Long.MAX_VALUE); //!!!!!!!!!!

            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int i=0;i<N;i++)
                ix[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            for(int i=0;i<N;i++)
                iy[i] = Integer.parseInt(st.nextToken());
            fee = Double.parseDouble(bf.readLine());

            //가충치 초기화
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    weight[i][j] = (long) (Math.pow(ix[i]-ix[j],2) + Math.pow(iy[i]-iy[j],2));
                }
            }

            //MST-Prim 알고리즘
            PriorityQueue<Node> queue = new PriorityQueue<>();
            int count = 0;
            double sum =0;
            queue.add(new Node(0,0));
            while (!queue.isEmpty()) {
                Node poll = queue.poll();

                if(visit[poll.index])
                    continue;
                sum += poll.dis;
                count++;
                visit[poll.index] = true; //방문 처리
                if(count == N) {
                    result = fee * sum;
                    break;
                }
                for(int i=0;i<N;i++){
                    if(visit[i])
                        continue;
                    if(weight[poll.index][i] < dis[i]){
                        dis[i] = weight[poll.index][i];
                        queue.add(new Node(i,dis[i]));
                    }
                }
            }

            //출력
            System.out.println("#"+test_case+" "+Math.round(result));
        }

    }

}
