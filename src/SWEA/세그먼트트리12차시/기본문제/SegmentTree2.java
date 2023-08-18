package SWEA.세그먼트트리12차시.기본문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * - 세그먼트트리 문제
 * - 문제: https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AYJh9F4aXmEDFAVG&categoryId=AYWab_JKjkwDFAQK&categoryType=BATTLE&battleMainPageIndex=1
 */
public class SegmentTree2 {

    static class SegmentTree{
        long [] tree; //각 원소가 담길 트리
        int treeSize; //트리의 크기


        public SegmentTree(int size) {
            //트리의 높이 구하기
            int h1 = (int)Math.ceil(Math.log(size)/Math.log(2));

            this.treeSize = (int)Math.pow(2,h1+1);
            tree = new long[treeSize];
        }

        //arr:원소배열, node:현재 노드 인덱스, start:현재 구간 배열 시작 인덱스, end:현재 구간 배열 끝 인덱스
        public long init(int []arr, int node, int start, int end){
            //리프 노드
            if(start==end)
                return tree[node] = arr[start];

            return tree[node] = init(arr,node*2,start, (start+end)/2) +
                    init(arr, node*2+1, (start+end)/2+1, end);
        }

        public void update(int node, int start, int end, int idx, int change){
            if(idx<start || end<idx)
                return;

            tree[node] += change;
            if(start!=end){
                update(node*2, start, (start+end)/2, idx, change);
                update(node*2+1, (start+end)/2+1, end, idx, change);
            }
        }

        public long query(int node, int start, int end, int left, int right) {
            if(left>end || right<start)
                return 0;

            //범위 내 완전히 포함 시에는 더 내려가지 않고 리턴
            if(left<=start && end<=right){
                return tree[node];
            }

            return query(node*2, start, (start+end)/2, left, right) +
                    query(node*2+1, (start+end)/2+1, end, left, right);
        }

    }

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/세그먼트트리12차시/res/input2.txt"));

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            int[] arr = new int[n+1];
            st = new StringTokenizer(bf.readLine());
            for(int i=1;i<=n;i++) {
                if(i%2==0)
                    arr[i] = -Integer.parseInt(st.nextToken()); //짝수번째를 마이너스로 만든다.
                else
                    arr[i] = Integer.parseInt(st.nextToken());
            }

            SegmentTree segmentTree = new SegmentTree(n);
            segmentTree.init(arr, 1, 1, n);

            List<Long> result = new ArrayList<>();
            for(int i=0;i<q;i++){
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b= Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if(a==0){ //값 변경
                    //바꾸려는 값의 인덱스가 짝수 인덱스->내가 임의로 짝수번째는 마이너스로 만들었으니 구분 해야함
                    if((b+1)%2==0){
                        int diff = -c - arr[b+1];
                        arr[b+1] = -c;
                        segmentTree.update(1,1,n,b+1,diff);

                    }else {//홀수 인덱스
                        int diff = c - arr[b+1];
                        arr[b+1] = c;
                        segmentTree.update(1, 1, n ,b+1, diff);
                    }

                }else { //query 구하기
                    long sum = segmentTree.query(1, 1, n, b + 1, c);

                    //시작이 짝수 인덱스이면 임의로 짝수번째를 마이너스로 만들었으니 다시 마이너스 곱해주기
                    if((b+1)%2==0)
                        sum *=-1;
                    result.add(sum);
                }
            }

            //출력
            System.out.print("#"+test_case);
            for(long v : result){
                System.out.print(" "+v);
            }
            System.out.println();

        }
    }
}
