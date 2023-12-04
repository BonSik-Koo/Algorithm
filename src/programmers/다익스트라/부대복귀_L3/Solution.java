package programmers.다익스트라.부대복귀_L3;

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> edge = new ArrayList<>();
        for(int i=0; i<=n; i++){
            edge.add(new ArrayList<>());
        }
        for(int i=0; i<roads.length; i++){
            // 양방향 연결
            edge.get(roads[i][0]).add(roads[i][1]);
            edge.get(roads[i][1]).add(roads[i][0]);
        }

        // 다익스트라 - 최소 시간으로 정렬
        boolean[] visit = new boolean[n+1];
        int[] times = new int[n+1];
        Arrays.fill(times, -1);
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.time-n2.time);
        queue.add(new Node(destination, 0));
        visit[destination] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            times[node.node] = node.time;

            for(int i=0; i<edge.get(node.node).size(); i++){
                int nextNode = edge.get(node.node).get(i);
                if(visit[nextNode]) continue;

                queue.add(new Node(nextNode, node.time+1));
                visit[nextNode] = true;
            }
        }

        int[] result = new int[sources.length];
        for(int i=0; i<sources.length; i++){
            result[i] = times[sources[i]];
        }
        return result;
    }

    static class Node {
        int node, time;
        public Node(int node, int time){
            this.node = node;
            this.time = time;
        }
    }

}