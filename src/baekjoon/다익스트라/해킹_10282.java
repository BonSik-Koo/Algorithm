package baekjoon.다익스트라;

import java.io.*;
import java.util.*;

public class 해킹_10282 {
    static List<List<Node>> edges;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            for(int j=0; j<=n; j++) {
                edges.add(new ArrayList<>());
            }

            for(int j=0; j<d; j++) {
                st = new StringTokenizer(br.readLine());
                int fromIdx = Integer.parseInt(st.nextToken());
                int toIdx = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges.get(toIdx).add(new Node(fromIdx, cost));
            }

            int cnt = 0;
            int maxTime = Integer.MIN_VALUE;
            int[] dis = dij(n, c);
            for(int j=0; j<=n; j++) {
                if(dis[j] != Integer.MAX_VALUE) {
                    cnt++;
                    maxTime = Math.max(maxTime, dis[j]);
                }
            }

            sb.append(cnt + " " + maxTime + "\n");
        }

        System.out.println(sb.toString());
    }

    static int[] dij(int n, int startIdx) {
        int[] dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startIdx, 0));
        dis[startIdx] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for(int i=0; i<edges.get(node.idx).size(); i++) {
                Node nextNode = edges.get(node.idx).get(i);

                if(dis[nextNode.idx] > node.cost + nextNode.cost) {
                    dis[nextNode.idx] = node.cost + nextNode.cost;
                    queue.add(new Node(nextNode.idx, node.cost + nextNode.cost));
                }
            }
        }

        return dis;
    }

    static class Node implements Comparable<Node>{
        private int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

}
