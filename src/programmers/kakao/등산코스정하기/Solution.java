package programmers.kakao.등산코스정하기;

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] intensities = new int[n+1]; // 각 노드까지 오는데 최대 Intensity 보관
        List<List<Node>> path = new ArrayList<>(); // 각 노드간의 가중치 보관
        for(int i=0; i<=n; i++){
            path.add(new ArrayList<>());
            intensities[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<paths.length; i++){
            int node1 = paths[i][0];
            int node2 = paths[i][1];
            int time = paths[i][2];
            // 경로 저장(양방향)
            path.get(node1).add(new Node(node2, time));
            path.get(node2).add(new Node(node1, time));
        }

        int minIntensity = Integer.MAX_VALUE;
        int minMountainNum = Integer.MAX_VALUE;
        Queue<Move> queue = new LinkedList<>();
        // 모든 출입구에서 시작
        for(int i=0; i<gates.length; i++){
            queue.add(new Move(gates[i], Integer.MIN_VALUE));
            intensities[gates[i]] = 0; // 0으로 설정하면 출발지 검사는 따로 하지 않아도 됨.
        }

        //다익스트라 시작
        while(!queue.isEmpty()){
            boolean arrival = false;
            Move move = queue.poll();
            // 산 봉우리 검사
            for(int summit : summits){
                // 산봉우리에 도착한 경우
                if(summit == move.node){
                    //최소 intensity를 찾은 경우
                    if(minIntensity > move.maxIntensity){
                        minIntensity = move.maxIntensity;
                        minMountainNum = move.node;
                    }else if(minIntensity == move.maxIntensity){
                        minMountainNum = Math.min(minMountainNum, move.node);
                    }
                    arrival = true; // 산봉우리 도착했다는 플래그 값
                    break;
                }
            }
            if(arrival){
                continue;
            }

            // 해당 노드에서 갈 수 있는 노드 찾기
            for(int i=0; i<path.get(move.node).size(); i++){
                Node nextNode = path.get(move.node).get(i);

                // 해당 노드의 intensity 보다 작은 intensity를 찾은 경우 -> 가면 되는 경우
                if(intensities[nextNode.node] > Math.max(move.maxIntensity, nextNode.time)){
                    intensities[nextNode.node] = Math.max(move.maxIntensity, nextNode.time);

                    // 지금까지 지나오면서 최대 intensity를 가지고 있는다.
                    queue.add(new Move(nextNode.node, Math.max(move.maxIntensity, nextNode.time)));
                }
            }
        }


        int[] answer = {minMountainNum, minIntensity};
        return answer;
    }

    class Node{
        int node; //노드 번호
        int time; //소요 시간
        public Node(int node, int time){
            this.node = node;
            this.time = time;
        }
    }

    class Move{
        int node; //현재 노드 번호
        int maxIntensity; //지나온 노드 간 최소 소요 시간
        public Move(int node, int intensity){
            this.node = node;
            this.maxIntensity = intensity;
        }
    }

}