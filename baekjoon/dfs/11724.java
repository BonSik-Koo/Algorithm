package baekjoon.dfs;

import java.util.ArrayList;
import java.util.Scanner;

public class Dfs_11724 {

    public static void dfs(int n, boolean[]node,ArrayList<Integer>[] edge ) {
        node[n] = true;
        for(int i=0;i<edge[n].size();i++) {
            //System.out.println("방문:" + n);
            Integer endNode = edge[n].get(i);
            //System.out.println("endNode:" + endNode);
            if (node[endNode] == false) {
                dfs(endNode, node, edge);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeNum = scanner.nextInt();
        int edgeNum = scanner.nextInt();

        ArrayList<Integer>[] edge = new ArrayList[nodeNum+1];
        boolean[] node = new boolean[nodeNum+1];
        int count =0;

        //초기화
        for(int i=1;i<=nodeNum;i++) {
            edge[i]=new ArrayList<Integer>();
            node[i]=false;
        }
        //System.out.println(Arrays.toString(node));

        //입력값 초기화
        for(int i=0;i<edgeNum;i++){
            int start = scanner.nextInt();
            int end = scanner.nextInt();

            //방향이 없는 그래프이기 때문에
            edge[start].add(end);
            edge[end].add(start);
        }
        //System.out.println(Arrays.toString(edge));
        for(int i=1;i<=nodeNum;i++){
            if(node[i]==false) {
                dfs(i, node, edge);
                //System.out.println(i);
                count++;
            }
        }
        System.out.println(count);
    }
}
