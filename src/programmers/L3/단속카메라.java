package programmers.L3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * - greedy 풀이
 * - 안보고 푼 문제
 */
public class 단속카메라 {

    public int solution(int[][] routes) {
        int answer = 0;

        //진입순으로 오름차순 정렬
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0])
                    return o1[1]-o2[1];
                return o1[0]-o2[0];
            }
        });

        //초기 세팅
        int start = routes[0][0];
        int end = routes[0][1];
        answer++;
        for(int i=1;i<routes.length;i++) {

            int nStart = routes[i][0];
            int nEnd = routes[i][1];

            if(start <= nStart && nStart <=end) { //동시에 처리할수 있는 카메라 존재
                start = Math.max(start, nStart);
                end = Math.min(end, nEnd);
            }else {
                start = nStart;
                end = nEnd;
                answer++;
            }
        }
        return answer;
    }

}
