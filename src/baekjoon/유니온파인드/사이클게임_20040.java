package baekjoon.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사이클게임_20040 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
        }

        int answer = 0;
        boolean result = false;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            if(result){
                continue;
            }
            result = union(node1, node2);
            if(result){
                answer = i+1;
            }
        }
        System.out.println(answer);
    }

    public static boolean union(int node1, int node2){
        int parent1 = find(node1);
        int parent2 = find(node2);

        if(parent1 == parent2){
            return true;
        }

        // 더 작은 부모 값 기준으로 저장
        if(parent1 < parent2){
            parent[parent2] = parent1;
        }else{
            parent[parent1] = parent2;
        }
        return false;
    }

    public static int find(int node){
        if(parent[node] == node){
            return node;
        }
        return find(parent[node]);
    }

}
