package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다시 풀 문제
 * 사실 아직도 잘 모르겠다..문제가...
 */
public class 벽장문의이동_2666 {

    static int M;
    static int[]seq;
    static int result = Integer.MAX_VALUE;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        seq = new int[M];
        for(int i=0;i<M;i++){
            seq[i] = Integer.parseInt(br.readLine());
        }

        dfs(first, second, 0, 0);
        System.out.println(result);
    }

    //first: 첫번째 열린문
    //second: 두번째 열린문
    //count: 현재까지 이동 횟수
    //index: 현재 열 문
    public static void dfs(int first, int second, int count, int index){
        if(index==M){
            result = Math.min(result, count);
            return;
        }

        if(count >= result){
            return;
        }

        //왼쪽으로 이동
        dfs(seq[index], second, count + Math.abs(seq[index] - first), index + 1);
        //오른쪽으로 이동
        dfs(first, seq[index], count + Math.abs(seq[index] - second), index + 1);
    }
}
