package programmers.kakao.코딩테스트공부;

import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;
        for (int i = 0; i < problems.length; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }

        //초기상태에 문제를 다풀수있는 경우
        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }

        //여기가 젤 문제 하..
        if (alp > maxAlp) {
            alp = maxAlp;
        }
        if (cop > maxCop) {
            cop = maxCop;
        }

        int[][] map = new int[maxAlp + 2][maxCop + 2]; //소요된 최소 시간 DP 배열
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        map[alp][cop] = 0;
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 코딩력 상승
                map[i][j + 1] = Math.min(map[i][j + 1], map[i][j] + 1);

                // 알고력 상승
                map[i + 1][j] = Math.min(map[i + 1][j], map[i][j] + 1);

                //문제 풀기
                for (int[] problem : problems) {
                    //문제를 풀수 없는 경우
                    if (i < problem[0] || j < problem[1]) {
                        continue;
                    }

                    if (i + problem[2] > maxAlp && j + problem[3] > maxCop) {
                        map[maxAlp][maxCop] = Math.min(map[maxAlp][maxCop], map[i][j] + problem[4]);
                    } else if (i + problem[2] > maxAlp) {
                        map[maxAlp][j + problem[3]] = Math.min(map[maxAlp][j + problem[3]], map[i][j] + problem[4]);
                    } else if (j + problem[3] > maxCop) {
                        map[i + problem[2]][maxCop] = Math.min(map[i + problem[2]][maxCop], map[i][j] + problem[4]);
                    } else {
                        map[i + problem[2]][j + problem[3]] = Math.min(map[i + problem[2]][j + problem[3]],
                                map[i][j] + problem[4]);
                    }
                }
            }
        }

        return map[maxAlp][maxCop];
    }
}