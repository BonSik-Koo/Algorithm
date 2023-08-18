package SWExpertAcademyProblem.힙6차시.기본문제.수만들기패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * - BFS 문제
 *
 * - 아 도저히 모르겠다.....
 * - 수학적으로 나타내는 방법을
 */
public class 수만들기 {

//    public static class Info{
//        int k; int d; int count;
//        public Info(int x,int d, int count) {
//            this.k = x;
//            this.d = d;
//            this.count = count;
//        }
//    }
//    public static int[] arr;
//    public static Boolean[]check;
//    public static List<Integer> subset = new ArrayList<>();
//
//    public static void main(String args[]) throws Exception
//    {
//        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/힙6차시/res/input5.txt"));
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        int T;
//        T=Integer.parseInt(bf.readLine());
//
//        for(int test_case = 1; test_case <= T; test_case++)
//        {
//            //초기화
//            int N = Integer.parseInt(bf.readLine());
//            arr = new int[N];
//            check = new Boolean[N];
//            StringTokenizer st = new StringTokenizer(bf.readLine());
//            for(int i=0;i<N;i++){
//                arr[i] = Integer.parseInt(st.nextToken());
//            }
//            int K = Integer.parseInt(bf.readLine()); //만들값
//
//            //부분 집합 생성-dfs
//            subset.add(1); //이전 D의 값을 사용하기 위해서
//            for(int i=1;i<=N;i++){
//                makeSubset(0,0,i,1,N);
//            }
//
//            //탐색 시작
//            int result = bfs(K);
//            System.out.println("#"+test_case+" "+result);
//            subset.clear();
//        }
//    }
//
//    public static void makeSubset(int index, int c_n, int g_n, int value,int N) {
//        if(c_n == g_n) {
//            subset.add(value);
//            return;
//        }
//        for(int i=index;i<N;i++){
//            check[i] = true;
//            makeSubset(i+1, c_n+1, g_n, value*arr[i], N);
//            check[i] = false;
//        }
//    }
//
//    public static int bfs(int K){
//
//        int result =0;
//        PriorityQueue<Info> queue = new PriorityQueue<>(new Comparator<Info>() {
//            @Override
//            public int compare(Info o1, Info o2) {
//                return o1.k -o2.k;
//            }
//        }); //K를 만들수 있는 값만 들어가게됨! -> 만들수 있는값들만 있으니 x기준 내림차순!!
//        queue.add(new Info(K,1,0)); //초기값
//        while (!queue.isEmpty()){
//            Info poll = queue.poll();
//
////            if(poll.x == K){
////                result = poll.count;
////                break;
////            }
//            if (poll.k == 0) {
//                result = poll.count;
//                break;
//            }
//            for(int i=0;i<subset.size();i++){ //부분 집합 개수 만큼
//                //int nd = poll.d * subset.get(i); //새로 만든 D 값
////                if( (K-poll.x)%nd==0 ){ //K값을 만들수 있는 경우만!
////                    queue.add(new Info(poll.x+nd, nd,poll.count+1));
////                }
//
//                if( (poll.k)%(poll.d*subset.get(i))==0 ){
//                    queue.add(new Info(poll.k -poll.d*subset.get(i), poll.d*subset.get(i), poll.count+1));
//                }
//            }
//        }
//        return result;
//    }

    public static void main(String args[]) throws Exception{
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/힙6차시/res/input5.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; ++tc){
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N];
            String[] arr = br.readLine().split(" ");
            int K = Integer.parseInt(br.readLine());

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++){
                A[i] = Integer.parseInt(arr[i]);
                min = Math.min(A[i], min);
            }

            Queue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(K, 0));

            int cnt = K;

            while(!pq.isEmpty()){
                Node n = pq.poll();

                if (n.left == 0){
                    cnt = n.cnt;
                    break;
                }

                pq.add(new Node(0, n.cnt + n.left));

                for (int i = 0; i < N; i++){
                    pq.add(new Node( n.left / A[i], n.cnt + n.left % A[i] ));
                }
            }

            System.out.println("#" + tc + " " + cnt);
        }
    }

    static class Node implements Comparable<Node>{
        int left, cnt;

        public Node(int left, int cnt){
            this.left = left;
            this.cnt = cnt;
        }

        public int compareTo(Node n){
            if (this.cnt > n.cnt) return 1;
            if (this.cnt == n.cnt) return 0;

            return -1;
        }
    }
}
