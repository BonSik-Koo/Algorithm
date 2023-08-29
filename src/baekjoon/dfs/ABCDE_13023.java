package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ABCDE_13023 {
    static List<List<Integer>> list;
    static Boolean[] visit;
    static Boolean isExist = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        visit = new Boolean[N+1];
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
            visit[i] = false;
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //양방향
            list.get(a).add(b);
            list.get(b).add(a);
        }

        //dfs 로직
        for(int i=1;i<=N;i++){
            visit[i] = true;
            dfs(i, 1);
            visit[i] = false;

            if(isExist){
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    private static void dfs(int node, int count){
        if(count==5){
            isExist = true;
            return;
        }

        //조기 멈춤 조건
        if(isExist){
            return;
        }

        for(int i=0;i<list.get(node).size();i++){
            int nextNode = list.get(node).get(i);

            if(visit[nextNode] || list.get(nextNode).isEmpty()){
                continue;
            }
            visit[nextNode] = true;
            dfs(nextNode, count+1);
            visit[nextNode] = false;
        }
    }
}
