package SWEA.세그먼트트리12차시.기본문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * - 세그먼트트리 문제
 * - 이론 : https://cano721.tistory.com/38
 *
 * - 문제 : https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AYJh54EqXOYDFAVG&categoryId=AYWab_JKjkwDFAQK&categoryType=BATTLE&battleMainPageIndex=1
 */
public class SegmentTree1 {

    static class Value{
        int min,max;
        public Value(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
    static class SegmentTree{
        Value [] tree; //각 원소가 담길 트리
        int treeSize; //트리의 그키

        public SegmentTree(int size) {
            //트리의 높이 구하기
            int h = (int)Math.ceil(Math.log(size)/Math.log(2));

            this.treeSize = (int)Math.pow(2,h+1);
            tree = new Value[treeSize];
        }

        //arr:원소배열, node:현재 노드 인덱스, start:현재 구간 배열 시작 인덱스, end:현재 구간 배열 끝 인덱스
        public Value init(int []arr, int node, int start, int end){
            if(start==end)
                return tree[node] = new Value(arr[start], arr[end]);

            Value v1 = init(arr, node * 2, start, (start + end) / 2);
            Value v2 = init(arr, node * 2 + 1, (start + end) / 2 + 1, end);

            return tree[node] = new Value(Math.min(v1.min,v2.min), Math.max(v1.max, v2.max));

        }

        //node:현재 노드 인덱스, start:현재 구간 배열 시작인덱스, end:현재 구간 배열 끝 인덱스
        //idx:변경된 데이터 인덱스, change:데이터 변경 값
        public Value update(int node, int start, int end, int idx, int change){
            if(idx<start || end<idx)
                return tree[node];

            Value v1 = null, v2 = null;
            if(start!=end) { //리프노드가 아닌경우
                v1 = update(node*2, start, (start+end)/2, idx, change);
                v2 = update(node*2+1, (start+end)/2+1, end, idx, change);
            }else if(start==end && start!=idx)
                return tree[node];
            else {
                tree[node] = new Value(change,change);
                return tree[node];
            }

            tree[node] = new Value(Math.min(v1.min, v2.min), Math.max(v1.max, v2.max));
            return tree[node];
        }

        //node:현재 노드 인덱스, start:현재 구간 배열 시작인덱스, end:현재 구간 배열 끝 인덱스
        //left:구하고자 하는 구간의 시작 인덱스, right:구하고자 하는 구간의 끝 인덱스
        public Value query(int node, int start, int end, int left, int right) {
            if(left>end || right<start)
                return new Value(Integer.MAX_VALUE, Integer.MIN_VALUE);

            //범위 내 완전히 포함 시에는 더 내려가지 않고 리턴
            if(left<=start && end<=right){
                return tree[node];
            }

            Value v1 = query(node * 2, start, (start + end) / 2, left, right);
            Value v2 = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

            return new Value(Math.min(v1.min, v2.min), Math.max(v1.max, v2.max));
        }
    }

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/세그먼트트리12차시/res/input1.txt"));

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken()); //배열의 길이
            int q = Integer.parseInt(st.nextToken()); //쿼리의 개수
            int[]arr = new int[n+1];

            st= new StringTokenizer(bf.readLine());
            for(int i=0;i<n;i++){
                arr[i+1] = Integer.parseInt(st.nextToken());
            }

            //세그먼트 트리 연산 시작
            SegmentTree segmentTree = new SegmentTree(n);
            segmentTree.init(arr, 1, 1, n); //1부터 인덱스 시작

            List<Integer> answer = new ArrayList<>();
            for(int i=0;i<q;i++){
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b= Integer.parseInt(st.nextToken());
                int c= Integer.parseInt(st.nextToken());

                if(a==0){ //값 변경
                    arr[b+1] = c; //입력값은 0 인덱스 부터 시작하니
                    segmentTree.update(1, 1, n,b+1,c);
                }else { //query 구하기
                    Value result = segmentTree.query(1, 1, n, b + 1, c);
                    answer.add(result.max - result.min);
                }
            }

            //출력
            System.out.print("#"+test_case);
            for(int v : answer) {
                System.out.print(" " +v);
            }
            System.out.println();
        }
    }

}
