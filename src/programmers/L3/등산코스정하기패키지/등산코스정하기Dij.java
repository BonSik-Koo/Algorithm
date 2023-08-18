package programmers.L3.등산코스정하기패키지;

import java.util.*;
import java.util.stream.Collectors;

/**
 * - 다익스트라 풀이
 * - 다시풀문제!!
 */
public class 등산코스정하기Dij {

    public static class Node {
        int e; int w;
        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    public static List<List<Node>> graph; //간선을 담은 리스트
    public static int[]intensity; //e노드까지 intensity 값을 담는 배열

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        graph = new ArrayList<>();
        for(int i=0;i<n+1;i++) {
            graph.add(new ArrayList<>());
        }
        intensity = new int[n+1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        List<Integer>LGates = Arrays.stream(gates).boxed().collect(Collectors.toList());
        List<Integer> LSummits = Arrays.stream(summits).boxed().collect(Collectors.toList());

        for(int i=0;i< paths.length;i++){
            int s = paths[i][0]; int e = paths[i][1]; int w=paths[i][2];
            /**
             * 출발지일경우에는 단방향으로 설정
             * 산봉우리일경우에도 단방향으로 설정
             * 그외는 양방향 설정
             */
            if(LGates.contains(s) || LSummits.contains(e)) {
                graph.get(s).add(new Node(e,w));
            }else if(LGates.contains(e) || LSummits.contains(s)){
                graph.get(e).add(new Node(s,w));
            }else {
                graph.get(s).add(new Node(e,w));
                graph.get(e).add(new Node(s,w));
            }
        }
        dijkstra(gates);

        //정답 고르기
        int[] answer = new int[2];
        answer[1] = Integer.MAX_VALUE;
        for(int i=0;i<summits.length;i++) {
            if( answer[1] > intensity[summits[i]]) {
                answer[0] = summits[i];
                answer[1] = intensity[summits[i]];
            }else if(answer[1] == intensity[summits[i]]){
                answer[0] = Math.min(answer[0], summits[i]);
            }
        }
        return answer;
    }

    public void dijkstra(int[] gates) {

        Queue<Node> queue = new LinkedList<>();
        //출발지 노드들을 한번에 넣는다.
        for(int i=0;i< gates.length;i++) {
            queue.add(new Node(gates[i],0));
            intensity[gates[i]] = 0; //시작점은 0으로 초기화
        }

        while (!queue.isEmpty()) {
            Node pNode = queue.poll();
            /**
             * 조기 가지치기를 위해서
             * 여러개의 출발지를 같이 넣고 다익스트라를 실행시키기 때문에 동시에 특정 노드로 가는 intensity 값을 서로 변경시키기때문에
             */
            if(pNode.w > intensity[pNode.e])
                continue;

            //해당 노드에서 갈수 있는 노드들만큼 반복
            for(int i=0;i<graph.get(pNode.e).size();i++){
                int startN = pNode.e;
                int endN = graph.get(pNode.e).get(i).e;
                int w = graph.get(pNode.e).get(i).w;  //end node까지 가중치 값

                if(intensity[endN] > Math.max(pNode.w,w)){ //새로운 최소 intensity 값이 나온경우
                    intensity[endN] = Math.max(pNode.w,w);
                    queue.add(new Node(endN,intensity[endN]));
                }
            }
        }
    }

}
