package programmers.L3;

import java.util.Arrays;

/**
 * 혼자 푼 문제
 * - DFS 이용
 */
public class 양과늑대 {

    static public int nodeNum;
    static public int edgeNum;
    static public Boolean []visitNode;
    static public Boolean []visitEdge;
    static public int answer;

    public static int solution(int[] info, int[][] edges) {

        nodeNum = info.length;
        edgeNum = edges.length;
        visitNode = new Boolean[nodeNum];
        visitEdge = new Boolean[edgeNum];
        Arrays.fill(visitNode,false); Arrays.fill(visitEdge, false);

        visitNode[0] =true;
        dfs(1,0,info, edges);

        return answer;
    }

    public static void dfs(int sheep, int wolf, int[]info, int[][]edges) {

        if(sheep <= wolf) //종료조건
            return;
        else
            answer = Math.max(answer, sheep);

        for(int i=0;i<edgeNum;i++) {
            int s = edges[i][0]; int e = edges[i][1];

            if(visitEdge[i]==false && visitNode[s]==true) { //유망한 경우
                visitEdge[i] = true;
                visitNode[e] = true;
                if(info[e]==0)
                    sheep+=1;
                else
                    wolf+=1;
                dfs(sheep,wolf,info,edges);
                visitEdge[i]=false;
                visitNode[e]= false;
                if(info[e]==0)
                    sheep-=1;
                else
                    wolf-=1;
            }
        }
    }

    public static void main(String[] args) {

        int[]info = {0,1,0,1,1,0,1,0,0,1,0};//{0,0,1,1,1,0,1,0,1,0,1,1};
        int[][]edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};//{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};

        solution(info, edges);
        System.out.println(answer);
    }

}
