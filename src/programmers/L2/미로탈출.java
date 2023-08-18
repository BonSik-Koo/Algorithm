package programmers.L2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * - BFS 풀이
 * - 혼자 푼 문제
 */
public class 미로탈출 {

    static class State{
        int x; int y; int count;
        public State(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    static int []dx = {1,-1,0,0};
    static int []dy = {0,0,1,-1};
    static int nx;
    static int ny;
    static boolean[][] visit;
    static char[][] map;

    public static int solution(String[] maps) {

        int startX =0, startY=0;
        int leverX =0, leverY=0;
        nx = maps.length; ny = maps[0].length();
        visit = new boolean[nx][ny];
        map = new char[nx][ny];
        for(int i=0;i<nx;i++){
            for(int j=0;j<ny;j++) {
                map[i][j] = maps[i].charAt(j);
                visit[i][j] = false;
                if(map[i][j]=='S'){
                    startX = i; startY = j;
                }else if(map[i][j]=='L'){
                    leverX = i; leverY = j;
                }
            }
        }

        int toLever = bfs(startX, startY, 'L'); //시작->레버까지 최소 시간
        for(int i=0;i<nx;i++) //방문 배열 초기화
            Arrays.fill(visit[i], false);
        int toExit = bfs(leverX, leverY, 'E'); //레퍼->끝까지 최소 시간

        return (toLever==-1||toExit==-1)?-1:toLever+toExit;
    }

    public static int bfs(int startX, int startY, char endChar){

        int result = -1;
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(startX, startY,0));
        visit[startX][startY] = true; //출발노드 방문 처리
        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            if(map[currentState.x][currentState.y]==endChar){
                result = currentState.count;
                break;
            }

            for(int i=0;i<4;i++){
                int newX = currentState.x+dx[i];
                int newY = currentState.y+dy[i];
                int newCount = currentState.count + 1;

                if(0>newX || newX>=nx || 0>newY || newY>=ny)
                    continue;
                if(map[newX][newY]=='X' || visit[newX][newY]==true)
                    continue;

                queue.add(new State(newX, newY, newCount));
                visit[newX][newY] = true;
            }
        }
        return result;
    }

}
