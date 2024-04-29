package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리맨더링_17471 {
    static int N;
    static int[] peoples;
    static List<List<Integer>> edges;
    static boolean[] group;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        peoples = new int[N+1];
        edges = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            edges.add(new ArrayList<>());
        }
        group = new boolean[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            peoples[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=0; j<cnt; j++) {
                int node = Integer.parseInt(st.nextToken());
                edges.get(i).add(node);
                edges.get(node).add(i);
            }
        }

        boolean[] group = new boolean[N+1];
        subnet(1, group);

        if(result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    // 두 선거구로 나누기
    static void subnet(int cnt, boolean[] group) {
        // 두 선거구를 다 나눈 경우
        if(cnt == N+1) {
            boolean[] visit = new boolean[N+1];
            int groupCnt = 0;

            for(int i=1; i<=N; i++) {
                if(!visit[i]) {
                    bfs(i, group, visit);
                    groupCnt++;
                }
            }

            // 나누어진 두 선거구가 모두 정상적인 경우 (연결된 경우)
            if(groupCnt == 2) {
                int sum1 = 0, sum2 = 0;
                for(int i=1; i<=N; i++) {
                    if(group[i]) {
                        sum1 += peoples[i];
                    } else {
                        sum2 += peoples[i];
                    }
                }
                int diff = Math.abs(sum1 - sum2);
                result = Math.min(result, diff);
            }

            return;
        }

        group[cnt] = true;
        subnet(cnt + 1, group);

        group[cnt] = false;
        subnet(cnt + 1, group);
    }

    // 같은 선거구를 방문처리
    static void bfs(int idx, boolean[] group, boolean[] visit) {
        Queue<Integer> queue = new LinkedList<>();
        visit[idx] = true;
        queue.add(idx);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int i=0; i<edges.get(cur).size(); i++) {
                int next = edges.get(cur).get(i);
                if(group[cur] == group[next] && !visit[next]) {
                    queue.add(next);
                    visit[next] = true;
                }
            }
        }

    }

}
