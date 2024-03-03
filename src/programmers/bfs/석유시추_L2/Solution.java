package programmers.bfs.석유시추_L2;

import java.util.*;

class Solution {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    private boolean[][] visit;
    private int oilId;
    private Map<Integer, Integer> oilCnt;
    private int[][] oilMap;

    public int solution(int[][] land) {
        visit = new boolean[land.length][land[0].length];
        oilId = 1;
        oilCnt = new HashMap<>();
        oilMap = new int[land.length][land[0].length];

        int answer = 0;
        for(int y=0; y<land[0].length; y++){
            Set<Integer> oilKinds = new HashSet<>();
            for(int x=0; x<land.length; x++) {
                if(land[x][y]==1 && !visit[x][y]) {
                    int cnt = bfs(land, x, y);
                    oilCnt.put(oilId, cnt);
                    oilKinds.add(oilId);
                    oilId++;
                    continue;
                }

                if(land[x][y]==1 && visit[x][y]) {
                    oilKinds.add(oilMap[x][y]);
                }
            }

            int sum = 0;
            for(Integer oilId : oilKinds) {
                sum += oilCnt.get(oilId);
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    private int bfs(int[][] land, int x, int y) {
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visit[x][y] = true;
        oilMap[x][y] = oilId;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || nx>=land.length || ny<0 || ny>=land[0].length || land[nx][ny]==0 || visit[nx][ny]) {
                    continue;
                }

                cnt++;
                oilMap[nx][ny] = oilId;
                visit[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }

        return cnt;
    }

}