package baekjoon.dfs_dp;

import java.util.*;
import java.io.*;
public class 사회망서비스_2533 {
    static List<List<Integer>> edges;
    //[0]: 얼리 어댑터일 경우 필요한 어댑터 개수, [1]: 얼리 어댑터가 아닐 경우 필요한 어댑터 개수
    static int[][] dp;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        edges = new ArrayList<>();
        for(int i=0; i<=N; i++){
            edges.add(new ArrayList<>());
        }
        dp = new int[N+1][2];
        visit = new boolean[N+1];

        for(int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edges.get(from).add(to);
            edges.get(to).add(from);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int node){
        if(isLastNode(node)) {
            dp[node][0] = 1;
            dp[node][1] = 0;
            return;
        }

        visit[node] = true;

        int myEarlyAdapter = 0;
        int noEarlyAdapter = 0;
        for(int nextNode : edges.get(node)) {
            if(visit[nextNode]) {
                continue;
            }

            dfs(nextNode);
            noEarlyAdapter += dp[nextNode][0];
            myEarlyAdapter += Math.min(dp[nextNode][0], dp[nextNode][1]);
        }

        dp[node][0] = myEarlyAdapter + 1; // 자기 자신 더하기
        dp[node][1] = noEarlyAdapter;
    }

    static boolean isLastNode(int node) {
        for(int nextNode : edges.get(node)) {
            if(!visit[nextNode]) {
                return false;
            }
        }
        return true;
    }


}
