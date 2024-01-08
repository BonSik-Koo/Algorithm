package programmers.kakao.도넛와막대그래프;

import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[][] edgeCount = new int[1000001][2]; //[0]: 나가는 노드, [1]: 들어오는 노드
        boolean[] exist = new boolean[1000001];
        List<List<Integer>> edgesForFindCreateNode = new ArrayList<>();
        int[] answer = new int[4];

        for(int i=0; i<1000001; i++){
            edgesForFindCreateNode.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            int formNode = edge[0];
            int toNode = edge[1];

            exist[formNode] = true;
            exist[toNode] = true;
            edgeCount[formNode][0]+=1;
            edgeCount[toNode][1]+=1;
            edgesForFindCreateNode.get(formNode).add(toNode);
        }

        int createNodeEdge = findCreateNodeEdge(edgeCount, edgesForFindCreateNode, answer);
        findGraphNum(createNodeEdge, edgeCount, exist, answer);

        return answer;
    }

    public int findCreateNodeEdge(int[][] edgeCount, List<List<Integer>> edges, int[] answer){
        // 생성 노드: in x and out >= 2
        int createNode = 0;
        for(int i=1; i<edgeCount.length; i++){
            if(edgeCount[i][0]>=2 && edgeCount[i][1]==0){
                createNode = i;
                break;
            }
        }

        // 생성 노드와 연결된 간선 제거
        for(int i=0; i<edges.get(createNode).size(); i++){
            int node = edges.get(createNode).get(i);
            edgeCount[node][1]--;
        }

        answer[0] = createNode;
        return edgeCount[createNode][0];
    }

    public void findGraphNum(int createNodeEdge, int[][] edgeCount, boolean[] exist, int[] answer){
        int stickGraphCount = 0;
        int graph8Count = 0;

        for(int i=1; i<=1000000; i++){
            // 막대 그래프 개수
            if((exist[i] && edgeCount[i][0]==0 && edgeCount[i][1]==0) || (edgeCount[i][0]==0 && edgeCount[i][1]==1)){
                stickGraphCount ++;
            }

            // 8자 그래프 개수
            if(edgeCount[i][0]>=2 && edgeCount[i][1]>=2){
                graph8Count++;
            }
        }


        answer[2] = stickGraphCount;
        answer[3] = graph8Count;
        answer[1] = createNodeEdge - (stickGraphCount + graph8Count);
    }

}