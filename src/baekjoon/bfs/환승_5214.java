package baekjoon.bfs;

import java.io.*;
import java.util.*;

// 웰-논 방식
// 연결된 정점을 한번에 주어질때
public class 환승_5214 {
    static List<List<Integer>> edge;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edge = new ArrayList<>();
        for(int i=0; i<=N+M; i++) {
            edge.add(new ArrayList<>());
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<K; j++) {
                int node = Integer.parseInt(st.nextToken());
                edge.get(node).add(N+i);
                edge.get(N+i).add(node);
            }
        }

        bfs(N, M);
        System.out.println(result);
    }

    static void bfs(int N, int M) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visit = new boolean[N+M+1];
        queue.add(new int[]{1, 1});
        visit[1] = true;

        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            if(node[0] == N) {
                result = node[1];
                break;
            }

            for(int nextNode : edge.get(node[0])) {
                if(visit[nextNode]) {
                    continue;
                }
                visit[nextNode] = true;
                queue.add(new int[]{nextNode, nextNode>N ? node[1] : node[1]+1});
            }
        }
    }

}
