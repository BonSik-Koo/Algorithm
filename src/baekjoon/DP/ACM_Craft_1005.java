package baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 위상 정렬 + dp
 */
public class ACM_Craft_1005 {
    static int[] arr;
    static int[] dp;
    static int[] degree;
    static List<List<Integer>> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            arr = new int[N+1];
            dp = new int[N+1];
            degree = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                arr[j] = Integer.parseInt(st.nextToken());
                degree[i] = 0;
            }

            edges = new ArrayList<>();
            for(int q=0;q<=N;q++){
                edges.add(new ArrayList<>());
            }
            for(int k=0;k<K;k++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edges.get(from).add(to);
                degree[to]++; //진입 차수 증가!
            }
            int W = Integer.parseInt(br.readLine());

            System.out.println(find(N, W));
        }
    }
    private static int find(int N, int W){
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N;i++){
            if(degree[i] == 0){ //집입 차수가 0인 경우(초기)
                dp[i] = arr[i];
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=0;i<edges.get(cur).size();i++){
                int next = edges.get(cur).get(i);
                dp[next] = Math.max(dp[next], arr[next] + dp[cur]);
                degree[next]--; //진입 차수 감소

                if(degree[next] == 0){
                    queue.add(next);
                }
            }
        }

        return dp[W];
    }


}
