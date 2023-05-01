import java.util.*;

class Solution {
    
    public class Dij {
        List<List<int[]>> list; //int[0]:도착 노드, int[1]:거리
        int[] distance; //각 노드까지의 최단 거리
        
        public Dij(int N){
            distance = new int[N+1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            
            list = new ArrayList<>();
            for(int i=0;i<=N;i++)//0~N
                list.add(new ArrayList<>());
        }
        
        public void init(int[][]road){
            for(int i=0;i<road.length;i++){
                int n1 = road[i][0];
                int n2 = road[i][1];
                int dis = road[i][2];
                //양방향
                list.get(n1).add(new int[]{n2,dis});
                list.get(n2).add(new int[]{n1,dis});
            }
        }
        
        public void dij(){ //최단 거리 계산
            PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1]-o2[1]; //오름차순 정렬
                } 
            });
            distance[1] = 0; //출발지
            queue.add(new int[]{1,0});
            
            while(!queue.isEmpty()){
                int[] v = queue.poll();
                //조기 멈춤 조건
                if(v[1] > distance[v[0]])
                    continue;
                
                for(int i=0;i<list.get(v[0]).size();i++){
                    int dn = list.get(v[0]).get(i)[0];
                    int dis = list.get(v[0]).get(i)[1];
                    if(v[1]+dis < distance[dn]){ //새로운 최단거리인 경우
                        distance[dn] = v[1]+dis;
                        queue.add(new int[]{dn, v[1]+dis});
                    }
                }
            }
            
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        Dij dij = new Dij(N);
        dij.init(road);
        dij.dij();
        
        for(int d : dij.distance){
            if(d<=K)
                answer++;
        }
        return answer;
    }
}
