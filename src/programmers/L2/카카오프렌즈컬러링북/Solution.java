package programmers.L2.카카오프렌즈컬러링북;
import java.util.*;

class Solution {
    int M, N;
    boolean[][] visit;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int[] solution(int m, int n, int[][] picture) {
        int countOfArea = 0;
        int maxSizeOfOneArea = 0;
        M = m;
        N = n;
        visit = new boolean[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(visit[i][j]) continue;

                if(picture[i][j] == 0) {
                    continue;
                }

                int count = bfs(i, j, picture);
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
                countOfArea++;
            }
        }

        int[] answer = new int[]{countOfArea, maxSizeOfOneArea};
        return answer;
    }

    int bfs(int row, int col, int[][] picture) {
        int color = picture[row][col];
        int count = 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        visit[row][col] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx<0 || nx>=M || ny<0 || ny>=N || visit[nx][ny] || picture[nx][ny] != color) continue;

                q.add(new int[]{nx, ny});
                visit[nx][ny] = true;
                count++;
            }
        }

        return count;
    }
}
