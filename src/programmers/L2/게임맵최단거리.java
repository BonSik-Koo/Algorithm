package programmers.L2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * bfs 풀이
 */
public class 게임맵최단거리 {

    public static int rowSize, colSize;
    public static boolean[][] visit;
    public static int[]x = {1,-1,0,0};
    public static int[]y = {0,0,1,-1};
    public static class pos {
        int row, col, count;
        public pos(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    public static int bfs(int[][] maps) {
        int result =0;
        Queue<pos> queue = new LinkedList<>();
        queue.add(new pos(0,0,1));
        visit[0][0] = true;
        while (!queue.isEmpty()) {
            pos poll = queue.poll();

            if(poll.row == rowSize -1 && poll.col== colSize -1){
                result = poll.count;
                break;
            }
            for(int i=0;i<4;i++) {
                int newR = poll.row + x[i];
                int newC = poll.col + y[i];

                if((0>newR || rowSize <=newR) || (0>newC || colSize <=newC))
                    continue;

                if(maps[newR][newC] == 1 && visit[newR][newC] == false) {
                    queue.add(new pos(newR, newC, poll.count+1));
                    visit[newR][newC] = true;
                }
            }

        }
        if(result == 0)
            result=-1;

        return result;
    }

    public static int solution(int[][] maps) {
        int answer = 0;
        rowSize = maps.length;
        colSize = maps[0].length;
        visit = new boolean[rowSize][colSize];
        for(int i = 0; i< rowSize; i++)
            Arrays.fill(visit[i],false);

        answer = bfs(maps);

        return answer;
    }

    public static void main(String[] args) {

        int[][]maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        int solution = solution(maps);
        System.out.println(solution);
    }
}
