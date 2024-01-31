package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 숫자고르기_2668 {
    static int[] arr;
    static boolean[] visit;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        visit = new boolean[N+1];
        result = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=N; i++) {
            visit[i] = true;
            dfs(i ,i); // 완탐이라도 최악 N*N (100*100)
            visit[i] = false;
        }

        Collections.sort(result);
        System.out.println(result.size());
        for(int val : result) {
            System.out.println(val);
        }
    }

    static void dfs(int start, int target){
        if(!visit[arr[start]]) {
            visit[arr[start]] = true;
            dfs(arr[start], target);
            visit[arr[start]] = false;
        }

        if(arr[start] == target){ // 해당 번호가 사이클인 경우
            result.add(target);
        }
    }

}
