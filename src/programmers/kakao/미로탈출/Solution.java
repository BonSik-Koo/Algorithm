package programmers.kakao.미로탈출;


import java.util.*;
class Solution {
    static int[][] graph;

    public int dij(int n, int start, int end, int[] traps){
        //int[] : cost, node, state
        // 다익스트라 : 우선순위 큐 + 방문처리
        PriorityQueue<int[]> queue = new PriorityQueue<>((n1,n2) -> n1[0]-n2[0]);
        // trap 의 번호를 (압축) 비트로 표현
        // trap : 2번 -> 10, 15번 -> 100, 20번 -> 1000 = 압축해서 표현
        boolean[][] visit = new boolean[n+1][1 << traps.length];
        queue.add(new int[]{0, start, 0});

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int cost = curr[0];
            int node = curr[1];
            int state = curr[2];

            if(node == end) {
                return cost;
            }
            if(visit[node][state]) {
                continue;
            }
            visit[node][state] = true;

            // state 최신화
            boolean curTrapOpen = false;
            Set<Integer> openTraps = new HashSet<>(); // 활성화 된 trap 모음
            for(int i=0; i<traps.length; i++){
                int bit = 1 << i;
                // 현재 state 중 활성화 된 trap를 발견
                if((state & bit) != 0){
                    if(node == traps[i]){ // 활성화 되어있는 trap이 현재 노드이 경우 -> 비활성화
                        state &= ~bit;
                    } else{ // 활성화 되어있는 trap이 현재 노드가 아닌 경우
                        openTraps.add(traps[i]);
                    }
                }
                else{
                    // 현재 노드가 trap 인데, 활성화 되어 있지 않은 경우 -> 활성화
                    if(node == traps[i]){
                        state |= bit;
                        curTrapOpen = true; //현재 노드가 trap 이고, 활성화 표시
                    }
                }
            }

            for(int i=1; i<=n; i++){
                if(node == i) continue; //자기 자신은 탐색 제외

                // 정/역방향 간선을 선택!!
                // 둘다 trap이 켜져 있거나, 둘다 일반 노드이면 ->  정방향
                // 한쪽 trap이 켜져 있고, 한쪽이 일반 노드이면 -> 역방향
                // 이동 가능한 다음 노드의 trap 이 활성화 트랩인지 확인
                boolean nextTrapOpen = openTraps.contains(i);
                if(curTrapOpen == nextTrapOpen){ //정방향
                    if(graph[node][i] != Integer.MAX_VALUE){
                        queue.add(new int[]{cost+graph[node][i], i, state});
                    }
                }else{ //역방향
                    if(graph[i][node] != Integer.MAX_VALUE){
                        queue.add(new int[]{cost+graph[i][node], i, state});
                    }
                }
            }
        }
        return Integer.MAX_VALUE; // 어차피 답은 항상 주어짐.
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        graph = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }

        for(int[] road : roads){
            int P = road[0]; int Q = road[1]; int S = road[2];
            // 두 지점에 연결된 간선이 여러개 가 존재할 수 있음 -> 최소 값만 보관
            graph[P][Q] = Math.min(graph[P][Q], S);
        }

        return dij(n, start, end, traps);
    }

}