package baekjoon.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.*;

public class 중량제한_1939 {
    static class Product{
        int pos, weight;
        Product(int pos, int weight){
            this.pos = pos;
            this.weight = weight;
        }
    }
    //인접 리스트는 메모리 초과
    static List<List<Product>> road;
    static Boolean[] visit;
    static int start, end, N;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        road = new ArrayList<>();
        for(int i=0;i<=N;i++){
            road.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st  = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            road.get(A).add(new Product(B, C));
            road.get(B).add(new Product(A, C));
            min = Math.min(min, C);
            max = Math.max(max, C);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int result = searchWeight(min, max);
        System.out.println(result);
    }
    private static int searchWeight(int min, int max){
        int result = 0;
        while (min <= max){
            int mid = min + (max-min)/2;
            visit = new Boolean[N+1];
            Arrays.fill(visit, false);

            //해당 weight 를 운반할 경로가 있는 경우
            if(bfs(mid)){
                result = mid;
                min = mid + 1;
            }else{
                max = mid -1;
            }
        }
        return result;
    }
    private static Boolean bfs(int weight){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visit[start] = true;

        while(!queue.isEmpty()){
            Integer current = queue.poll();

            if(current == end){
                //해당 weight 를 운반할 경로가 있음.
                return true;
            }
            for(int i=0;i<road.get(current).size(); i++){
                Product next = road.get(current).get(i);
                if(visit[next.pos] || next.weight < weight){
                    continue;
                }

                queue.add(next.pos);
                visit[next.pos] = true;
            }
        }
        return false;
    }
}
