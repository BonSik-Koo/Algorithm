package baekjoon.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합의표현_1717 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());

            if(op == 0){
                union(a, b);
            }else {
                boolean check = check(a, b);
                if(check){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }

    public static boolean check(int node1, int node2){
        if(node1 == node2){
            return true;
        }

        int parent1 = find(node1);
        int parent2 = find(node2);
        return parent1 == parent2;
    }

    public static void union(int node1, int node2){
        int parent1 = find(node1);
        int parent2 = find(node2);

        if(parent1 < parent2){
            parent[parent2] = parent1;
        }else{
            parent[parent1] = parent2;
        }
    }

    public static int find(int node){
        if(parent[node] == node){
            return node;
        }
        return find(parent[node]);
    }

}
