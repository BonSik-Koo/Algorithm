import java.util.Arrays;
import java.util.Comparator;

/**
 * - greedy 사용 ,다시 풀 문제
 * - 크루스칼 알고리즘
 */

public class 섬연결하기 {

    public int findParent(int[]parent, int node) {
        if(parent[node] == node)
            return node;
        return findParent(parent, parent[node]);
    }

    public void union(int[] parent, int node1, int node2) { //이 부분이 생각하기 hard!!!!!
        int parent1 = findParent(parent, node1);
        int parent2 = findParent(parent, node2);
        if(parent1 > parent2) {
            parent[parent1] = parent2;
        }else
            parent[parent2] = parent1;
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;

        int[]parent = new int[n];
        for(int i=0;i<n;parent[i] = i++);

        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2] == o2[2])
                    return o1[0] - o1[0];
                return o1[2]-o2[2];
            }
        });

        for(int i=0;i<costs.length;i++) {

            if( findParent(parent, costs[i][0]) != findParent(parent, costs[i][1])) { //간선이 추가 되는 경우
                answer += costs[i][2];
                union(parent, costs[i][0], costs[i][1]);
            }
        }

        return answer;
    }
}
