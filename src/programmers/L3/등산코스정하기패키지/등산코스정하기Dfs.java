package programmers.L3.등산코스정하기패키지;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 초기 DFS 접근 - 무조건 시간초과...이풀이는 안됌..
 */
public class 등산코스정하기Dfs {

    public static int num;
    public static int[][] rPaths;
    public int[] checkN;
    public static Boolean[] check;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        answer[0] = -1; answer[1] = Integer.MAX_VALUE;

        Arrays.sort(paths, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int m1 = Math.min(o1[0], o1[1]);
                int m2 = Math.min(o2[0], o2[1]);
                return m1-m2;
            }
        });
        num = paths.length;
        rPaths = paths;
        checkN = new int[n+1];
        Arrays.sort(gates); Arrays.sort(summits);
        Arrays.stream(gates).forEach(e -> checkN[e]=1); //출입구 노드의 값은 1
        Arrays.stream(summits).forEach(e -> checkN[e]=2); //산봉우리 노드의 값은 2

        check = new Boolean[num];
        Arrays.fill(check, false);

        for(int i=0;i<gates.length;i++) {
            dfs(0,gates[i], answer, 0);
        }

        return answer;
    }
    //root 해당 노드가 처음시작한 출발지라는 의미를 담기위해 사용
    public void dfs(int intensity, int node, int[]answer, int root) {

        if(checkN[node]==2) { //산봉우리에 도착한경우
            if(answer[1] > intensity) { //새로운 최소 경로를 찾은경우
                answer[0] = node; answer[1] = intensity;
            }else if(answer[1]==intensity) { //값이 같은 경우
                if(answer[0]>node) {  //새로운 최소 경로를 찾은경우
                    answer[0] = node; answer[1] =intensity;
                }
            }
            return;
        }
        if(checkN[node]==1 && root!=0) //봉우리를 가지 않고 출입구에 도착한 경우
            return;

        int startN=0;
        int nIntensity=0;
        for(int i=0;i<num;i++) {
            int n1 = rPaths[i][0]; int n2=rPaths[i][1]; int dis = rPaths[i][2];

            if( (n1==node || n2==node)&& check[i]==false ) {
                startN = (n1==node?n2:n1);

                if(Math.max(intensity,dis) > answer[1])  //기존에 찾은 경로의 intensity 보다 이미 큰 값을 가진경우
                    continue;

                check[i] = true;
                nIntensity = Math.max(intensity, dis);
                dfs(nIntensity, startN, answer, root+1);
                check[i] = false;
            }
        }

    }

}
